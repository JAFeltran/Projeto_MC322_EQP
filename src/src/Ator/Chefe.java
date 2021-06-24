package src.Ator;

import java.util.Random;

public class Chefe extends Inimigo {
    // Construtor
    public Chefe(int x, int y, int vida, int ataque, int defesa) {
        super(x, y, vida, ataque, defesa);
    }

    // ICombate
    public int causarDano() {
        Random random = new Random();
        int acerto = random.nextInt(20);

        if (acerto < 5) {
            return 0;
        }
        else if (acerto == 19){
            return (int) (1.5 * ataque);
        }

        return ataque;
    }

    public void receberDano(int dano) {
        if (dano > 0) {
            vida -= dano - defesa;
        }
    }
}