package gifts.childs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

record ChildRepositoryImpl(List<Child> childrenRepository) implements ChildRepository {

    ChildRepositoryImpl(){
        this(new ArrayList<>());
    }

    @Override
    public Optional<Child> findByName(String childName) {
        Optional<Child> found = Optional.empty();
        for (int i = 0; i < childrenRepository.size(); i++) {
            Child currentChild = childrenRepository.get(i);
            if (currentChild.name().equals(childName)) {
                found = Optional.of(currentChild);
            }
        }
        return found;
    }

    @Override
    public void addChild(Child child) {
        childrenRepository.add(child);
    }
}