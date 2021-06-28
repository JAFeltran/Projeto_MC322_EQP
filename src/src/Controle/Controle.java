package src.Controle;

import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

import src.Ator.*;
import src.Item.IItem;
import src.Item.Item;
import src.Painel.*;

public class Controle implements IControle {
    // Atributos
    private IHeroi heroi;
    private ICombate inimigo;
    private int fase;
    private ArrayList<IItem> itens;
    private IItem chave;
    private Random random;
    private boolean acabou;

    // Construtor
    public Controle() {
        random = new Random();
        itens = new ArrayList<IItem>();
        acabou = false;
        heroi = null;
        setFase(1);
    }

    // IControle
    public void setHeroi(IHeroi heroi) {
        this.heroi = heroi;
    }

    public void setFase(int fase) {
        this.fase = fase;

        switch (fase) {
            case 1:
                itens.add(new Item(50, 0, "Machado de Pedra"));
                itens.add(new Item(50, 1, "Gibao de Peles"));
                chave = new Item(1, 3, "Chave");
                break;
            case 2:
                itens.add(new Item(100, 0, "Gladio"));
                itens.add(new Item(100, 1, "Loriga Segmentada"));
                //itens.add(new Item(1, 2, "Tocha"));
                chave = new Item(2, 3, "Chave");
                break;
            case 3:
                itens.add(new Item(175, 0, "Alabarda"));
                itens.add(new Item(175, 1, "Cota de Malha"));
                chave = new Item(3, 3, "Chave");
                break;
            case 4:
                itens.add(new Item(250, 0, "Mosquete"));
                itens.add(new Item(250, 1, "Peitoral de Aco"));
                itens.add(new Item(2, 2, "Luneta"));
                chave = new Item(4, 3, "Chave");
                break;
            case 5:
                itens.add(new Item(350, 0, "Rifle"));
                itens.add(new Item(350, 1, "Mascara de Gas"));
                itens.add(new Item(3, 2, "Binoculo"));
                chave = new Item(5, 3, "Chave");
                break;
            case 6:
                itens.add(new Item(500, 0, "Arma Laser"));
                itens.add(new Item(450, 1, "Super-Armadura"));
                chave = new Item(6, 3, "Chave");
                break;
        }
    }

    public boolean getVivo() {
        return heroi.getVivo();
    }

    public boolean getAcabou() {
        return acabou;
    }

    public IHeroi getHeroi() {
        return heroi;
    }

    public String getVisualNaPosicao(int x, int y) {
        return heroi.getVisualNaPosicao(x, y);
    }

    // Atualiza a posição do Herói
    private void mover(int x, int y) {
        heroi.mover(x, y, fase);
    }

    // Fornece um item ao Herói como recompensa por derrotar um inimigo
    private void pegarItem() {
        if (((IAtor) inimigo).getTipo() == 'i' && itens.size() > 0) {
            IItem item = itens.get(random.nextInt(itens.size()));
            heroi.setItemInventario(item.getPosicao(), item.getValor(), item.getNome());
            JOptionPane.showMessageDialog(new JFrame(), "O Inimigo derrubou um item:\n" + item.getNome(), "Novo item!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("assets/Itens/Fase" + fase + "/" + item.getNome() + ".png"));
        }
        else if (((IAtor) inimigo).getTipo() == 'c') {
            heroi.setItemInventario(chave.getPosicao(), chave.getValor(), chave.getNome());
            JOptionPane.showMessageDialog(new JFrame(), "O poderoso chefe derrubou uma chave!\nEncontre a fechadura para vencer esta fase.", "Novo item!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("assets/Itens/Fase" + fase + "/Chave.png"));
        }
    }

    public void jogada(int x, int y) {
        char movimento = heroi.verificarMovimento(x, y);

        if (Math.abs(x - heroi.getX()) <= 1 && Math.abs(y - heroi.getY()) <= 1) {
            switch (movimento) {
                case 'c':
                case 'i':
                    inimigo = (ICombate) heroi.getInimigoNaPosicao(x, y);
                    IPainelBatalha batalha = new PainelBatalha(heroi, inimigo, fase);

                    while (!batalha.getAcabou()) {
                        try {
                            Thread.currentThread().wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            System.exit(1);
                        }
                    }

                    if (heroi.getVivo()) {
                        pegarItem();

                        heroi.removerInimigo(x, y);
                        mover(x, y);

                        if (random.nextInt(4) == 3) {
                            heroi.curar();
                        }
                    }

                    break;
                case 's':
                    if (heroi.pegouChave()) {
                        if (fase < 6) {
                            if (JOptionPane.showConfirmDialog(new JFrame(), "O Herói conseguiu derrotar o mal nessa era.\nVocê deseja continuar nessa aventura e avançar para a próxima fase?", "Sucesso!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon("assets/Controle/chefe.png")) != 0) {
                                System.exit(0);
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(new JFrame(), "O Herói cumpriu sua missão e protegeu a humanidade! Parabéns!", "Parabéns!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("assets/Controle/chefe.png"));
                            System.exit(0);
                        }

                        acabou = true;
                    }
                    else {
                        JOptionPane.showMessageDialog(new JFrame(), "Encontre a chave para prosseguir!", "Aviso", JOptionPane.WARNING_MESSAGE, new ImageIcon("assets/Controle/atencao.png"));
                        heroi.setVisualNaPosicao(x, y, 's', fase);
                    }

                    break;
                case 'o':
                    JOptionPane.showMessageDialog(new JFrame(), "O Herói não pode se mover para lá!", "Aviso", JOptionPane.WARNING_MESSAGE, new ImageIcon("assets/Controle/atencao.png"));
                    heroi.setVisualNaPosicao(x, y, 'o', fase);
                    break;
                case 't':
                    if (!heroi.pegouChave()) {
                        JOptionPane.showMessageDialog(new JFrame(), "O Herói sente que um grande mal se aproxima...", "Aviso", JOptionPane.WARNING_MESSAGE, new ImageIcon("assets/Controle/chefe.png"));
                    }
                    mover(x, y);
                    break;
                case '_':
                    mover(x, y);
                    break;
            }
        }
        else {
            JOptionPane.showMessageDialog(new JFrame(), "O Herói não pode se mover para lá!", "Aviso", JOptionPane.WARNING_MESSAGE, new ImageIcon("assets/Controle/atencao.png"));
        }
    }
}
