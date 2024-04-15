# Zhu Minghui's Project Portfolio Page

## Project: EconoCraft Pro

EconoCraft is a single player text adventure game where a player takes on the role of a business owner or manager,
tasked with growing their company from a small startup to a large corporation. The game would simulate real-world
business challenges, including resource management, market analysis, product development, and financial planning to
help students prepare for the future challenges.

Given below are my contributions to the project.

* **New Features**: Added a typing game to simulate the player's work action when player typed `work` in terminal.
  * What it does: allows users to type in the command to simulate the work action by playing a typing game.
  * Justification: provides a more interactive way for users to simulate the work action, making the game more engaging.
  * Highlights: uses 3 different accuracy calculation algorithms to measure correct accuracy for player's typo or missing words.
* **New Features**: Added Company management system for advanced players and its corresponding commands.
  * What it does: allows players to manage their company to gain more rewards by controlling the company's resources, employees.
  * Justification: provides a more challenging way for advanced players to play the game.
* **New Features**: Added Random events at each player round.
  * What it does: allows players to face random events that may affect their company's performance.
  * Justification: provides a more challenging way for players to play the game.
  * Highlights: This feature is not purely random, the algorithm would adjust the randomness based on the player's profile.

* **New Features**: Added upgrade command that allows player access to more features.
* **New Features**: Added status commands that allow players to check their status.
* **New Features**: Added help command that allows players to check the available commands.

* **Code Quality Enhancement**: Abstracted main components of the project. [#30](https://github.com/AY2324S2-CS2113-T11-4/tp/pull/30) [#80](https://github.com/AY2324S2-CS2113-T11-4/tp/pull/80)
    * What it does: adds interfaces to manage the commands, miniGames, and random events.
    * Justification: improves the code maintainability and extensibility of the project and follows the SOLID principles of OOP.
    * Highlights: uses factory pattern to create commands and random events.

* **General Contribution**:
  * Designed the general logic of the application.
  * Refactored the code structure to improve the code quality. [#24](https://github.com/AY2324S2-CS2113-T11-4/tp/pull/24) [#156](https://github.com/AY2324S2-CS2113-T11-4/tp/pull/156)

* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=zminghuiz&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-02-23&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* Project management:
  * Managed issues, milestones creation and allocation from release v1.0 to v2.1.
  * Managed the project's release cycle, ensuring that the project is on track to meet the deadline.

* Enhancements to existing features:
  * Improved the typing game's accuracy calculation algorithm. [#156](https://github.com/AY2324S2-CS2113-T11-4/tp/pull/156)
  * Improved the robustness of the file loader. [#189](https://github.com/AY2324S2-CS2113-T11-4/tp/pull/189)
  * Formatted UI output and used ANSI escape codes to improve the game's visual effects in Terminal. [#187](https://github.com/AY2324S2-CS2113-T11-4/tp/pull/187)  [#49](https://github.com/AY2324S2-CS2113-T11-4/tp/issues/49)

* Documentation:
  * User Guide:
    * Added documentation for `v1.0` and `v2.0` features. [#59](https://github.com/AY2324S2-CS2113-T11-4/tp/pull/59) [#92](https://github.com/AY2324S2-CS2113-T11-4/tp/pull/92)
    * Updated documentation for `work`, `company management`, `status check`, `upgrade`, `help` and `random events` features. [#190](https://github.com/AY2324S2-CS2113-T11-4/tp/pull/190)
  * Developer Guide:
    * Added overall architecture and components interaction explanation with diagram. [#65](https://github.com/AY2324S2-CS2113-T11-4/tp/pull/65)
    * Updated implementation details for the `work`, `company management`, and `random events` features. [#65](https://github.com/AY2324S2-CS2113-T11-4/tp/pull/65)
    * Added flowchart to explain the game logic. [#84](https://github.com/AY2324S2-CS2113-T11-4/tp/pull/84)

* Community:
  * Pull requests reviewed (with non-trivial review comments): [#81](https://github.com/AY2324S2-CS2113-T11-4/tp/pull/81#discussion_r1545932644) [#77](https://github.com/AY2324S2-CS2113-T11-4/tp/pull/77#discussion_r1545442772) [#78](https://github.com/AY2324S2-CS2113-T11-4/tp/pull/78#discussion_r1545451873) [#83](https://github.com/AY2324S2-CS2113-T11-4/tp/pull/83#discussion_r1547968340)
  * Organized unofficial user testing sessions with another team and test each other's projects. **Honourable Mention**: [CS2113-F14-2](https://ay2324s2-cs2113-f14-2.github.io/tp/)