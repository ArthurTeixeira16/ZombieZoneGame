package Model;

import com.aor.ZombieZone.Model.Arena;
import com.aor.ZombieZone.Model.Position;
import com.aor.ZombieZone.Model.Wall;
import com.aor.ZombieZone.Model.Zombie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ZombieTester {
    private Arena arena;
    private Zombie zombie;
    private List< Zombie> zombies;
    private List <Wall> walls;
    @BeforeEach
    public void setUp(){
        arena = new Arena(40,30,zombies, walls);
        zombie = new Zombie(10, 15);
    }

    @Test
    public void testInitialZombiePosition(){
        Position InitialPosition = zombie.getPosition();
        Assertions.assertEquals(10, InitialPosition.getX());
        Assertions.assertEquals(15, InitialPosition.getY());
    }
}
