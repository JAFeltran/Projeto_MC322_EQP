package src.Montador;

import src.Ator.IAtor;
import src.Ator.Inimigo;

import java.util.Random;

public interface IMontaAtor {
    public int[] modificarHabilidades(int vida, int ataque, int defesa);
    public void gerarInimigo(int x, int y);
}
