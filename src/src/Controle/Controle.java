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
    private IPainelPrincipal painel;
    private int fase;
    private ArrayList<IItem> itens;
    private Random random;

    // Construtor
    public Controle() {
        random = new Random();
        itens = new ArrayList<IItem>();
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
                itens.add(new Item(50, 1, "Machado de Pedra"));
                itens.add(new Item(50, 2, "Gibão de Peles"));
                break;
            case 2:
                itens.add(new Item(100, 1, "Gládio"));
                itens.add(new Item(100, 2, "Loriga Segmentada"));
                itens.add(new Item(1, 3, "Tocha"));
                break;
            case 3:
                itens.add(new Item(175, 1, "Alabarda"));
                itens.add(new Item(175, 2, "Cota de Malha"));
                break;
            case 4:
                itens.add(new Item(250, 1, "Mosquete"));
                itens.add(new Item(250, 2, "Peitoral de Aço"));
                itens.add(new Item(2, 3, "Luneta"));
                break;
            case 5:
                itens.add(new Item(350, 1, "Rifle"));
                itens.add(new Item(350, 2, "Máscara de Gás"));
                itens.add(new Item(3, 3, "Binóculo"));
                break;
            case 6:
                itens.add(new Item(500, 1, "Arma Laser"));
                itens.add(new Item(450, 2, "Super-Armadura"));
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
        heroi.mover(x, y);
        // TODO atualizar quais quadrados são visíveis e quais não são mais
    }

    // Cria um item e fornece-o ao Herói
    private void pegarItem() {
        if (itens.size() > 0) {
            IItem item = itens.get(random.nextInt(itens.size()));
            heroi.setItemInventario(item.getPosicao(), item.getValor(), item.getNome());
            JOptionPane.showMessageDialog(new JFrame(), "O Inimigo derrubou um item:\n" + item.getNome(), "Novo item!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("assets/Itens/Fase" + fase + "/" + item.getNome() + ".png"));
        }
    }

    public void jogada(int x, int y) {
        char movimento = heroi.verificarMovimento(x, y);

        switch (movimento) {
            case 'i':
                ICombate inimigo = (ICombate)heroi.getInimigoNaPosicao(x, y);
                new PainelBatalha(heroi, inimigo, fase);

                if (heroi.getVivo()) {
                    pegarItem();
                    mover(x, y);
                }

                break;
            case 'o':
                JOptionPane.showMessageDialog(new JFrame(), "O Herói não pode se mover para lá!", "Aviso", JOptionPane.WARNING_MESSAGE, new ImageIcon("assets/Controle/atencao.png"));
                break;
            case 't':
                JOptionPane.showMessageDialog(new JFrame(), "O Herói sente que um grande mal se aproxima...", "Aviso", JOptionPane.WARNING_MESSAGE, new ImageIcon("assets/Controle/chefe.png"));
                break;
            case 'v':
                mover(x, y);
                break;
        }
    } 
}
