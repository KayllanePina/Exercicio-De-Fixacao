package br.com.segundoprojeto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LeituraDeArquivos{
    private final List<TabelaDeArtistas> tabelaDeArtistasList;

    public LeituraDeArquivos(String filename){
        this.tabelaDeArtistasList = lerTabelas(filename);
    }

    private List<TabelaDeArtistas> lerTabelas(String filename){
        try(Stream<String> fileLines = Files.lines(Paths.get(filename))){
            return fileLines
                    .skip(1)
                    .map(TabelaDeArtistas::of)
                    .collect(Collectors.toList());
        } catch (IOException e){
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<TabelaDeArtistas> getTabelaDeArtistasList() {
        return tabelaDeArtistasList;
    }
}
