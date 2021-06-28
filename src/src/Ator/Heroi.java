package src.Ator;

import src.Mapa.*;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import src.Item.*;

public class Heroi extends Ator implements IHeroi {
    // Atributos
    private IItem inventario[];
    // inventario tem 4 itens, cada um representando um dos atributos (na seguinte ordem):
    // ataque, defesa, visao, chave.
    private int vida;
    private int vidaMax;
    private IMapa mapa;
    private Random random;

    // Construtor
    public Heroi(int x, int y, IMapa mapa) {
        super(x, y, 'h');
        vida = 300;
        vidaMax = 300;
        this.mapa = mapa;
        inventario = new IItem[4];
        random = new Random();

        inventario[0] = new Item(50, 0, "Desarmado");
        inventario[1] = new Item(0, 1, "Pelado");
        inventario[2] = new Item(1, 2, "Nada");
        inventario[3] = null;
    }

    // IHeroi
    public void setItemInventario(int posicao, int valor, String nome) {
        inventario[posicao].setValor(valor);
        inventario[posicao].setNome(nome);
    }

    public void setVisualNaPosicao(int x, int y, char tipo, int fase) {
        mapa.getVisual().setAtorVisualNaPosicao(x, y, tipo, fase);
    }

    public void removerInimigo(int x, int y) {
        mapa.setAtorNaPosicao(new Ator(x, y, '_'), x, y);
    }

    public int getVida() {
        return vida;
    }

    public int getVidaMax() {
        return vidaMax;
    }

    public String getVisualNaPosicao(int x, int y) {
        return mapa.getVisualNaPosicao(x, y);
    }

    public void curar() {
        if (vida + (vidaMax / 2) > vidaMax) {
            vida = vidaMax;
        } else {
            vida += (vidaMax / 2);
        }
    }

    public boolean pegouChave() {
        if (inventario[3] != null) {
            return true;
        }
        
        return false;
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
        return mapa.getAtorNaPosicao(x, y).getTipo();
    }

    public void mover(int x, int y, int fase) {
        mapa.getVisual().setAtorVisualNaPosicao(super.getX(), super.getY(), mapa.getAtorNaPosicao(super.getX(), super.getY()).getTipo(), fase);

        super.setX(x);
        super.setY(y);

        mapa.ajustarVisibilidade(inventario[2].getValor(), x, y, fase);
    }

    public int causarDano() {
        if (random.nextInt(20) == 19) {
            JOptionPane.showMessageDialog(new JFrame(), "O Herói acertou um ataque crítico no inimigo!", "Ataque crítico!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("assets/PainelBatalha/espada.png"));
            return (int)(1.5 * inventario[0].getValor());
        }

        return inventario[0].getValor();
    }

    public void receberDano(int dano) {
        if (dano > inventario[1].getValor()) {
            vida -= (dano - inventario[1].getValor());
        }
        else {
            vida -= 10;
        }
    }
}