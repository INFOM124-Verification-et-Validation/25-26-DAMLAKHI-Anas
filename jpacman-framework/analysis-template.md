# Specification-based Testing

## 1. Goal, inputs and outputs
- Goal: returns the next direction for the Ai to take (next position)
- Input domain: this (the current object, clyde), map, player(Pac-man)
- Output domain: the direction which is Optional<Direction> or Null;

## 2. Explore the program (if needed)

## 3. Identify input and output partitions

### Input partitions
this
distance (clyde and PacMan):
Q1: clyde is within a grid spaces of Pac-Man (distance < 8)
Q2: clyde is beyond a grid spaces of Pac-Man (distance > 9)
Q3: clyde is at exactly a grid of spaces of Pac-Man (distance = 8)

Pac-Man not on the Board partition
Pac-Man at the edge of the board partition
Pac-Man does not have a square partition

Obstacle direction
Q1: path of clyde is free
Q2: path of clyde is blocked
Q3: when clyde is exactly on Pac-Man
Q4: clyde has multiple equivalent(valid) paths

Not valid maps:
Q1: multiple clydes
Q2: multiple Pac-Mans

#### Individual inputs

il y'a beacoup de combinaison possible mais nous on essayera de combiner 
pour avoir des bons tests (on choisit les inputs partitions plus important)

distance < 8 && path free;
distance < 8 && path blocked;
distance < 8 && multiple paths;

distance > 8 && path free;
distance > 8 && path blocked;
distance > 8 && multiple paths;

distance = 8 && path free;
distance = 8 && path blocked;
distance = 8 && multiple paths;

#### Combinations of input values

### Output partitions

- Empty direction
- Direction away from pacman
- Direction towards pacman

## 4. Identify boundaries

not valid map:
-multiple clydes
-multiple Pac-Mans
-Clyde on Pac-Man
-Pac-Man not on the board partition
-Pac-Man does not have a square partition

Pac-Man at the edge of the board partition

Pac-Man out of bound (outside the board)

-8 value with distance

## 5. Select test cases

lets pick:

Distance < 8:
    -T1: path_free => away from pacman
    -T2: path_blocked => Empty direction
    -T3: multiple paths => away from pacman

Distance > 8:
    -T4: path_free => towards pacman
    -T5: path_blocked => Empty direction
    -T6: multiple paths => towards pacman

Distance = 8:
    -T7: path_free => away from pacman
    -T8: path_blocked => Empty direction
    -T9: multiple paths => away from pacman

now for limit boundaries we wont combine them but we will test them
Boundaries:
-t10: multiple clydes
-t11: multiple pacmans
-t12: clyde is on pacman

BLINKY:
1: same
2: same
3: 
this
location of blinky and pacman and the ahead point of pacman
-Q1 
