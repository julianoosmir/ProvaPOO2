package telas.resourse;

import models.Autor_Julianoosmir;

import java.util.List;

public class TabelaAutor extends ViewAbstractTableModel<Autor_Julianoosmir>{
    public TabelaAutor(List<Autor_Julianoosmir> rows) {
        super(rows);
        columns = new String[]{
                "nome", "data de Nacimento",
        };
    }



    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Autor_Julianoosmir autorJulianoosmir =  rows.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return autorJulianoosmir.getNome();
            case 1:
                return autorJulianoosmir.getDataNascimento();
            default:
                return null;
        }
    }
}
