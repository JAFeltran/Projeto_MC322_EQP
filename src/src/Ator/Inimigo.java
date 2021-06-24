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
        int acerto = random.nextInt(2);
        return (acerto * ataque);
    }

    public void receberDano(int dano) {
        if (dano > 0) {
            ;
            vida -= dano - defesa;
        }
    }
}