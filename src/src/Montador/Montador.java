package src.Montador;

import src.Ator.*;
import src.Mapa.*;

import static src.CSV.LeitorCSV.lerCSV;

public class Montador {
    // Atributos
    private int fase;
    private String caminhoCSV;
    private IHeroi heroi;
    private IMapa mapa;

    // Construtor
    public Montador(int fase, String caminhoCSV) {
        this.fase = fase;
        this.caminhoCSV = caminhoCSV;
        mapa = new Mapa();
    }

    // Setters
    public void setFase(int fase) {
        this.fase = fase;
    }

    // Getters
    public int getFase() {
        return fase;
    }

    public IHeroi getHeroi() {
        return heroi;
    }

    public IMapa getMapa() {
        return mapa;
    }

    // Outras funcoes
    public void criarFase() {
        String conteudoCSV[][] = lerCSV(caminhoCSV);
        int xHeroi = 0, yHeroi = 0;
        IAtor ator;

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                switch (conteudoCSV[i][j]) {
                    case "_":
                        ator = new Ator(i, j, '_');
                        mapa.setAtorNaPosicao(ator, i, j);
                        break;
                    case "o":
                        ator = new Obstaculo(i, j);
                        mapa.setAtorNaPosicao(ator, i, j);
                        break;
                    case "i":
                        gerarInimigo(i, j);
                        break;
                    case "c":
                        //tem q definir os valores do chefe
                        break;
                    case "s":
                        ator = new Ator(i, j, 's');
                        mapa.setAtorNaPosicao(ator, i, j);
                        break;
                    case "h":
                        xHeroi = i;
                        yHeroi = j;
                        break;
                }
            }
        }

        heroi = new Heroi(xHeroi, yHeroi, mapa);
    }

    public void gerarInimigo(int x, int y) {
        int vida, ataque, defesa;
        IAtor inimigo;

        inimigo = new Inimigo(x, y, vida, ataque, defesa);
        mapa.setAtorNaPosicao();
    }
}
