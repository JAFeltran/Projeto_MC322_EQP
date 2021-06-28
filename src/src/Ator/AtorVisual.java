package src.Ator;

public class AtorVisual {
    // Atributos
    private String icone;

    // Construtor
    public AtorVisual() {
        icone = "assets/Mapa/Fumaca.png";
    }

    // Setters
    public void setIcone(char tipo, int fase) {
        switch (tipo) {
            case '_':
            case 't':
                icone = "assets/Mapa/Vazio.png";
                break;
            case 'o':
                icone = "assets/Mapa/Obstaculo.png";
                break;
            case 'i':
                setIconeInimigo(fase);
                break;
            case 'h':
                icone = "assets/Mapa/Heroi.png";
                break;
            case 'c':
                setIconeChefe(fase);
                break;
            case 's':
                icone = "assets/Mapa/Saida.png";
        }
    }

    public void setIconeInimigo(int fase) {
        switch (fase) {
            case 1:
                icone = "assets/Inimigos/Tigre-Dentes-de-Sabre.png";
                break;
            case 2:
                icone = "assets/Inimigos/Barbaro.png";
                break;
            case 3:
                icone = "assets/Inimigos/Cavaleiro.png";
                break;
            case 4:
                icone = "assets/Inimigos/Soldado Frances.png";
                break;
            case 5:
                icone = "assets/Inimigos/Soldado Nazista.png";
                break;
            case 6:
                icone = "assets/Inimigos/Alienigena.png";
                break;
        }
    }

    public void setIconeChefe(int fase) {
        switch (fase) {
            case 1:
                icone = "assets/Inimigos/Mamute.png";
                break;
            case 2:
                icone = "assets/Inimigos/Lider Barbaro.png";
                break;
            case 3:
                icone = "assets/Inimigos/Mago.png";
                break;
            case 4:
                icone = "assets/Inimigos/Rei.png";
                break;
            case 5:
                icone = "assets/Inimigos/Tanque.png";
                break;
            case 6:
                icone = "assets/Inimigos/Super Alienigena.png";
                break;
        }
    }

    // Getters
    public String getIcone() {
        return icone;
    }
}
