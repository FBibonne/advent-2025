import { Toy, ToyState } from "../domain/Toy";
import { ToyRepository } from "../domain/ToyRepository";
import { NotificationService } from "./NotificationService";

export class ToyProductionService {
  constructor(
    private readonly repository: ToyRepository,
    private readonly notificationService: NotificationService
  ) {}

  assignToyToElf(toyName: string): void {
    const toy = this.repository.findByName(toyName);
    if (toy && toy.state === ToyState.UNASSIGNED) {
      toy.state = ToyState.IN_PRODUCTION;
      this.repository.save(toy);
      this.notificationService.notifyToyAssigned(toy);
    }
  }
}
