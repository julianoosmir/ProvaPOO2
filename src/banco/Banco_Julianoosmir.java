package banco;

/*
*  Nome: Juliano Cesar Osmir hein RA: 339112013098
*  Curso: ciencia da computação data: 29/09/2021
*
*/


import models.Autor_Julianoosmir;
import models.Didatico;
import models.Literatura;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Banco_Julianoosmir implements Serializable {

    private List<Autor_Julianoosmir> autorJulianoosmirs;
    private List<Literatura> literaturas;
    private List<Didatico> didaticos;

    public Banco_Julianoosmir() {
        this.autorJulianoosmirs =  new ArrayList();
        this.literaturas = new ArrayList<>();
        this.didaticos = new ArrayList<>();
    }

    public void setAutor(Autor_Julianoosmir autorJulianoosmir){
        this.autorJulianoosmirs.add(autorJulianoosmir);
    }

    public List<Autor_Julianoosmir> getAutors() {
        return autorJulianoosmirs;
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
