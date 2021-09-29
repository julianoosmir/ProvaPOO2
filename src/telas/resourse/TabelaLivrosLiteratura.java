package telas.resourse;

import models.Literatura;

import java.util.List;
/*
Nome: Juliano Cesar Osmir Hein RA: 339112013098
Curso: Ciência da Computação Data: 29/09/2021
*/
public class TabelaLivrosLiteratura extends ViewAbstractTableModel<Literatura> {

    public TabelaLivrosLiteratura(List<Literatura> rows) {
        super(rows);
        columns = new String[]{
                "codigo","autor",
                "precoCusto", "precoVenda",
                "qtdPaginas",
                "tributacao","genero"
        };
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Literatura literatura = rows.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return literatura.getCodigo();
            case 1:
                return literatura.getAutor().getNome();
            case 2:
                return literatura.getPrecoCusto();
            case 3:
                return literatura.getPrecoVenda();
            case 4:
                return literatura.getQtdPaginas();
            case 5:
                return literatura.getTributacao();
            case 6:
                return literatura.getGenero();
            default:
                return null;
        }
    }
}
