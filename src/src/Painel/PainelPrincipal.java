package src.Painel;

import javax.swing.*;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.*;

import src.Controle.*;

public class PainelPrincipal extends JFrame implements IPainelPrincipal {
    // Atributos
    private JLabel fundo, labelJogar, vida;
    private JButton jogar1, jogar2;
    private JButton botoes[][];
    private IControle controle;

    // Construtor
    public PainelPrincipal(IControle controle) {
        super("O Herói das Eras");
        this.controle = controle;

        criarTela1();
        pack();
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // Setter
    public void setControle(IControle controle) {
        this.controle = controle;
    }

    // Cria a tela inicial
    private void criarTela1() {
        fundo = new JLabel(new ImageIcon("assets/PainelPrincipal/tela1.png"));
        labelJogar = new JLabel(new ImageIcon("assets/PainelPrincipal/jogar.png"));
        jogar1 = new JButton();
        jogar1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jogar1.setBounds(238, 458, 240, 80);
        jogar1.addActionListener(this);
        jogar1.add(labelJogar);
        fundo.add(jogar1);
        add(fundo);
    }

    // Cria a segunda tela, com a história do jogo
    private void criarTela2() {
        fundo.setIcon(new ImageIcon("assets/PainelPrincipal/tela2.png"));
        fundo.remove(jogar1);
        jogar2 = new JButton();
        jogar2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jogar2.setBounds(238, 558, 240, 80);
        jogar2.addActionListener(this); 
        jogar2.add(labelJogar);
        fundo.add(jogar2);
    }

    // Cria a tela com o mapa e os botões de movimento
    private void criarMapa() {
        fundo.setIcon(new ImageIcon("assets/PainelPrincipal/mapa.png"));
        fundo.remove(jogar2);        
        botoes = new JButton[7][7];

        vida = new JLabel("VIDA: " + controle.getHeroi().getVida() + "/" + controle.getHeroi().getVidaMax());
        vida.setForeground(Color.WHITE);
        vida.setOpaque(true);
        vida.setBounds(500, 25, 100, 25);

        String mensagem = "Clique em um quadrado visível para mover o Herói para lá.\nAo mover o Herói para o local onde está um Inimigo, uma batalha épica se iniciará!";
        JOptionPane.showMessageDialog(new JFrame(), mensagem, "Instruções", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("assets/PainelBatalha/info.png"));
        
        for (int i = 0; i < 7; i ++) {
            for (int j = 0; j < 7; j ++) {
                botoes[i][j] = new JButton();
                botoes[i][j].addActionListener(this);
                botoes[i][j].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                botoes[i][j].setBounds(42 + (j * 90), 48 + (i * 90), 80, 80);
                
                botoes[i][j].setIcon(new ImageIcon(controle.getVisualNaPosicao(i, j)));

                // JLabel label = new JLabel(new ImageIcon(controle.getVisualNaPosicao(i, j)));
                // botoes[i][j].add(label);
                fundo.add(botoes[i][j]);
                fundo.validate();
            }
        }

        //this.validate();
        atualizarMapa();
    }

    // Atualiza os visuais do Mapa
    private void atualizarMapa() {
        for (int i = 0; i < 7; i ++) {
            for (int j = 0; j < 7; j ++) {
                //JLabel label = new JLabel(new ImageIcon(controle.getVisualNaPosicao(i, j)));
                //botoes[i][j].add(label);
                botoes[i][j].setIcon(new ImageIcon(controle.getVisualNaPosicao(i,j)));
                fundo.add(botoes[i][j]);
            }
        }
        vida.setText("VIDA: " + controle.getHeroi().getVida() + "/" + controle.getHeroi().getVidaMax());
    }

    // ActionListener
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jogar1) {
            criarTela2();
        } else if (e.getSource() == jogar2) {
            criarMapa();
        } else {
            for (int i = 0; i < 7; i ++) {
                for (int j = 0; j < 7; j ++) {
                    if (e.getSource() == botoes[i][j]) {
                        controle.jogada(i, j);
                        atualizarMapa();
                    }
                }
            }
        }
    }

    // TODO Remover abaixo depois de testar =D
    public static void main(String[] args) {
        new PainelPrincipal(new Controle());
    }
}
