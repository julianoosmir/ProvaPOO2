package telas.resourse;

import models.Didatico;
import java.util.List;

public class TabelaLivrosDidaticos extends ViewAbstractTableModel<Didatico>{
    public TabelaLivrosDidaticos(List<Didatico> rows) {
        super(rows);
        columns = new String[]{
                "codigo","autor",
                "precoCusto", "precoVenda",
                "qtdPaginas","tributacao",
                "nivel","serie"
        };
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Didatico didatico = rows.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return didatico.getCodigo();
            case 1:
                return didatico.getAutor().getNome();
            case 2:
                return didatico.getPrecoCusto();
            case 3:
                return didatico.getPrecoVenda();
            case 4:
                return didatico.getQtdPaginas();
            case 5:
                return didatico.getTributacao();
            case 6:
                return didatico.getNivel();
            case 7:
                return didatico.getSerie();
            default:
                return null;
        }
    }
}
