package src.Montador;

public interface IMontaMapa {
    // cria a fase, posicionando os atores em seus devidos locais e inicializando-os
    public void criarFase();
    // modifica as celulas ao redor do chefe (em um raio de 1 celula) para serem do tipo 't'
    public void definirTerritorioChefe(int x, int y);
}
