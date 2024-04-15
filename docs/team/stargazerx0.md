# Li Kunjun's Project Portfolio Page

## Project: Econocraft Pro

EconoCraft is a single player text adventure game where a player takes on the role of a business owner or manager,
tasked with growing their company from a small startup to a large corporation. The game would simulate real-world
business challenges, including resource management, market analysis, product development, and financial planning to
help students prepare for the future challenges.

Given below are my contributions to the project.

* **New Features**: Added file management support to save player's profile when they exit the game and load player's
* profile when they continue to play.
  * What it does: save their current game state, including complex assets (stocks, bonds, and cryptocurrencies), and 
  * load it back at a later time. 
  * Justification: enhances the user experience by providing the ability to preserve game progress and resume from the 
  * exact state left off.
  * Highlights: a `Serializer` class is developed to convert complex data structures into Json file and a `Decoder` 
  * class is used to convert the Json file back to data structures. The `Loader` and `Saver` class will check the
  * location of Json file and create a new record if the file is missing.
  * Credits: `org.json` library is used in `Decoder` to help Json file decoding by simplifying the conversion of 
    * String array and this improve readability. But the design of the `Decoder` required careful planning and thorough
    * understanding of the game.
* **New Features**: Added a Tic Tac Toe Game to simulate the player's exercise action when player typed `exercise` in 
* terminal and select option `1` .
  * What it does: allows users to place mark against AI player to simulate exercise action by playing a game.
  * Justification: makes the game more challenging and engaging for users, and improved the user interaction with the 
  * validation checks for input.
  * Highlights: AI is deployed to make the game more challenging, and special attention was given to the user interface 
  * to ensure that players receive clear instructions and immediate feedback on their actions.  
* **New Features**: Added a Hangman Game to simulate the player's exercise action when player typed `exercise` in
* terminal and select option `2` .
  * What it does: allow users to learn from the game by guessing words related to CS2113 software engineering concepts, 
  * providing an educational twist to the game experience.
  * Justification: makes the game more challenging and engaging for users, and improved the user interaction with the
  * validation checks for input.
  * Highlights: educational theme is implemented by adding a list of software engineering-related concepts in CS2113,
  * and user input handling mechanism is improved with forgiving input system that aligns with common user behaviors 
  * and expectations.

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