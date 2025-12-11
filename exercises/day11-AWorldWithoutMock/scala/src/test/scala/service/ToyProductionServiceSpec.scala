package service

import domain.{Toy, ToyRepository}
import domain.ToyState._
import org.mockito.ArgumentCaptor
import org.mockito.Mockito._
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class ToyProductionServiceSpec extends AnyFunSuite with Matchers {

  private val TOY_NAME = "Train"

  test("assignToyToElf should save toy in production and notify") {
    val repository = mock(classOf[ToyRepository])
    val notificationService = mock(classOf[NotificationService])
    val service = new ToyProductionService(repository, notificationService)
    val toy = Toy(TOY_NAME, UNASSIGNED)
    when(repository.findByName(TOY_NAME)).thenReturn(toy)

    service.assignToyToElf(TOY_NAME)

    val captor: ArgumentCaptor[Toy] = ArgumentCaptor.forClass(classOf[Toy])
    verify(repository).save(captor.capture())
    val savedToy = captor.getValue
    savedToy.state shouldBe IN_PRODUCTION

    verify(notificationService).notifyToyAssigned(savedToy)
  }

  test("assignToyToElf should not save or notify when toy not found") {
    val repository = mock(classOf[ToyRepository])
    val notificationService = mock(classOf[NotificationService])
    val service = new ToyProductionService(repository, notificationService)
    when(repository.findByName(TOY_NAME)).thenReturn(null)

    service.assignToyToElf(TOY_NAME)

    verify(repository).findByName(TOY_NAME)
    verify(repository, never()).save(org.mockito.ArgumentMatchers.any())
    verifyNoInteractions(notificationService)
  }

  test("assignToyToElf should not save or notify when toy already in production") {
    val repository = mock(classOf[ToyRepository])
    val notificationService = mock(classOf[NotificationService])
    val service = new ToyProductionService(repository, notificationService)
    val toy = Toy(TOY_NAME, IN_PRODUCTION)
    when(repository.findByName(TOY_NAME)).thenReturn(toy)

    service.assignToyToElf(TOY_NAME)

    verify(repository).findByName(TOY_NAME)
    verify(repository, never()).save(org.mockito.ArgumentMatchers.any())
    verifyNoInteractions(notificationService)
  }

  test("assignToyToElf should save before notifying") {
    val repository = mock(classOf[ToyRepository])
    val notificationService = mock(classOf[NotificationService])
    val service = new ToyProductionService(repository, notificationService)
    val toy = Toy(TOY_NAME, UNASSIGNED)
    when(repository.findByName(TOY_NAME)).thenReturn(toy)

    service.assignToyToElf(TOY_NAME)

    val inOrder = org.mockito.Mockito.inOrder(repository, notificationService)
    inOrder.verify(repository).findByName(TOY_NAME)
    inOrder.verify(repository).save(org.mockito.ArgumentMatchers.any(classOf[Toy]))
    inOrder.verify(notificationService).notifyToyAssigned(org.mockito.ArgumentMatchers.any(classOf[Toy]))
    inOrder.verifyNoMoreInteractions()
  }
}
