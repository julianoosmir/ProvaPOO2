package banco;

import models.Autor;
import models.Literatura;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Banco implements Serializable {

    private List<Autor> autors;
    private List<Literatura> literaturas;

    public Banco() {
        this.autors =  new ArrayList();
        this.literaturas = new ArrayList<>();
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
}
