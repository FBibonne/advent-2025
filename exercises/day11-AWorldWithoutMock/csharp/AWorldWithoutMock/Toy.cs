namespace AWorldWithoutMocksBefore
{
    public enum ToyState
    {
        Unassigned,
        InProduction
    }

    public class Toy
    {
        public string Name { get; }
        public ToyState State { get; set; }

        public Toy(string name, ToyState state)
        {
            Name = name;
            State = state;
        }
    }
}
