package models;

abstract class Livro {
    private Integer codigo;

    private Autor_Julianoosmir autorJulianoosmir;
    private Double precoCusto;
    private Double precoVenda;
    private Integer qtdPaginas;
    private Double tributacao;

    abstract void calcularTributacao();

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Autor_Julianoosmir getAutor() {
        return autorJulianoosmir;
    }

    public void setAutor(Autor_Julianoosmir autorJulianoosmir) {
        this.autorJulianoosmir = autorJulianoosmir;
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
