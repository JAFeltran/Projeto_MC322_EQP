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
        if (x < 7 && x >= 0) {
            if (y < 7 && y >= 0) {
                mapa[x][y] = ator;
            }
        }
    }

    public IAtor getAtorNaPosicao(int x, int y) {
        return mapa[x][y];
    }

    public MapaVisual getVisual() {
        return visual;
    }

    // IMapaVisual
    public String getVisualNaPosicao(int x, int y) {
        return visual.getAtorVisualNaPosicao(x, y).getIcone();
    }

    public void ajustarVisibilidade(int visao, int xHeroi, int yHeroi, int fase) {
        visual.setAtorVisualNaPosicao(xHeroi, yHeroi, 'h', fase);

        if (visao >= 1) {
            if (xHeroi + 1 < 7) {
                visual.setAtorVisualNaPosicao(xHeroi + 1, yHeroi, mapa[xHeroi + 1][yHeroi].getTipo(), fase);
            }
            if (xHeroi - 1 >= 0) {
                visual.setAtorVisualNaPosicao(xHeroi - 1, yHeroi, mapa[xHeroi - 1][yHeroi].getTipo(), fase);
            }
            if (yHeroi + 1 < 7) {
                visual.setAtorVisualNaPosicao(xHeroi, yHeroi + 1, mapa[xHeroi][yHeroi + 1].getTipo(), fase);
            }
            if (yHeroi - 1 >= 0) {
                visual.setAtorVisualNaPosicao(xHeroi, yHeroi - 1, mapa[xHeroi][yHeroi - 1].getTipo(), fase);
            }
            if (visao >= 2) {
                if (xHeroi + 1 < 7 && yHeroi + 1 < 7) {
                    visual.setAtorVisualNaPosicao(xHeroi + 1, yHeroi + 1, mapa[xHeroi + 1][yHeroi + 1].getTipo(), fase);
                }
                if (xHeroi - 1 >= 0 && yHeroi + 1 < 7) {
                    visual.setAtorVisualNaPosicao(xHeroi - 1, yHeroi + 1, mapa[xHeroi - 1][yHeroi + 1].getTipo(), fase);
                }
                if (xHeroi + 1 < 7 && yHeroi - 1 >= 0) {
                    visual.setAtorVisualNaPosicao(xHeroi + 1, yHeroi - 1, mapa[xHeroi + 1][yHeroi - 1].getTipo(), fase);
                }
                if (xHeroi - 1 >= 0 && yHeroi - 1 >= 0) {
                    visual.setAtorVisualNaPosicao(xHeroi - 1, yHeroi - 1, mapa[xHeroi - 1][yHeroi - 1].getTipo(), fase);
                }
                if (visao == 3) {
                    if (xHeroi + 2 < 7) {
                        visual.setAtorVisualNaPosicao(xHeroi + 2, yHeroi, mapa[xHeroi + 2][yHeroi].getTipo(), fase);
                    }
                    if (xHeroi - 2 >= 0) {
                        visual.setAtorVisualNaPosicao(xHeroi - 2, yHeroi, mapa[xHeroi - 2][yHeroi].getTipo(), fase);
                    }
                    if (yHeroi + 2 < 7) {
                        visual.setAtorVisualNaPosicao(xHeroi, yHeroi + 2, mapa[xHeroi][yHeroi + 2].getTipo(), fase);
                    }
                    if (yHeroi - 2 >= 0) {
                        visual.setAtorVisualNaPosicao(xHeroi, yHeroi - 2, mapa[xHeroi][yHeroi - 2].getTipo(), fase);
                    }
                }
            }
        }
    }
}
