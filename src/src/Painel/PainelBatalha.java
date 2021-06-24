package src.Painel;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.*;

import java.util.ArrayList;
import java.util.Random;

import src.Ator.*;
import src.Mapa.Mapa;

public class PainelBatalha extends JFrame implements ActionListener {
    // Atributos
    private Color cores[] = {Color.RED.darker(), Color.GREEN.darker(), Color.YELLOW.darker(), Color.BLUE.darker()};
    private JPanel paineis[] = new JPanel[cores.length];
    private int bordas[][] = {{125, 125, 100, 100}, {25, 225, 100, 100}, {125, 325, 100, 100}, {225, 225, 100, 100}};
    private JButton botoes[] = new JButton[cores.length];
    private JLabel pontuacao, titulo;

    private Random random;
    private ArrayList<Integer> sequencia;
    private int fase, atual;
    private String mensagem;

    private ICombate heroi, inimigo;

    // Construtor
    public PainelBatalha(ICombate heroi, ICombate inimigo) {
        super("Batalha!");
        fase = 1;
        atual = 0;
        random = new Random();

        this.heroi = heroi;
        this.inimigo = inimigo;

        // TODO Trocar abaixo por algo mais bonito... uma imagem de escudo com espadas cruzadas pra dizer que é uma batalha?
        // Se for trocar, tem que remover todas as atualizações pra pontuação mais pra baixo

        pontuacao = new JLabel("1", JLabel.CENTER);
        pontuacao.setBounds(125, 225, 100, 100);
        pontuacao.setFont(pontuacao.getFont().deriveFont(30F));
        add(pontuacao);

        // TODO Trocar abaixo por algo mais bonito... imagem do herói e do inimigo e coraçõezinhos com a vida deles?

        titulo = new JLabel("Batalha!", JLabel.CENTER);
        titulo.setBounds(100, 25, 150, 75);
        titulo.setFont(pontuacao.getFont().deriveFont(30F));
        add(titulo);


        for (int i = 0; i < botoes.length; i ++) {
            paineis[i] = new JPanel();
            paineis[i].setBackground(cores[i].darker());
            botoes[i] = new JButton();
            botoes[i].setBounds(bordas[i][0], bordas[i][1], bordas[i][2], bordas[i][3]);
            botoes[i].add(paineis[i]);
            botoes[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            botoes[i].addActionListener(this);
            add(botoes[i]);
        }

        setSize(350, 475);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        mensagem = "O Herói encontrou um inimigo!";
        mensagem.concat("\nClique nas cores na ordem em que elas acenderem para atacá-lo.");
        mensagem.concat("\nCuidado! Se você errar, ele poderá contra-atacar o Herói.");
        
        JOptionPane.showMessageDialog(new JFrame(), mensagem, "Instruções", JOptionPane.INFORMATION_MESSAGE);
        novaBatalha();
    }

    // Inicia uma nova batalha
    private void novaBatalha() {       
        sequencia = new ArrayList<Integer>();
        sequencia.add(random.nextInt(botoes.length));
        
        fase = 1;
        atual = 0;

        try {
            Thread.sleep(500);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
            System.exit(ERROR);
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
                Thread.sleep(1000);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
                System.exit(ERROR);
            }

            paineis[botao].setBackground(cores[botao].darker());
            paineis[botao].paintAll(paineis[botao].getGraphics());

            try {
                Thread.sleep(250);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
                System.exit(ERROR);
            }
        }

        for (int i = 0; i < botoes.length; i ++) {
            botoes[i].setEnabled(true);
        }
    }

    public void actionPerformed(ActionEvent e) {
        int botao = sequencia.get(atual);
        
        if (e.getSource() == botoes[botao]) {
            paineis[botao].setBackground(cores[botao].brighter());
            paineis[botao].paintAll(paineis[botao].getGraphics());

            try {
                Thread.sleep(500);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
                System.exit(ERROR);
            }

            paineis[botao].setBackground(cores[botao].darker());
            paineis[botao].paintAll(paineis[botao].getGraphics());
            atual ++;

            if (atual == fase) {
                inimigo.receberDano(heroi.causarDano());
                mensagem = "O Herói acertou um golpe no inimigo!";
                JOptionPane.showMessageDialog(new JFrame(), mensagem, "Acerto!", JOptionPane.INFORMATION_MESSAGE);

                fase ++;
                atual = 0;
                pontuacao.setText("" + fase);
                sequencia.add(random.nextInt(botoes.length));

                try {
                    Thread.sleep(500);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                    System.exit(ERROR);
                }

                if (!inimigo.getVivo()) {
                    mensagem = "O Herói conseguiu derrotar o inimigo!";
                    JOptionPane.showMessageDialog(new JFrame(), mensagem, "Vitória!", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                    return;
                }

                apresentar();
            }
        } else {
            heroi.receberDano(inimigo.causarDano());
            mensagem = "O inimigo se defendeu e contra-atacou o Herói!";
            JOptionPane.showMessageDialog(new JFrame(), mensagem, "Contra-ataque", JOptionPane.INFORMATION_MESSAGE);
            
            if (!heroi.getVivo()) {
                mensagem = "As forças do mal são fortes demais e derrotaram o Herói...";
                JOptionPane.showMessageDialog(new JFrame(), mensagem, "Derrota!", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                return;
            }

            atual = 0;
            apresentar();
        }
    }

    // TODO Remover abaixo depois de testar =D
    public static void main(String[] args) {
        new PainelBatalha(new Heroi(0, 0, new Mapa()), new Inimigo(0, 0, 300, 100, 0));
    }
}
