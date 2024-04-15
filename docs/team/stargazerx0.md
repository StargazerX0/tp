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

* **Code Quality Enhancement**: Extracted methods to form new classes in file packages  [#161](https://github.com/AY2324S2-CS2113-T11-4/tp/pull/161)
  * What it does: extracted methods from `Saver` and `Loader` classes to form `Serializer` and `Decoder`.
  * Justification: readability `Saver` and `Loader` classes with methods extracted and code becomes more OOP.
  * Highlights: more reasonable split of classes, `Saver` and `Loader` handle the Json file while 
  * `Serializer` and `Decoder` handle the data in Json.

* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=stargazerx0&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-02-23&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **General Contribution**:
  * Tested and reported bugs in game.

* Project management:
  * Reviewed and approved the pull requests of other team members on GitHub. [#76](https://github.com/AY2324S2-CS2113-T11-4/tp/pull/76)[#80](https://github.com/AY2324S2-CS2113-T11-4/tp/pull/80)

* Enhancements to existing features:
  * Added randomness to the typing game. [#79](https://github.com/AY2324S2-CS2113-T11-4/tp/pull/79)
  * Improve user experience for Hangman. [#162 ](https://github.com/AY2324S2-CS2113-T11-4/tp/pull/162)

* Documentation:
  * User Guide:
    * Added documentation for `exercise`, `tic tac toe`, `hangman`, `save` and `load` features. [#186](https://github.com/AY2324S2-CS2113-T11-4/tp/pull/186) [#186](https://github.com/AY2324S2-CS2113-T11-4/tp/pull/186)
  * Developer Guide:
    * Updated implementation details for the `save` and `load` features. [#178](https://github.com/AY2324S2-CS2113-T11-4/tp/pull/178)

* Community:
  * Request to use org.json library in CS2113 Forum. [#40](https://github.com/nus-cs2113-AY2324S2/forum/issues/40)