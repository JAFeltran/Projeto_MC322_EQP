package src.Controle;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

import src.Ator.*;
import src.Item.IItem;
import src.Item.Item;
import src.Painel.IPainelPrincipal;
import src.Painel.PainelBatalha;

public class Controle implements IControle {
    // Atributos
    private IHeroi heroi;
    private ICombate inimigo;
    private IPainelPrincipal painel;
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
                itens.add(new Item(50, 1, "Gibão de Peles"));
                chave = new Item(1, 3, "Chave");
                break;
            case 2:
                itens.add(new Item(100, 0, "Gládio"));
                itens.add(new Item(100, 1, "Loriga Segmentada"));
                itens.add(new Item(1, 2, "Tocha"));
                chave = new Item(2, 3, "Chave");
                break;
                case 3:
                itens.add(new Item(175, 0, "Alabarda"));
                itens.add(new Item(175, 1, "Cota de Malha"));
                chave = new Item(3, 3, "Chave");
                break;
                case 4:
                itens.add(new Item(250, 0, "Mosquete"));
                itens.add(new Item(250, 1, "Peitoral de Aço"));
                itens.add(new Item(2, 2, "Luneta"));
                chave = new Item(4, 3, "Chave");
                break;
                case 5:
                itens.add(new Item(350, 0, "Rifle"));
                itens.add(new Item(350, 1, "Máscara de Gás"));
                itens.add(new Item(3, 2, "Binóculo"));
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

    public void connectPainel(IPainelPrincipal painel) {
        this.painel = painel;
    }

    // Atualiza a posição do Herói
    private void mover(int x, int y) {
        int xAtual = heroi.getX();
        int yAtual = heroi.getY();

        heroi.mover(x, y, fase);

        // TODO atualizar quais quadrados são visíveis e quais não são mais
    }

    // Fornece um item ao Herói como recompensa por derrotar um inimigo
    private void pegarItem() {
        if (((IAtor)inimigo).getTipo() == 'i' && itens.size() > 0) {
            IItem item = itens.get(random.nextInt(itens.size()));
            heroi.setItemInventario(item.getPosicao(), item.getValor(), item.getNome());
            JOptionPane.showMessageDialog(new JFrame(), "O Inimigo derrubou um item:\n" + item.getNome(), "Novo item!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("assets/Itens/Fase" + fase + "/" + item.getNome() + ".png"));
        } else if (((IAtor)inimigo).getTipo() == 'c' && chave != null) {
            heroi.setItemInventario(chave.getPosicao(), chave.getValor(), chave.getNome());
            JOptionPane.showMessageDialog(new JFrame(), "O poderoso chefe derrubou uma chave!\nEncontre a fechadura para vencer esta fase.", "Novo item!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("assets/Itens/Fase" + fase + "/Chave.png"));
            chave = null;
        }
    }

    // Responde ao evento do jogador de clicar em um botão do mapa
    public void jogada(int x, int y) {
        char movimento = heroi.verificarMovimento(x, y);

        switch (movimento) {
            case 'c':
                inimigo = (ICombate)heroi.getInimigoNaPosicao(x, y);
                new PainelBatalha(heroi, inimigo, fase);

                if (heroi.getVivo()) {
                    pegarItem();
                    mover(x, y);

                    if (random.nextInt(4) == 3) {
                        heroi.curar();
                    }
                }

                break;
            case 'i':
                inimigo = (ICombate)heroi.getInimigoNaPosicao(x, y);
                new PainelBatalha(heroi, inimigo, fase);

                if (heroi.getVivo()) {
                    pegarItem();
                    mover(x, y);

                    if (random.nextInt(4) == 3) {
                        heroi.curar();
                    }
                }

                break;
            case 's':
                if (heroi.pegouChave()) {
                    acabou = true;
                    if (fase < 6) {
                        if (JOptionPane.showConfirmDialog(new JFrame(), "O Herói conseguiu derrotar o mal nessa era.\nVocê deseja continuar nessa aventura e avançar para a próxima fase?", "Sucesso!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon("assets/Controle/chefe.png")) != 0) {
                            System.exit(0);
                        }
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "O Herói protegeu a humanidade! Parabéns!", "Parabéns!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("assets/Controle/chefe.png"));
                        System.exit(0);
                    }

                    break;
                }
            case 'o':
                JOptionPane.showMessageDialog(new JFrame(), "O Herói não pode se mover para lá!", "Aviso", JOptionPane.WARNING_MESSAGE, new ImageIcon("assets/Controle/atencao.png"));
                break;
            case 't':
                JOptionPane.showMessageDialog(new JFrame(), "O Herói sente que um grande mal se aproxima...", "Aviso", JOptionPane.WARNING_MESSAGE, new ImageIcon("assets/Controle/chefe.png"));
                break;
            case '_':
                mover(x, y);
                break;
        }
    } 
}
