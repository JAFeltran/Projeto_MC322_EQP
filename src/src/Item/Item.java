package src.Item;

public class Item implements IItem {
    private int valor;
    private String nome;

    // Construtor
    public Item(int valor, String nome) {
        this.valor = valor;
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

    public String getNome() {
        return nome;
    }
}
