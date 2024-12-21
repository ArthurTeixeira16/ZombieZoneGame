import com.aor.ZombieZone.Model.Enemy;
import com.aor.ZombieZone.Model.Soldier;
import com.aor.ZombieZone.Model.Spawn;
import com.aor.ZombieZone.Model.Wall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpawnTester {
    private Spawn spawn;
    private Soldier soldier;
    private List<Wall> walls;

    @BeforeEach
    public void setUp() {
        soldier = new Soldier(1,1);
        walls = List.of(
                new Wall(0, 0 ),
                new Wall( 2, 2 ),
                new Wall(3,3),
                new Wall(4,4)
        );
        spawn = new Spawn(5,5,soldier,walls);
    }
    @Test
    public void SpawnZombiesTest(){
        List<Enemy> enemies = new ArrayList<>();
        //teste sobre o limite de zombies na tela
        enemies = spawn.SpawnZombies(100);
        assertEquals(enemies.size(),21 );
    }
}
