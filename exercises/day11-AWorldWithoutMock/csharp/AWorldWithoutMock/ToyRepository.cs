namespace AWorldWithoutMocksBefore
{
    public interface IToyRepository
    {
        Toy? FindByName(string name);
        void Save(Toy toy);
    }
}
