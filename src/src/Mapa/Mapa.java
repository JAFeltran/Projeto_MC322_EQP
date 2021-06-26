package src.Mapa;

import src.Ator.IAtor;

public class Mapa implements IMapa {
    // Atributos
    private IAtor mapa[][];
    private MapaVisual visual;

    // Construtor
    public Mapa() {
        mapa = new IAtor[7][7];
        visual = new MapaVisual();
    }

    // IMapaPropriedades
    public void setAtorNaPosicao(IAtor ator, int x, int y) {
        mapa[x][y] = ator;
    }

    public IAtor getAtorNaPosicao(int x, int y) {
        return mapa[x][y];
    }

    // IMapaVisual
    public void ajustarVisibilidade(int visao, int xHeroi, int yHeroi, int fase) {
        if (visao == 0) {
            visual.setAtorVisualNaPosicao(xHeroi, yHeroi, 'h', fase);
            // problema: e se esse espaco for ocupado por alguma outra coisa alem do heroi?
        }
        else if (visao >= 1) {
            visual.setAtorVisualNaPosicao(xHeroi + 1, yHeroi, mapa[xHeroi + 1][yHeroi].getTipo(), fase);
            visual.setAtorVisualNaPosicao(xHeroi - 1, yHeroi, mapa[xHeroi - 1][yHeroi].getTipo(), fase);
            visual.setAtorVisualNaPosicao(xHeroi, yHeroi + 1, mapa[xHeroi][yHeroi + 1].getTipo(), fase);
            visual.setAtorVisualNaPosicao(xHeroi, yHeroi - 1, mapa[xHeroi][yHeroi - 1].getTipo(), fase);

            if (visao >= 2) {
                visual.setAtorVisualNaPosicao(xHeroi + 1, yHeroi + 1, mapa[xHeroi + 1][yHeroi + 1].getTipo(), fase);
                visual.setAtorVisualNaPosicao(xHeroi - 1, yHeroi + 1, mapa[xHeroi - 1][yHeroi + 1].getTipo(), fase);
                visual.setAtorVisualNaPosicao(xHeroi + 1, yHeroi - 1, mapa[xHeroi + 1][yHeroi - 1].getTipo(), fase);
                visual.setAtorVisualNaPosicao(xHeroi - 1, yHeroi - 1, mapa[xHeroi - 1][yHeroi - 1].getTipo(), fase);

                if (visao == 3) {
                    visual.setAtorVisualNaPosicao(xHeroi + 2, yHeroi, mapa[xHeroi + 2][yHeroi].getTipo(), fase);
                    visual.setAtorVisualNaPosicao(xHeroi - 2, yHeroi, mapa[xHeroi - 2][yHeroi].getTipo(), fase);
                    visual.setAtorVisualNaPosicao(xHeroi, yHeroi + 2, mapa[xHeroi][yHeroi + 2].getTipo(), fase);
                    visual.setAtorVisualNaPosicao(xHeroi, yHeroi - 2, mapa[xHeroi][yHeroi - 2].getTipo(), fase);
                }
            }
        }
    }
}
