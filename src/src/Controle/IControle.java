package src.Controle;

import src.Ator.IHeroi;

public interface IControle {
    // Setters
    public void setHeroi(IHeroi heroi);
    public void setFase(int fase);

    // Getters
    public boolean getVivo();
    public boolean getAcabou();
    public IHeroi getHeroi();
    public String getVisualNaPosicao(int x, int y);

    // Responde ao evento do jogador de clicar em um botão do mapa
    public void jogada(int x, int y);
}
