# Day 06 - Carve a pure pipeline

> **Memory fragment – 2023 · Day 7**  
> I remember that `run` method.  
> It did everything at once: read, transformed, logged, wrote…  
> We sliced it into smaller methods, and it felt better.  
> But something was still… missing. Just to many states... 
> ...  
> *We need a cleaner pipeline.*  

On the sixth day of the journey, we revisit the **a memory from 2023 after a week**, where the original goal was to simplify the `run` method by extracting the right behavior.

This time, we go one step further:

> **We aim to make `run` (or its core) as *referentially transparent* as possible.**  

In short: Same inputs → same outputs.
No hidden state. No surprise side effects.

---

## Challenge — Turn the `run` method into a pure pipeline

 💡A hint for today’s challenge💡

A pure function should look like this:

   ```text
   Result runPure(Input input)
   ```

   You should be able to **replace a call** to this function with its result  
   without changing the behaviour of the program.

**Be aware of your changes in the tests**

---

## Where to play

Pick the stack you want to use as your **main stack** this year and open the Day 7 memory from 2023:

- `memories/2023/exercises/<your-stack>/day07`

This is your playground.

If you are short on time:

- focus on the **core transformation** (the heart of the `run`),  
- extract just enough to have one clearly **pure** pipeline function.

Let the pipeline tell the story ➰💧

---

## Share with the community

On Discord, you can share for example:

- before / after snippet of your `run` method,
- the signature of your pure pipeline function,
- a short note on what you pushed to the edges (mutables states, outputs, etc...).

If you want your work to be public, add your file to:

- `community/solutions/dayNN/` by copying the template:  
  `community/solutions/TEMPLATE-[replace_with_your_name].md`  
- then open a pull request.

In `journey/your-name.md`, write a short entry for today:

- What side effects did I find inside `run`?  
- What does my **pure pipeline** look like (at a high level)?  
- Did making things more referentially transparent change how I test or reason about the code?  

Every time you make code a little more "pure",  
you make it easier for your future self to step back into the time loop. ⏳
