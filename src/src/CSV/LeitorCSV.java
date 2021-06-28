package src.CSV;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class LeitorCSV {
    // le um arquivo csv de tamanho 7x7 e retorna uma matriz com o conteudo do arquivo
    public static String[][] lerCSV(String caminhoCSV) {
        if (caminhoCSV != null) {
            try {
                BufferedReader arquivo = new BufferedReader(new FileReader(caminhoCSV));
                String linha = arquivo.readLine();
                String conteudo[][] = new String[7][7];
                int i = 0;
                
                while (linha != null) {
                    conteudo[i] = linha.split(",");
                    linha = arquivo.readLine();
                    i ++;
                }

                arquivo.close();
                return conteudo;
            } catch (IOException e) {
                e.printStackTrace();
                String mensagem = "Não foi possível ler o arquivo '" + caminhoCSV + "'.\nPor favor, tente novamente.";
                JOptionPane.showMessageDialog(new JFrame(), mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        }
        return null;
    }
}