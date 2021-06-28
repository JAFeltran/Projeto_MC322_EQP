package src.Ator;

import java.util.Random;

public class Chefe extends Inimigo {
    // Construtor
    public Chefe(int x, int y, int vida, int ataque, int defesa) {
        super(x, y, vida, ataque, defesa);

        setTipo('c');
    }

    // ICombate
    public int causarDano() {
        Random random = new Random();
        int dano = ataque;

        if (random.nextInt(20) == 19) {
            dano = (int) (1.5 * ataque);
        }

        return dano;
    }
}