package src.AppHeroiDasEras;

import src.Ator.*;
import src.Controle.*;
import src.Montador.*;

public class AppHeroiDasHeras {

    public static void main(String[] args) {
        IControle controle = new Controle();
        int fase;
        IMontador montador;
        IHeroi heroi = null;
        int xHeroi, yHeroi;

        for (fase = 1; fase < 7; fase++) {
            montador = new Montador(fase, ("Assets/CSV/Fase" + fase));

            montador.criarFase();
            controle.setFase(fase);

            if (controle.getHeroi() == null) {
                controle.setHeroi(montador.getHeroi());
            }
            else if (heroi != null) {
                xHeroi = montador.getHeroi().getX();
                yHeroi = montador.getHeroi().getY();
                heroi.setX(xHeroi);
                heroi.setY(yHeroi);
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
