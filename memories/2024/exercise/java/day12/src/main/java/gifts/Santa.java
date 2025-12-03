package gifts;

import gifts.childs.Child;
import gifts.childs.ChildRepository;

public interface Santa {
    static Santa newInstance(ChildRepository childRepository) {
        return new SantaImpl(childRepository);
    }

    Toy chooseToyForChild(String childName);

    void addChild(Child child);
}
