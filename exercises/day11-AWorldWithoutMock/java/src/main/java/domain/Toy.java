package domain;

public class Toy {

    public enum State {
        UNASSIGNED,
        IN_PRODUCTION
    }

    private final String name;
    private State state;

    public Toy(String name, State state) {
        this.name = name;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public State getState() {
        return state;
    }

    public void setState(State newState) {
        this.state = newState;
    }
}
