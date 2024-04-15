# Project Portfolio Page

## Overview
Our group have constructed an game to mimic user's daily life in working and investing.
EconoCraft is a single player text adventure game where a player takes on the role of a business owner or manager,
tasked with growing their company from a small startup to a large corporation. The game would simulate real-world
business challenges, including resource management, market analysis, product development, and financial planning to
help students prepare for the future challenges.

## Link for code contribution:
[RepoSense link](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=cxia17&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-02-23&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

## Contribution to UG
### Game feature description and command guide
Described in detail about how the `rest` command, `sellstock` and `stock` command is going to operate.
By describing command features and illustrating input
format and sample outputs.

## Contribution to DG
Drawing the Sequence Diagram for `stock` and `sellstock` functions, describe the mechanism of 
respective functions in detail. Contributed in user stories with fellow teammates and sorted them 
into goals that need to be accomplished for v1.0, v2.0, and v2.1.
Completed the mechanism description for the True and False game that will be invoked by the `rest` 
command.

## Contributions to the project
### Feature contribution
- Designed and implemented True and False Game for `rest` command.
  [PR #33](https://github.com/AY2324S2-CS2113-T11-4/tp/pull/33)
  - What it does: Allows player to gain health by answer different questions relating to entrepreneurship.
- Designed and implemented virtual stock market inside the game where users can 
purchase stock through `stock` command and sell all the stock they possess by `sellstock` command.
  [PR #81](https://github.com/AY2324S2-CS2113-T11-4/tp/pull/81)
  - What it does: Allow users to purchase stock based on the current money they possess and the market
  information they have access to. Player can gain or lose money inside the virtual stock market.
- Made the virtual crypto and bond market more playable by setting different properties to both of the
markets and invoked uncertain events inside each of the markets.
  [PR #166](https://github.com/AY2324S2-CS2113-T11-4/tp/pull/166)
  - What it does: Enables user from gaining financial returns at the end of every round.
- Allow the program to sum up player's performance in different markets at the end of each round.
  [PR #175](https://github.com/AY2324S2-CS2113-T11-4/tp/pull/175)
  - What it does: Sums up player's performance in investments for the last round.
### Project management
- Managed release v1.0 and v2.0 on github.

    [Release v2.0](https://github.com/AY2324S2-CS2113-T11-4/tp/releases/tag/v2.0)

    [Release v1.0](https://github.com/AY2324S2-CS2113-T11-4/tp/releases/tag/v1.0)

### Feature enhancement
  - Wrote additional test cases to obtain a 100% method coverage to `stockCommand`, `sellStockCommand`
  `restCommand`, `stockActivate`, `stockStorage`, and `TrueFalseGame` class. 

    [PR #195](https://github.com/AY2324S2-CS2113-T11-4/tp/pull/195)

### Documentation
Added documentation to all public methods relating to the virtual stock market feature, True&False game
feature, and asset management features.
[PR #200](https://github.com/AY2324S2-CS2113-T11-4/tp/pull/200)

### Community contribution
- Actively communicating within the group to find bugs and potential improvements to the program.
- Organised multiple group meetings to share our thoughts and brainstorm ideas and features.
- Organised multiple internal smoke tests and Beta tests to strengthen the test coverage of our program.
- Improved class test coverage and method test coverage from 50% to 78% and 70%.
  [PR-branchTest](https://github.com/AY2324S2-CS2113-T11-4/tp/pull/195)

## Review/mentoring contributions
Reviewed dozens fo pull requests and leaved multiple comments and suggestions to every pull request.
- [Linked to PR reviewed: random event feature](https://github.com/AY2324S2-CS2113-T11-4/tp/pull/80)
- [Linked to PR reviewed: PR #89](https://github.com/AY2324S2-CS2113-T11-4/tp/pull/89)
- [Linked to PR reviewed: PR #73](https://github.com/AY2324S2-CS2113-T11-4/tp/pull/73)








