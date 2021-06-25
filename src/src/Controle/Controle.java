package src.Controle;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JFrame;


import src.Ator.*;
import src.Painel.IPainelPrincipal;
import src.Painel.PainelBatalha;

public class Controle implements IControle {
    // Atributos
    private IHeroi heroi;
    private IPainelPrincipal painel;
    private int fase;

    // Construtor
    public Controle() {
        fase = 1;
    }

    // IControle
    public void setHeroi(IHeroi heroi) {
        this.heroi = heroi;
    }
    
    public void setFase(int fase) {
        this.fase = fase;
    }
    
    public boolean getVivo() {
        return heroi.getVivo();
    }

    public void connectPainel(IPainelPrincipal painel) {
        this.painel = painel;
    }

    private void mover(int x, int y) {
        int xAtual = heroi.getX();
        int yAtual = heroi.getY();
        heroi.mover(x, y);
        // TODO atualizar quais quadrados são visíveis e quais não são mais
    }

    public void jogada(int x, int y) {
        char movimento = heroi.verificarMovimento(x, y);

        switch (movimento) {
            case 'i':
                ICombate inimigo = (ICombate)heroi.getInimigoNaPosicao(x, y);
                new PainelBatalha(heroi, inimigo, fase);

                if (heroi.getVivo()) {
                    // heroi.pegarItem(); ??????????????????????????????????????
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
