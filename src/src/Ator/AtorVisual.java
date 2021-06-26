package src.Ator;

public class AtorVisual {
    // Atributos
    private String icone;

    // Construtor
    public AtorVisual() {
        //icone = endereco fumaca
    }

    // Setters
    public void setIcone(char tipo, int fase) {
        switch (tipo) {
            case '_':
                //icone = endereco grama
                break;
            case 'o':
                //icone = endereco obstaculo
                break;
            case 'i':
                setIconeInimigo(fase);
                break;
            case 'h':
                //icone = endereco heroi
                break;
            case 'c':
                setIconeChefe(fase);
                break;
        }
    }

    public void setIconeInimigo(int fase) {
        switch (fase) {
            case 1:
                icone = "assets/Inimigos/Tigre-Dentes-de-Sabre.png";
                break;
            case 2:
                icone = "assets/Inimigos/Bárbaro.png";
                break;
            case 3:
                icone = "assets/Inimigos/Cavaleiro.png";
                break;
            case 4:
                icone = "assets/Inimigos/Soldado Francês.png";
                break;
            case 5:
                icone = "assets/Inimigos/Soldado Nazista.png";
                break;
            case 6:
                icone = "assets/Inimigos/Alienígena.png";
                break;
        }
    }

    public void setIconeChefe(int fase) {
        // switch pra cada fase e icone
    }

    // Getters
    public String getIcone() {
        return icone;
    }
}
