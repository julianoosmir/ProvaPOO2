import banco.Banco;
import models.Autor;
import telas.CadastroLivros;
/*
Nome: Juliano Cesar Osmir Hein RA: 339112013098
Curso: Ciência da Computação Data: 29/09/2021
*/
public class App {
    public static void main(String[] args) throws Exception {
        Banco banco = new Banco();
        Autor autor = new Autor();
        autor.setNome("juliano");
        banco.setAutor(autor);
        new CadastroLivros(banco).setVisible(true);
    }
}
