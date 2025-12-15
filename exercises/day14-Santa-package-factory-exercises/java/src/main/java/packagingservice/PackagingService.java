package packagingservice;

public class PackagingService {

    public PackageType determinePackageType(Gift gift, Child child) {
        if (gift.size() == GiftSize.EXTRA_LARGE) {
            return PackageType.SPECIAL_CONTAINER;
        }

        if (gift.fragile()) {
            return gift.size() == GiftSize.SMALL
                    ? PackageType.BOX_SMALL
                    : PackageType.BOX_MEDIUM;
        }

        if (child.age() < 5) {
            return PackageType.GIFT_BAG;
        }

        switch (gift.size()) {
            case SMALL:
                return PackageType.BOX_SMALL;
            case MEDIUM:
                return PackageType.BOX_MEDIUM;
            case LARGE:
                return PackageType.BOX_LARGE;
            default:
                return PackageType.WRAPPER_PAPER;
        }
    }

    public boolean canPackageGift(Gift gift, Child child) {
        if (!child.hasBeenNice()) {
            return false;
        }

        if (gift.recommendedMinAge() > child.age()) {
            return false;
        }

        return true;
    }
}
