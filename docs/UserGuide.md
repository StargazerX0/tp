# EconoCraft Pro v2.0 
# User Guide

## Table of Contents
- [Introduction](#introduction)
- [Quick Start](#quick-start)
- [Features](#features)
  - [Work](#feature---work)
  - [Exercise](#feature---exercise)
  - [Rest](#feature---rest)
  - [Upgrade](#feature---upgrade)
  - [Manage Company](#feature---manage-company)
    - [Hire Employee](#hire-employee)
    - [Fire Employee](#fire-employee)
    - [Adjust Employee Salary](#adjust-employee-salary)
  - [Check Status](#feature---check-status)
    - [Player Status](#player-status)
    - [Company Status](#company-status)
  - [Stock](#feature---stock)
    - [Buy Stock](#buy-stock)
    - [Sell Stock](#sell-stock)
  - [Bond](#feature---bond)
  - [Crypto](#feature---crypto)
  - [Help](#feature---help)
  - [Random Events](#feature---random-events)
  - [Company Status](#company-status)
  - [Random Events](#feature---random-events)

## Introduction

EconoCraft is a single player text adventure game where a player takes on the role of a business owner or manager,
tasked with growing their company from a small startup to a large corporation. The game would simulate real-world
business challenges, including resource management, market analysis, product development, and financial planning to
help students prepare for the future challenges.

## Quick Start

1. Ensure you have Java 11 installed on your computer.
2. Download the latest `econoCraft.jar` from [here](https://github.com/AY2324S2-CS2113-T11-4/tp/releases/download/v2.0/econoCraft.jar).
3. Copy the file to the folder you want to use as the home folder for EconoCraft.
4. Open a terminal and navigate to the folder where `econoCraft.jar` is located.
5. Run the command `java -jar econoCraft.jar` to start the game. The chatbot should start and display the welcome message.
6. Follow the game instructions to start playing.
7. Your game progress will be auto saved in the `data` folder as `PlayerProfile.json`.

>[!Note]
> * The game would prompt you to enter your action during the game.
> * If you are stuck, you can use the `help` command to see a list of commands that you can use.
> * For advanced user, only normal modification is allowed. Extreme data modification may result in game progress reset.

## Features

### Feature - Work
Allows player to work to earn money by playing a small typing game.

Format: `work`

Expected outcome:
```
===========================================================================
Welcome to the Typing Game!
Try to finish typing the given text within 20 seconds.
Type the following as fast as you can:
===========================================================================
===========================================================================
Five or six big jet planes zoomed quickly by the tower.
===========================================================================
===========================================================================
Press ENTER to start 
===========================================================================
```

Player would lose **20** health after performing the work. The program would select one text at random,
player will be prompted to type the given text within 20 seconds.
The money earned would be based on the **accuracy** of the typing.
> [!Note]
> * If the player fails to type the text within 20 seconds, the player's earning would be reduced by 50%.
> * The formula for calculating the money earned is: `money = 2500 * accuracy`
 
Examples:
```
===========================================================================
Type here: Five or six big jet planes zoomed quickly by the tower.
===========================================================================
You typed at 100% accuracy in 18.07 seconds!
Great job!
===========================================================================
===========================================================================
Your have lost 20 health.
Your current health is: 80
===========================================================================
===========================================================================
$2500 has been added to ur asset - Detail: (2500 * 100%).
Your total asset is now $7500.
===========================================================================
```

### Feature - Exercise
Allows player to exercise to increase their health.

Format: `exercise`

Expected outcome:
```
===========================================================================
exercise
===========================================================================
Choose your game:
1. Tic Tac Toe
2. Hangman
===========================================================================
===========================================================================
Enter your choice (1 for Tic Tac Toe, 2 for Hangman):
===========================================================================
```
There are two game options, `1` for Tic Tac Toe game, `2` for Hangman game.

Format `1`

```
===========================================================================
Please choose your mark: X or O
===========================================================================
```
Player would then select which mark they would like to use, the game would start after the selection.

Format: `X` or `O`

Expected outcome:
```
===========================================================================
- - - 
- - - 
- - - 
===========================================================================
===========================================================================
Player O, enter your move (row [1-3] column [1-3]):
===========================================================================
```
Player would then enter the row and column they would like to place their mark. 
The bot would then place the other mark in a random position.

Format: `row column`

Example of usage:`1 1`

Expected outcome:
```
===========================================================================
O - - 
- - - 
- - - 
===========================================================================
===========================================================================
AI's turn!
===========================================================================
===========================================================================
O - - 
- - - 
- - X 
===========================================================================
===========================================================================
Player O, enter your move (row [1-3] column [1-3]):
===========================================================================
```
The game would continue until a player wins or the board is full.

Examples:
```
===========================================================================
O O O 
- X - 
- - X 
===========================================================================
===========================================================================
Siuuuuu, player O wins!
===========================================================================
```
This would increase 10% of the player's health if player wins the game.

If player type 2 when selecting the game, the Hangman game will be initialized.

Format `2`

Expected outcome:
```
===========================================================================
Welcome to the Hangman Game!
===========================================================================
===========================================================================
This game's words are related to concepts in Software Engineering.
===========================================================================
===========================================================================
Please enter only one character at a time as your guess.
===========================================================================
```

In each round, player will be given a letter related to CS2113 concepts with one character missing and is required to 
guess the missing character.
Example:
```
===========================================================================
Round 1:
===========================================================================
===========================================================================
d e v e l o p m e _ t 
===========================================================================
===========================================================================
Guess the missing letter:
===========================================================================
```

If the guess is correct, the game will show correct message and enter next round.
Example:
```
===========================================================================
Guess the missing letter:
===========================================================================
n
===========================================================================
d e v e l o p m e n t
===========================================================================
===========================================================================
Correct! Moving to next round.
===========================================================================
```
If the guess is incorrect, the game will show error message and enter next round.
Example:
```
===========================================================================
c _ o u d 
===========================================================================
===========================================================================
Guess the missing letter:
===========================================================================
a
===========================================================================
Wrong guess!
===========================================================================
```

If the input format is incorrect, player will be asked to make another guess.
Example:
```
===========================================================================
l _ _ p 
===========================================================================
===========================================================================
Guess the missing letter:
===========================================================================
dd
===========================================================================
Invalid input. Please guess exactly one letter at a time:
===========================================================================
```

If the total wrong guesses are fewer than 3, player will win the game.
Example:
```
===========================================================================
Game completed! You finished all rounds.
===========================================================================
```
This would increase 10% of the player's health if player wins the game.

### Feature - Rest
Allows player to rest to increase their health. Player would be tasked to answer two True or False question
related to cs2113.

Format: `rest`

Expected outcome:
```
===========================================================================
Welcome to the MCQ Game!
Answer the following questions:
===========================================================================
===========================================================================
Retailing business sells products to other businesses rather than the final customer? 
===========================================================================
===========================================================================
Type T for true and F for false
===========================================================================
```
Player would then enter `T` or `F` to answer the question.

Format: `T` or `F`

Example of usage:`T`

Expected outcome:
```
===========================================================================
Incorrect!
===========================================================================
```
After two questions, the player would be informed of their score.

Examples:
```
===========================================================================
You answered 1 question correctly.

===========================================================================
Your have gained 20 health.
Your current health is: 70
===========================================================================
```

This would increase 20% of the player's health if player achieves more than 50% correct answers.

### Feature - Upgrade

Allows player to upgrade their status to access advanced features of the game.
This would require the player to have at least **$10000** in their asset account.

The features are:

- Allow player to manage a company and gain more profit.
- Allow player to purchase financial products such as stock, bond and crypto.
- Allow player to sell stock that they currently possess.

Format: `upgrade`

Expected outcome:
```
===========================================================================
$10000 has been deducted from ur asset.
 Your total asset is now $10000.
===========================================================================
===========================================================================
You have successfully upgraded your player!
===========================================================================
```

### Feature - Manage Company

After upgrading, player can manage a company to earn more profit.
The name of the company is `ECONO CROP`. 

The default company status is as follows
* 0 employees
* Employee salary of $800
* Revenue per employee of $1200

>[!Note]
> * The profit per round is calculated as <br>`profit = numOfEmployees * (revenue per employee - employee salary)`
> * The player can hire employees to increase the profit per round.
> * The player can fire employees to reduce the cost of the company.
> * The player can adjust the salary of the employees.

#### Hire Employee
Allows player to hire an employee to help manage the company.
With more employees, the player can earn more profit.

Format: `hire <employee number>`

Example of usage: `hire 2`

Expected outcome:
```
===========================================================================
$2000 has been deducted from ur asset.
 Your total asset is now $9000.
===========================================================================
===========================================================================
1 of employees has been hired. :)
Current No. of Employees: 2
===========================================================================
```

#### Fire Employee
Allows player to fire employees to reduce the cost of the company.

>[!Note]
> * The player cannot fire more employees than the number of employees they currently have.

Format: `fire <employee number>`

Example of usage: `fire 1`

Expected outcome:
```
===========================================================================
1 of employees has been fired. :(
Current No. of Employees: 1
===========================================================================
```

#### Adjust Employee Salary
Allows player to adjust the salary of the employees.

Format: `raise <amount>` or `lower <amount>`

Example of usage: `raise 100` or `lower 100`

Expected outcome:
```
===========================================================================
You have successfully raised the salary by $100.
The new salary is $800.
===========================================================================
```
```
===========================================================================
You have successfully lowered the salary by $100.
The new salary is $900.
===========================================================================
```

### Feature - Check Status

#### Player Status
Allows player to check their current status.

Format: `status`

Expected outcome:
```
===========================================================================
Current Status:Your name is: jj
occupation: Artificial intelligence
current health: |##########| 100%
current money: 5000
you need $95000 more to win the game
===========================================================================
Current round: 1
you have 19 rounds left before the game ends!
===========================================================================
You have 1 action left
Input your action! If needed, type 'help' for more info
===========================================================================
```

#### Company Status
If player what to find out their company status, e.g. number of employees, salary, and profit, they can type `company`.

Format: `company`

Expected outcome:
```
===========================================================================
Company: ECONO CROP
Number of Employees: 0
Employee Salary: 800
Revenue Per Employee: 1200
Profit per Round: 0
===========================================================================
```

### Feature - Stock

#### Buy Stock

Allows player to buy stock from the virtual stock market.

Format: `stock`

Expected outcome:
```
============================================================
▲                                                        
│                                                        
│                                                        
│                                                        
│                                                        
│                                                        
│                                                        
│                                                        
│                                                        
│     x                                                  
│   xxxxx                                                
│xxxx   xx    xxxxxxxxxxx                                
│         xxxx          xxxxxxx                 xxxxxxxx 
│          x                   xxxxxxxxx     xxxx        
│                                       xxxxxx           
│                                                        
│                                                        
│                                                        
│                                                        
│                                                        
│                                                        
│                                                        
│                                                        
│                                                        
│                                                        
└───────────────────────────────────────────────────────►
============================================================
============================================================
Profit for microsoft in quarter one is similar to quarter two -CNN 
Where is the next step for Microsoft? -Economist 
Not much progress in our R&D department for microsoft so far -User5642 from Twitter 
============================================================
============================================================
Microsoft (Multi-national cooperation) 
============================================================
============================================================
Price per stock : 420
============================================================
How many stock do you want to purchase? Input 0 if you want none
```

Player would enter an integer to indicate the number of stocks to purchase.

```
StockCommand executed
============================================================
```

Player can also type `status` to check the stock that they are currently holding.

```
============================================================
Current Status:
Your name is :xia
occupation :Semi-conductor
current health :100
current asset: 800
Microsoft (Multi-national cooperation) 
 currently share count : 10
============================================================
```

#### Sell Stock

Allow player to sell the stocks they currently possess.

Format: `sellstock`

Expected outcome:

```
The stock price risen by: -2
Your gain in stock for this round is: -20

============================================================
$4180 returned to your account. 
============================================================
```

The system will output the profit earn from selling the stock and 
return the money gained from selling the stock back to your asset account.

### Feature - Bond

#### Buy Bond

Allows player to buy bond from the virtual stock market.

Format: 'bond'

Expected outcome:

```
Select a bond to purchase:

1: Government Stability Bond
2: Corporate Growth Bond
3: High Yield Bond
4: Inflation Linked Bond
Enter the number of the bond you wish to purchase, or 0 to exit:

```

Player would enter an integer to indicate which type of bond to purchase.

Expected outcome（choose number 1）:
```
============================================================
The Government Stability Bond is considered a secure investment,
with fixed interest payouts and a guaranteed return of principal at maturity.
This bond is favored by investors seeking stability and low risk.
It provides fixed return to you every round

Bond Name: Government Stability Bond
Price per bond unit: 1000
Annual Interest Rate (%): 3
============================================================
How many units of Government Stability Bond do you want to purchase? Input 0 if you want none
```

Player would enter an integer to indicate the number of bonds to purchase.

Expected outcome (choose integer 1 )
```
============================================================
$1000 has been deducted from ur asset.
Your total asset is now $0.
============================================================
You've successfully purchased 1 units of Government Stability Bond. Expected total interest gain is $30.00.
BuyBondCommand executed
You have 2 actions left
Input your action! If needed, type 'help' for more info
============================================================
```

Player would enter an integer to indicate which type of crypto to purchase.

Expected outcome（choose number 1）:
```
============================================================
Bitcoin, as the first decentralized digital currency, has led the way in blockchain technology. 
It offers a peer-to-peer system without a central authority, 
making it a revolutionary approach to currency. 
It provides return to you every round, but it might be listed as illegal items.

Crypto Name: Bitcoin
Current Price: 10000 USD
============================================================
How much in USD do you want to invest in Bitcoin? Input 0 if you want none
```

Player would enter an integer to indicate the number of cryptos to purchase.

Expected outcome (choose integer 10000 )
```
Positive news has increased the value of Bitcoin by 3%.
============================================================
$10000 has been deducted from ur asset.
 Your total asset is now $974630.
============================================================
You've successfully invested 10000 USD in Bitcoin.
BuyCryptoCommand executed
You have 2 actions left
Input your action! If needed, type 'help' for more info
============================================================
```

Player can also type `status` to check the crypto that they are currently holding.
```
============================================================
Current Status:
Your name is: MA
occupation: Robotics
current health: |#####     | 50%
current money: $974630, you need $25370 more to win the game
============================================================
Your current investments are: 
Bitcoin current crypto count : 1

============================================================
You have 2 actions left
Input your action! If needed, type 'help' for more info
============================================================
```

### Feature - Crypto

Purchase for crypto is similar to bond.
>[!NOTE]
> * It provides more financial return.
> * It might be confiscated by the government.

### Feature - Help

Shows a list of commands that the player can use when player is stuck.

Format: `help`

Expected outcome:
```
===========================================================================
Here are the list of commands:
===========================================================================
help - Show the list of commands
work - to work
rest - to rest
exercise - to exercise
status - to check status
upgrade - to upgrade(!NOTE your money needs to more than $10000)
bye - to exit
===========================================================================
Commands below are only available after UPGRADE!
===========================================================================
stock - to purchase stocks from the stock market 
sellstock - to sell all of your stocks 
bond - to purchase bonds from the bond market 
crypto - to purchase cryptocurrency from the cryptocurrency market 
company - to check company status
hire <number> - to hire employee (each employee cost $1000 to hire)
fire <number> - to fire employee
raise <number> - to raise salary
lower <number> - to lower salary
===========================================================================
```

### Feature - Random Events

At the end of each round, there is a chance that a random event would occur. The
random event could be:
* **Positive event**: Player would receive a reward, such as money or health.
* **Negative event**: Player would receive a penalty, such as losing money or health.
* **Lottery event**: Player would have a chance to win a large amount of money.
* **Decision event**: Player would need to make a decision that would affect their status.

Example of an event:
```
===========================================================================
There is a food challenge in town.
You need to eat 1kg of A5 Wagyu in 15min
If you win, U will receive $500
Otherwise, you will need to pay $200
Do you want to participate?
(Yes/No)?
```

if you choose `yes`, the outcome could be:
```
You have failed to complete the food challenge!
You have lost $200 as a penalty!
```
or
```
You have successfully completed the food challenge!
You have received $500 as a reward!
```
or
```
You have completed the food challenge, but your body cannot handle!
You have received $200 as a consolation prize!
You have lost $300 to pay the medical bill!
You have lost $100 TAT
```
Feel free to explore the different random events in the game!

## FAQ

**Q**: How do I know what action I should do at different stages of the game? 

**A**: No worries! The game would prompt you to enter your action during the game. 
If you are stuck, you can use the `help` command to see a list of commands that you can use.

**Q**: Can I transfer my game record to another computer?

**A**: Yes you can! The record is stored as `PlayerProfile.json` in `data` folder. The data in json file will be restored
by loader class during initialization, if the folder is empty, the program will open a new record. To transfer the
record, you can copy your current json file into `data` folder at your new computer.

## Command Summary

| Description            | Command                              |
|------------------------|--------------------------------------|
| Work                   | `work`                               |
| Exercise               | `exercise`                           |
| Rest                   | `rest`                               |
| Check Status           | `status`                             |
| Help                   | `help`                               |
| Sell Stock             | `sellstock`                          |
| Buy Stock              | `stock`                              |
| Upgrade                | `upgrade`                            |
| Hire Employee          | `hire <employee number>`             |
| Fire Employee          | `fire <employee number>`             |
| Adjust Employee Salary | `raise <amount>` or `lower <amount>` |
| Exit                   | `bye`                                |


