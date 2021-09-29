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
/*
Nome: Juliano Cesar Osmir Hein RA: 339112013098
Curso: Ciência da Computação Data: 29/09/2021
*/
public class CadastroDidaticos extends JPanel {

    private JButton botaoAdd;
    private JButton botaoUp;
    private JLabel jLabel;
    private JTextField precocusto;
    private JTextField qtdpag;
    private JTextField porcentual;
    private JComboBox autor;
    private JComboBox comboBoxNivel;
    private JComboBox comboBoxSeries;
    private TabelaLivrosDidaticos tabelaLivrosDidaticos;
    private JTable jTable;
    private Didatico didatico;


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
        didatico = banco.getDidaticos().get(codigo -1);
        JDialog jdialog = new JDialog();
        jdialog.setSize(1250, 100);
        jdialog.setLayout(new FlowLayout());
        jdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        jdialog.add(new JLabel("Autor :"));
        autor = new JComboBox();
        banco.getAutors().forEach(x -> autor.addItem(x.getNome()));
        jdialog.add(autor);
        jdialog.add(new JLabel("Preço de custo :"));
        precocusto = new JTextField(10);
        precocusto.setText(didatico.getPrecoCusto().toString());
        jdialog.add(precocusto);
        jdialog.add(new JLabel("quantidade paginas :"));
        qtdpag = new JTextField(10);
        qtdpag.setText(didatico.getQtdPaginas().toString());
        jdialog.add(qtdpag);
        jdialog.add(new JLabel("porcentual :"));
        porcentual = new JTextField(10);
        jdialog.add(porcentual);
        jdialog.add(new JLabel("nivels :"));
        comboBoxNivel = new JComboBox();
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
        comboBoxSeries = new JComboBox();
        series.forEach(x -> comboBoxSeries.addItem(x.toString()));
        jdialog.add(comboBoxSeries);
        JButton salvar = new JButton("Salvar");
        JButton sair = new JButton("Sair");

        salvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeAutor = autor.getSelectedItem().toString();
                String nivel = comboBoxNivel.getSelectedItem().toString();
                String series = comboBoxSeries.getSelectedItem().toString();
                String valorPorcentual = porcentual.getText().length() == 0 ? "0" :  porcentual.getText();
                atulizarDidatico(nomeAutor, Double.parseDouble(precocusto.getText()),
                        Integer.parseInt(valorPorcentual), Integer.parseInt(qtdpag.getText()),
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
        didatico = banco.getDidaticos().get(codigo);
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
        jdialog.setSize(1250, 100);
        jdialog.setLayout(new FlowLayout());
        jdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        jdialog.add(new JLabel("Autor :"));
        autor = new JComboBox();
        banco.getAutors().forEach(x -> autor.addItem(x.getNome()));
        jdialog.add(autor);
        jdialog.add(new JLabel("Preço de custo :"));
        precocusto = new JTextField(10);
        jdialog.add(precocusto);
        jdialog.add(new JLabel("quantidade paginas :"));
        qtdpag = new JTextField(10);
        jdialog.add(qtdpag);
        jdialog.add(new JLabel("porcentual :"));
        porcentual = new JTextField(10);
        jdialog.add(porcentual);
        jdialog.add(new JLabel("nivels :"));
        comboBoxNivel = new JComboBox();
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
        comboBoxSeries = new JComboBox();
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
                jdialog.removeAll();
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

        didatico = new Didatico();
        didatico.setCodigo(banco.getDidaticos().size() + 1);
        didatico.setAutor(new Autor(nomeAutor, null));
        didatico.setQtdPaginas(qtdpaginas);
        didatico.setPrecoCusto(precoCusto);
        didatico.calcularTributacao();
        didatico.setNivel(nivel);
        didatico.setSerie(series);

        didatico.setPrecoVenda(
                (didatico.getPrecoCusto() + didatico.getTributacao()) + ((porcentual / 100) * (didatico.getPrecoCusto() + didatico.getTributacao()))
        );
        banco.setDidaticos(didatico);
        tabela.fireTableDataChanged();
    }
}
