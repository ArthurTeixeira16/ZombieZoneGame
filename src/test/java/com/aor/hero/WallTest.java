package com.aor.hero;

import com.aor.hero.element.Wall;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class WallTest {

    private Wall wall;
    private int width = 40;
    private int height = 40;

       // Criação do "BeforeEach" para os teste
    void setUp1() {
        wall = new Wall(10, 51);
    }
    void setUp2() {
        wall = new Wall(-1, 5);
    }
    void setUp3() {
        wall = new Wall(-5, -5);
    }
    void setUp4() {
        wall = new Wall(10, 5);
    }
    @Test    // teste simples de ver se a posição é bem atribuída
    void testWallPosition1() {
        setUp1();
        assertTrue(wall.getPosition().getX() >= 0 && wall.getPosition().getX() < width,
                "Wall X position is out of bounds");
        assertFalse(wall.getPosition().getY() >= 0 && wall.getPosition().getY() < height,
                "Wall Y position is out of bounds");

    }
    @Test
    void testWallPosition2(){
        setUp2();
        assertFalse(wall.getPosition().getX() >= 0 && wall.getPosition().getX() < width,
                "Wall X position is out of bounds");
        assertTrue(wall.getPosition().getY() >= 0 && wall.getPosition().getY() < height,
                "Wall Y position is out of bounds");

    }
    @Test
    void testWallPosition3(){
        setUp3();
        assertFalse(wall.getPosition().getX() >= 0 && wall.getPosition().getX() < width,
                "Wall X position is out of bounds");
        assertFalse(wall.getPosition().getY() >= 0 && wall.getPosition().getY() < height,
                "Wall Y position is out of bounds");

    }

    @Test  //
    void testDrawMethod() {
        setUp4();
        // Criamos um mock do TextGraphics seguir com o teste
        TextGraphics graphics = Mockito.mock(TextGraphics.class);

        // Desenhamos o Wall
        wall.draw(graphics);

        /* Verificamos se o método
           @setForegroundColor ( função que usamos para definir a cor do Wall no Wall.java)
           foi chamado com a cor correta   */
        Mockito.verify(graphics).setForegroundColor(Mockito.eq(TextColor.Factory.fromString("#38291A")));

        /* Verificamos se o método
           @putString ( função que usamos para definir o Carácter do Wall no Wall.java)
           foi chamado com o caracter correto   */
        Mockito.verify(graphics).putString(Mockito.eq(new TerminalPosition(10, 5)), Mockito.eq("█"));
    }
}
