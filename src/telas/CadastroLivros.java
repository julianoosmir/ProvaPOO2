package telas;

import banco.Banco;
import telas.autor.CadastroAutor;
import telas.didatico.CadastroDidaticos;
import telas.literatura.CadastroLiteratura;

import javax.swing.*;
/*
Nome: Juliano Cesar Osmir Hein RA: 339112013098
Curso: Ciência da Computação Data: 29/09/2021
*/
public class CadastroLivros extends JFrame {

    CadastroLiteratura cadastroLiteratura;
    CadastroAutor cadastroAutor;
    CadastroDidaticos cadastroDidaticos;

    public CadastroLivros(Banco banco) {
        setTitle("Cadastro de livros");
        setSize(1200,600);
        setLocationRelativeTo(null);
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        cadastroLiteratura = new CadastroLiteratura(banco);
        cadastroAutor = new CadastroAutor(banco);
        cadastroDidaticos = new CadastroDidaticos(banco);

        container.add(cadastroAutor);
        container.add(cadastroDidaticos);
        container.add(cadastroLiteratura);

        this.setContentPane(container);
        this.setResizable(false);
    }

}
