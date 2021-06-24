package src.Ator;

import src.Mapa.*;
import src.Item.*;

public class Heroi extends Ator implements IHeroi {
    // Atributos
    private IItem inventario[];
    // inventario tem 5 itens, cada um representando um dos atributos (na seguinte ordem):
    // ataque, defesa, visao, chave.
    private int vida;
    private IMapa mapa;

    // Construtor
    public Heroi(int x, int y, IMapa mapa) {
        super(x, y, 'h');
        vida = 300;
        this.mapa = mapa;
        inventario = new IItem[4];

        inventario[0] = new Item(100, "Desarmado");
        inventario[1] = new Item(100, "Pelado");
        inventario[2] = new Item(0, "Nada");
        inventario[3] = null;
    }

    // IHeroi
    public void setItemInventario(int posicao, int valor, String nome) {
        inventario[posicao].setValor(valor);
        inventario[posicao].setNome(nome);
    }

    public void removerInimigo(int x, int y) {
        mapa.setAtorNaPosicao(null, x, y);
    }

    public boolean getVivo() {
        if (vida > 0) {
            return true;
        }

        return false;
    }

    public IAtor getInimigoNaPosicao(int x, int y) {
        return mapa.getAtorNaPosicao(x, y);
    }

    public char verificarMovimento(int x, int y) {
        if (mapa.getAtorNaPosicao(x, y) == null) {
            return 'v';
        }
        else {
            return mapa.getAtorNaPosicao(x, y).getTipo();
        }
    }

    public void mover(int x, int y) {
        super.setX(x);
        super.setY(y);
    }

    public int causarDano() {
        return inventario[0].getValor();
    }

    public void receberDano(int dano) {
        if (dano > 0) {
            vida -= dano - inventario[1].getValor();
        }
    }
}