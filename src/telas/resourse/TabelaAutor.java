package telas.resourse;

import models.Autor;

import java.util.List;

public class TabelaAutor extends ViewAbstractTableModel<Autor>{
    public TabelaAutor(List<Autor> rows) {
        super(rows);
        columns = new String[]{
                "nome", "data de Nacimento",
        };
    }



    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Autor autor =  rows.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return autor.getNome();
            case 1:
                return autor.getDataNascimento();
            default:
                return null;
        }
    }
}
