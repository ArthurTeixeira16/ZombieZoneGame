import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Spawn { //classe pra spawnar os zumbis separado da arena, tentei jogar
    private int height;
    private int width;
    private Random random;
    private Soldier soldier;
    public Spawn(int width,int height){
        this.height = height;
        this.width = width;
        this.random = new Random();
    }
    public List<Zombie> SpawnZombies(){//não sei pq mas os zumbis ainda não tão respeitando a caceta das boudaries
        List<Zombie> zombies = new ArrayList<>();
        List<Position> occupied = new ArrayList<>();//posições já ocupadas
        occupied.add(new Position(width/2,height/2));//adicionei aqui a posição do soldier(teremos que fazer simultaneo ao jogo nos rounds)
        int i = 0;
        while(i < 4) {//Loop para quantidade de zumbis que quero spawnar (depois alteraremos quando fizermos os rounds)
            int num1 = random.nextInt(3,width);
            int num2 = random.nextInt(3,height);
            Position current_position = new Position(num1,num2);//as coordenadas a serem testadas
            for(Position pos:occupied){//passei um vetor ns lista para testar se o módulo da subtração de cada coordenada é menor que 3
                while((Math.abs(current_position.getX() - pos.getX()) < 3 && Math.abs(current_position.getY() - pos.getY()) < 3)){
                    num1= random.nextInt(3,width-5);//se for menor escolhe outro numero aleatoriamente
                    num2 = random.nextInt(3,height-5);
                    current_position = new Position(num1,num2);
                }
            }
            Zombie zombie = new Zombie(num1,num2);//se não foda-se, ele só adiciona um novo zumbi e a posição em occupied
            zombies.add(zombie);
            occupied.add(current_position);
            i++;
        }
        return zombies;
    }
}
