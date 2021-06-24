package src.Montador;

import src.Ator.*;
import src.Mapa.*;

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
    public void preencherMapa() {
        //se ler "S" eh saida, mas o quadrado ainda eh um espaco vazio
    }

    public void criarHeroi() {
        int x, y;

        //le o CSV pra achar a posicao do heroi
        heroi = new Heroi(x, y, mapa);
    }
}
