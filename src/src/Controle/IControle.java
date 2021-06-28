package src.Controle;

import javax.swing.JFrame;

import src.Ator.IHeroi;
// import src.Painel.IPainelPrincipal;

public interface IControle {
    // Setters
    public void setHeroi(IHeroi heroi);
    public void setFase(int fase);
    public void connectPainel(JFrame painel);

    // Getters
    public boolean getVivo();
    public boolean getAcabou();
    public IHeroi getHeroi();
    public String getVisualNaPosicao(int x, int y);

    // Responde ao evento do jogador de clicar em um botão do mapa
    public void jogada(int x, int y);
}
