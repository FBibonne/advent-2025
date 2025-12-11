package service

import domain.Toy

trait NotificationService {
  def notifyToyAssigned(toy: Toy): Unit
}
