package src.Ator;

public interface IAcao {
    // Setters
    public void removerInimigo(int x, int y);

    // Getters
    public IAtor getInimigoNaPosicao(int x, int y);

    // Movimento
    public char verificarMovimento(int x, int y);
    public void mover(int x, int y);
}
