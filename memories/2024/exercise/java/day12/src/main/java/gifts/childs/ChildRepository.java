package gifts.childs;

import java.util.Optional;

public interface ChildRepository {

    static ChildRepository newInstance() {
        return new ChildRepositoryImpl();
    }

    Optional<Child> findByName(String childName);

    void addChild(Child child);
}
