package service;

import domain.Toy;
import domain.ToyRepository;
import doubles.NotificationServiceSpy;
import doubles.ToyRepositorySpy;
import org.junit.jupiter.api.Test;

import static domain.Toy.State.IN_PRODUCTION;
import static domain.Toy.State.UNASSIGNED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ToyProductionServiceTest {

    public static final String TOY_NAME = "Train";

//    @Mock
    ToyRepository repository;

//    @Mock
    NotificationService notificationService;

//    @InjectMocks
    ToyProductionService service;

    @Test
    void assignToyToElfShouldSaveToyInProductionAndNotify() {
        // given
        Toy toy = new Toy(TOY_NAME, UNASSIGNED);
        ToyRepositorySpy toyRepositorySpy = new ToyRepositorySpy(toy);
        NotificationServiceSpy notificationServiceSpy = new NotificationServiceSpy();
        service = new ToyProductionService(toyRepositorySpy, notificationServiceSpy);

        // when
        service.assignToyToElf(TOY_NAME);

        // then
        Toy savedToy = toyRepositorySpy.savedToy();
        assertThat(savedToy.getState()).isEqualTo(IN_PRODUCTION);
        assertThat(notificationServiceSpy.notifyToyAssigned()).isEqualTo(savedToy);
    }

    @Test
    void assignToyToElfShouldNotSaveOrNotifyWhenToyNotFound() {
        // given
        ToyRepositorySpy toyRepositorySpy = new ToyRepositorySpy(null);
        NotificationServiceSpy notificationServiceSpy = new NotificationServiceSpy();
        service = new ToyProductionService(toyRepositorySpy, notificationServiceSpy);

        // when
        service.assignToyToElf(TOY_NAME);

        // then
        //verify(repository).findByName(TOY_NAME);
        assertTrue(toyRepositorySpy.findByNameHasBeenCalledOnceWith(TOY_NAME));
        //verify(repository, never()).save(any());
        assertThat(toyRepositorySpy.countSaveCalls()).isZero();
        //verifyNoInteractions(notificationService);
        assertThat(notificationServiceSpy.countNotifyCalls()).isZero();
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
