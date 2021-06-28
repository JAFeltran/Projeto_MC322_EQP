package src.Ator;

public interface IAcao {
    // Getters
    public IAtor getInimigoNaPosicao(int x, int y);

    // Movimento
    // Remove um inimigo do mapa, transformando em um espaco vazio
    public void removerInimigo(int x, int y);
    // Retorna qual o tipo de ator na posicao x e y
    public char verificarMovimento(int x, int y);
    // Move o heroi para a posicao x e y
    public void mover(int x, int y, int fase);
}
