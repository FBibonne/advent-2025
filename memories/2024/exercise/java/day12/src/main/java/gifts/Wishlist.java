package gifts;

import java.util.Optional;

public class Wishlist implements ChoiceProvider, ChoiceReceiver {

    private static final int FIRST = 0;
    private static final int SECOND = 1;
    private static final int THIRD = 2;

    private final Toy[] toys = new Toy[3];

    @Override
    public void setFirstChoice(Toy choice) {
        setChoice(FIRST, choice);
    }

    @Override
    public void setSecondChoice(Toy choice) {
        setChoice(SECOND, choice);
    }

    @Override
    public void setThirdChoice(Toy choice) {
        setChoice(THIRD, choice);
    }

    @Override
    public Optional<Toy> getFirstChoice() {
        return Optional.ofNullable(toys[FIRST]);
    }

    @Override
    public Optional<Toy> getSecondChoice() {
        return Optional.ofNullable(toys[SECOND]);
    }

    @Override
    public Optional<Toy> getThirdChoice() {
        return Optional.ofNullable(toys[THIRD]);
    }

    private void setChoice(int position, Toy choice) {
        toys[position] = choice;
    }
}