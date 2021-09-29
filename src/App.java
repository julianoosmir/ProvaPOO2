import banco.Banco;
import models.Autor;
import telas.CadastroLivros;

public class App {
    public static void main(String[] args) throws Exception {
        Banco banco = new Banco();
        Autor autor = new Autor();
        autor.setNome("juliano");
        banco.setAutor(autor);
        new CadastroLivros(banco).setVisible(true);
    }
}
