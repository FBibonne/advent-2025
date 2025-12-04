package gifts;

import java.util.Optional;

@FunctionalInterface
public interface ToySelector {

    Optional<Toy> selectToy(Wishlist wishlist);

}
