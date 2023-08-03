package org.unibl.etf.GUI;

import org.unibl.etf.MODELS.Car;
import org.unibl.etf.MODELS.Company;
import org.unibl.etf.MODELS.Customer;
import org.unibl.etf.MODELS.Individual;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class CustomerTableModel extends AbstractTableModel {
    private List<Customer> data;
    private boolean[] columnEditables = new boolean[]{false, false, false, false, false, false};
    private String[] header = new String[]{"Id", "First Name", "Last Name", "Company Name", "Email", "Phone"};

    public CustomerTableModel() {
        this.data = new ArrayList<Customer>();
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

    public List<Customer> getData() {
        return this.data;

    }

    public void updateData(List<Customer> data) {
        this.data = data;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Customer obj = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return obj.getId();
            case 1:
                if (obj instanceof Individual)
                    return ((Individual) obj).getFirstName();
                else return null;
            case 2:
                if (obj instanceof Individual)
                    return ((Individual) obj).getLastName();
                else return null;
            case 3:
                if (obj instanceof Company)
                    return ((Company) obj).getName();
                else return null;
            case 4:
                return obj.getEmail();
            case 5:
                return obj.getPhone();
            default:
                return null;
        }
    }

    public void addRow(Customer customer) {
        data.add(customer);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public Customer getAt(int row) {
        return this.data.get(row);
    }

    public void deleteRow(int row) {
        this.data.remove(row);
        fireTableRowsDeleted(row, row);
    }

    public void updateRow(int index, Customer customer) {
        this.data.set(index, customer);
        fireTableRowsUpdated(index, index);
    }
}
