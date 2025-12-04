package gifts.childs;

import gifts.Behavior;
import gifts.Wishlist;

public record Child(String name, Behavior behavior, Wishlist wishlist) {

    public boolean hasSameName(String childName) {
        return name.equals(childName);
    }
}
