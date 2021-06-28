package src.AppHeroiDasEras;

import src.Ator.*;
import src.Controle.*;
import src.Montador.*;
import src.Painel.*;

public class AppHeroiDasEras {

    public static void main(String[] args) {
        IControle controle = new Controle();
        IPainelPrincipal painel;
        int fase;
        IMontador montador;
        IHeroi heroi = null;

        painel = new PainelPrincipal(controle);

        for (fase = 1; fase < 7; fase++) {
            montador = new Montador(fase, ("data/CSVs/Fase" + fase + ".csv"));

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

            while (!controle.getAcabou()) {
                try {
                    Thread.currentThread().wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
            }
        }
    }
}
