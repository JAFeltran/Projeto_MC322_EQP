package src.AppHeroiDasEras;

import src.Ator.*;
import src.Controle.*;
import src.Montador.*;
import src.Painel.*;

public class AppHeroiDasHeras {

    public static void main(String[] args) {
        IPainelPrincipal painel;
        IControle controle = new Controle();
        int fase;
        IMontador montador;
        IHeroi heroi = null;

        painel = new PainelPrincipal(controle);

        for (fase = 1; fase < 7; fase++) {
            montador = new Montador(fase, ("assets/CSVs/Fase" + fase + ".csv"));

            montador.criarFase();
            controle.setFase(fase);

            if (controle.getHeroi() == null) {
                controle.setHeroi(montador.getHeroi());
            }
            else if (heroi != null) {
                heroi.setX(montador.getHeroi().getX());
                heroi.setY(montador.getHeroi().getY());
                controle.setHeroi(heroi);
            }

            while (true) {
                if (controle.getAcabou()) {
                    heroi = controle.getHeroi();
                    break;
                }
            }
        }
    }
}
