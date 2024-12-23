package com.aor.ZombieZoneTest.Model;

import com.aor.ZombieZone.Model.Position;
import com.aor.ZombieZone.Model.Projectile;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
public class ProjectileTester {
    private Projectile projectile;
    private TextGraphics mockGraphics;

    @BeforeEach
    void setUp() {
        projectile = new Projectile(5, 5, 10, 1);
        mockGraphics = Mockito.mock(TextGraphics.class);
    }
    @Test
    void testInitialPosition(){
        assertEquals(5, projectile.getPosition().getX());
        assertEquals(5, projectile.getPosition().getY());
    }

    @Test
    void testUpdateProjectileWithinRange(){
        projectile.setDirection(new Position(0, -1));
        projectile.updateProjectile(1000);
        assertEquals(5, projectile.getPosition().getX());
        assertEquals(4, projectile.getPosition().getY());
        assertFalse(projectile.isDestroyed());
        }

    @Test
    void testUpdateProjectileOutOfRange(){
        projectile.setDirection(new Position(0, -1));
        for (int i = 0; i < 10; i++) {
            projectile.updateProjectile(1000);
        }
        assertEquals(5, projectile.getPosition().getX());
        assertEquals(-5, projectile.getPosition().getY());
        assertTrue(projectile.isDestroyed());
    }
    @Test
    void testDestroy(){
        projectile.destroy();
        assertTrue(projectile.isDestroyed());
    }
    @Test
    void testMoveMethods(){
        projectile.moveUp();
        assertEquals(5, projectile.getPosition().getX());
        assertEquals(4, projectile.getPosition().getY());

        projectile.moveDown();
        assertEquals(5, projectile.getPosition().getX());
        assertEquals(5, projectile.getPosition().getY());

        projectile.moveLeft();
        assertEquals(4, projectile.getPosition().getX());
        assertEquals(5, projectile.getPosition().getY());

        projectile.moveRight();
        assertEquals(5, projectile.getPosition().getX());
        assertEquals(5, projectile.getPosition().getY());
    }
    @Test
    void testDraw(){
        projectile.draw(mockGraphics);
        Mockito.verify(mockGraphics).setForegroundColor(Mockito.any());
        Mockito.verify(mockGraphics).putString(5, 5, ".");
    }
    @Test
    void testSetDirection(){
        projectile.setDirection(new Position(1, 0));
        assertEquals(new Position(1, 0), projectile.direction);
    }
    @Test
    void testUpdateProjectileNoMovement(){
        projectile.setDirection(new Position(0, 1));
        projectile.updateProjectile(0);
        assertEquals(5, projectile.getPosition().getY());
    }
    @Test
    void testUpdateProjectileLargeDeltaTime(){
        projectile.setDirection(new Position(0, 1));
        projectile.updateProjectile(2000);
        assertEquals(7, projectile.getPosition().getY());
        assertFalse(projectile.isDestroyed());
    }
    @Test
    void testMoveLeft(){
        projectile.setDirection(new Position(-1, 0));
        projectile.move();
        assertEquals(4, projectile.getPosition().getX());
    }
    @Test
    void testMoveRight() {
        projectile.setDirection(new Position(1, 0));
        projectile.move();
        assertEquals(6, projectile.getPosition().getX());
    }
}
