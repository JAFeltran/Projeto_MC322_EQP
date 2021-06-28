package src.Montador;

public interface IMontaAtor {
    // modifica os valores de habilidade do inimigo de forma aleatoria, retornando um vetor com
    // os novos valores
    public int[] modificarHabilidades(int vida, int ataque, int defesa);
    // gera um inimigo e o posiciona em [x][y]
    public void gerarInimigo(int x, int y);
    // gera um chefe e o posiciona em [x][y]
    public void gerarChefe(int x, int y);
}
