package gifts;

public enum Behavior {
    NAUGHTY, NICE, VERY_NICE;

    public boolean isNaughty(){
        return NAUGHTY == this;
    }
    public boolean isNice(){
        return NICE == this;
    }
    public boolean isVeryNice(){
        return VERY_NICE == this;
    }
}