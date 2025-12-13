package service;

import domain.Toy;
import domain.ToyRepository;
import doubles.Calls;
import doubles.NotificationServiceSpy;
import doubles.ToyRepositorySpy;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

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
        Calls calls = new Calls();
        Toy toy = new Toy(TOY_NAME, UNASSIGNED);
        ToyRepositorySpy toyRepositorySpy = new ToyRepositorySpy(toy, calls);
        NotificationServiceSpy notificationServiceSpy = new NotificationServiceSpy(calls);
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
        Calls calls = new Calls();
        ToyRepositorySpy toyRepositorySpy = new ToyRepositorySpy(null, calls);
        NotificationServiceSpy notificationServiceSpy = new NotificationServiceSpy(calls);
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
        Calls calls = new Calls();
        Toy toy = new Toy(TOY_NAME, IN_PRODUCTION);
        ToyRepositorySpy toyRepositorySpy = new ToyRepositorySpy(toy, calls);
        NotificationServiceSpy notificationServiceSpy = new NotificationServiceSpy(calls);
        service = new ToyProductionService(toyRepositorySpy, notificationServiceSpy);

        // when
        service.assignToyToElf(TOY_NAME);

        // then
        assertTrue(toyRepositorySpy.findByNameHasBeenCalledOnceWith(TOY_NAME));
        assertThat(toyRepositorySpy.countSaveCalls()).isZero();
        assertThat(notificationServiceSpy.countNotifyCalls()).isZero();
    }

    @Test
    void assignToyToElfShouldSaveBeforeNotifying() {
        // given
        Calls calls = new Calls();
        Toy toy = new Toy(TOY_NAME, UNASSIGNED);
        ToyRepositorySpy toyRepositorySpy = new ToyRepositorySpy(toy, calls);
        NotificationServiceSpy notificationServiceSpy = new NotificationServiceSpy(calls);
        service = new ToyProductionService(toyRepositorySpy, notificationServiceSpy);

        // when
        service.assignToyToElf(TOY_NAME);

        // then
        Iterator<Calls.Call> callsIterator = calls.iterator();
        //inOrder.verify(repository).findByName(TOY_NAME);
        assertThat(callsIterator.next()).isEqualTo(new Calls.Call(ToyRepository.class, "findByName", TOY_NAME));
        //inOrder.verify(repository).save(any(Toy.class));
        assertThat(callsIterator.next()).matches(call -> call.fit(ToyRepository.class, "save"));
        //inOrder.verify(notificationService).notifyToyAssigned(any(Toy.class));
        assertThat(callsIterator.next()).matches(call -> call.fit(NotificationService.class, "notifyToyAssigned"));
        //inOrder.verifyNoMoreInteractions();
        assertThat(callsIterator.hasNext()).isFalse();
    }
}
