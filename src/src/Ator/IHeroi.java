package src.Ator;

public interface IHeroi extends IAtor, ICombate, IAcao {
    // Setters
    public void setItemInventario(int posicao, int valor, String nome);
    public void setVisualNaPosicao(int x, int y, char tipo, int fase);

    // Getters
    public int getVida();
    public int getVidaMax();
    public String getVisualNaPosicao(int x, int y);
    public int getValorItemInventario(int posicao);

    // Outras funcoes
    // Verifica se o heroi carrega uma chave
    public boolean pegouChave(int fase);
    // Recupera uma porcao da vida maxima do heroi
    public void curar();
}
