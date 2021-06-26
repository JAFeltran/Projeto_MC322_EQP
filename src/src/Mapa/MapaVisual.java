package src.Mapa;

import src.Ator.AtorVisual;

public class MapaVisual {
    // Atributos
    private AtorVisual icones[][];

    // Construtor
    public MapaVisual() {
        icones = new AtorVisual[7][7];

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                icones[i][j] = new AtorVisual();
            }
        }
    }

    // Setters
    public void setAtorVisualNaPosicao(int x, int y, char tipo, int fase) {
        if (x < 7 && x >= 0) {
            if (y < 7 && y >= 0) {
                icones[x][y].setIcone(tipo, fase);
            }
        }
    }

    // Getters
    public AtorVisual getAtorVisualNaPosicao(int x, int y) {
        return icones[x][y];
    }
}
