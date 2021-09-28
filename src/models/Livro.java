package models;

import java.util.UUID;

abstract class Livro {
    private UUID codigo;
    private Autor autor;
    private Double precoCusto;
    private Double precoVenda;
    private Integer qtdPaginas;
    private Double tributacao;

    abstract void calcularTributacao();

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Double getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(Double precoCusto) {
        this.precoCusto = precoCusto;
    }

    public Double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(Double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public Integer getQtdPaginas() {
        return qtdPaginas;
    }

    public void setQtdPaginas(Integer qtdPaginas) {
        this.qtdPaginas = qtdPaginas;
    }

    public Double getTributacao() {
        return tributacao;
    }

    public void setTributacao(Double tributacao) {
        this.tributacao = tributacao;
    }
}
