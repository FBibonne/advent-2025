package gifts.childs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public record ChildrenRepository(List<Child> childrenRepository){

    public ChildrenRepository(){
        this(new ArrayList<>());
    }

    public Optional<Child> findByName(String childName) {
        return childrenRepository.stream()
                .filter(child -> child.hasSameName(childName))
                .findFirst();
    }

    public void addChild(Child child) {
        childrenRepository.add(child);
    }
}