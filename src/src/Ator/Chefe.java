package src.Ator;

public class Chefe extends Inimigo{
    // Construtor
    public Chefe(int x, int y, int vida, int ataque, int defesa) {
        super(x, y, vida, ataque, defesa);
    }

    // ICombate
    public int causarDano() {
        return ataque;
    }

    public void receberDano(int dano) {
        if (dano > 0) {
            vida -= dano - defesa;
        }
    }
}