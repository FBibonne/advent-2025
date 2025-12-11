package service

import domain.Toy
import domain.ToyRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.*

@ExtendWith(MockitoExtension::class)
class ToyProductionServiceTest {

    companion object {
        private const val TOY_NAME = "Train"
    }

    @Mock
    lateinit var repository: ToyRepository

    @Mock
    lateinit var notificationService: NotificationService

    @InjectMocks
    lateinit var service: ToyProductionService

    @Test
    fun `assignToyToElf should save toy in production and notify`() {
        val toy = Toy(TOY_NAME, Toy.State.UNASSIGNED)
        whenever(repository.findByName(TOY_NAME)).thenReturn(toy)

        service.assignToyToElf(TOY_NAME)

        val toyCaptor = argumentCaptor<Toy>()
        verify(repository).save(toyCaptor.capture())
        val savedToy = toyCaptor.firstValue
        assertThat(savedToy.state).isEqualTo(Toy.State.IN_PRODUCTION)

        verify(notificationService).notifyToyAssigned(savedToy)
    }

    @Test
    fun `assignToyToElf should not save or notify when toy not found`() {
        whenever(repository.findByName(TOY_NAME)).thenReturn(null)

        service.assignToyToElf(TOY_NAME)

        verify(repository).findByName(TOY_NAME)
        verify(repository, never()).save(any())
        verifyNoInteractions(notificationService)
    }

    @Test
    fun `assignToyToElf should not save or notify when toy already in production`() {
        val toy = Toy(TOY_NAME, Toy.State.IN_PRODUCTION)
        whenever(repository.findByName(TOY_NAME)).thenReturn(toy)

        service.assignToyToElf(TOY_NAME)

        verify(repository).findByName(TOY_NAME)
        verify(repository, never()).save(any())
        verifyNoInteractions(notificationService)
    }

    @Test
    fun `assignToyToElf should save before notifying`() {
        val toy = Toy(TOY_NAME, Toy.State.UNASSIGNED)
        whenever(repository.findByName(TOY_NAME)).thenReturn(toy)

        service.assignToyToElf(TOY_NAME)

        inOrder(repository, notificationService) {
            verify(repository).findByName(TOY_NAME)
            verify(repository).save(any())
            verify(notificationService).notifyToyAssigned(any())
            verifyNoMoreInteractions()
        }
    }
}