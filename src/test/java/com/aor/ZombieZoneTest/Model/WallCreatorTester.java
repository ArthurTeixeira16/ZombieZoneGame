package com.aor.ZombieZoneTest.Model;

import com.aor.ZombieZone.Model.Wall;
import com.aor.ZombieZone.Model.WallCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WallCreatorTester {
    private WallCreator wallCreator;

    @BeforeEach
    public void setUp() {
        wallCreator = new WallCreator();
    }
    @Test
    public void testCreateWall() {
        List<Wall> walls = wallCreator.createWalls(1,1);
        assertEquals(1, walls.size());
    }

    @Test
    public void testCreateWall_withoutInternWalls() {
        List<Wall> walls = wallCreator.createWalls(3,2);
        assertEquals(walls.size() , 6);
    }

    @Test
    public void testCreateWall_withInternWalls() {
        List<Wall> walls = wallCreator.createWalls(100,100);
        Integer wallsexpected = 100*2 + 100*2 -4;
        wallsexpected += wallCreator.wallsinternas(100,100);
        assertEquals(wallsexpected, walls.size());
    }
}
