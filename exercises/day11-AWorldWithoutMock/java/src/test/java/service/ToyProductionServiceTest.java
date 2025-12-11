package service;

import domain.Toy;
import domain.ToyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.InOrder;
import org.mockito.junit.jupiter.MockitoExtension;

import static domain.Toy.State.IN_PRODUCTION;
import static domain.Toy.State.UNASSIGNED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ToyProductionServiceTest {

    private static final String TOY_NAME = "Train";

    @Mock
    ToyRepository repository;

    @Mock
    NotificationService notificationService;

    @InjectMocks
    ToyProductionService service;

    @Test
    void assignToyToElfShouldSaveToyInProductionAndNotify() {
        // given
        Toy toy = new Toy(TOY_NAME, UNASSIGNED);
        when(repository.findByName(TOY_NAME)).thenReturn(toy);

        // when
        service.assignToyToElf(TOY_NAME);

        // then
        ArgumentCaptor<Toy> toyCaptor = ArgumentCaptor.forClass(Toy.class);
        verify(repository).save(toyCaptor.capture());
        Toy savedToy = toyCaptor.getValue();
        assertThat(savedToy.getState()).isEqualTo(IN_PRODUCTION);

        verify(notificationService).notifyToyAssigned(savedToy);
    }

    @Test
    void assignToyToElfShouldNotSaveOrNotifyWhenToyNotFound() {
        // given
        when(repository.findByName(TOY_NAME)).thenReturn(null);

        // when
        service.assignToyToElf(TOY_NAME);

        // then
        verify(repository).findByName(TOY_NAME);
        verify(repository, never()).save(any());
        verifyNoInteractions(notificationService);
    }

    @Test
    void assignToyToElfShouldNotSaveOrNotifyWhenToyAlreadyInProduction() {
        // given
        Toy toy = new Toy(TOY_NAME, IN_PRODUCTION);
        when(repository.findByName(TOY_NAME)).thenReturn(toy);

        // when
        service.assignToyToElf(TOY_NAME);

        // then
        verify(repository).findByName(TOY_NAME);
        verify(repository, never()).save(any());
        verifyNoInteractions(notificationService);
    }

    @Test
    void assignToyToElfShouldSaveBeforeNotifying() {
        // given
        Toy toy = new Toy(TOY_NAME, UNASSIGNED);
        when(repository.findByName(TOY_NAME)).thenReturn(toy);

        // when
        service.assignToyToElf(TOY_NAME);

        // then
        InOrder inOrder = inOrder(repository, notificationService);
        inOrder.verify(repository).findByName(TOY_NAME);
        inOrder.verify(repository).save(any(Toy.class));
        inOrder.verify(notificationService).notifyToyAssigned(any(Toy.class));
        inOrder.verifyNoMoreInteractions();
    }
}
