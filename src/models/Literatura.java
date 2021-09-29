package models;

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
        setTributacao((getPrecoCusto() * 1.12) + 2);
    }
}
