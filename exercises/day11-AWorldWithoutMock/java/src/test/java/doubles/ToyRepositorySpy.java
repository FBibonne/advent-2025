package doubles;

import domain.Toy;
import domain.ToyRepository;

import static service.ToyProductionServiceTest.TOY_NAME;

public class ToyRepositorySpy implements ToyRepository {

    private final Toy toyFoundByName;
    private Toy savedToy=null;
    private int findByNameCalledCount = 0;
    private int countSaveCalls=0;

    public ToyRepositorySpy(Toy toyFoundByName) {
        this.toyFoundByName = toyFoundByName;
    }

    @Override
    public Toy findByName(String name) {
        if (TOY_NAME.equals(name)){
            findByNameCalledCount++;
            return toyFoundByName;
        }
        throw new RuntimeException("value not mocked : "+name);
    }

    @Override
    public void save(Toy toy) {
        if (savedToy != null){
            throw new IllegalStateException("a toy has already been saved");
        }
        countSaveCalls++;
        savedToy = toy;
    }

    public Toy savedToy() {
        return savedToy;
    }

    public boolean findByNameHasBeenCalledOnceWith(String toyName) {
        return findByNameCalledCount == 1 && TOY_NAME.equals(toyName);
    }

    public int countSaveCalls() {
        return countSaveCalls;
    }
}
