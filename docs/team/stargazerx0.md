# Li Kunjun's Project Portfolio Page

## Project: Econocraft Pro

EconCraft is a single player text adventure game where a player takes on the role of a business owner or manager, tasked with growing their company from a small startup to a large corporation. The game would simulate real-world business challenges, including resource management, market analysis, product development, and financial planning to help students prepare for the future challenges.

Given below are my contributions to the project.

* **New Feature**: Implemented the Save and Load functionality for player profiles, including detailed asset management of stock, bond and crypto packages.
  * What it does: This feature allows the user to save their current game state, including detailed financial assets (stocks, bonds, and cryptocurrencies), and load it back at a later time. This functionality enhances the user experience by providing the ability to preserve game progress and resume from the exact state left off.
  * Justification: This feature is crucial for the product as it provides a mechanism for persisting user progress and enables long-term engagement with the game. Users can take breaks and return to their game without losing their progress, which is essential for user retention and satisfaction.
  * Highlights: Implementing this feature required a thorough understanding of object serialization and file management in Java. It involved designing the JSON structure for saving complex objects and ensuring backward compatibility for future extensions of the game model. The challenge was to efficiently serialize and deserialize game objects, including hierarchical relationships like assets containing stocks, bonds, and cryptocurrencies, while maintaining the integrity and security of the data.
  * Credits: The JSON parsing was facilitated by the `org.json` library, which simplified the handling of JSON data structures. However, the design of the data model and the integration of save/load functionality into the game's flow were original contributions that required careful planning and implementation.

* **New Feature**: Implemented Hangman game with Software Engineering Concepts and Improved User Input Handling
  * What it does: The updated Hangman game now includes words related to software engineering concepts, providing an educational twist to the game experience.
  * Justification: This feature significantly improves the product by making the game more relevant and educational for users interested in software engineering. Furthermore, the input validation feature enhances user experience by preventing common input mistakes and allowing users to rectify them without consequence.
  * Highlights: Implementing the educational theme involved curating a list of software engineering-related words, integrating them into the game, and testing to ensure coherence and relevance. Improving the input handling mechanism involved designing a user-friendly and forgiving input system that aligns with common user behaviors and expectations.
  * Credits: The inspiration for enhancing the Hangman game came from my experience playing traditional Hangman. Motivated to add educational value, I innovated by integrating software engineering concepts, making the game not only enjoyable but also informative. The enhanced input validation was a result of observing common user input mistakes in classic Hangman games, leading to a more user-friendly and error-tolerant design.

* **New Feature**: Enhanced Tic Tac Toe Game with AI and improved User Interaction
  * What it does: This enhancement refines the Tic Tac Toe game by introducing an AI opponent and improving user interaction. The game now actively validates player input, ensuring that only valid moves are accepted and processed.
  * Justification: Enhancing the AI makes the game more challenging and engaging for users. Improved input validation enhances user experience by preventing common mistakes, thereby making the game more enjoyable and less frustrating.
  * Highlights: This enhancement required a detailed review and modification of the game logic, particularly in how the AI decides its moves and how the game handles user input. Special attention was given to the user interface to ensure that players receive clear instructions and immediate feedback on their actions.
  * Credits: The inspiration for this project came from classic Tic Tac Toe games and their variations. While the foundational game logic was based on standard Tic Tac Toe rules, significant modifications were made to the AI logic and user input handling to create a more engaging and user-friendly experience.


* **Code contributed**: https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=stargazerx0&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-02-23&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other

