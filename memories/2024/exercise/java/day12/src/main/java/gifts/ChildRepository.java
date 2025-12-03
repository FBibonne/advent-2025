package gifts;

import java.util.Optional;

public interface ChildRepository {
    Optional<Child> findByName(String childName);

    void addChild(Child child);
}
