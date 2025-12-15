package packagingservice;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static packagingservice.GiftSize.SMALL;
import static packagingservice.PackageType.BOX_SMALL;

public class PackagingServiceTests {

    public static final GiftBuilder DEFAULT_GIFT_BUILDER = GiftBuilder.builder()
            .name("DefaultGift")
            .recommendedMinAge(0);
    private static final ChildBuilder DEFAULT_CHILD_BUILDER = ChildBuilder.builder()
            .name("DefaultChild")
            .age(8);
    private final PackagingService service = new PackagingService();


    @Test
    public void shouldUseSmallBoxForSmallNonFragileGift() {

        //given
        Gift giftNonFragileWithSmallSize = DEFAULT_GIFT_BUILDER.fragile(false)
                .size(SMALL)
                .build();
        Child child =  DEFAULT_CHILD_BUILDER.build();

        assertThat(service.determinePackageType(giftNonFragileWithSmallSize, child)).isEqualTo(BOX_SMALL);
    }

    @Test
    public void shouldUseSpecialContainerForExtraLargeGift() {
        Gift extraLargeGift = DEFAULT_GIFT_BUILDER.size(GiftSize.EXTRA_LARGE).build();
        Child child =  DEFAULT_CHILD_BUILDER.build();

        assertThat(service.determinePackageType(extraLargeGift, child)).isEqualTo(PackageType.SPECIAL_CONTAINER);
    }

    @Test
    public void shouldUseGiftBagForYoungChildren() {
        //given
        Gift defaultGift = DEFAULT_GIFT_BUILDER.build();
        Child youngChild = DEFAULT_CHILD_BUILDER.age(3).build();

        assertThat(service.determinePackageType(defaultGift, youngChild)).isEqualTo(PackageType.GIFT_BAG);
    }

    @Test
    public void shouldNotPackageGiftForNaughtyChild() {
        //Given
        Gift defaultGift = DEFAULT_GIFT_BUILDER.build();
        Child naughtyChild = DEFAULT_CHILD_BUILDER.hasBeenNice(false).build();

        assertFalse(service.canPackageGift(defaultGift, naughtyChild));
    }

    @Test
    public void shouldNotPackageGiftForChildTooYoung() {
        Gift defaultGift = DEFAULT_GIFT_BUILDER.build();

        Child youngChild = DEFAULT_CHILD_BUILDER.age(4).build();

        assertFalse(service.canPackageGift(defaultGift, youngChild));
    }
}
