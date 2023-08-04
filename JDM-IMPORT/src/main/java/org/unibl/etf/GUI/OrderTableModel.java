package org.unibl.etf.GUI;

import org.unibl.etf.MODELS.Car;
import org.unibl.etf.MODELS.Company;
import org.unibl.etf.MODELS.Individual;
import org.unibl.etf.MODELS.Order;

import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class OrderTableModel extends AbstractTableModel {
    private List<Order> data;
    private boolean[] columnEditables = new boolean[] { false, false, false, false, false, false,false, false, false, false, false};
    private String[] header = new String[] { "Order ID", "Name", "Surname", "Company Name", "Email", "Phone","Brand","Model","Year","Quantity","Total Price"};

    public OrderTableModel() {
        this.data = new ArrayList<Order>();
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

    public List<Order> getData(){
        return this.data;

    }

    public void updateData(List<Order>data) {
        this.data=data;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Order obj = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return obj.getId();
            case 1:
                if(obj.getCustomer() instanceof Individual) {
                    return ((Individual)obj.getCustomer()).getFirstName();
                }
                else return  null;
            case 2:
                if(obj.getCustomer() instanceof Individual) {
                    return ((Individual)obj.getCustomer()).getLastName();
                }
                else return  null;
            case 3:
                if (obj.getCustomer() instanceof Company) {
                    return ((Company) obj.getCustomer()).getName();
                } else return null;
            case 4:
                return obj.getCustomer().getEmail();
            case 5:
                return obj.getCustomer().getPhone();
            case 6:
                return obj.getCar().getBrand();
            case 7:
                return obj.getCar().getModel();
            case 8:
                return obj.getCar().getYear();
            case 9:
                return obj.getQuantity();
            case 10:
                return obj.getOrderTotal();
            default:
                return null;
        }
    }

    public void addRow(Order order) {
        data.add(order);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public Order getAt(int row) {
        return this.data.get(row);
    }

    public void deleteRow(int row) {
        this.data.remove(row);
        fireTableRowsDeleted(row, row);
    }

    public void updateRow(int index, Order order) {
        this.data.set(index, order);
        fireTableRowsUpdated(index, index);
    }

}
