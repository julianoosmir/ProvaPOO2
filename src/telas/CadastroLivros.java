package telas;

import banco.Banco;
import telas.autor.CadastroAutor;
import telas.literatura.CadastroLiteratura;

import javax.swing.*;

public class CadastroLivros extends JFrame {

    CadastroLiteratura cadastroLiteratura;
    CadastroAutor cadastroAutor;

    public CadastroLivros(Banco banco) {
        setTitle("Cadastro de livros");
        setSize(1200,600);
        setLocationRelativeTo(null);
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        cadastroLiteratura = new CadastroLiteratura(banco);
        cadastroAutor = new CadastroAutor(banco);


        container.add(cadastroAutor);

        container.add(cadastroLiteratura);


        this.setContentPane(container);

//        this.setContentPane(cadastroAutor);
//        this.setContentPane(cadastroLiteratura);

        this.setResizable(false);
    }

}
