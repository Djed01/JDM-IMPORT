package org.unibl.etf.GUI;

import org.unibl.etf.DAO.impl.CarDAOImpl;
import org.unibl.etf.MODELS.Car;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MainFrame extends JFrame {
    private static final String LOGOPATH = "./src/resources/img/JDM-LOGO.png";
    private static final String ADDPATH = "./src/resources/img/Add.png";
    private static final String DELETEPATH = "./src/resources/img/Delete.png";
    private static final String UPDATEPATH = "./src/resources/img/Update.png";
    private static final String CLEARPATH = "./src/resources/img/Clear.png";

    private final Icon addIcon = new ImageIcon(ADDPATH);
    private final Icon deleteIcon = new ImageIcon(DELETEPATH);
    private final Icon updateIcon = new ImageIcon(UPDATEPATH);
    private final Icon clearIcon = new ImageIcon(CLEARPATH);
    private final JPanel contentPane;
    private final JTable carsTable;
    private final JTable ordersTable;
    private final JTable customersTable;


    private JTextField imageURLtextField;
    private JTextField priceTextField;
    private JTextField yearTextField;
    private JTextField modelTextField;
    private JTextField idTextField;
    private JTextField brandTextField;




    private JTextField nameTextField;
    private JTextField SurnameTextField;
    private JTextField emailTextField;
    private JTextField phoneTextField;



    private JTextField dateTextField;
    private JTextField deliveryDateTextField;
    private JTextField orderTotalTextField;
    private JTextField quantityTextField;


    private CarDAOImpl carDAO;
    private List<Car> cars;
    private CarTableModel model;


    public void setButtonColor(JButton p) {
        p.setBackground(Color.RED);
        p.setForeground(Color.WHITE);
    }

    public void resetButtonColor(JButton p) {
        p.setBackground(new Color(47, 47, 47));
        p.setForeground(Color.RED);
    }

    class CarTableModel extends AbstractTableModel{
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
                    return obj.getYear();
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

    /**
     * Create the frame.
     */
    public MainFrame() {


        // ------------------- DATABASE CARS -------------------
        carDAO = new CarDAOImpl();

        try{
            cars = carDAO.findAll();
        }catch (SQLException e){
            e.printStackTrace();
        }




        BufferedImage myPicture = null;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 998, 567);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JPanel MenuPanel = new JPanel();
        MenuPanel.setBackground(new Color(47, 47, 47));
        MenuPanel.setBounds(0, 0, 197, 530);
        MenuPanel.setLayout(null);
        contentPane.add(MenuPanel);

        try {
            myPicture = ImageIO.read(new File(LOGOPATH));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Image newImage = myPicture.getScaledInstance(150, 65, Image.SCALE_DEFAULT);
        JLabel LogoLabel = new JLabel(new ImageIcon(newImage));
        LogoLabel.setBounds(10, 44, 177, 67); //Sets the location of the image
        MenuPanel.add(LogoLabel);

        JButton CarsButton = new JButton("CARS");
        CarsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setButtonColor(CarsButton);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                resetButtonColor(CarsButton);
            }
        });


        CarsButton.setForeground(Color.RED);
        CarsButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        CarsButton.setBackground(new Color(47, 47, 47));
        CarsButton.setBounds(10, 164, 177, 41);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        CarsButton.setBorder(emptyBorder);
        //remove border around text
        CarsButton.setFocusPainted(false);
        MenuPanel.add(CarsButton);

        JButton OrdersButton = new JButton("ORDERS");
        OrdersButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setButtonColor(OrdersButton);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                resetButtonColor(OrdersButton);
            }
        });
        OrdersButton.setBackground(new Color(47, 47, 47));
        OrdersButton.setForeground(Color.RED);
        OrdersButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        OrdersButton.setBounds(10, 216, 177, 41);
        OrdersButton.setBorder(emptyBorder);
        //remove border around text
        OrdersButton.setFocusPainted(false);
        MenuPanel.add(OrdersButton);

        JButton CustomersButton = new JButton("CUSTOMERS");
        CustomersButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setButtonColor(CustomersButton);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                resetButtonColor(CustomersButton);
            }
        });
        CustomersButton.setBackground(new Color(47, 47, 47));
        CustomersButton.setForeground(Color.RED);
        CustomersButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        CustomersButton.setBounds(10, 268, 177, 41);
        CustomersButton.setBorder(emptyBorder);
        //remove border around text
        CustomersButton.setFocusPainted(false);
        MenuPanel.add(CustomersButton);

        JButton ExitButton = new JButton("EXIT");
        ExitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setButtonColor(ExitButton);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                resetButtonColor(ExitButton);
            }
        });
        ExitButton.setBackground(new Color(47, 47, 47));
        ExitButton.setForeground(Color.RED);
        ExitButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        ExitButton.setBounds(10, 320, 177, 41);
        ExitButton.setBorder(emptyBorder);
        //remove border around text
        ExitButton.setFocusPainted(false);
        MenuPanel.add(ExitButton);



        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(195, -25, 790, 557);
        contentPane.add(tabbedPane);

        JPanel CarsTabbedPane = new JPanel();
        CarsTabbedPane.setBackground(new Color(81, 86, 88));
        CarsTabbedPane.setLayout(null);
        tabbedPane.addTab("CARS", null, CarsTabbedPane, null);


        // ---------------- CARS -----------------------
        carsTable = new JTable();
        this.model = new CarTableModel();
        carsTable.setModel(model);
        JScrollPane carsTableScrollPane = new JScrollPane(carsTable);
        carsTableScrollPane.setPreferredSize(new Dimension(740, 200));
        carsTableScrollPane.setBounds(20,300,740, 200);
        CarsTabbedPane.add(carsTableScrollPane);




        JButton addCarButton = new JButton("Add");
        addCarButton.setBackground(Color.GREEN);
        addCarButton.setBounds(431, 63, 133, 58);
        addCarButton.setIcon(addIcon);
        addCarButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        addCarButton.setVerticalTextPosition(SwingConstants.CENTER);
        CarsTabbedPane.add(addCarButton);

        addCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Car car = carCheckAndCreate();
                if (car != null)
                    try {
                        carDAO.insert(car);
                        model.addRow(car);
                    } catch (SQLException a) {
                        JOptionPane.showMessageDialog(null, "Došlo je do greške prilikom komunikacije sa bazom podataka", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
            }
        });

        JButton updateCarButton = new JButton("Update");
        updateCarButton.setBackground(new Color(30, 144, 255));
        updateCarButton.setBounds(599, 63, 133, 58);
        updateCarButton.setIcon(updateIcon);
        updateCarButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        updateCarButton.setVerticalTextPosition(SwingConstants.CENTER);
        CarsTabbedPane.add(updateCarButton);

        JButton deleteCarButton = new JButton("Delete");
        deleteCarButton.setBackground(Color.RED);
        deleteCarButton.setBounds(431, 170, 133, 58);
        deleteCarButton.setIcon(deleteIcon);
        deleteCarButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        deleteCarButton.setVerticalTextPosition(SwingConstants.CENTER);
        CarsTabbedPane.add(deleteCarButton);

        JButton clearCarButton = new JButton("Clear");
        clearCarButton.setBackground(Color.YELLOW);
        clearCarButton.setBounds(599, 170, 133, 58);
        clearCarButton.setIcon(clearIcon);
        clearCarButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        clearCarButton.setVerticalTextPosition(SwingConstants.CENTER);
        CarsTabbedPane.add(clearCarButton);

        clearCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearCarFields();
            }
        });

        JLabel BrandLabel = new JLabel("Brand:");
        BrandLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        BrandLabel.setForeground(Color.BLACK);
        BrandLabel.setBounds(58, 83, 81, 14);
        CarsTabbedPane.add(BrandLabel);

        JLabel ModelLabel = new JLabel("Model:");
        ModelLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        ModelLabel.setForeground(Color.BLACK);
        ModelLabel.setBounds(58, 123, 52, 14);
        CarsTabbedPane.add(ModelLabel);

        JLabel YearLabel = new JLabel("Year:");
        YearLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        YearLabel.setForeground(Color.BLACK);
        YearLabel.setBounds(58, 159, 52, 23);
        CarsTabbedPane.add(YearLabel);

        JLabel PriceLabel = new JLabel("Price:");
        PriceLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        PriceLabel.setForeground(Color.BLACK);
        PriceLabel.setBounds(58, 204, 46, 14);
        CarsTabbedPane.add(PriceLabel);

        JLabel ImageURLLabel = new JLabel("ImageURL:");
        ImageURLLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        ImageURLLabel.setForeground(Color.BLACK);
        ImageURLLabel.setBounds(58, 241, 90, 14);
        CarsTabbedPane.add(ImageURLLabel);

        imageURLtextField = new JTextField();
        imageURLtextField.setBounds(163, 237, 150, 30);
        CarsTabbedPane.add(imageURLtextField);
        imageURLtextField.setColumns(10);

        priceTextField = new JTextField();
        priceTextField.setColumns(10);
        priceTextField.setBounds(163, 197, 150, 30);
        CarsTabbedPane.add(priceTextField);

        yearTextField = new JTextField();
        yearTextField.setColumns(10);
        yearTextField.setBounds(163, 157, 150, 30);
        CarsTabbedPane.add(yearTextField);

        modelTextField = new JTextField();
        modelTextField.setColumns(10);
        modelTextField.setBounds(163, 117, 150, 30);
        CarsTabbedPane.add(modelTextField);

        brandTextField = new JTextField();
        brandTextField.setColumns(10);
        brandTextField.setBounds(163, 77, 150, 30);
        CarsTabbedPane.add(brandTextField);

        displayCars();

        carsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Double-click event
                    int selectedRow = carsTable.getSelectedRow();
                    if (selectedRow != -1) { // Ensure a row is selected
                        // Get the data from the selected row
                        int id = (int) carsTable.getValueAt(selectedRow, 0);
                        String brand = (String) carsTable.getValueAt(selectedRow, 1);
                        String model = (String) carsTable.getValueAt(selectedRow, 2);
                        String year = (String) carsTable.getValueAt(selectedRow, 3);
                        double price = (double) carsTable.getValueAt(selectedRow, 4);
                        String imageURL = (String) carsTable.getValueAt(selectedRow, 5);

                       modelTextField.setText(model);
                       brandTextField.setText(brand);
                       yearTextField.setText(year);
                       priceTextField.setText(String.valueOf(price));
                       imageURLtextField.setText(imageURL);
                    }
                }
            }
        });



        // ---------------- ORDERS -------------------------

        JPanel OrdersPanel = new JPanel();
        OrdersPanel.setLayout(null);
        OrdersPanel.setBackground(new Color(81, 86, 88));
        tabbedPane.addTab("ORDERS", null, OrdersPanel, null);

        ordersTable = new JTable();
        JScrollPane ordersTableScrollPane = new JScrollPane(ordersTable);
        ordersTableScrollPane.setPreferredSize(new Dimension(740, 200));
        ordersTableScrollPane.setBounds(20,300,740, 200);
        OrdersPanel.add(ordersTableScrollPane);

        JButton addOrderButton = new JButton("Add");
        addOrderButton.setBackground(Color.GREEN);
        addOrderButton.setBounds(431, 63, 133, 58);
        addOrderButton.setIcon(addIcon);
        addOrderButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        addOrderButton.setVerticalTextPosition(SwingConstants.CENTER);
        OrdersPanel.add(addOrderButton);

        JButton updateOrderButton = new JButton("Update");
        updateOrderButton.setBackground(new Color(30, 144, 255));
        updateOrderButton.setBounds(599, 63, 133, 58);
        updateOrderButton.setIcon(updateIcon);
        updateOrderButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        updateOrderButton.setVerticalTextPosition(SwingConstants.CENTER);
        OrdersPanel.add(updateOrderButton);

        JButton deleteOrderButton = new JButton("Delete");
        deleteOrderButton.setBackground(Color.RED);
        deleteOrderButton.setBounds(431, 170, 133, 58);
        deleteOrderButton.setIcon(deleteIcon);
        deleteOrderButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        deleteOrderButton.setVerticalTextPosition(SwingConstants.CENTER);
        OrdersPanel.add(deleteOrderButton);

        JButton clearOrderButton = new JButton("Clear");
        clearOrderButton.setBackground(Color.YELLOW);
        clearOrderButton.setBounds(599, 170, 133, 58);
        clearOrderButton.setIcon(clearIcon);
        clearOrderButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        clearOrderButton.setVerticalTextPosition(SwingConstants.CENTER);
        OrdersPanel.add(clearOrderButton);

        JLabel DateLabel = new JLabel("Date:");
        DateLabel.setForeground(Color.BLACK);
        DateLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        DateLabel.setBounds(58, 119, 46, 14);
        OrdersPanel.add(DateLabel);

        dateTextField = new JTextField();
        dateTextField.setColumns(10);
        dateTextField.setBounds(163, 113, 150, 30);
        OrdersPanel.add(dateTextField);

        JLabel lblDeliveryDate = new JLabel("Delivery date:");
        lblDeliveryDate.setForeground(Color.BLACK);
        lblDeliveryDate.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblDeliveryDate.setBounds(58, 158, 99, 14);
        OrdersPanel.add(lblDeliveryDate);

        deliveryDateTextField = new JTextField();
        deliveryDateTextField.setColumns(10);
        deliveryDateTextField.setBounds(163, 152, 150, 30);
        OrdersPanel.add(deliveryDateTextField);

        JLabel lblOrderTotal = new JLabel("Order total:");
        lblOrderTotal.setForeground(Color.BLACK);
        lblOrderTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblOrderTotal.setBounds(58, 242, 99, 14);
        OrdersPanel.add(lblOrderTotal);

        orderTotalTextField = new JTextField();
        orderTotalTextField.setColumns(10);
        orderTotalTextField.setBounds(163, 236, 150, 30);
        OrdersPanel.add(orderTotalTextField);

        JLabel lblNewLabel = new JLabel("Customer:");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel.setBounds(58, 49, 99, 14);
        OrdersPanel.add(lblNewLabel);

        JLabel lblCar = new JLabel("Car:");
        lblCar.setForeground(Color.BLACK);
        lblCar.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblCar.setBounds(58, 82, 99, 14);
        OrdersPanel.add(lblCar);

        JComboBox carComboBox = new JComboBox();
        carComboBox.setBounds(163, 80, 150, 22);
        OrdersPanel.add(carComboBox);

        JComboBox customerComboBox = new JComboBox();
        customerComboBox.setBounds(163, 47, 150, 22);
        OrdersPanel.add(customerComboBox);

        JLabel QuantitiyLabel = new JLabel("Quantity:");
        QuantitiyLabel.setForeground(Color.BLACK);
        QuantitiyLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        QuantitiyLabel.setBounds(58, 200, 99, 14);
        OrdersPanel.add(QuantitiyLabel);

        quantityTextField = new JTextField();
        quantityTextField.setColumns(10);
        quantityTextField.setBounds(163, 193, 150, 30);
        OrdersPanel.add(quantityTextField);


        // ------------------- CUSTOMERS ---------------------------

        JPanel CustomersPanel = new JPanel();
        CustomersPanel.setLayout(null);
        CustomersPanel.setBackground(new Color(81, 86, 88));
        tabbedPane.addTab("CUSTOMERS", null, CustomersPanel, null);

        customersTable = new JTable();
        JScrollPane customersTableScrollPane = new JScrollPane(customersTable);
        customersTableScrollPane.setPreferredSize(new Dimension(740, 200));
        customersTableScrollPane.setBounds(20,300,740, 200);
        CustomersPanel.add(customersTableScrollPane);

        JButton addCustomerButton = new JButton("Add");
        addCustomerButton.setBackground(Color.GREEN);
        addCustomerButton.setBounds(431, 63, 133, 58);
        addCustomerButton.setIcon(addIcon);
        addCustomerButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        addCustomerButton.setVerticalTextPosition(SwingConstants.CENTER);
        CustomersPanel.add(addCustomerButton);

        JButton updateCustomerButton = new JButton("Update");
        updateCustomerButton.setBackground(new Color(30, 144, 255));
        updateCustomerButton.setBounds(599, 63, 133, 58);
        updateCustomerButton.setIcon(updateIcon);
        updateCustomerButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        updateCustomerButton.setVerticalTextPosition(SwingConstants.CENTER);
        CustomersPanel.add(updateCustomerButton);

        JButton deleteCustomerButton = new JButton("Delete");
        deleteCustomerButton.setBackground(Color.RED);
        deleteCustomerButton.setBounds(431, 170, 133, 58);
        deleteCustomerButton.setIcon(deleteIcon);
        deleteCustomerButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        deleteCustomerButton.setVerticalTextPosition(SwingConstants.CENTER);
        CustomersPanel.add(deleteCustomerButton);

        JButton clearCustomerButton = new JButton("Clear");
        clearCustomerButton.setBackground(Color.YELLOW);
        clearCustomerButton.setBounds(599, 170, 133, 58);
        clearCustomerButton.setIcon(clearIcon);
        clearCustomerButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        clearCustomerButton.setVerticalTextPosition(SwingConstants.CENTER);
        CustomersPanel.add(clearCustomerButton);

        JLabel typeLabel = new JLabel("Type:");
        typeLabel.setForeground(Color.BLACK);
        typeLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        typeLabel.setBounds(58, 63, 62, 14);
        CustomersPanel.add(typeLabel);

        JComboBox typeComboBox = new JComboBox();
        typeComboBox.setToolTipText("");
        typeComboBox.setBounds(163, 61, 150, 22);
        CustomersPanel.add(typeComboBox);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setForeground(Color.BLACK);
        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        nameLabel.setBounds(58, 100, 62, 14);
        CustomersPanel.add(nameLabel);

        nameTextField = new JTextField();
        nameTextField.setColumns(10);
        nameTextField.setBounds(163, 94, 150, 30);
        CustomersPanel.add(nameTextField);

        JLabel surnameLabel = new JLabel("Surname:");
        surnameLabel.setForeground(Color.BLACK);
        surnameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        surnameLabel.setBounds(58, 141, 77, 14);
        CustomersPanel.add(surnameLabel);

        SurnameTextField = new JTextField();
        SurnameTextField.setColumns(10);
        SurnameTextField.setBounds(163, 135, 150, 30);
        CustomersPanel.add(SurnameTextField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(Color.BLACK);
        emailLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        emailLabel.setBounds(58, 182, 77, 14);
        CustomersPanel.add(emailLabel);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setForeground(Color.BLACK);
        phoneLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        phoneLabel.setBounds(58, 221, 77, 14);
        CustomersPanel.add(phoneLabel);

        emailTextField = new JTextField();
        emailTextField.setColumns(10);
        emailTextField.setBounds(163, 176, 150, 30);
        CustomersPanel.add(emailTextField);

        phoneTextField = new JTextField();
        phoneTextField.setColumns(10);
        phoneTextField.setBounds(163, 215, 150, 30);
        CustomersPanel.add(phoneTextField);




//        URL url = null;
//        try {
//            url = new URL("https://www.motortrend.com/uploads/sites/10/2015/11/2016-nissan-gtr-premium-coupe-angular-front.png");
//        } catch (MalformedURLException e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//        }
//        BufferedImage image=null;
//        try {
//            image = ImageIO.read(url);
//        } catch (IOException e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//        }
//        Image scaledImage = image.getScaledInstance(175, 90, Image.SCALE_DEFAULT);
//        JLabel label = new JLabel(new ImageIcon(scaledImage));
//        label.setBounds(10, 400, 175, 90);
//        MenuPanel.add(label);


        CarsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tabbedPane.setSelectedIndex(0);
            }
        });

        OrdersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tabbedPane.setSelectedIndex(1);
            }
        });

        CustomersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tabbedPane.setSelectedIndex(2);
            }
        });

        ExitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });




    }

    private void displayCars() {
            try {
                this.cars = this.carDAO.findAll();
                SwingUtilities.invokeLater(() -> {
                    cars.stream().forEach(car ->model .addRow(car));
                });
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Došlo je do greške prilikom komunikacije sa bazom podataka",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }


    private Car carCheckAndCreate() {
        String brand = brandTextField.getText();
        if (brand.isBlank()) {
            JOptionPane.showMessageDialog(null, "Insert the brand name", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
        String model = modelTextField.getText();
        if (model.isBlank()) {
            JOptionPane.showMessageDialog(null, "Insert the model name", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
        String year = yearTextField.getText();
        if (year.isBlank()) {
            JOptionPane.showMessageDialog(null, "Insert the year", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
        String price = priceTextField.getText();
        if (price.isBlank()) {
            JOptionPane.showMessageDialog(null, "Insert the price", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
        String imageURL = imageURLtextField.getText();
        if (imageURL.isBlank()) {
            JOptionPane.showMessageDialog(null, "Insert the image URL", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return new Car(brand, model, year, Double.parseDouble(price), imageURL);
    }

    private void clearCarFields() {
        brandTextField.setText("");
        modelTextField.setText("");
        yearTextField.setText("");
        priceTextField.setText("");
        imageURLtextField.setText("");
    }
}
