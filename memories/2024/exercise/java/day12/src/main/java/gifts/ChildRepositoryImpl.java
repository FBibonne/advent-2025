package gifts;

import java.util.List;
import java.util.Optional;

public class ChildRepositoryImpl implements ChildRepository {
    final List<Child> childrenRepository;

    public ChildRepositoryImpl() {
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