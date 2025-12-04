package gifts;

import java.util.Optional;

public class Wishlist {

    private static final int FIRST = 0;
    private static final int SECOND = 1;
    private static final int THIRD = 2;

    private final Toy[] toys = new Toy[3];

    
    public void setFirstChoice(Toy choice) {
        setChoice(FIRST, choice);
    }

    
    public void setSecondChoice(Toy choice) {
        setChoice(SECOND, choice);
    }

    
    public void setThirdChoice(Toy choice) {
        setChoice(THIRD, choice);
    }

    
    public Optional<Toy> getFirstChoice() {
        return Optional.ofNullable(toys[FIRST]);
    }

    
    public Optional<Toy> getSecondChoice() {
        return Optional.ofNullable(toys[SECOND]);
    }

    
    public Optional<Toy> getThirdChoice() {
        return Optional.ofNullable(toys[THIRD]);
    }

    private void setChoice(int position, Toy choice) {
        toys[position] = choice;
    }
}