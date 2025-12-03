package communication;

public class SantaCommunicator {
    private final int numberOfDaysToRest;
    private final Logger logger;

    public SantaCommunicator(int numberOfDaysToRest, Logger logger) {
        this.numberOfDaysToRest = numberOfDaysToRest;
        this.logger = logger;
    }

    public String composeMessage(Reindeer reindeer, int numberOfDaysBeforeChristmas) {
        var daysBeforeReturn = daysBeforeReturn(reindeer.numbersOfDaysForComingBack(), numberOfDaysBeforeChristmas);

        return "Dear " + reindeer.reindeerName() + ", please return from " + reindeer.currentLocation() +
                " in " + daysBeforeReturn + " day(s) to be ready and rest before Christmas.";
    }

    public boolean isOverdue(Reindeer reindeer, int numberOfDaysBeforeChristmas) {
        boolean overdue = isOverdue(reindeer.numbersOfDaysForComingBack(), numberOfDaysBeforeChristmas);
        if (overdue) {
            logger.log("Overdue for "+reindeer.reindeerName()+" located "+reindeer.currentLocation()+".");
        }
        return overdue;
    }

    private boolean isOverdue(int numbersOfDaysForComingBack, int numberOfDaysBeforeChristmas) {
        return daysBeforeReturn(numbersOfDaysForComingBack, numberOfDaysBeforeChristmas) <= 0;
    }

    private int daysBeforeReturn(int numbersOfDaysForComingBack, int numberOfDaysBeforeChristmas) {
        return numberOfDaysBeforeChristmas - numbersOfDaysForComingBack - numberOfDaysToRest;
    }
}