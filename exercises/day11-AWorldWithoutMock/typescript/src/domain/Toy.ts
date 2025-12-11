export enum ToyState {
  UNASSIGNED = "UNASSIGNED",
  IN_PRODUCTION = "IN_PRODUCTION"
}

export class Toy {
  constructor(public name: string, public state: ToyState) {}
}
