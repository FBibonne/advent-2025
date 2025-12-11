package doubles;

import domain.Toy;
import service.NotificationService;

public class NotificationServiceSpy implements NotificationService {

    private Toy notifyToyAssigned=null;
    private int countNotifyCalls=0;

    @Override
    public void notifyToyAssigned(Toy toy) {
        if (notifyToyAssigned!=null){
            throw new RuntimeException("Toy already assigned : "+notifyToyAssigned);
        }
        countNotifyCalls++;
        this.notifyToyAssigned = toy;
    }

    public Toy notifyToyAssigned() {
        return notifyToyAssigned;
    }

    public int countNotifyCalls(){
        return countNotifyCalls;
    }
}
