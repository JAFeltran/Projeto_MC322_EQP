package src.Ator;

public interface IHeroi extends IAtor, ICombate, IAcao {
    // Setters
    public void setItemInventario(int posicao, int valor, String nome);

    // Getters
    public int getVida();
    public int getVidaMax();

    public boolean pegouChave();
    public void curar();
}
