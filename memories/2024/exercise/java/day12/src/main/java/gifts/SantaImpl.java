package gifts;

import gifts.childs.Child;
import gifts.childs.ChildRepository;
import gifts.choices.ChoiceProvider;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;

record SantaImpl(ChildRepository childRepository) implements Santa {

    @Override
    public Toy chooseToyForChild(String childName) {
        Optional<Child> found = childRepository.findByName(childName);

        return found.flatMap(child -> choicer(child).apply(child.choiceProvider()))
                .orElseThrow(NoSuchElementException::new);
    }

    private Function<ChoiceProvider, Optional<Toy>> choicer(Child child) {
        return switch (child.behavior()) {
            case Behavior b when b.isNaughty() -> ChoiceProvider::getThirdChoice;
            case Behavior b when b.isNice() -> ChoiceProvider::getSecondChoice;
            case Behavior b when b.isVeryNice() -> ChoiceProvider::getFirstChoice;
            default -> throw new IllegalStateException("unexpected behavior: " + child.behavior());
        };
    }

    @Override
    public void addChild(Child child) {
        childRepository.addChild(child);
    }
}
