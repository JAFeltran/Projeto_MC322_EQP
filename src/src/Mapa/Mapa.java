package src.Mapa;

import src.Ator.IAtor;

public class Mapa implements IMapa {
    // Atributos
    private IAtor mapa[][];

    // Construtor
    public Mapa() {
        mapa = new IAtor[7][7];
    }

    // IMapaPropriedades
    public void setAtorNaPosicao(IAtor ator, int x, int y) {
        mapa[x][y] = ator;
    }

    public IAtor getAtorNaPosicao(int x, int y) {
        return mapa[x][y];
    }
}
