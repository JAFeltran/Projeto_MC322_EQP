package src.Ator;

public class Ator implements IAtor {
    // Atributos
    private int x, y;
    private char tipo;
    private boolean ehVazio;
    private boolean ehSaida;

    // Construtor
    public Ator(int x, int y, char tipo, boolean ehVazio, boolean ehSaida) {
        this.x = x;
        this.y = y;
        this.tipo = tipo;
        this.ehVazio = ehVazio;
        this.ehSaida = ehSaida;
    }

    // IAtor
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getTipo() {
        return tipo;
    }
}