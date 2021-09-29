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
    private JTextField precocusto;
    private JTextField qtdpag;
    private JTextField genero;
    private JTextField porcentual;
    private JComboBox jComboBox;
    private TabelaLivrosLiteratura tabelaLivrosLiteratura;
    private JTable jTable;
    private Literatura literatura;

    public CadastroLiteratura(Banco banco) {
        setSize(1200, 600);
        jLabel = new JLabel("Cadastrar Livros de Literatura");
        add(jLabel);
        tabelaLivrosLiteratura = new TabelaLivrosLiteratura(banco.getLiteraturas());
        jTable = new JTable(tabelaLivrosLiteratura);
        jTable.setPreferredScrollableViewportSize(new Dimension(1200, 100));
        add(new JScrollPane(jTable));
        botaoAdd = new JButton("adicionar livro literatura");
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
                atualizar(codigo, banco, tabelaLivrosLiteratura);
            }
        });

        add(botaoAdd);
        add(botaoUp);
    }

    private void atualizar(Integer codigo, Banco banco, TabelaLivrosLiteratura tabela) {

        literatura = banco.getLiteraturas().get(codigo - 1);
        JDialog jdialog = new JDialog();
        jdialog.setSize(1250, 100);
        jdialog.setLayout(new FlowLayout());
        jdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        jdialog.add(new JLabel("Autor :"));
        jComboBox = new JComboBox();
        banco.getAutors().forEach(x -> jComboBox.addItem(x.getNome()));
        jdialog.add(jComboBox);
        jdialog.add(new JLabel("Preço de custo :"));
        precocusto = new JTextField(10);
        precocusto.setText(literatura.getPrecoCusto().toString());
        jdialog.add(precocusto);
        jdialog.add(new JLabel("quantidade paginas :"));
        qtdpag = new JTextField(10);
        qtdpag.setText(literatura.getQtdPaginas().toString());
        jdialog.add(qtdpag);
        jdialog.add(new JLabel("genero :"));
        genero = new JTextField(10);
        genero.setText(literatura.getGenero().toString());
        jdialog.add(genero);
        jdialog.add(new JLabel("porcentual :"));
        porcentual = new JTextField(10);
        jdialog.add(porcentual);
        JButton salvar = new JButton("Salvar");
        JButton sair = new JButton("Sair");


        salvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeAutor = jComboBox.getSelectedItem().toString();
                String valorPorcentual = porcentual.getText().length() == 0 ? "0" :  porcentual.getText();
                atualizarItem(nomeAutor, Double.parseDouble(precocusto.getText()),
                        Integer.parseInt(valorPorcentual), Integer.parseInt(qtdpag.getText()),
                        genero.getText(), banco, tabela, codigo - 1);
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

    private void atualizarItem(String nomeAutor, Double precoCusto, Integer porcentual, Integer qtdpaginas, String genero,
                               Banco banco, TabelaLivrosLiteratura tabela, int codigo) {
        literatura = banco.getLiteraturas().get(codigo);
        literatura.setAutor(new Autor(nomeAutor, null));
        literatura.setGenero(genero);
        literatura.setQtdPaginas(qtdpaginas);
        literatura.setPrecoCusto(precoCusto);
        literatura.calcularTributacao();
        if (porcentual > 0) {
            literatura.setPrecoVenda(
                    (literatura.getPrecoCusto() + literatura.getTributacao()) +
                            (porcentual / 100) * (literatura.getPrecoCusto() + literatura.getTributacao())
            );
        }
        tabela.fireTableDataChanged();
    }

    public void inserir(Banco banco, TabelaLivrosLiteratura tabela) {

        JDialog jdialog = new JDialog();
        jdialog.setSize(1250, 100);
        jdialog.setLayout(new FlowLayout());
        jdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        jdialog.add(new JLabel("Autor :"));
        jComboBox = new JComboBox();
        banco.getAutors().forEach(x -> jComboBox.addItem(x.getNome()));
        jdialog.add(jComboBox);
        jdialog.add(new JLabel("Preço de custo :"));
        precocusto = new JTextField(10);
        jdialog.add(precocusto);
        jdialog.add(new JLabel("quantidade paginas :"));
        qtdpag = new JTextField(10);
        jdialog.add(qtdpag);
        jdialog.add(new JLabel("genero :"));
        genero = new JTextField(10);
        jdialog.add(genero);
        jdialog.add(new JLabel("porcentual :"));
        porcentual = new JTextField(10);
        jdialog.add(porcentual);
        JButton salvar = new JButton("Salvar");
        JButton sair = new JButton("Sair");

        salvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeAutor = jComboBox.getSelectedItem().toString();
                criarLiteratura(nomeAutor, Double.parseDouble(precocusto.getText()),
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


    public void criarLiteratura(String nomeAutor, Double precoCusto, Integer porcentual, Integer qtdpaginas, String genero,
                                Banco banco, TabelaLivrosLiteratura tabela) {
        literatura = new Literatura();
        literatura.setCodigo(banco.getLiteraturas().size() + 1);
        literatura.setAutor(new Autor(nomeAutor, null));
        literatura.setGenero(genero);
        literatura.setQtdPaginas(qtdpaginas);
        literatura.setPrecoCusto(precoCusto);
        literatura.calcularTributacao();

        literatura.setPrecoVenda(
                (literatura.getPrecoCusto() + literatura.getTributacao()) +
                        (porcentual / 100) * (literatura.getPrecoCusto() + literatura.getTributacao())
        );
        banco.setLiteraturas(literatura);
        tabela.fireTableDataChanged();

    }
}
