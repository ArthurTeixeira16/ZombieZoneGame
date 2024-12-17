# LDTS_T06_G06 - Zombie Zone

## Game Description

Zombie Zone is a fast-paced 2D arena shooter inspired by Space Invaders with a freer gameplay. Players navigate in the arena, avoiding walls while dodging and shooting waves of zombies. Each round intensifies the action, introducing stronger and more frequent enemies to keep the challenge alive.

Unlike Space Invaders, the player has complete freedom of movement, making strategic positioning crucial for survival. Every zombie eliminated with a projectile boosts the score, and a touch from a zombie costs the player a life.

With its action and difficulty, Zombie Zone tests your reflexes and resilience as you strive to achieve the highest score possible. How long can you survive? 

This project was developed by *Arthur Teixeira* (*up202300368*@fe.up.pt), *Divaldo Dias* (*up202309923*@fe.up.pt) and *Pedro Gouveia* (*up202200045*@fe.up.pt) for LEIC.LTDS 2024⁄25.

![](src/main/resources/ImagesForReadme/giflead.gif)
![](src/main/resources/ImagesForReadme/gifgame.gif)

### IMPLEMENTED FEATURES
- **Connected Menus** - The user has the capability of browsing through the different menus. (Ex: Menu, Play, Game over, Try again).
- **Keyboard control** - The mouse and keyboard inputs are received through the respective events and interpreted according to the current game state.
- **Soldier control** - The soldier may move with the keyboard control and shoot his gun when arrows are pressed.
- **Walls** - Walls are obstacles created to limit the arena and randomized to obstruct the player's movement but without completely blocking the action, allowing the player to move freely, as long as he does not hit the so-called Walls.
- **Different zombies** - There is 3 types of zombies, the Normal Zombie('Z') has two lives and normal speed, the Speedy Zombie('S') has only one live, but it is way faster than the Normal Zombie and the Tank Zombie('T') has 4 lives but he is slower.
- **Life** - Both soldier and zombies have a life attribute, which decreases when they take damage.
- **Score** - When the soldier kills a zombies, the score is increased.
- **Shoot** - The soldier can shoot projectiles in every direction with a speed and a range predetermined to kill the enemies.
- **Delayed attack** - Both soldier and zombies have a delayed attack for balance purposes, the delay to the soldier to shoot is 1 second, while the zombie hit delay is 3 seconds so the player can reposition.
- **Round** - The round change when all zombies are killed, and each round increase makes the game harder.
- **Spawn** - The spawn of enemies varies with the round, using a function it increases the number and the chance for different zombies to appear.
- **Track** - A method that enemies use to trace the soldier on the shortest path, while considering the walls and another enemy.
- **LeaderBoard** - A menu where it is stored the past five best scores and if a higher score has been made, it changes the file.
- **GameOver** - When the player dies, the game over menu pop up, and you can select go back to menu or try again.
- **Hud** - Shows the score, round and life while playing in the arena.
- **Textured background** - For design purposes the arena is drawn with textured ground with gray shades.
![](src/main/resources/ImagesForReadme/ddb784e4-f823-474d-a634-95f4e3ff47f6.JPG "Game Screen")
![](src/main/resources/ImagesForReadme/5119c833-ba12-4970-a63d-460cadfce9bb.JPG "Leaderboard Screen")
![](src/main/resources/ImagesForReadme/d0ced638-2706-49f9-b1ba-24db40f3301f.JPG "Game Over Screen")

### PLANNED FEATURES

All the planned features were successfully implemented.

### DESIGN
![](src/main/resources/ImagesForReadme/ZombieZoneDiagramFinal.png)
> This section should be organized in different subsections, each describing a different design problem that you had to solve during the project. Each subsection should be organized in four different parts:

- **Problem in Context.** The description of the design context and the concrete problem that motivated the instantiation of the pattern. Someone else other than the original developer should be able to read and understand all the motivations for the decisions made. When refering to the implementation before the pattern was applied, don’t forget to [link to the relevant lines of code](https://help.github.com/en/articles/creating-a-permanent-link-to-a-code-snippet) in the appropriate version.
- **The Pattern.** Identify the design pattern to be applied, why it was selected and how it is a good fit considering the existing design context and the problem at hand.
- **Implementation.** Show how the pattern roles, operations and associations were mapped to the concrete design classes. Illustrate it with a UML class diagram, and refer to the corresponding source code with links to the relevant lines (these should be [relative links](https://help.github.com/en/articles/about-readmes#relative-links-and-image-paths-in-readme-files). When doing this, always point to the latest version of the code.
- **Consequences.** Benefits and liabilities of the design after the pattern instantiation, eventually comparing these consequences with those of alternative solutions.

**Example of one of such subsections**:

------

#### THE JUMP ACTION OF THE KANGAROOBOY SHOULD BEHAVE DIFFERENTLY DEPENDING ON ITS STATE

**Problem in Context**

There was a lot of scattered conditional logic when deciding how the KangarooBoy should behave when jumping, as the jumps should be different depending on the items that came to his possession during the game (an helix will alow him to fly, driking a potion will allow him to jump double the height, etc.). This is a violation of the **Single Responsability Principle**. We could concentrate all the conditional logic in the same method to circumscribe the issue to that one method but the **Single Responsability Principle** would still be violated.

**The Pattern**

We have applied the **State** pattern. This pattern allows you to represent different states with different subclasses. We can switch to a different state of the application by switching to another implementation (i.e., another subclass). This pattern allowed to address the identified problems because […].

**Implementation**

The following figure shows how the pattern’s roles were mapped to the application classes.

![img](https://www.fe.up.pt/~arestivo/page/img/examples/lpoo/state.svg)

These classes can be found in the following files:

- [Character](https://web.fe.up.pt/~arestivo/page/courses/2021/lpoo/template/src/main/java/Character.java)
- [JumpAbilityState](https://web.fe.up.pt/~arestivo/page/courses/2021/lpoo/template/src/main/java/JumpAbilityState.java)
- [DoubleJumpState](https://web.fe.up.pt/~arestivo/page/courses/2021/lpoo/template/src/main/java/DoubleJumpState.java)
- [HelicopterState](https://web.fe.up.pt/~arestivo/page/courses/2021/lpoo/template/src/main/java/HelicopterState.java)
- [IncreasedGravityState](https://web.fe.up.pt/~arestivo/page/courses/2021/lpoo/template/src/main/java/IncreasedGravityState.java)

**Consequences**

The use of the State Pattern in the current design allows the following benefits:

- The several states that represent the character’s hability to jump become explicit in the code, instead of relying on a series of flags.
- We don’t need to have a long set of conditional if or switch statements associated with the various states; instead, polimorphism is used to activate the right behavior.
- There are now more classes and instances to manage, but still in a reasonable number.

#### KNOWN CODE SMELLS

> This section should describe 3 to 5 different code smells that you have identified in your current implementation.

### TESTING

- Screenshot of coverage report.
- Link to mutation testing report.

### SELF-EVALUATION

- Arthur Teixeira: 33.33%
- Divaldo Dias: 33.33%%
- Pedro Gouveia: 33.33%