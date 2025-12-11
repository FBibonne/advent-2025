import { Toy } from "../domain/Toy";

export interface NotificationService {
  notifyToyAssigned(toy: Toy): void;
}
