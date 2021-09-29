package telas.literatura;

import banco.Banco;
import models.Autor;
import models.Literatura;
import telas.resourse.TabelaLivrosLiteratura;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CadastroLiteratura extends JPanel {

    private JButton botaoAdd;
    private JButton botaoUp;
    private JLabel jLabel;
    private JTextField precocusto = new JTextField(10);
    private JTextField qtdpag = new JTextField(10);
    private JTextField genero = new JTextField(20);
    private JTextField porcentual = new JTextField(20);
    private JComboBox jComboBox;
    private TabelaLivrosLiteratura tabelaLivrosLiteratura;
    private JTable jTable;
    private Literatura literatura = new Literatura();


    public CadastroLiteratura(Banco banco) {


        setSize(1200, 600);
        jLabel = new JLabel("Cadastrar Livros de Literatura");
        add(jLabel);
        tabelaLivrosLiteratura = new TabelaLivrosLiteratura(banco.getLiteraturas());
        jTable = new JTable(tabelaLivrosLiteratura);
        jTable.setPreferredScrollableViewportSize(new Dimension(1200, 100));
        add(new JScrollPane(jTable));
        botaoAdd = new JButton("adiconar livro literatura");
        botaoAdd.setBounds(500, 340, 100, 20);
        botaoAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inserir(banco, tabelaLivrosLiteratura);
            }
        });

        botaoUp = new JButton("atualizar");
        botaoUp.setBounds(500, 340, 100, 20);
        botaoUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer codigo = Integer.parseInt(JOptionPane.showInputDialog("digite codigo"));
                atualizar(codigo,banco,tabelaLivrosLiteratura);
            }
        });

        add(botaoAdd);
    }

    private void atualizar(Integer codigo, Banco banco, TabelaLivrosLiteratura tabelaLivrosLiteratura) {

    }

    public void inserir(Banco banco, TabelaLivrosLiteratura tabela) {

        JDialog jdialog = new JDialog();
        jdialog.setSize(1200, 100);
        jdialog.setLayout(new FlowLayout());
        jdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        jdialog.add(new JLabel("Autor :"));
        jComboBox = new JComboBox();
        banco.getAutors().forEach(x -> jComboBox.addItem(x.getNome()));

        jdialog.add(jComboBox);
        jdialog.add(new JLabel("Preço de custo :"));
        jdialog.add(precocusto);
        jdialog.add(new JLabel("quantidade paginas :"));
        jdialog.add(qtdpag);
        jdialog.add(new JLabel("genero :"));
        jdialog.add(genero);
        jdialog.add(new JLabel("porcentual :"));
        jdialog.add(porcentual);
        JButton salvar = new JButton("Salvar");
        JButton sair = new JButton("Sair");

        String nomeAutor = jComboBox.getSelectedItem().toString();
        salvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CriarLiteratura(nomeAutor, Double.parseDouble(precocusto.getText()),
                        Integer.parseInt(porcentual.getText()), Integer.parseInt(qtdpag.getText()),
                        genero.getText(), banco, tabela);
                jdialog.dispose();
            }
        });
        sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jdialog.dispose();
            }
        });

        jdialog.add(salvar);
        jdialog.add(sair);
        jdialog.setVisible(true);
    }

    public void CriarLiteratura(String nomeAutor, Double precoCusto, Integer porcentual, Integer qtdpaginas, String genero,
                                Banco banco, TabelaLivrosLiteratura tabela) {

        this.literatura.setCodigo(banco.getLiteraturas().size() + 1);
        this.literatura.setAutor(new Autor(nomeAutor, null));
        this.literatura.setGenero(genero);
        this.literatura.setQtdPaginas(qtdpaginas);
        this.literatura.setPrecoCusto(precoCusto);
        this.literatura.calcularTributacao();

        this.literatura.setPrecoVenda(
                (this.literatura.getPrecoCusto() + this.literatura.getTributacao()) +
                        (porcentual / 100) * (this.literatura.getPrecoCusto() + this.literatura.getTributacao())
        );

        banco.setLiteraturas(this.literatura);


        tabela.fireTableDataChanged();


    }
}
