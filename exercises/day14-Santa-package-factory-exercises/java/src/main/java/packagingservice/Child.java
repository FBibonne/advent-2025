package packagingservice;

import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record Child(String name, int age, ChildGender gender, boolean hasBeenNice, Gift assignedGift) {

}
