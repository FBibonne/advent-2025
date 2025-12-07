# Day 07 – Bug Driven Development

> **Memory fragment – 2023 · Day 9**  
> I remember that statement generator.  
> It printed a nice summary, and the total looked right.  
> At least the first time.  
> But next time… the number changed.  
> ...  
> *Maybe the bug wasn’t in the maths, but in the way we let time and state creep in.*  

On the seventh day of the journey, we revisit the **Day 9 memory from 2023**:

The existing test passes… but is the behaviour really correct?

Today, the approach is different, we practice **Bug Driven Development**:

> You are **not allowed to change the production code**  
> unless you have a failing test that shows the wrong behaviour.

---

## Challenge — Let the bug lead the refactor

Your mission:

 - **Understand the current behaviour**
 - **Think of behaviours that might be wrong**
 - **Write tests that expose the bug(s)**
 - **Fix the code, one failing test at a time**

 Let the failing tests guide *where* and *how* to refactor.

---

## Where to play

Pick the stack you want to use as your **main stack** this year and open the Day 9 memory from 2023:

- `memories/2023/exercises/<your-stack>/day09`

This is your playground.

---

## Share with the community

On Discord, you can share for example:

- the **first failing test** you wrote and what bug it revealed,
- a before/after snippet of the `Client` design,
- how your view of “where the bug really was” evolved.

If you want your work to be public, add your file to:

- `community/solutions/dayNN/` by copying the template:  
  `community/solutions/TEMPLATE-[replace_with_your_name].md`  
- then open a pull request.

In `journey/your-name.md`, write a short entry for today:

- Which bug did I **intentionally hunt** first?  
- What did my **new tests** say about the design I really wanted?  

When the time loop surfaces a bug,  
let the test be the lantern that shows you how to fix it. 🐛💡
