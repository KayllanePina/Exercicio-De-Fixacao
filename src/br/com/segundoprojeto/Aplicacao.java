package br.com.segundoprojeto;

import java.util.List;

public class Aplicacao{
    private static LeituraDeArquivos leituraDeArquivos;

    public static void main(String[] args) {
        leituraDeArquivos = new LeituraDeArquivos("oscar_age_female.csv");
        System.out.println(leituraDeArquivos.getTabelaDeArtistasList());
    }
}
