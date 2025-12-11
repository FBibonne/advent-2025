package service

import domain.Toy

interface NotificationService {
    fun notifyToyAssigned(toy: Toy)
}
