import { Toy, ToyState } from "../domain/Toy";
import { ToyRepository } from "../domain/ToyRepository";
import { NotificationService } from "./NotificationService";
import { ToyProductionService } from "./ToyProductionService";

const TOY_NAME = "Train";

describe("ToyProductionService (before, with mocks)", () => {
  let repository: jest.Mocked<ToyRepository>;
  let notifications: jest.Mocked<NotificationService>;
  let service: ToyProductionService;

  beforeEach(() => {
    repository = {
      findByName: jest.fn(),
      save: jest.fn()
    };
    notifications = {
      notifyToyAssigned: jest.fn()
    };
    service = new ToyProductionService(repository, notifications);
  });

  test("assignToyToElf should save toy in production and notify", () => {
    const toy = new Toy(TOY_NAME, ToyState.UNASSIGNED);
    repository.findByName.mockReturnValue(toy);

    service.assignToyToElf(TOY_NAME);

    expect(repository.save).toHaveBeenCalledTimes(1);
    const savedToy = (repository.save.mock.calls[0] as [Toy])[0];
    expect(savedToy.state).toBe(ToyState.IN_PRODUCTION);

    expect(notifications.notifyToyAssigned).toHaveBeenCalledWith(savedToy);
  });

  test("assignToyToElf should not save or notify when toy not found", () => {
    repository.findByName.mockReturnValue(null);

    service.assignToyToElf(TOY_NAME);

    expect(repository.findByName).toHaveBeenCalledWith(TOY_NAME);
    expect(repository.save).not.toHaveBeenCalled();
    expect(notifications.notifyToyAssigned).not.toHaveBeenCalled();
  });

  test("assignToyToElf should not save or notify when toy already in production", () => {
    const toy = new Toy(TOY_NAME, ToyState.IN_PRODUCTION);
    repository.findByName.mockReturnValue(toy);

    service.assignToyToElf(TOY_NAME);

    expect(repository.findByName).toHaveBeenCalledWith(TOY_NAME);
    expect(repository.save).not.toHaveBeenCalled();
    expect(notifications.notifyToyAssigned).not.toHaveBeenCalled();
  });

  test("assignToyToElf should save before notifying (interaction order)", () => {
    const toy = new Toy(TOY_NAME, ToyState.UNASSIGNED);
    repository.findByName.mockReturnValue(toy);

    service.assignToyToElf(TOY_NAME);

    expect(repository.save).toHaveBeenCalledTimes(1);
    expect(notifications.notifyToyAssigned).toHaveBeenCalledTimes(1);
    expect(repository.save.mock.invocationCallOrder[0])
      .toBeLessThan(notifications.notifyToyAssigned.mock.invocationCallOrder[0]);
  });
});
