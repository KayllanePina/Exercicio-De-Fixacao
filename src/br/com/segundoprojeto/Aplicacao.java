package br.com.segundoprojeto;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class Aplicacao{
    private static LeituraDeArquivos leituraDeArquivos;

    public static void main(String[] args) {
        //1. Quem foi o ator mais jovem a ganhar um Oscar
        atorMaisJovemQueGanhouOscar();

        //2. Quem foi a atriz mais vezes premiadas
        atrizMaisVezesPremiada();

        //3. Qual atriz entre 20 e 30 anos que mais vezes foi vencedora?
        AtrizEntreVinteETrintaMaisPremiada();

        //4. Quais atores ou atrizes receberam mais de um Oscar? Elabore uma única estrutura contendo atores e atrizes.
        atoresEAtrizesQueReceberamMaisOscars();

        //5.Quando informado o nome de um ator ou atriz, dê um resumo de quantos prêmios ele/ela recebeu e liste ano, idade e nome de cada filme pelo qual foi premiado(a).
        resumoDosPremios();
    }

    private static void atorMaisJovemQueGanhouOscar(){
        leituraDeArquivos = new LeituraDeArquivos("oscar_age_male.csv");
        List<TabelaDeArtistas> tabelaDeArtistasList = leituraDeArquivos.getTabelaDeArtistasList();

        Optional<TabelaDeArtistas> itemOptional = tabelaDeArtistasList.stream()
                .filter(tabelaDeArtistas -> tabelaDeArtistas.getAge() > 0)
                .min(Comparator.comparing(TabelaDeArtistas::getAge));

        itemOptional.ifPresent(it -> System.out.println("O ator mais novo a ganhar um Oscar foi o " + it.getName() + " com " + it.getAge() + " anos \n"));
    }

    private static void atrizMaisVezesPremiada(){
        leituraDeArquivos = new LeituraDeArquivos("oscar_age_female.csv");
        List<TabelaDeArtistas> tabelaDeArtistasList = leituraDeArquivos.getTabelaDeArtistasList();

        tabelaDeArtistasList.stream()
                .map(TabelaDeArtistas::getName)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .ifPresent(it -> System.out.println("A atriz "+ it.getKey() + " é atriz mais vezes premiadas com " + it.getValue() + " de premios \n"));
    }

    private static void AtrizEntreVinteETrintaMaisPremiada(){
        leituraDeArquivos = new LeituraDeArquivos("oscar_age_female.csv");
        List<TabelaDeArtistas> tabelaDeArtistasList = leituraDeArquivos.getTabelaDeArtistasList();

        tabelaDeArtistasList.stream()
                .filter(tabelaDeArtistas -> tabelaDeArtistas.getAge() >= 20 && tabelaDeArtistas.getAge() <= 30)
                .map(TabelaDeArtistas::getName)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .ifPresent(it -> System.out.println("A atriz que tem entre 20 e 30 anos e é a mais premiada  é a " + it.getKey() + " com " + it.getValue() + " premios \n"));
    }

    private static void atoresEAtrizesQueReceberamMaisOscars(){
        leituraDeArquivos = new LeituraDeArquivos("oscar_age_female.csv");
        leituraDeArquivos = new LeituraDeArquivos("oscar_age_male.csv");
        List<TabelaDeArtistas> tabelaDeArtistasList = leituraDeArquivos.getTabelaDeArtistasList();

        tabelaDeArtistasList.stream()
                .map(TabelaDeArtistas::getName)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(it -> it.getValue() >= 2)
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toList())
                .forEach(it -> System.out.println("Os atores e atrizes que mais receberam o Oscar foram " + it.getKey() + " com " + it.getValue() + " premiações \n"));
    }

    private static void resumoDosPremios(){
        leituraDeArquivos = new LeituraDeArquivos("oscar_age_female.csv");
        leituraDeArquivos = new LeituraDeArquivos("oscar_age_male.csv");

        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o nome do Artista que você gostaria de saber sobre: ");
        String atorOuAtriz = sc.nextLine();

        List<TabelaDeArtistas> tabelaDeArtistasList = leituraDeArquivos.getTabelaDeArtistasList();

        try{
            tabelaDeArtistasList.stream()
                    .filter(tabelaDeArtistas -> tabelaDeArtistas.getName().equals(atorOuAtriz))
                    .collect(Collectors.toList())
                    .forEach(it -> System.out.println(atorOuAtriz + " é um(a) ator/atriz que com " + it.getAge() + " anos de idade foi premiado com o filme " + it.getMovie()));

        } catch ( Error e){
             e.printStackTrace();
        }

        sc.close();
    }
}


