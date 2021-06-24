package src.Mapa;

import src.Ator.IAtor;

public interface IMapaPropriedades {
    public void setAtorNaPosicao(IAtor ator, int x, int y);
    public IAtor getAtorNaPosicao(int x, int y);
}
