package src.Controle;

import javax.swing.JOptionPane;
import javax.swing.JFrame;

import src.Ator.*;
import src.Painel.PainelBatalha;

public class Controle implements IControle {
    // Atributos
    private IHeroi heroi;

    // IControle
    public void setHeroi(IHeroi heroi) {
        this.heroi = heroi;
    }

    public boolean getVivo() {
        return heroi.getVivo();
    }

    public void jogada(int x, int y) {
        char movimento = heroi.verificarMovimento(x, y);

        switch (movimento) {
            case 'i':
                ICombate inimigo = (ICombate)heroi.getInimigoNaPosicao(x, y);
                new PainelBatalha(heroi, inimigo);

                if (heroi.getVivo()) {
                    // heroi.pegarItem(); ??????????????????????????????????????
                    heroi.mover(x, y);
                }

                break;
            case 'o':
                JOptionPane.showMessageDialog(new JFrame(), "Você não pode se mover para lá!", "Aviso", JOptionPane.WARNING_MESSAGE);
                break;
            case 't':
                JOptionPane.showMessageDialog(new JFrame(), "O Herói sente que um grande mal se aproxima...", "Aviso", JOptionPane.WARNING_MESSAGE);
                break;
            case 'v':
                heroi.mover(x, y);
                break;
        }
    } 
}
