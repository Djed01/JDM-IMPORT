package org.unibl.etf.GUI;

import org.unibl.etf.MODELS.Car;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public  class CarTableModel extends AbstractTableModel {
    private List<Car> data;
    private boolean[] columnEditables = new boolean[] { false, false, false, false, false, false };
    private String[] header = new String[] { "Id", "Brand", "Model", "Year", "Price", "Image URL"};

    public CarTableModel() {
        this.data = new ArrayList<Car>();
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return columnEditables[column];
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    public List<Car> getData(){
        return this.data;

    }

    public void updateData(List<Car>data) {
        this.data=data;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Car obj = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return obj.getId();
            case 1:
                return obj.getBrand();
            case 2:
                return obj.getModel();
            case 3:
                return obj.getYear().split("-")[0];
            case 4:
                return obj.getPrice();
            case 5:
                return obj.getImageURL();
            default:
                return null;
        }
    }

    public void addRow(Car car) {
        data.add(car);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public Car getAt(int row) {
        return this.data.get(row);
    }

    public void deleteRow(int row) {
        this.data.remove(row);
        fireTableRowsDeleted(row, row);
    }

    public void updateRow(int index, Car car) {
        this.data.set(index, car);
        fireTableRowsUpdated(index, index);
    }
}