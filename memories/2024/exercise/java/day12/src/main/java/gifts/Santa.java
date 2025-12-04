package gifts;

import gifts.childs.Child;
import gifts.childs.ChildrenRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

record Santa(ChildrenRepository childrenRepository){

    public Toy chooseToyForChild(String childName) {
        Optional<Child> foundChild = childrenRepository.findByName(childName);

        if (foundChild.isEmpty()){
            throw new NoSuchElementException();
        }
        Child child = foundChild.get();
        return toySelectorBasedOnBehavior((child.behavior())).selectToy(child.wishlist()).orElseThrow();
    }

    private ToySelector toySelectorBasedOnBehavior(Behavior behavior) {
        return switch (behavior) {
            case Behavior b when b.isNaughty() -> Wishlist::getThirdChoice;
            case Behavior b when b.isNice() -> Wishlist::getSecondChoice;
            case Behavior b when b.isVeryNice() -> Wishlist::getFirstChoice;
            default -> throw new IllegalStateException("unexpected behavior: " + behavior);
        };
    }

    public void addChild(Child child) {
        childrenRepository.addChild(child);
    }
}
