package com.aor.hero.element;

public class Position {
    protected int x;
    protected int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false; // Verificamos se uma classe for NULL ou se elas não forem do mesmo tipo de classe
        Position p = (Position) o; // Fazemos o cast(copy position para uma variavel)
        return x == p.x && y == p.y; // Compara os valores de x e y
    }
    public int hashCode() {
        return 31 * x + y; // Garante consistência com equals(supostamente)
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean equals(Position o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        Position p = (Position) o;
        return x == p.getX() && y == p.getY();
    }
}