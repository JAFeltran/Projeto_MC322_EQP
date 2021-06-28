package src.Mapa;

import src.Ator.IAtor;

public interface IMapaPropriedades {
    // Setters
    public void setAtorNaPosicao(IAtor ator, int x, int y);

    // Getters
    public IAtor getAtorNaPosicao(int x, int y);
    public MapaVisual getVisual();
}
