namespace AWorldWithoutMocksBefore
{
    public class ToyProductionService
    {
        private readonly IToyRepository _repository;
        private readonly INotificationService _notificationService;

        public ToyProductionService(IToyRepository repository, INotificationService notificationService)
        {
            _repository = repository;
            _notificationService = notificationService;
        }

        public void AssignToyToElf(string toyName)
        {
            var toy = _repository.FindByName(toyName);
            if (toy != null && toy.State == ToyState.Unassigned)
            {
                toy.State = ToyState.InProduction;
                _repository.Save(toy);
                _notificationService.NotifyToyAssigned(toy);
            }
        }
    }
}
