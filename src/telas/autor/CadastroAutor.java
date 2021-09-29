package telas.autor;


import banco.Banco_Julianoosmir;
import models.Autor_Julianoosmir;
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
    private Autor_Julianoosmir autorJulianoosmir;

    public CadastroAutor(Banco_Julianoosmir bancoJulianoosmir) {
        setSize(1200,1000);
        jLabel = new JLabel("Cadastrar autor");
        add(jLabel);
        inserirTable(bancoJulianoosmir);

        botaoAdd = new JButton("adiconar autor ");
        botaoAdd.setBounds(500, 340, 100, 20);
        botaoAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autorJulianoosmir = new Autor_Julianoosmir();
                autorJulianoosmir.setNome(JOptionPane.showInputDialog("nome").toString());
                autorJulianoosmir.setDataNascimento(LocalDate.parse(JOptionPane.showInputDialog("data de nacimento").toString(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                bancoJulianoosmir.setAutor(autorJulianoosmir);
                tabelaAutor.fireTableDataChanged();
            }
        });

        add(botaoAdd);

    }




    public void inserirTable(Banco_Julianoosmir bancoJulianoosmir){
        tabelaAutor = new TabelaAutor(bancoJulianoosmir.getAutors());
        jTable = new JTable(tabelaAutor);
        jTable.setPreferredScrollableViewportSize(new Dimension(1200, 100));
        add(new JScrollPane(jTable));
    }
}
