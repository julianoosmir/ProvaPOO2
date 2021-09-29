import banco.Banco_Julianoosmir;
import models.Autor_Julianoosmir;
import telas.CadastroLivros;

/*
 *  Nome: Juliano Cesar Osmir hein RA: 339112013098
 *  Curso: ciencia da computação data: 29/09/2021
 *
 */


public class App_Julianoosmir {
    public static void main(String[] args) throws Exception {
        Banco_Julianoosmir bancoJulianoosmir = new Banco_Julianoosmir();
        Autor_Julianoosmir autorJulianoosmir = new Autor_Julianoosmir();
        autorJulianoosmir.setNome("juliano");
        bancoJulianoosmir.setAutor(autorJulianoosmir);
        new CadastroLivros(bancoJulianoosmir).setVisible(true);
    }
}
