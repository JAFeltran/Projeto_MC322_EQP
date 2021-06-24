package src.Ator;

import java.util.Random;

public class Inimigo extends Ator implements ICombate {
    protected int vida, ataque, defesa;

    // Construtor
    public Inimigo(int x, int y, int vida, int ataque, int defesa) {
        super(x, y, 'i');
        this.vida = vida;
        this.ataque = ataque;
        this.defesa = defesa;
    }

    // ICombate
    public boolean getVivo() {
        if (vida > 0) {
            return true;
        }

        return false;
    }

    public int causarDano() {
        Random random = new Random();
        int acerto = random.nextInt(20);

        if (acerto < 10) {
            return 0;
        }
        else if (acerto == 19) {
            return (int) (1.5 * ataque);
        }

        return ataque;
    }

    public void receberDano(int dano) {
        if (dano > 0) {
            ;
            vida -= dano - defesa;
        }
    }
}