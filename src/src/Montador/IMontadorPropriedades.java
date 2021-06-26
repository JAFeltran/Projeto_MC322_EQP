package src.Montador;

import src.Ator.IHeroi;
import src.Mapa.IMapa;

public interface IMontadorPropriedades {
    // Setters
    public void setFase(int fase);

    // Getters
    public int getFase();

    public IHeroi getHeroi();

    public IMapa getMapa();
}
