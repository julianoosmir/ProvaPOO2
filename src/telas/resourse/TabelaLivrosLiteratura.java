package telas.resourse;

import models.Literatura;

import java.util.List;

public class TabelaLivrosLiteratura extends ViewAbstractTableModel<Literatura> {

    public TabelaLivrosLiteratura(List<Literatura> rows) {
        super(rows);
        columns = new String[]{
                "autor",
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
                return literatura.getAutor().getNome();
            case 1:
                return literatura.getPrecoCusto();
            case 2:
                return literatura.getPrecoVenda();
            case 3:
                return literatura.getQtdPaginas();
            case 4:
                return literatura.getTributacao();
            case 5:
                return literatura.getGenero();
            default:
                return null;
        }
    }
}
