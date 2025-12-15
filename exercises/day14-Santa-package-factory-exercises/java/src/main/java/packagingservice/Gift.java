package packagingservice;

import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record Gift(String name, GiftSize size, boolean fragile, int recommendedMinAge) {

}
