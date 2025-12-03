# Advent of Craft 2025

## Solution DAY 01

- Contributor Discord Name : fbibonne
- Stack : Java (Intellij ultimate)
- Fork : https://github.com/FBibonne/advent-2025/pull/1

## More feedback to share (optional)

- I coded the solution driven by complexity tools in my IDE : SonarQube for IDE and CodeMetrics
- SonarQube threshold need to be configured (max number of parameters in a method => 3, cognitive complexity => 1) in order to >
warnings to drive refactor
- I couldn't install CodeComplexity in my Intellij because I have an early access version : that's the price of the "up to   
date" obession ! It would have been a good tool also
- As I satisfied the metrics, I stopped my refactor before wrapping primitives (such as `int` for the number of days) in     
value types whereas I would have spontaneously introduce value types first without metric driven refactor.
