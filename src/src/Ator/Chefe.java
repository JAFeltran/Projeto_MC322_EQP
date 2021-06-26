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
        int acerto = random.nextInt(4);

        if (acerto == 0) {
            return 0;
        }
        else {
            return ataque;
        }
    }
}