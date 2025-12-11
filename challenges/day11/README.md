# Day 11 - A World without mock

> _December 11, 2025 - A world without mock_

![snippet of the day](img/day11.png)

  ==> **Loading Fractured Memory** <==

On the eleventh day of the journey, you work in the Elf Factory in orbit, where toys are assigned to elves across dimensions. Production runs 24/7, telemetry hums, tests glow green...

...until a new law is passed:

> « The council of the North Pole has declared that Mocks are banned forever. »

Overnight, every mocking framework becomes illegal. Your carefully crafted tests for ToyProductionService are now contraband. They still describe the right behavior... but their implementation is forbidden.

To keep the factory running, you’ll have to rebuild your safety net without a single mock.

---

## Challenge — Survive in a world without mocks.

Today, you start from a “before” version of the exercise where all tests rely on mocking frameworks (Mockito, Moq, Jest, etc.).

Your mission: rewrite the tests using only hand-crafted test doubles.

---

## Where to play

For the exploration of the fractured memories, since they are not your own memories
We will go to /exercises and the dayNN

You can then pick the stack you want to use as your **main stack**.

- `exercises/2024/day11/<your-stack>/`

This is your playground.

---

## Share with the community

On Discord, you can share:

- How did it feel to write tests without a mocking framework?
- Which test doubles did you end up creating (fake, stub, spy, …)?
- Did the design of your production code become easier or harder to work with?

If you want your work to be public, add your file to:

- `community/solutions/dayNN/` by copying the template:  
  `community/solutions/TEMPLATE-[replace_with_your_name].md`  
- then open a pull request.

In `journey/your-name.md`, write a short entry for today:

 - In which situations will you still use mocks in real life?
 - Where do you now prefer hand-written doubles instead?
 - Did this change how you think about coupling between tests and implementation?

**Welcome to the mockless world. Let’s keep the factory running. 🚀**
