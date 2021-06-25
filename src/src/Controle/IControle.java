package src.Controle;

import src.Ator.IHeroi;
import src.Painel.IPainelPrincipal;

public interface IControle {
    public void setHeroi(IHeroi heroi);
    public void setFase(int fase);
    public boolean getVivo();
    public void connectPainel(IPainelPrincipal painel);
    public void jogada(int x, int y);
}
