package telas;

import banco.Banco_Julianoosmir;
import telas.autor.CadastroAutor;
import telas.didatico.CadastroDidaticos;
import telas.literatura.CadastroLiteratura;

import javax.swing.*;

public class CadastroLivros extends JFrame {

    CadastroLiteratura cadastroLiteratura;
    CadastroAutor cadastroAutor;
    CadastroDidaticos cadastroDidaticos;

    public CadastroLivros(Banco_Julianoosmir bancoJulianoosmir) {
        setTitle("Cadastro de livros");
        setSize(1200,600);
        setLocationRelativeTo(null);
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        cadastroLiteratura = new CadastroLiteratura(bancoJulianoosmir);
        cadastroAutor = new CadastroAutor(bancoJulianoosmir);
        cadastroDidaticos = new CadastroDidaticos(bancoJulianoosmir);

        container.add(cadastroAutor);
        container.add(cadastroDidaticos);
        container.add(cadastroLiteratura);

        this.setContentPane(container);
        this.setResizable(false);
    }

}
