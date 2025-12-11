import { Toy } from "./Toy";

export interface ToyRepository {
  findByName(name: string): Toy | null;
  save(toy: Toy): void;
}
