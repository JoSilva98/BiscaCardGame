# Bisca card game
___
Bisca is a 2 players card game (player vs PC) played with a 40 cards deck.
The game starts by selecting the delay between each dialog 
which must be a value between 0ms (excluded) and 2000ms (included):

```
Enter the dialog delay (milliseconds): 
```

The text box will appear as long as the value does not belong to the above range.
If you want to quit in this stage enter ***q*** or ***Q***.
<br/><br/>

### Rules
___
* Both players draw 7 cards and play multiple rounds until both players 
have 0 cards left in their hands;
* Each round both players draw a card and the card suit they play must match
the other players suit. Example: if the PC plays a 7 of hearts, the player must play a
hearts cards. If the player doesn't have any hearts card, he can play any other card
(Note: if you play an A or a 7 and the PC doesn't have the same suit the PC will play a trump);
* In every match there is a card whose suit represents the trump. Trump is the strongest
suit that wins against any other suit. If both players play a trump the highest value card
wins;
* The player with most points (above 60) wins the game. If both players score 60 points 
the match results in a draw.
<br/><br/>

### Points System
___
* Aces - 11 points;
* Sevens - 10 points;
* Kings - 4 points;
* Jacks - 3 points;
* Queens - 2 points;
* All the remaining cards - 0 points;
<br/><br/>

### Suits
___
* S - Spades;
* H - Hearts;
* C - Clubs;
* D - Diamonds.
<br/><br/>

### Structure
___
The game contains:
* **Game** class - orchestrates the game;
* **PCPlayer** class - plays the PC cards and holds each player's cards;
* **Deck** class - contains the 40 cards.
<br/><br/>

### Examples
___
#### The game starts:

```
-----Both players draw 7 cards-----

-----Player plays first-----

Cards left in the deck: 25
Trump: AS

Your cards:
[JP, KH, KP, 4S, 5H, 6H, 5S]

Play a card: 
```

#### PC plays the same suit:

```
Play a card: jp

Player played JP
PC played 2P
Trump: AS

-----Player won this round-----

Player score: 3
PC score: 0
```

#### Player tries to play a diferent suit:

```
PC played 4S

Your cards:
[5P, 2S, QS, 7S, 6S, 7P, 3H]

Play a card: 7p
Play a card: 3h
Play a card: 7s

PC played 4S
Player played 7S
Trump: JH
```

#### PC plays a trump over a 7 of clubs:

```
Your cards:
[2P, 4P, 2H, 5P, 7P, 7S, QS]

Play a card: 7p

Player played 7P
PC played QH
Trump: JH

-----PC won this round-----
```

#### Game over:

```
Player played 2S
PC played 3S
Trump: 7D

-----PC won this round-----

Player score: 84
PC score: 36
Player won the game with 84 points!
```