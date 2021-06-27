package src.Mapa;

public interface IMapaVisual {
    public void ajustarVisibilidade(int visao, int xHeroi, int yHeroi, int fase);
    public String getVisualNaPosicao(int x, int y);
}
