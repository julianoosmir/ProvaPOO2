package models;
/*
Nome: Juliano Cesar Osmir Hein RA: 339112013098
Curso: Ciência da Computação Data: 29/09/2021
*/
public class Literatura extends Livro {
    private String genero;

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public void calcularTributacao() {
        setTributacao((getPrecoCusto() * 0.12) + 2);
    }
}
