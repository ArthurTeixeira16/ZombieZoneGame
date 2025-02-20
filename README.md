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

#### HANDLING THE DIFFERENT STATES OF THE GAME
**Problem in Context**

In the game, the system must support multiple states: Game State, Leaderboard State, Menu State, and Game Over State. Each state requires different behaviors and functionalities. Before implementing a design pattern, the state logic could have been managed with long conditional checks or switch statements inside a single run() method. This is not scalable as new states are added.

**The Pattern**

The State Pattern is applied to encapsulate state-specific behavior and allow transitioning between states dynamically. Each state implements the State<Interface> interface, which defines common operations like run() and getController(). The State Pattern makes it easier to add new states and removes scattered conditional logic.

**Implementation**

The State<Interface> defines the contract for the states. The states (GameState, MenuState, LeaderboardState, and GameOverState) implement this interface and provide unique behavior.

**Consequences**

- Cleaner, modular code where each state is isolated.
- Adding new states does not affect existing code.
- Increases the number of classes.

#### MANAGING GAME EVENTS LIKE SCORE UPDATES AND GAME ENDING
**Problem in Context**

The game requires real-time updates for events like changes to the score, rounds, or game ending. Initially, the game logic likely relied on tightly coupled classes to manage these updates, which made the system harder to maintain or extend.

**The Pattern**

The Observer Pattern is applied to decouple the game events from the objects that react to those events. Observers like ScoreObserver<Interface> and GameObserver<Interface> listen for changes in the game, such as when a score is updated or the game ends.

**Implementation**

Classes like Game and Hud generate events and GameObserver<Interface> listens for endGame() events and ScoreObserver<Interface> listens for score-related events through onEndGame().

**Consequences**

- Decoupling between the game logic and observers.
- New observers can be added without changing the existing code.
- Increases the number of classes.

#### CREATING DIFFERENT TYPES OF ZOMBIES
**Problem in Context**

The game features multiple types of enemies, such as ZombieNormal, ZombieSpeed, and ZombieHeavy. Initially, these zombies could have been created manually with specific constructors or hardcoded methods, leading to duplicated code and reduced flexibility.

**The Pattern**

The Factory Pattern is applied to encapsulate the creation logic of the enemy objects. This allows the game to create different types of zombies dynamically while centralizing the instantiation logic.

**Implementation**

The Enemy class serves as the base product with common attributes like life, speed, and elapsed_time.

**Consequences**

- Simplifies object creation logic.
- Easy to add new enemy types without changing existing code.
- The factory may need to handle many product types as the game expands.

#### CONTINUOUS EXECUTION OF GAME LOGIC AND RENDERING
**Problem in Context**

The game requires continuous execution of logic, including updates and rendering. Without a centralized game loop, the system may have handled updates and rendering in an unreliable manner. 

**The Pattern**

The Game Loop Pattern is applied to centralize the execution of game updates and rendering. This ensures that all game components are updated in a synchronized manner within a single loop.

**Implementation**

Views like GameView, MenuView, and HudView implement the View<Interface> interface and render components through render(), and each state manages the controller logic getController().

**Consequences**

- Centralized game execution simplifies synchronization of updates and rendering.
- Easy to manage frame rates and updates.

#### KNOWN CODE SMELLS

The major code smell that was analyzed was in the class Game, it has too many attributes and methods that can complicate the design if we want to increase the number of features in the game.
In second instance, the group has struggled a bit in the Initializer class, specially at the massive class constructor.

### TESTING

#### Overall Test Coverage
- ![](src/main/resources/ImagesForReadme/CoveragePrint.png)
- 
- #### Mutation Testing
- The mutation testing topic unfortunately was the biggest issue for the group. After several tryouts to implement, following the teacher's advice, searching other alternatives, consultating the pitest website, we have ended up not being able to have the mutation testing done, despite all the effort on building a sturdy and complete test code. Afterknowing that we would not make it, the group has decided to reverse the changes in the code in order to keep the code cleaner and more organized.

### SELF-EVALUATION

- Arthur Teixeira: 33.33%
- Divaldo Dias: 33.33%%
- Pedro Gouveia: 33.33%
- All the components of the group have worked with a similar amount of effort. We have organized ourselves in order to attempt all the tasks and there is nothing to complain about the inidividual perfomance of any member. The result of the evaluation should be equal for everyone.
=======
# Descrição

O nosso jogo é inspirado em Space Invaders, mas com uma jogabilidade mais livre. A cada round, novos zumbis aparecem, ficando mais fortes e vindo com mais frequência à medida que o jogo avança. Ao contrário de Space Invaders, o jogador pode se mover livremente pelo mapa, evitando as paredes (Walls) e disparando projéteis para eliminar os zumbis.
Cada zumbi morto pelo projétil aumenta a pontuação, mas se um zumbi tocar o jogador, ele perde uma vida. É um jogo rápido, com ação constante e desafios crescentes a cada round.
E que o Desafio deve ser obter a maior pontuação possível .

## Lista de Features

