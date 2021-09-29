package models;

import java.time.LocalDate;

public class Autor_Julianoosmir {
    private String nome;
    private LocalDate dataNascimento;

    public Autor_Julianoosmir() {
    }


    public Autor_Julianoosmir(String nome, LocalDate dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }


    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return this.dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

}

   