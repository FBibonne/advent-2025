package gifts;

public interface Behavior {
    static Behavior naugthyInstance() {
        return new Behavior() {
            @Override
            public boolean isNaughty() {
                return true;
            }
        };
    }

    static Behavior niceInstance() {
        return new Behavior() {
            @Override
            public boolean isNice() {
                return true;
            }
        };


    }

    static Behavior veryNiceInstance() {
        return new Behavior() {
            @Override
            public boolean isVeryNice() {
                return true;
            }
        };
    }


    default boolean isNaughty(){
        return false;
    }
    default boolean isNice(){
        return false;
    }
    default boolean isVeryNice(){
        return false;
    }
}