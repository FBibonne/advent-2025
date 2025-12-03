package gifts;

import java.util.Optional;

public interface ChoiceProvider {
    Optional<Toy> getFirstChoice();

    Optional<Toy> getSecondChoice();

    Optional<Toy> getThirdChoice();
}
