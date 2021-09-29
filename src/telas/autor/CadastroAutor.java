package telas.autor;


import banco.Banco;
import models.Autor;
import telas.resourse.TabelaAutor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class CadastroAutor  extends JPanel {

    private JButton botaoAdd;
    private JLabel jLabel;
    private TabelaAutor tabelaAutor;
    private JTable jTable;
    private Autor autor;

    public CadastroAutor(Banco banco) {
        setSize(1200,1000);
        jLabel = new JLabel("Cadastrar autor");
        add(jLabel);
        inserirTable(banco);

        botaoAdd = new JButton("adiconar autor ");
        botaoAdd.setBounds(500, 340, 100, 20);
        botaoAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autor = new Autor();
                autor.setNome(JOptionPane.showInputDialog("nome").toString());
                autor.setDataNascimento(LocalDate.parse(JOptionPane.showInputDialog("data de nacimento").toString(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                banco.setAutor(autor);
                tabelaAutor.fireTableDataChanged();
            }
        });

        add(botaoAdd);

    }




    public void inserirTable(Banco banco){
        tabelaAutor = new TabelaAutor(banco.getAutors());
        jTable = new JTable(tabelaAutor);
        jTable.setPreferredScrollableViewportSize(new Dimension(1200, 100));
        add(new JScrollPane(jTable));
    }
}
