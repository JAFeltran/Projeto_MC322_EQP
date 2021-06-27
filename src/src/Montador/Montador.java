package src.Montador;

import src.Ator.*;
import src.Mapa.*;

import static src.CSV.LeitorCSV.lerCSV;

import java.util.Random;

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

    // IMontadorPropriedades
    public void setFase(int fase) {
        this.fase = fase;
    }

    public int getFase() {
        return fase;
    }

    public IHeroi getHeroi() {
        return heroi;
    }

    public IMapa getMapa() {
        return mapa;
    }

    // IMontaMapa
    public void criarFase() {
        String conteudoCSV[][] = lerCSV(caminhoCSV);
        int xHeroi = 0, yHeroi = 0;
        int xChefe = 7, yChefe = 7;
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
                        xChefe = i;
                        yChefe = j;
                        //gerarChefe(xChefe, yChefe);
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

        definirTerritorioChefe(xChefe, yChefe);
    }

    public void definirTerritorioChefe(int x, int y) {
        IAtor ator;

        if (x + 1 < 7) {
            ator = mapa.getAtorNaPosicao(x + 1, y);
            ator.setTipo('t');

            if (x + 2 < 7) {
                ator = mapa.getAtorNaPosicao(x + 2, y);
                ator.setTipo('t');
            }
        }

        if (x - 1 >= 0) {
            ator = mapa.getAtorNaPosicao(x - 1, y);
            ator.setTipo('t');

            if (x - 2 >= 0) {
                ator = mapa.getAtorNaPosicao(x - 2, y);
                ator.setTipo('t');
            }
        }

        if (y + 1 < 7) {
            ator = mapa.getAtorNaPosicao(x, y + 1);
            ator.setTipo('t');

            if (y + 2 < 7) {
                ator = mapa.getAtorNaPosicao(x, y + 2);
                ator.setTipo('t');
            }
        }

        if (y - 1 >= 0) {
            ator = mapa.getAtorNaPosicao(x, y - 1);
            ator.setTipo('t');

            if (y - 2 >= 0) {
                ator = mapa.getAtorNaPosicao(x, y - 2);
                ator.setTipo('t');
            }
        }
    }

    // IMontaAtor
    public int[] modificarHabilidades(int vida, int ataque, int defesa) {
        int habilidades[] = new int[3];
        Random random = new Random();

        habilidades[0] = vida;
        habilidades[1] = ataque;
        habilidades[2] = defesa;

        if (random.nextInt(2) == 0) {
            habilidades[1] += habilidades[1] * 0.2;
            habilidades[2] -= habilidades[2] * 0.2;
        }
        else {
            habilidades[1] -= habilidades[1] * 0.2;
            habilidades[2] += habilidades[2] * 0.2;
        }

        return habilidades;
    }

    public void gerarInimigo(int x, int y) {
        int habilidades[];
        IAtor inimigo;

        switch (fase) {
            case 1:
                habilidades = modificarHabilidades(100, 90, 0);
                break;
            case 2:
                habilidades = modificarHabilidades(120, 170, 40);
                break;
            case 3:
                habilidades = modificarHabilidades(160, 275, 95);
                break;
            case 4:
                habilidades = modificarHabilidades(225, 400, 150);
                break;
            case 5:
                habilidades = modificarHabilidades(300, 600, 225);
                break;
            case 6:
                habilidades = modificarHabilidades(370, 850, 325);
                break;
            default:
                throw new IllegalStateException("Valor de fase inesperado: " + fase);
        }

        inimigo = new Inimigo(x, y, habilidades[0], habilidades[1], habilidades[2]);

        mapa.setAtorNaPosicao(inimigo, x, y);
    }

    public void gerarChefe(int x, int y) {
        int vida, ataque, defesa;
        IAtor chefe;

        switch (fase) {
            case 1:
                vida = 80;
                ataque = 100;
                defesa = 30;
                break;
            case 2:
                vida = 130;
                ataque = 180;
                defesa = 100;
                break;
            case 3:
                vida = 180;
                ataque = 275;
                defesa = 180;
                break;
            case 4:
                vida = 250;
                ataque = 370;
                defesa = 230;
                break;
            case 5:
                vida = 320;
                ataque = 490;
                defesa = 320;
                break;
            case 6:
                vida = 400;
                ataque = 630;
                defesa = 420;
                break;
            default:
                throw new IllegalStateException("Valor de fase inesperado: " + fase);
        }

        chefe = new Chefe(x, y, vida, ataque, defesa);

        mapa.setAtorNaPosicao(chefe, x, y);
    }
}
