package br.com.segundoprojeto;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Aplicacao{
    private static LeituraDeArquivos leituraDeArquivos;

    public static void main(String[] args) {
        leituraDeArquivos = new LeituraDeArquivos("oscar_age_female.csv");

        //1. Quem foi o ator mais jovem a ganhar um Oscar
        atorMaisJovemQueGanhouOscar();
    }

    private static void atorMaisJovemQueGanhouOscar(){
        List<TabelaDeArtistas> tabelaDeArtistasList = leituraDeArquivos.getTabelaDeArtistasList();

        Optional<TabelaDeArtistas> itemOptional = tabelaDeArtistasList.stream()
                .filter(tabelaDeArtistas -> tabelaDeArtistas.getAge() > 0)
                .max(Comparator.comparing(TabelaDeArtistas::getAge));

        itemOptional.ifPresent(it -> System.out.println("O ator mais a ganhar um Oscar foi o " + it.getName() + " com " + it.getAge() + " anos"));

    }
}
