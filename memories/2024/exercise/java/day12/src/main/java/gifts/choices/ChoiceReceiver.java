package gifts.choices;

import gifts.Toy;

public interface ChoiceReceiver {
    void setFirstChoice(Toy choice);

    void setSecondChoice(Toy choice);

    void setThirdChoice(Toy choice);
}
