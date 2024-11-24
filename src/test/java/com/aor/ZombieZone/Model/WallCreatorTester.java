package com.aor.ZombieZone.Model;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WallCreatorTester {

    @Test
    public void testDeDimensãoPequena() {
        // Caso com dimensões pequenas: 2x2
        // é interessante porque neste caso não temos walls internas e
        // queremos testar se tem apenas 1 wall em cada possição
        int width = 2, height = 2;
        List<Wall> walls = WallCreator.createWalls(width, height);

        // Só devem existir as bordas (1 parede contínua)
        assertEquals(4, walls.size(), "Número de paredes para dimensões 2x2 deve ser 4");
        System.out.println(walls);
        assertTrue(walls.contains(new Wall(0, 0)));
        assertTrue(walls.contains(new Wall(0, 1)));
        assertTrue(walls.contains(new Wall(1, 0)));
        assertTrue(walls.contains(new Wall(1, 1)));
    }
    @Test
    public void testDeDimensãogrande() {
        // Caso com dimensões grandes: 40x40
        // Caso Interessante, porque esse teremos um grande numero de paredes internas
        int width = 40, height = 40;

        List<Wall> walls = WallCreator.createWalls(width, height);

        // Deve conter bordas (156) + paredes internas (calculadas)
        int walls_internas_expectadas = WallCreator.wallsinternas(width, height);
        int walls_total = 2 * width + 2 * height-4  + walls_internas_expectadas;
        assertEquals(walls_total, walls.size(), "Número total de paredes está errado para dimensões 40x40");
    }

    @Test
    public void testeDeWallsInternas() {
        // Valida a lógica de cálculo de paredes internas
        assertEquals(0, WallCreator.wallsinternas(3, 3), "Dimensões 3x3 devem gerar 1 parede interna");
        assertEquals(6, WallCreator.wallsinternas(10, 10), "Dimensões 10x10 devem gerar pelo menos 10 paredes internas");
        assertEquals(26, WallCreator.wallsinternas(20, 20), "Dimensões 20x20 devem gerar 26 paredes internas");
    }
}