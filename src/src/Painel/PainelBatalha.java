package src.Painel;

import javax.swing.*;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.*;

import java.util.ArrayList;
import java.util.Random;

import src.Ator.*;
import src.Mapa.Mapa;

public class PainelBatalha extends JFrame implements IPainelBatalha {
    // Atributos
    private Color cores[] = {Color.RED.darker(), Color.GREEN.darker(), Color.YELLOW.darker(), Color.BLUE.darker()};
    private JPanel paineis[] = new JPanel[cores.length];
    private int bordas[][] = {{125, 125, 100, 100}, {25, 225, 100, 100}, {125, 325, 100, 100}, {225, 225, 100, 100}};
    private JButton botoes[] = new JButton[cores.length];
    private JLabel fundo;

    private Random random;
    private ArrayList<Integer> sequencia;
    private int fase, atual, tempo;
    private String mensagem;
    private boolean acabou;

    private ICombate heroi, inimigo;

    // Construtor
    public PainelBatalha(ICombate heroi, ICombate inimigo, int fase) {
        super("Batalha!");
        atual = 0;
        random = new Random();
        acabou = false;

        if (fase > 0 && fase < 7) {
            tempo = fase * 125;
        } else {
            tempo = 125;
        }
        
        this.fase = fase;
        this.heroi = heroi;
        this.inimigo = inimigo;

        fundo = new JLabel(new ImageIcon("assets/PainelBatalha/fundo.png"));
        
        for (int i = 0; i < botoes.length; i ++) {
            paineis[i] = new JPanel();
            paineis[i].setBackground(cores[i].darker());
            botoes[i] = new JButton();
            botoes[i].setBounds(bordas[i][0], bordas[i][1], bordas[i][2], bordas[i][3]);
            botoes[i].add(paineis[i]);
            botoes[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            botoes[i].addActionListener(this);
            fundo.add(botoes[i]);
        }
        
        add(fundo);
        pack();
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        mensagem = "O Herói encontrou um inimigo!\nClique nas cores na ordem em que elas acenderem para atacá-lo.\nCuidado! Se você errar, ele poderá contra-atacar o Herói.";
        
        JOptionPane.showMessageDialog(new JFrame(), mensagem, "Instruções", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("assets/PainelBatalha/info.png"));
        novaBatalha();
    }

    // IPainelBatalha
    public boolean getAcabou() {
        return acabou;
    }

    // Inicia uma nova batalha
    private void novaBatalha() {       
        sequencia = new ArrayList<Integer>();

        for (int i = 0; i < fase; i ++) {
            sequencia.add(random.nextInt(botoes.length));
        }

        try {
            Thread.sleep(500);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
            System.exit(1);
        }

        apresentar();
    }

    // Apresenta a sequência para o jogador
    private void apresentar() {
        this.paintAll(this.getGraphics());

        for (int i = 0; i < botoes.length; i ++) {
            botoes[i].setEnabled(false);
        }

        for (int i = 0; i < fase; i ++) {
            int botao = sequencia.get(i);
            paineis[botao].setBackground(cores[botao].brighter());
            paineis[botao].paintAll(paineis[botao].getGraphics());

            try {
                Thread.sleep(1250 - tempo);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
                System.exit(1);
            }

            paineis[botao].setBackground(cores[botao].darker());
            paineis[botao].paintAll(paineis[botao].getGraphics());

            try {
                Thread.sleep(250);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
                System.exit(1);
            }
        }

        for (int i = 0; i < botoes.length; i ++) {
            botoes[i].setEnabled(true);
        }
    }

    // ActionListener
    public void actionPerformed(ActionEvent e) {
        int botao = sequencia.get(atual);
        
        if (e.getSource() == botoes[botao]) {
            paineis[botao].setBackground(cores[botao].brighter());
            paineis[botao].paintAll(paineis[botao].getGraphics());

            try {
                Thread.sleep(500);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
                System.exit(1);
            }

            paineis[botao].setBackground(cores[botao].darker());
            paineis[botao].paintAll(paineis[botao].getGraphics());
            atual ++;

            if (atual == fase) {
                inimigo.receberDano(heroi.causarDano());
                mensagem = "O Herói acertou um golpe no inimigo!";
                JOptionPane.showMessageDialog(new JFrame(), mensagem, "Acerto!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("assets/PainelBatalha/espada.png"));

                fase ++;
                atual = 0;
                sequencia.add(random.nextInt(botoes.length));

                if (!inimigo.getVivo()) {
                    mensagem = "O Herói conseguiu derrotar o inimigo!";
                    JOptionPane.showMessageDialog(new JFrame(), mensagem, "Vitória!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("assets/PainelBatalha/caveira.png"));
                    acabou = true;
                    this.dispose();
                    return;
                }

                if (((IAtor)inimigo).getTipo() == 'c') {
                    heroi.receberDano(inimigo.causarDano());
                    mensagem = "O poderoso chefe atacou o Herói!";
                    JOptionPane.showMessageDialog(new JFrame(), mensagem, "Contra-ataque", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("assets/PainelBatalha/espada.png"));
                }
            }
        } else {
            heroi.receberDano(inimigo.causarDano());
            mensagem = "O inimigo se defendeu e contra-atacou o Herói!";
            JOptionPane.showMessageDialog(new JFrame(), mensagem, "Contra-ataque", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("assets/PainelBatalha/escudo.png"));
        }
            
        if (!heroi.getVivo()) {
            mensagem = "As forças do mal são fortes demais e derrotaram o Herói...";
            JOptionPane.showMessageDialog(new JFrame(), mensagem, "Derrota!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("assets/PainelBatalha/derrota.png"));
            acabou = true;
            System.exit(0);
            return;
        }

        atual = 0;

        try {
            Thread.sleep(500);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
            System.exit(1);
        }

        apresentar();
    }

    // TODO Remover abaixo depois de testar =D
    public static void main(String[] args) {
        new PainelBatalha(new Heroi(0, 0, new Mapa()), new Inimigo(0, 0, 300, 100, 0), 1);
    }
}
