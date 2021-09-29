package banco;

import models.Autor;
import models.Didatico;
import models.Literatura;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/*
Nome: Juliano Cesar Osmir Hein RA: 339112013098
Curso: Ciência da Computação Data: 29/09/2021
*/
public class Banco implements Serializable {

    private List<Autor> autors;
    private List<Literatura> literaturas;
    private List<Didatico> didaticos;

    public Banco() {
        this.autors =  new ArrayList();
        this.literaturas = new ArrayList<>();
        this.didaticos = new ArrayList<>();
    }

    public void setAutor(Autor autor){
        this.autors.add(autor);
    }

    public List<Autor> getAutors() {
        return autors;
    }

    public void setLiteraturas(Literatura literatura){
        this.literaturas.add(literatura);
    }

    public List<Literatura> getLiteraturas() {
        return literaturas;
    }

    public List<Didatico> getDidaticos() {
        return didaticos;
    }
    public void setDidaticos(Didatico didatico){
        this.didaticos.add(didatico);
    }
}
