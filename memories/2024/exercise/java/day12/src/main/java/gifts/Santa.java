package gifts;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Santa {

    private final ChildRepository childRepository;

    public Santa(ChildRepository childRepository) {
        this.childRepository = childRepository;
    }

    public Toy chooseToyForChild(String childName){
        Optional<Child> found = childRepository.findByName(childName);

        return found.flatMap(child -> choicer(child).apply(child.choiceProvider()))
                .orElseThrow(NoSuchElementException::new);
        //TODO should return null when Behavior is not naughty, neither nice neither very nice
    }

    private Function<ChoiceProvider, Optional<Toy>> choicer(Child child) {
        return switch (child.behavior()){
            case Behavior b when b.isNaughty() -> ChoiceProvider::getThirdChoice;
            case Behavior b when b.isNice() -> ChoiceProvider::getSecondChoice;
            case Behavior b when b.isVeryNice() -> ChoiceProvider::getFirstChoice;
            default -> unused -> Optional.empty();
        };
    }

    public void addChild(Child child) {
        childRepository.addChild(child);
    }
}
