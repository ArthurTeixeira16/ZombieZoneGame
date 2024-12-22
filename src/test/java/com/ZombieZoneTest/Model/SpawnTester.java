package com.ZombieZoneTest.Model;

import com.aor.ZombieZone.Model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;

public class SpawnTester {
    private Spawn spawn;
    private Soldier soldier;
    private List<Enemy> enemies;

    @BeforeEach
    public void setUp() {
        soldier = new Soldier(1,1);
        List<Wall> walls = List.of(
                new Wall(0, 0 ),
                new Wall(2, 2),
                new Wall(2, 0)
        );
        spawn = spy(new Spawn( 5,5,soldier, walls));
    }
    @Test
    public void SpawnZombiesTest(){
        //teste sobre o limite de zombies na tela
        //ocuparemos todos os lugares e veremos se o
        // SpawnZombies retorna o numero de zombies nos
        // lugares disponiveis
        enemies = spawn.SpawnZombies(100);
        assertEquals(21 ,enemies.size());
    }
    @Test
    public void SpawnZombiesSpeedTest() throws IllegalAccessException, NoSuchFieldException {
        Field zombiesNormalIndexField = Spawn.class.getDeclaredField("MINZOMBIES");
        zombiesNormalIndexField.setAccessible(true);
        zombiesNormalIndexField.set(spawn, 0);
        //Field explicado pelo chatGPT para acessar/mudar
        // valores privados de um spy
        Field zombiesSpeedPerRoundField = Spawn.class.getDeclaredField("speedzombie_percentage");
        zombiesSpeedPerRoundField.setAccessible(true);
        zombiesSpeedPerRoundField.set(spawn, 100);
        enemies = spawn.SpawnZombies(2);


        for(Enemy enemy : enemies){
            assertTrue(enemy instanceof ZombieSpeed);
        }
    }
    @Test
    public void SpawnZombiesHeavyTest() throws IllegalAccessException, NoSuchFieldException{
        Field zombiesNormalIndexField = Spawn.class.getDeclaredField("MINZOMBIES");
        zombiesNormalIndexField.setAccessible(true);
        zombiesNormalIndexField.set(spawn, 0);
        //Field explicado pelo chatGPT para acessar/mudar
        // valores privados de um spy
        Field zombiesSpeedPerRoundField = Spawn.class.getDeclaredField("speedzombie_percentage");
        zombiesSpeedPerRoundField.setAccessible(true);
        zombiesSpeedPerRoundField.set(spawn, 0);

        Field zombiesHeavyPerRoundField = Spawn.class.getDeclaredField("heavyzombie_percentage");
        zombiesHeavyPerRoundField.setAccessible(true);
        zombiesHeavyPerRoundField.set(spawn, 100);
        enemies = spawn.SpawnZombies(2);
        for(Enemy enemy : enemies){
            assertTrue(enemy instanceof ZombieHeavy );
        }
    }
    @Test
    public void SpawnZombiesNormalTest() throws IllegalAccessException, NoSuchFieldException {
        Field zombiesNormalIndexField = Spawn.class.getDeclaredField("MINZOMBIES");
        zombiesNormalIndexField.setAccessible(true);
        zombiesNormalIndexField.set(spawn, 5);
        //Field explicado pelo chatGPT para acessar/mudar
        // valores privados de um spy
        Field zombiesSpeedPerRoundField = Spawn.class.getDeclaredField("speedzombie_percentage");
        zombiesSpeedPerRoundField.setAccessible(true);
        zombiesSpeedPerRoundField.set(spawn, 0);

        Field zombiesHeavyPerRoundField = Spawn.class.getDeclaredField("heavyzombie_percentage");
        zombiesHeavyPerRoundField.setAccessible(true);
        zombiesHeavyPerRoundField.set(spawn, 0);
        enemies = spawn.SpawnZombies(5);
        for(Enemy enemy : enemies){
            assertTrue(enemy instanceof ZombieNormal);
        }
    }

}
