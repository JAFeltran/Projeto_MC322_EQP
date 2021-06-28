package src.Mapa;

public interface IMapaVisual {
    // Getters
    // retorna o endereco do icone do ator na posicao [x][y]
    public String getVisualNaPosicao(int x, int y);

    // revela areas escondidas do mapa com base na visao do heroi
    public void ajustarVisibilidade(int visao, int xHeroi, int yHeroi, int fase);
}
