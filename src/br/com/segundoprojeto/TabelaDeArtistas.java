package br.com.segundoprojeto;

import java.util.Arrays;

import static java.lang.Integer.*;

public class TabelaDeArtistas {
    private int index;
    private int year;
    private int age;
    private String name;
    private String movie;

    private TabelaDeArtistas(int index, int year, int age, String name, String movie) {
        this.index = index;
        this.year = year;
        this.age = age;
        this.name = name;
        this.movie = movie;
    }

    public static TabelaDeArtistas of(String line){
        String[] split = line.split(";\\s");

        return new TabelaDeArtistas(
                //NumberFormatException For input string: "1; 1928; 44; Emil Jannings; The Last Command, The Way of All Flesh
                Integer.parseInt(split[0]),
                Integer.parseInt(split[1]),
                Integer.parseInt(split[2]),
                split[3],
                split[4]
                );

    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "LeituraDeArquivos{" +
                "index=" + index +
                ", year=" + year +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", movie='" + movie + '\'' +
                '}';
    }
}
