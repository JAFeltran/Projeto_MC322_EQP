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
    public Heroi(int x, int y, IMapa mapa, int fase) {
        super(x, y, 'h');

        random = new Random();

        vida = 300;
        vidaMax = 300;
        if (fase > 1) {
            vida += fase * 100;
        }

        this.mapa = mapa;
        inventario = new IItem[4];

        definirItensIniciais(fase);
    }

    // IHeroi
    public void setItemInventario(int posicao, int valor, String nome) {
        inventario[posicao].setValor(valor);
        inventario[posicao].setNome(nome);
    }

    public void setVisualNaPosicao(int x, int y, char tipo, int fase) {
        mapa.getVisual().setAtorVisualNaPosicao(x, y, tipo, fase);
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

    public int getValorItemInventario(int posicao) {
        return inventario[posicao].getValor();
    }

    public void definirItensIniciais(int fase) {
        switch (fase) {
            case 1:
                inventario[0] = new Item(50, 0, "Desarmado");
                inventario[1] = new Item(0, 1, "Pelado");
                inventario[2] = new Item(1, 2, "Nada");
                break;
            case 2:
                inventario[0] = new Item(100, 0, "Machado de Pedra");
                inventario[1] = new Item(50, 1, "Gibao de Peles");
                inventario[2] = new Item(1, 2, "Nada");
                break;
            case 3:
                inventario[0] = new Item(150, 0, "Gladio");
                inventario[1] = new Item(100, 1, "Loriga Segmentada");
                inventario[2] = new Item(1, 2, "Nada");
                break;
            case 4:
                inventario[0] = new Item(225, 0, "Alabarda");
                inventario[1] = new Item(175, 1, "Cota de Malha");
                inventario[2] = new Item(1, 2, "Nada");
                break;
            case 5:
                inventario[0] = new Item(300, 0, "Mosquete");
                inventario[1] = new Item(250, 1, "Peitoral de Aco");
                inventario[2] = new Item(2, 2, "Luneta");
                break;
            case 6:
                inventario[0] = new Item(400, 0, "Rifle");
                inventario[1] = new Item(350, 1, "Mascara de Gas");
                inventario[2] = new Item(3, 2, "Binoculo");
                break;
        }

        inventario[3] = new Item(0, 3, "Sem Chave");
    }

    public void curar(int porcento) {
        int cura = (porcento / 100) * vida;

        if (vida + cura > vidaMax) {
            vida = vidaMax;
        }
        else {
            vida += cura;
        }
    }

    public boolean pegouChave(int fase) {
        if (inventario[3].getValor() == fase) {
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

    public void removerInimigo(int x, int y) {
        mapa.setAtorNaPosicao(new Ator(x, y, '_'), x, y);
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
        int acerto = random.nextInt(20);

        if (acerto == 19) {
            JOptionPane.showMessageDialog(new JFrame(), "O Herói acertou um ataque crítico no inimigo!", "Ataque crítico!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("assets/PainelBatalha/espada.png"));
            return (int) (1.5 * inventario[0].getValor());
        }
        else if (acerto > 4) {
            return inventario[0].getValor();
        }

        return 0;
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