- **Wall**: As paredes (Walls) são obstáculos no mapa que limitam e obstruem o movimento do jogador mas sem bloquear completamente a ação, permitindo que o jogador se movimente livremente, desde que não bata nas chamadas Walls.
- **Soldier**: O soldado é o player do jogo, controlado pelo jogador. Ele tem a capacidade de se mover pelo mapa e disparar projéteis para eliminar os zumbis. Com ele, é possível sobreviver e alcançar uma pontuação cada vez maior.
- **Zombie**:Os zumbis são os inimigos do jogador. Eles aparecem em maior número à medida que os rounds avançam e se tornam mais fortes conforme o jogo progride, criando um desafio crescente para o jogador.
- **View**: A "view" é responsável por exibir o que está acontecendo no jogo para o jogador. É como a camada gráfica que permite que o usuário veja o cenário, os inimigos, o personagem e os elementos interativos de forma clara e compreensível.
- **Pontuação**: O sistema de pontuação do jogo. Cada zumbi que o jogador elimina aumenta a sua pontuação. Isso funciona como o placar do jogo, incentivando o jogador a buscar sempre uma pontuação mais alta.
- **Vida**: O indicador de saúde. Quando um zumbi atinge o jogador, ele perde uma vida. O número de vidas restantes determina o quanto o jogador ainda pode resistir aos ataques dos inimigos.
- **Nº de Rounds**: O número de rounds é uma mecânica central do jogo. A cada round, a dificuldade aumenta com o surgimento de novos zumbis mais fortes.
- **Track**: Track é o método usado para saber o melhor caminho dos zombis para o player, ajustando-se de acordo com a posição do herói no mapa.
- **Controller**:  O controller administra a lógica do jogo, controlando como as ações do jogador afetam o estado do jogo. Ele é responsável por gerenciar os inputs do jogador, a atualização do estado do jogo e a interação entre os diferentes elementos, como o personagem, os zumbis e o ambiente.
  
## Progresso de Implementação das features
- **Track**: Estamos trabalhando para melhorar o algoritmo de busca pelo melhor caminho dos zombies, permitindo que eles evitem obstáculos como as *Wall* e encontrem o melhor caminho até o player. O objetivo é tornar a movimentação dos zombies mais inteligente e desafiadora, mesmo em ambientes complexos, onde tenhamos linhas e colunas de *Walls* a frente do zumbi.

- **Pontuação**:Já temos a pontuação visível na interface gráfica(GUI) do jogo, mas ainda não tem nenhum função ou metodo associada a mesma. O próximo passo é integrar o sistema de pontuação de forma que ela aumente coénforme o player derrota zombies.
- **Velocidade do Jogo**:Atualmente, os inimigos só se movem quando o player anda, o que limita a dinâmica do jogo. Vamos implementar uma velocidade definida para os inimigos, permitindo que eles se movam independentemente se o player move-se, tornando o jogo mais dinâmico e desafiador.

## Pontos a Serem Implementados
- **Dano**:O cálculo de dano e suas interações com os zombies ainda não foram configurados. Cada projétil que atingir um zombie deve reduzir sua vida até que ele seja derrotado.
- **Projétil**:O sistema de projéteis ainda precisa ser implementado. O jogador deverá ser capaz de disparar projéteis, que se movem cum uma determinada velocidade á uma direção para sempre, e que causem dano aos zombies caso os 2 estejam em colisão.
- **Cooldown**:Para evitar que o jogador "spamme" projéteis de forma desbalanceada, um sistema de cooldown será  devidamente implementado para contrapor a isso. Isso limitará a quantidade de projéteis disparados por vez, não tornando o jogo extremamente fácil 

## Mockups

![Mockup do RUN GAME](mochup%20do%20Run%20Game.jpg)

## Design Patterns Escolhidos

### MVC (Model-View-Controller)

O padrão de design **MVC** (Model-View-Controller) foi escolhido para organizar o código e separar claramente as responsabilidades de cada parte do jogo. Ele facilita a manutenção e evolução do sistema, dado que ainda temos um progresso de implementação de features, e pontos a serem implementados.

#### Como o MVC é aplicado no jogo:

1. **Model**:
   - Representa os dados e a lógica de negócio do jogo. O **Model** é responsável por manipular esses dados, como atualizar a posição dos personagens, verificar colisões, e calcular a pontuação e etc...

2. **View**:
   - A **View** é responsável por exibir a interface do jogo para o jogador, renderização de todos os elementos como o **Soldier**, **Zombie**, e **Wall**, além de exibir a pontuação, a vida e outras informações ao jogador.

3. **Controller**:
   - O **Controller** é o intermediário entre o **Model** e a **View**. Ele recebe as entradas do jogador (como comandos de movimento do player ou disparo par aa criação de projéteis) e manipula o **Model** de acordo com essas ações. Ela também organiza as interações entre a **View** e o **Model**, como atualizar a tela sempre que o estado do jogo muda.

O uso do padrão **MVC** permitiu que o código do jogo ficasse mais organizado, tornando a adição de novas funcionalidades e a manutenção do sistema mais simples.

### Singleton

O padrão **Singleton** é utilizado para garantir que uma classe tenha apenas uma instância e que forneça um ponto de acesso global a essa instância. Esse padrão ajudou-nos porque precisa-mos de um único objeto controlando um recurso compartilhado, mas não em toda a aplicacação, aplicamos em apenas algumas partes.

## Diagrama UML

![UML DIAGRAMA](UmlDoProjeto.png)

## Observação

Ainda está sendo descutido a implementação de outros padrões de Design como:

-**observer**
-**factory**
-**SOLID**
e entres outros para a entrega porsterior!


