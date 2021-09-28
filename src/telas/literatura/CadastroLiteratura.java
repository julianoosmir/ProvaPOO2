package telas.literatura;

import banco.Banco;
import models.Autor;
import models.Literatura;
import telas.resourse.TabelaLivrosLiteratura;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class CadastroLiteratura extends JPanel {

    private JButton botao;
    private JLabel jLabel;
    private JTextField precocusto = new JTextField(10);
    private JTextField qtdpag = new JTextField(10);
    private JTextField genero = new JTextField(20);
    private JComboBox jComboBox;
    private TabelaLivrosLiteratura tabelaLivrosLiteratura;
    private JTable jTable;


    public CadastroLiteratura(Banco banco) {

        setSize(1200,600);
        jLabel = new JLabel("Cadastrar Livros de Literatura");
        add(jLabel);
        tabelaLivrosLiteratura = new TabelaLivrosLiteratura(banco.getLiteraturas());
        jTable = new JTable(tabelaLivrosLiteratura);
        jTable.setPreferredScrollableViewportSize(new Dimension(1200, 100));
        add(new JScrollPane(jTable));
        botao = new JButton("adiconar livro");
        botao.setBounds(500, 340, 100, 20);
        botao.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {

                inserir(banco.getAutors());
                tabelaLivrosLiteratura.fireTableDataChanged();
            }
        });
        add(botao);

    }
    public void inserir(List<Autor> autors){

        JDialog jdialog = new JDialog();
        jdialog.setSize(1200, 100);
        jdialog.setLayout(new FlowLayout());
        jdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        jdialog.add(new JLabel("Autor :" ));
        jComboBox = new JComboBox();
        autors.forEach(x-> jComboBox.addItem(x.getNome()));

        jdialog.add(jComboBox);
        jdialog.add(new JLabel("Preço de custo :" ));
        jdialog.add(precocusto);
        jdialog.add(new JLabel("quantidade paginas :" ));
        jdialog.add(qtdpag);
        jdialog.add(new JLabel("genero :" ));
        jdialog.add(genero);


        jdialog.setVisible(true);
    }

    public void CriarLiteratura(){

    }
}
