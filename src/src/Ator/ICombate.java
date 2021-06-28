package src.Ator;

public interface ICombate {
    // Getters
    public boolean getVivo();

    // Calcula o dano causado por um ataque
    public int causarDano();
    // Calcula o dano recebido por um ataque
    public void receberDano(int dano);
}
