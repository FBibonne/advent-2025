package doubles;

import domain.Toy;
import service.NotificationService;

public class NotificationServiceSpy implements NotificationService {

    private Toy notifyToyAssigned=null;
    private int countNotifyCalls=0;
    private final Calls calls;

    public NotificationServiceSpy(Calls calls) {
        this.calls = calls;
    }

    @Override
    public void notifyToyAssigned(Toy toy) {
        if (notifyToyAssigned!=null){
            throw new RuntimeException("Toy already assigned : "+notifyToyAssigned);
        }
        countNotifyCalls++;
        calls.add(NotificationService.class, "notifyToyAssigned", toy);
        this.notifyToyAssigned = toy;
    }

    public Toy notifyToyAssigned() {
        return notifyToyAssigned;
    }

    public int countNotifyCalls(){
        return countNotifyCalls;
    }
}
