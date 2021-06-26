package src.Painel;

import javax.swing.*;

import java.awt.Cursor;
import java.awt.event.*;

import src.Controle.*;

public class PainelPrincipal extends JFrame implements IPainelPrincipal {
    // Atributos
    private JLabel fundo, labelJogar;
    private JButton jogar1, jogar2;
    private JButton botoes[];
    private IControle controle;

    // Construtor
    public PainelPrincipal(IControle controle) {
        super("O Herói das Eras");
        this.controle = controle;
        controle.connectPainel(this);

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
        botoes = new JButton[49];

        String mensagem = "Clique em um quadrado visível para mover o Herói para lá.\nAo mover o Herói para o local onde está um Inimigo, uma batalha épica se iniciará!";
        JOptionPane.showMessageDialog(new JFrame(), mensagem, "Instruções", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("assets/PainelBatalha/info.png"));
        
        for (int i = 0; i < 7; i ++) {
            for (int j = 0; j < 7; j ++) {
                int n = (7 * i) + j;
                botoes[n] = new JButton();
                botoes[n].addActionListener(this);
                botoes[n].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                botoes[n].setBounds(42 + (j * 90), 48 + (i * 90), 80, 80);
                
                // TODO trocar abaixo pelas imagens corretas e setar tudo invisível a não ser perto do herói... talvez tenha que mudar o construtor pra receber a posição do herói
                JLabel label = new JLabel("" + n, JLabel.CENTER);
                botoes[n].add(label);
                fundo.add(botoes[n]);

                // TODO acho que isso aqui vai funcionar assim:
                // Quando o jogador clica no botão, ele avisa o controle qual botão foi e o controle se vira pra resolver.
                // Quando for mover o herói, o controle pode, além de alterar a posição do herói, calcular quais casas são visíveis e mandar o controle deixar essas visíveis e deixar as outras que não são mais visíveis, invisíveis.
                // Fazendo isso, não tem como o jogador se mover pra algum lugar não acessível, mas a visibilidade do herói teria que ser sempre maior ou igual a 1, se não não daria pra mover at all.
                // Qualquer coisa a gente pode, em vez de usar o setVisible, só mudar a imagem do label do botão pra uma imagem de desconhecido ou a imagem dele mesmo.
                //botoes[n].setVisible(false);
            }
        }
    }
    
    // Altera a visibilidade do botão na posição x, y
    public void setVisibilidadeBotao(int x, int y, boolean visivel) {
        // Ler o "to do" acima
        botoes[(x * 7) + y].setVisible(true);
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
                    int n = (7 * i) + j;
                    if (e.getSource() == botoes[n]) {
                        controle.jogada(i, j);
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
