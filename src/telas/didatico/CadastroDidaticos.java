package telas.didatico;

import banco.Banco;
import models.Autor;
import models.Didatico;
import telas.resourse.TabelaLivrosDidaticos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CadastroDidaticos extends JPanel {

    private JButton botaoAdd;
    private JButton botaoUp;
    private JLabel jLabel;
    private JTextField precocusto = new JTextField(10);
    private JTextField qtdpag = new JTextField(10);
    private JTextField porcentual = new JTextField(20);
    private JComboBox autor = new JComboBox();
    private JComboBox comboBoxNivel = new JComboBox();
    private JComboBox comboBoxSeries = new JComboBox();
    private TabelaLivrosDidaticos tabelaLivrosDidaticos;
    private JTable jTable;
    private Didatico didatico = new Didatico();


    List<String> niveis = new ArrayList<String>(List.of("fundamental", "medio", "superior"));
    List<Integer> series = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

    public CadastroDidaticos(Banco banco) {
        setSize(1200, 600);
        jLabel = new JLabel("Cadastrar Livros didaticos");
        add(jLabel);
        tabelaLivrosDidaticos = new TabelaLivrosDidaticos(banco.getDidaticos());
        jTable = new JTable(tabelaLivrosDidaticos);
        jTable.setPreferredScrollableViewportSize(new Dimension(1200, 100));
        add(new JScrollPane(jTable));
        botaoAdd = new JButton("adicionar livro didatico");
        botaoAdd.setBounds(500, 340, 100, 20);
        botaoAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inserir(banco, tabelaLivrosDidaticos);
            }
        });
        botaoUp = new JButton("atualizar");
        botaoUp.setBounds(500, 340, 100, 20);
        botaoUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer codigo = Integer.parseInt(JOptionPane.showInputDialog("digite codigo"));
                atualizar(codigo, banco, tabelaLivrosDidaticos);
            }
        });
        add(botaoAdd);
        add(botaoUp);

    }

    public void atualizar(Integer codigo, Banco banco, TabelaLivrosDidaticos tabela) {
        Didatico didatico = banco.getDidaticos().get(codigo -1);
        JDialog jdialog = new JDialog();
        jdialog.setSize(1200, 100);
        jdialog.setLayout(new FlowLayout());
        jdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        jdialog.add(new JLabel("Autor :"));
        banco.getAutors().forEach(x -> autor.addItem(x.getNome()));
        jdialog.add(autor);
        jdialog.add(new JLabel("Preço de custo :"));
        precocusto.setText(didatico.getPrecoCusto().toString());
        jdialog.add(precocusto);
        jdialog.add(new JLabel("quantidade paginas :"));
        qtdpag.setText(didatico.getQtdPaginas().toString());
        jdialog.add(qtdpag);
        jdialog.add(new JLabel("porcentual :"));
        jdialog.add(porcentual);
        jdialog.add(new JLabel("nivels :"));
        niveis.forEach(x -> comboBoxNivel.addItem(x));
        comboBoxNivel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Integer> novaSeries = series;
                if (comboBoxNivel.getSelectedItem().toString() == "medio") {
                    novaSeries = series.stream().filter(x -> x <= 3).collect(Collectors.toList());
                } else if (comboBoxNivel.getSelectedItem().toString() == "superior") {
                    novaSeries = series.stream().filter(x -> x <= 6).collect(Collectors.toList());
                }
                comboBoxSeries.removeAllItems();
                novaSeries.forEach(x -> comboBoxSeries.addItem(x.toString()));
            }
        });
        add(comboBoxNivel);
        add(new JLabel("serie :"));
        series.forEach(x -> comboBoxSeries.addItem(x.toString()));
        add(comboBoxSeries);
        JButton salvar = new JButton("Salvar");
        JButton sair = new JButton("Sair");
        String nomeAutor = autor.getSelectedItem().toString();
        String nivel = comboBoxNivel.getSelectedItem().toString();
        String series = comboBoxSeries.getSelectedItem().toString();
        salvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeAutor = autor.getSelectedItem().toString();
                String nivel = comboBoxNivel.getSelectedItem().toString();
                String series = comboBoxSeries.getSelectedItem().toString();
                atulizarDidatico(nomeAutor, Double.parseDouble(precocusto.getText()),
                        Integer.parseInt(porcentual.getText()), Integer.parseInt(qtdpag.getText()),
                        banco, tabela, nivel, Integer.parseInt(series), codigo - 1);

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
    private void atulizarDidatico(String nomeAutor, Double precoCusto, Integer porcentual, Integer qtdpaginas,
                                  Banco banco, TabelaLivrosDidaticos tabela, String nivel, int series, Integer codigo) {
        Didatico didatico = banco.getDidaticos().get(codigo);
        didatico.setAutor(new Autor(nomeAutor, null));
        didatico.setQtdPaginas(qtdpaginas);
        didatico.setPrecoCusto(precoCusto);
        didatico.calcularTributacao();
        didatico.setNivel(nivel);
        didatico.setSerie(series);
        didatico.setPrecoVenda(
                (this.didatico.getPrecoCusto() + this.didatico.getTributacao()) +  ((porcentual / 100) * (this.didatico.getPrecoCusto() + this.didatico.getTributacao()))
        );
        tabela.fireTableDataChanged();
    }

    private void inserir(Banco banco, TabelaLivrosDidaticos tabela) {
        JDialog jdialog = new JDialog();
        jdialog.setSize(1200, 100);
        jdialog.setLayout(new FlowLayout());
        jdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        jdialog.add(new JLabel("Autor :"));
        banco.getAutors().forEach(x -> autor.addItem(x.getNome()));
        jdialog.add(autor);
        jdialog.add(new JLabel("Preço de custo :"));
        jdialog.add(precocusto);
        jdialog.add(new JLabel("quantidade paginas :"));
        jdialog.add(qtdpag);
        jdialog.add(new JLabel("porcentual :"));
        jdialog.add(porcentual);
        jdialog.add(new JLabel("nivels :"));
        niveis.forEach(x -> comboBoxNivel.addItem(x));
        comboBoxNivel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Integer> novaSeries = series;
                if (comboBoxNivel.getSelectedItem().toString() == "medio") {
                    novaSeries = series.stream().filter(x -> x <= 3).collect(Collectors.toList());
                } else if (comboBoxNivel.getSelectedItem().toString() == "superior") {
                    novaSeries = series.stream().filter(x -> x <= 6).collect(Collectors.toList());
                }
                comboBoxSeries.removeAllItems();
                novaSeries.forEach(x -> comboBoxSeries.addItem(x.toString()));
            }
        });
        jdialog.add(comboBoxNivel);
        jdialog.add(new JLabel("serie :"));
        series.forEach(x -> comboBoxSeries.addItem(x.toString()));
        jdialog.add(comboBoxSeries);

        JButton salvar = new JButton("Salvar");
        JButton sair = new JButton("Sair");

        String nomeAutor = autor.getSelectedItem().toString();
        String nivel = comboBoxNivel.getSelectedItem().toString();
        String series = comboBoxSeries.getSelectedItem().toString();

        salvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeAutor = autor.getSelectedItem().toString();
                String nivel = comboBoxNivel.getSelectedItem().toString();
                String series = comboBoxSeries.getSelectedItem().toString();
                criarDidatico(nomeAutor, Double.parseDouble(precocusto.getText()),
                        Integer.parseInt(porcentual.getText()), Integer.parseInt(qtdpag.getText()),
                        banco, tabela, nivel, Integer.parseInt(series));
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

    private void criarDidatico(String nomeAutor, Double precoCusto, Integer porcentual,
                               Integer qtdpaginas, Banco banco, TabelaLivrosDidaticos tabela, String nivel, int series) {

        this.didatico.setCodigo(banco.getLiteraturas().size() + 1);
        this.didatico.setAutor(new Autor(nomeAutor, null));
        this.didatico.setQtdPaginas(qtdpaginas);
        this.didatico.setPrecoCusto(precoCusto);
        this.didatico.calcularTributacao();
        this.didatico.setNivel(nivel);
        this.didatico.setSerie(series);

        this.didatico.setPrecoVenda(
                (this.didatico.getPrecoCusto() + this.didatico.getTributacao()) + ((porcentual / 100) * (this.didatico.getPrecoCusto() + this.didatico.getTributacao()))
        );
        banco.setDidaticos(this.didatico);
        tabela.fireTableDataChanged();
    }
}
