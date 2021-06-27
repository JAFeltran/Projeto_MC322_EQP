package src.Controle;

import src.Ator.IHeroi;

public interface IControle {
    public void setHeroi(IHeroi heroi);
    public void setFase(int fase);
    public boolean getVivo();
    public boolean getAcabou();
    public IHeroi getHeroi();
    public String getVisualNaPosicao(int x, int y);
    public void jogada(int x, int y);
}
