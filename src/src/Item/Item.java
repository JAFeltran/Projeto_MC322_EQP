package src.Item;

public class Item implements IItem {
    private int valor, posicao;
    private String nome;

    // Construtor
    public Item(int valor, int posicao, String nome) {
        this.valor = valor;
        this.posicao = posicao;
        this.nome = nome;
    }

    // Setters
    public void setValor(int valor) {
        this.valor = valor;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getters
    public int getValor() {
        return valor;
    }

    public int getPosicao() {
        return posicao;
    }

    public String getNome() {
        return nome;
    }
}
