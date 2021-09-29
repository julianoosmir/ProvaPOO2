package models;
/*
Nome: Juliano Cesar Osmir Hein RA: 339112013098
Curso: Ciência da Computação Data: 29/09/2021
*/
public class Didatico extends Livro {

    private String nivel;
    private Integer serie;

    public Didatico() {
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public Integer getSerie() {
        return serie;
    }

    public void setSerie(Integer serie) {
        this.serie = serie;
    }

    @Override
    public void calcularTributacao() {
        this.setTributacao(getPrecoCusto() * 0.03);
    }
}
