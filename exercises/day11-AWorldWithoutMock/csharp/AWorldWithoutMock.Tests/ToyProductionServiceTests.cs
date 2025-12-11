using Moq;
using Xunit;

namespace AWorldWithoutMocksBefore.Tests
{
    public class ToyProductionServiceTests
    {
        private const string ToyName = "Train";

        [Fact]
        public void AssignToyToElf_ShouldSaveToyInProduction_AndNotify()
        {
            var repoMock = new Mock<IToyRepository>();
            var notificationMock = new Mock<INotificationService>();
            var service = new ToyProductionService(repoMock.Object, notificationMock.Object);
            var toy = new Toy(ToyName, ToyState.Unassigned);
            repoMock.Setup(r => r.FindByName(ToyName)).Returns(toy);

            service.AssignToyToElf(ToyName);

            repoMock.Verify(r => r.Save(It.Is<Toy>(t => t.State == ToyState.InProduction)), Times.Once);
            notificationMock.Verify(n => n.NotifyToyAssigned(It.Is<Toy>(t => t.State == ToyState.InProduction)), Times.Once);
        }

        [Fact]
        public void AssignToyToElf_ShouldNotSaveOrNotify_WhenToyNotFound()
        {
            var repoMock = new Mock<IToyRepository>();
            var notificationMock = new Mock<INotificationService>();
            var service = new ToyProductionService(repoMock.Object, notificationMock.Object);
            repoMock.Setup(r => r.FindByName(ToyName)).Returns((Toy?)null);

            service.AssignToyToElf(ToyName);

            repoMock.Verify(r => r.FindByName(ToyName), Times.Once);
            repoMock.Verify(r => r.Save(It.IsAny<Toy>()), Times.Never);
            notificationMock.VerifyNoOtherCalls();
        }

        [Fact]
        public void AssignToyToElf_ShouldNotSaveOrNotify_WhenToyAlreadyInProduction()
        {
            var repoMock = new Mock<IToyRepository>();
            var notificationMock = new Mock<INotificationService>();
            var service = new ToyProductionService(repoMock.Object, notificationMock.Object);
            var toy = new Toy(ToyName, ToyState.InProduction);
            repoMock.Setup(r => r.FindByName(ToyName)).Returns(toy);

            service.AssignToyToElf(ToyName);

            repoMock.Verify(r => r.FindByName(ToyName), Times.Once);
            repoMock.Verify(r => r.Save(It.IsAny<Toy>()), Times.Never);
            notificationMock.VerifyNoOtherCalls();
        }
    }
}
