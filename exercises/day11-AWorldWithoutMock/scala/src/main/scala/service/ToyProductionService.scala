package service

import domain.{Toy, ToyRepository}
import domain.ToyState._

class ToyProductionService(repository: ToyRepository, notificationService: NotificationService) {

  def assignToyToElf(toyName: String): Unit = {
    val toy = repository.findByName(toyName)
    if (toy != null && toy.state == UNASSIGNED) {
      toy.state = IN_PRODUCTION
      repository.save(toy)
      notificationService.notifyToyAssigned(toy)
    }
  }
}
