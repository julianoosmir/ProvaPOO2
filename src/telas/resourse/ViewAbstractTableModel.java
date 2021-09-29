package telas.resourse;

import javax.swing.table.AbstractTableModel;
import java.util.List;
/*
Nome: Juliano Cesar Osmir Hein RA: 339112013098
Curso: Ciência da Computação Data: 29/09/2021
*/
public abstract class ViewAbstractTableModel<E> extends AbstractTableModel{

    protected List<E> rows;
    protected String[] columns;

    public ViewAbstractTableModel(List<E> rows) {
        this.rows = rows;
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        if(column < getColumnCount())
            return columns[column];
        return super.getColumnName(column);
    }

    public E getValueAtRow(int row){
        return rows.get(row);
    }

    public void setValueAtRow(int row, E object){
        rows.set(row, object);
    }
}