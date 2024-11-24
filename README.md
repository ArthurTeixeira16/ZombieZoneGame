# Descrição

O nosso jogo é inspirado em Space Invaders, mas com uma jogabilidade mais livre. A cada round, novos zumbis aparecem, ficando mais fortes e vindo com mais frequência à medida que o jogo avança. Ao contrário de Space Invaders, o jogador pode se mover livremente pelo mapa, evitando as paredes (Walls) e disparando projéteis para eliminar os zumbis.
Cada zombi morto pelo projétil aumenta a pontuação, mas se um zumbi tocar o jogador, ele perde uma vida. É um jogo rápido, com ação constante e desafios crescentes a cada round.
E que o Desafio deve ser obter a maior pontuação possível .

## Lista de Features

- **Wall**: As paredes (Walls) são obstáculos no mapa que limitam e obstruem o movimento do jogador mas sem bloquear completamente a ação, permitindo que o jogador se movimente livremente, desde que não bata nas chamadas Walls.
- **Soldier**: O soldado é o player do jogo, controlado pelo jogador. Ele tem a capacidade de se mover pelo mapa e disparar projéteis para eliminar os zumbis.Com ele, é possível sobreviver e alcançar uma pontuação cada vez maior.
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

