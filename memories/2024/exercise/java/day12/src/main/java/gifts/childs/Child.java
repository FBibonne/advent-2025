package gifts.childs;

import gifts.Behavior;
import gifts.choices.ChoiceProvider;

public record Child(String name, Behavior behavior, ChoiceProvider choiceProvider) {

}
