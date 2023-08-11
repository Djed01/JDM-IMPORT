package org.unibl.etf.GUI;

import org.unibl.etf.DAO.impl.CarDAOImpl;
import org.unibl.etf.DAO.impl.CustomerDAOImpl;
import org.unibl.etf.DAO.impl.OrderDAOImpl;
import org.unibl.etf.MODELS.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
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
    private JTextField surnameTextField;
    private JTextField emailTextField;
    private JTextField phoneTextField;


    private JTextField dateTextField;
    private JTextField deliveryDateTextField;
    private JTextField orderTotalTextField;
    private JTextField quantityTextField;


    private CarDAOImpl carDAO;
    private List<Car> cars;
    private CarTableModel carsModel;


    private CustomerDAOImpl customerDAO;
    private List<Customer> customers;
    private CustomerTableModel customersModel;
    private JComboBox typeComboBox;


    private OrderDAOImpl orderDAO;
    private List<Order> orders;
    private OrderTableModel ordersModel;

    private JComboBox customerComboBox;
    private JComboBox carComboBox;
    private int idEmployee;

    // private final DefaultComboBoxModel<Car> carDefaultComboBoxModel = new DefaultComboBoxModel<>();
    // private final DefaultComboBoxModel<Customer> customerDefaultComboBoxModel = new DefaultComboBoxModel<>();


    public void setButtonColor(JButton p) {
        p.setBackground(Color.RED);
        p.setForeground(Color.WHITE);
    }

    public void resetButtonColor(JButton p) {
        p.setBackground(new Color(47, 47, 47));
        p.setForeground(Color.RED);
    }

    /**
     * Create the frame.
     */
    public MainFrame(int idEmployee) {
        this.idEmployee = idEmployee;


        // ------------------- DATABASE CARS -------------------
        carDAO = new CarDAOImpl();

        try {
            cars = carDAO.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        this.setTitle("JDM-IMPORT");
        BufferedImage myPicture = null;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1200, 567);
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
        this.setIconImage(newImage);

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
        tabbedPane.setBounds(195, -25, 992, 557);
        contentPane.add(tabbedPane);

        JPanel CarsTabbedPane = new JPanel();
        CarsTabbedPane.setBackground(new Color(81, 86, 88));
        CarsTabbedPane.setLayout(null);
        tabbedPane.addTab("CARS", null, CarsTabbedPane, null);


        // ---------------- CARS -----------------------
        carsTable = new JTable();
        this.carsModel = new CarTableModel();
        carsTable.setModel(carsModel);
        JScrollPane carsTableScrollPane = new JScrollPane(carsTable);
        carsTableScrollPane.setPreferredSize(new Dimension(942, 200));
        carsTableScrollPane.setBounds(20, 300, 942, 200);
        CarsTabbedPane.add(carsTableScrollPane);


        JButton addCarButton = new JButton("Add");
        addCarButton.setBackground(Color.GREEN);
        addCarButton.setBounds(550, 53, 150, 72);
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
                        carsModel.addRow(car);
                    } catch (SQLException a) {
                        JOptionPane.showMessageDialog(null, "Došlo je do greške prilikom komunikacije sa bazom podataka", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                carComboBox.addItem(car);
            }
        });

        JButton updateCarButton = new JButton("Update");
        updateCarButton.setBackground(new Color(30, 144, 255));
        updateCarButton.setBounds(747, 53, 150, 72);
        updateCarButton.setIcon(updateIcon);
        updateCarButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        updateCarButton.setVerticalTextPosition(SwingConstants.CENTER);
        CarsTabbedPane.add(updateCarButton);

        updateCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = carsTable.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "Select a car!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    Car car = carCheckAndCreate();

                    if (car != null) {

                        int id = (int) carsTable.getValueAt(row, 0);
                        car.setId(id);

                        try {
                            if (carDAO.update(car)) {
                                carsModel.updateRow(row, car);
                            }
                        } catch (SQLException a) {
                            JOptionPane.showMessageDialog(null, "Došlo je do greške prilikom komunikacije sa bazom podataka", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                refreshCarComboBox();
            }
        });

        JButton deleteCarButton = new JButton("Delete");
        deleteCarButton.setBackground(Color.RED);
        deleteCarButton.setBounds(550, 170, 150, 72);
        deleteCarButton.setIcon(deleteIcon);
        deleteCarButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        deleteCarButton.setVerticalTextPosition(SwingConstants.CENTER);
        CarsTabbedPane.add(deleteCarButton);

        deleteCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = carsTable.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "Select a car!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the car?",
                            "Confirm delete", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        Car car = carsModel.getAt(row);
                        try {
                            if (carDAO.delete(car)) {
                                carsModel.deleteRow(row);
                            }
                        } catch (SQLException a) {
                            JOptionPane.showMessageDialog(null, "Došlo je do greške prilikom komunikacije sa bazom podataka", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        refreshCarComboBox();
                    }
                }
            }
        });

        JButton clearCarButton = new JButton("Clear");
        clearCarButton.setBackground(Color.YELLOW);
        clearCarButton.setBounds(747, 170, 150, 72);
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
        BrandLabel.setBounds(100, 53, 81, 14);
        CarsTabbedPane.add(BrandLabel);

        JLabel ModelLabel = new JLabel("Model:");
        ModelLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        ModelLabel.setForeground(Color.BLACK);
        ModelLabel.setBounds(100, 96, 52, 14);
        CarsTabbedPane.add(ModelLabel);

        JLabel YearLabel = new JLabel("Year:");
        YearLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        YearLabel.setForeground(Color.BLACK);
        YearLabel.setBounds(100, 141, 52, 23);
        CarsTabbedPane.add(YearLabel);

        JLabel PriceLabel = new JLabel("Price:");
        PriceLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        PriceLabel.setForeground(Color.BLACK);
        PriceLabel.setBounds(100, 190, 46, 14);
        CarsTabbedPane.add(PriceLabel);

        JLabel ImageURLLabel = new JLabel("ImageURL:");
        ImageURLLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        ImageURLLabel.setForeground(Color.BLACK);
        ImageURLLabel.setBounds(100, 229, 90, 14);
        CarsTabbedPane.add(ImageURLLabel);

        imageURLtextField = new JTextField();
        imageURLtextField.setBounds(191, 223, 150, 30);
        CarsTabbedPane.add(imageURLtextField);
        imageURLtextField.setColumns(10);

        priceTextField = new JTextField();
        priceTextField.setColumns(10);
        priceTextField.setBounds(191, 179, 150, 30);
        CarsTabbedPane.add(priceTextField);

        yearTextField = new JTextField();
        yearTextField.setColumns(10);
        yearTextField.setBounds(191, 135, 150, 30);
        CarsTabbedPane.add(yearTextField);

        modelTextField = new JTextField();
        modelTextField.setColumns(10);
        modelTextField.setBounds(191, 91, 150, 30);
        CarsTabbedPane.add(modelTextField);

        brandTextField = new JTextField();
        brandTextField.setColumns(10);
        brandTextField.setBounds(191, 47, 150, 30);
        CarsTabbedPane.add(brandTextField);

        displayCars();

        carsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) { // Single-click event
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

        orderDAO = new OrderDAOImpl();

        try {
            orders = orderDAO.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JPanel OrdersPanel = new JPanel();
        OrdersPanel.setLayout(null);
        OrdersPanel.setBackground(new Color(81, 86, 88));
        tabbedPane.addTab("ORDERS", null, OrdersPanel, null);

        ordersTable = new JTable();
        JScrollPane ordersTableScrollPane = new JScrollPane(ordersTable);
        ordersTableScrollPane.setPreferredSize(new Dimension(942, 200));
        ordersTableScrollPane.setBounds(20, 300, 942, 200);
        this.ordersModel = new OrderTableModel();
        ordersTable.setModel(ordersModel);
        OrdersPanel.add(ordersTableScrollPane);


        JButton addOrderButton = new JButton("Add");
        addOrderButton.setBackground(Color.GREEN);
        addOrderButton.setBounds(550, 53, 150, 72);
        addOrderButton.setIcon(addIcon);
        addOrderButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        addOrderButton.setVerticalTextPosition(SwingConstants.CENTER);
        OrdersPanel.add(addOrderButton);

        addOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Order order = orderCheckAndCreate();

                if(order != null){
                    try {
                        order = orderDAO.insert(order);
                        ordersModel.addRow(order);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        JButton updateOrderButton = new JButton("Update");
        updateOrderButton.setBackground(new Color(30, 144, 255));
        updateOrderButton.setBounds(747, 53, 150, 72);
        updateOrderButton.setIcon(updateIcon);
        updateOrderButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        updateOrderButton.setVerticalTextPosition(SwingConstants.CENTER);
        OrdersPanel.add(updateOrderButton);

        updateOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = ordersTable.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "Morate selektovati red u tabeli", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    Order order = orderCheckAndCreate();
                    if (order != null) {
                        try {
                            order.setId((int) ordersTable.getValueAt(row, 0));
                            order = orderDAO.update(order);
                            ordersModel.updateRow(row, order);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });

        JButton deleteOrderButton = new JButton("Delete");
        deleteOrderButton.setBackground(Color.RED);
        deleteOrderButton.setBounds(550, 170, 150, 72);
        deleteOrderButton.setIcon(deleteIcon);
        deleteOrderButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        deleteOrderButton.setVerticalTextPosition(SwingConstants.CENTER);
        OrdersPanel.add(deleteOrderButton);

        deleteOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = ordersTable.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "Morate selektovati red u tabeli", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this order?",
                            "Confirm delete", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        int selectedRow = ordersTable.getSelectedRow();
                        if (selectedRow != -1) { // Ensure a row is selected
                            // Get the data from the selected row
                            int id = (int) ordersTable.getValueAt(selectedRow, 0);
                            try {
                                orderDAO.delete(id);
                                ordersModel.deleteRow(selectedRow);
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

        JButton clearOrderButton = new JButton("Clear");
        clearOrderButton.setBackground(Color.YELLOW);
        clearOrderButton.setBounds(747, 170, 150, 72);
        clearOrderButton.setIcon(clearIcon);
        clearOrderButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        clearOrderButton.setVerticalTextPosition(SwingConstants.CENTER);
        OrdersPanel.add(clearOrderButton);

        clearOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearOrderFields();
            }
        });


        JLabel lblDeliveryDate = new JLabel("Delivery date:");
        lblDeliveryDate.setForeground(Color.BLACK);
        lblDeliveryDate.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblDeliveryDate.setBounds(100, 152, 99, 14);
        OrdersPanel.add(lblDeliveryDate);

        deliveryDateTextField = new JTextField();
        deliveryDateTextField.setColumns(10);
        deliveryDateTextField.setBounds(230, 146, 150, 30);
        OrdersPanel.add(deliveryDateTextField);


        JLabel lblNewLabel = new JLabel("Customer:");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel.setBounds(100, 53, 99, 14);
        OrdersPanel.add(lblNewLabel);

        JLabel lblCar = new JLabel("Car:");
        lblCar.setForeground(Color.BLACK);
        lblCar.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblCar.setBounds(100, 101, 99, 14);
        OrdersPanel.add(lblCar);

        carComboBox = new JComboBox(cars.toArray());
        carComboBox.setBounds(230, 95, 150, 30);
        OrdersPanel.add(carComboBox);

        JLabel QuantitiyLabel = new JLabel("Quantity:");
        QuantitiyLabel.setForeground(Color.BLACK);
        QuantitiyLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        QuantitiyLabel.setBounds(100, 204, 99, 14);
        OrdersPanel.add(QuantitiyLabel);

        quantityTextField = new JTextField();
        quantityTextField.setColumns(10);
        quantityTextField.setBounds(230, 198, 150, 30);
        OrdersPanel.add(quantityTextField);

        ordersTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) { // Single-click event
                    int selectedRow = ordersTable.getSelectedRow();
                    if (selectedRow != -1) { // Ensure a row is selected
                        // Get the data from the selected row
                        int id = (int) ordersTable.getValueAt(selectedRow, 0);
                        String name = (String) ordersTable.getValueAt(selectedRow, 1);
                        String surname = (String) ordersTable.getValueAt(selectedRow, 2);
                        String companyName = (String) ordersTable.getValueAt(selectedRow, 3);
                        String brand = (String) ordersTable.getValueAt(selectedRow, 6);
                        String model = (String) ordersTable.getValueAt(selectedRow, 7);
                        String year = (String) ordersTable.getValueAt(selectedRow, 8);
                        int quantity = (int) ordersTable.getValueAt(selectedRow, 9);
                        String deliveryDate = (String) ordersTable.getValueAt(selectedRow, 12);


                        // Select the customer in the combobox
                        for(int i=0; i < customerComboBox.getItemCount();i++){
                            Customer customer = (Customer) customerComboBox.getItemAt(i);
                            if(customer instanceof Individual){
                                if(((Individual)customer).getFirstName().equals(name) && ((Individual)customer).getLastName().equals(surname)){
                                    customerComboBox.setSelectedIndex(i);
                                    break;
                                }
                            }else if(customer instanceof Company){
                                if(((Company)customer).getName().equals(companyName)){
                                    customerComboBox.setSelectedIndex(i);
                                    break;
                                }
                            }
                        }

                        // Select the car in the combobox
                        for(int i=0; i < carComboBox.getItemCount();i++){
                            Car car = (Car) carComboBox.getItemAt(i);
                            if(car.getBrand().equals(brand) && car.getModel().equals(model)){
                                carComboBox.setSelectedIndex(i);
                                break;
                            }
                        }

                        SwingUtilities.invokeLater(() -> {
                            quantityTextField.setText(quantity+"");
                            deliveryDateTextField.setText(deliveryDate);
                        });
                    }
                }
            }
        });

        displayOrders();


        // ------------------- CUSTOMERS ---------------------------

        customerDAO = new CustomerDAOImpl();

        try {
            customers = customerDAO.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // ------------------- ORDER COMBOBOX ---------------------------
        customerComboBox = new JComboBox(customers.toArray());
        customerComboBox.setBounds(230, 47, 150, 30);
        OrdersPanel.add(customerComboBox);
        // ------------------- ORDER COMBOBOX ---------------------------

        JPanel CustomersPanel = new JPanel();
        CustomersPanel.setLayout(null);
        CustomersPanel.setBackground(new Color(81, 86, 88));
        tabbedPane.addTab("CUSTOMERS", null, CustomersPanel, null);

        customersTable = new JTable();
        this.customersModel = new CustomerTableModel();
        customersTable.setModel(customersModel);
        JScrollPane customersTableScrollPane = new JScrollPane(customersTable);
        customersTableScrollPane.setPreferredSize(new Dimension(942, 200));
        customersTableScrollPane.setBounds(20, 300, 942, 200);
        CustomersPanel.add(customersTableScrollPane);

        JButton addCustomerButton = new JButton("Add");
        addCustomerButton.setBackground(Color.GREEN);
        addCustomerButton.setBounds(550, 53, 150, 72);
        addCustomerButton.setIcon(addIcon);
        addCustomerButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        addCustomerButton.setVerticalTextPosition(SwingConstants.CENTER);
        CustomersPanel.add(addCustomerButton);

        addCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Customer customer = customerCheckAndCreate();
                if (customer != null)
                    try {
                        customerDAO.insert(customer, typeComboBox.getSelectedItem().toString(),idEmployee);
                        customersModel.addRow(customer);
                    } catch (SQLException a) {
                        JOptionPane.showMessageDialog(null, "Došlo je do greške prilikom komunikacije sa bazom podataka", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                customerComboBox.addItem(customer);
            }
        });

        JButton updateCustomerButton = new JButton("Update");
        updateCustomerButton.setBackground(new Color(30, 144, 255));
        updateCustomerButton.setBounds(747, 53, 150, 72);
        updateCustomerButton.setIcon(updateIcon);
        updateCustomerButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        updateCustomerButton.setVerticalTextPosition(SwingConstants.CENTER);
        CustomersPanel.add(updateCustomerButton);


        updateCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = customersTable.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "Morate selektovati red u tabeli", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    Customer customer = customerCheckAndCreate();
                    if (customer != null) {
                        int id = (int) customersTable.getValueAt(row, 0);
                        try {
                            customerDAO.update(customer, id);
                            customer.setId(id);
                            customersModel.updateRow(row, customer);
                        } catch (SQLException a) {
                            JOptionPane.showMessageDialog(null, "Došlo je do greške prilikom komunikacije sa bazom podataka", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            System.out.println("Error Code: " + a.getErrorCode());
                            System.out.println("SQL State: " + a.getSQLState());
                            System.out.println("Message: " + a.getMessage());
                        }
                    }
                }
                refreshCustomerComboBox();
            }
        });

        JButton deleteCustomerButton = new JButton("Delete");
        deleteCustomerButton.setBackground(Color.RED);
        deleteCustomerButton.setBounds(550, 170, 150, 72);
        deleteCustomerButton.setIcon(deleteIcon);
        deleteCustomerButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        deleteCustomerButton.setVerticalTextPosition(SwingConstants.CENTER);
        CustomersPanel.add(deleteCustomerButton);

        deleteCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = customersTable.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "Morate selektovati red u tabeli", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the customer?",
                            "Confirm delete", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        int id = (int) customersTable.getValueAt(row, 0);
                        try {
                            customerDAO.delete(id);
                            customersModel.deleteRow(row);
                        } catch (SQLException a) {
                            JOptionPane.showMessageDialog(null, "Došlo je do greške prilikom komunikacije sa bazom podataka", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            System.err.println("SQL Exception:");
                            System.err.println("Error Code: " + a.getErrorCode());
                            System.err.println("SQL State: " + a.getSQLState());
                            System.err.println("Message: " + a.getMessage());
                        }
                        refreshCustomerComboBox();
                    }
                }
            }
        });

        JButton clearCustomerButton = new JButton("Clear");
        clearCustomerButton.setBackground(Color.YELLOW);
        clearCustomerButton.setBounds(747, 170, 150, 72);
        clearCustomerButton.setIcon(clearIcon);
        clearCustomerButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        clearCustomerButton.setVerticalTextPosition(SwingConstants.CENTER);
        CustomersPanel.add(clearCustomerButton);

        clearCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearCustomerFields();
            }
        });

        JLabel typeLabel = new JLabel("Type:");
        typeLabel.setForeground(Color.BLACK);
        typeLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        typeLabel.setBounds(100, 53, 62, 14);
        CustomersPanel.add(typeLabel);

        typeComboBox = new JComboBox();
        typeComboBox.addItem("Individual");
        typeComboBox.addItem("Company");
        typeComboBox.setToolTipText("");
        typeComboBox.setBounds(191, 47, 150, 30);
        CustomersPanel.add(typeComboBox);


        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setForeground(Color.BLACK);
        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        nameLabel.setBounds(100, 96, 85, 17);
        CustomersPanel.add(nameLabel);

        nameTextField = new JTextField();
        nameTextField.setColumns(10);
        nameTextField.setBounds(191, 91, 150, 30);
        CustomersPanel.add(nameTextField);

        JLabel surnameLabel = new JLabel("Surname:");
        surnameLabel.setForeground(Color.BLACK);
        surnameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        surnameLabel.setBounds(100, 141, 77, 14);
        CustomersPanel.add(surnameLabel);

        surnameTextField = new JTextField();
        surnameTextField.setColumns(10);
        surnameTextField.setBounds(191, 135, 150, 30);
        CustomersPanel.add(surnameTextField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(Color.BLACK);
        emailLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        emailLabel.setBounds(100, 190, 77, 14);
        CustomersPanel.add(emailLabel);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setForeground(Color.BLACK);
        phoneLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        phoneLabel.setBounds(100, 229, 77, 14);
        CustomersPanel.add(phoneLabel);

        emailTextField = new JTextField();
        emailTextField.setColumns(10);
        emailTextField.setBounds(191, 179, 150, 30);
        CustomersPanel.add(emailTextField);

        phoneTextField = new JTextField();
        phoneTextField.setColumns(10);
        phoneTextField.setBounds(191, 223, 150, 30);
        CustomersPanel.add(phoneTextField);

        customersTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) { // Single-click event
                    clearCustomerFields();
                    int selectedRow = customersTable.getSelectedRow();
                    if (selectedRow != -1) { // Ensure a row is selected
                        // Get the data from the selected row
                        int id = (int) customersTable.getValueAt(selectedRow, 0);
                        String name = (String) customersTable.getValueAt(selectedRow, 1);
                        String surname = (String) customersTable.getValueAt(selectedRow, 2);

                        if (surname == null) {
                            name = (String) customersTable.getValueAt(selectedRow, 3);
                            typeComboBox.setSelectedItem("Company");
                        } else {
                            typeComboBox.setSelectedItem("Individual");
                        }

                        ActionListener[] actionListeners = typeComboBox.getActionListeners();
                        if (actionListeners != null && actionListeners.length > 0) {
                            ActionEvent event = new ActionEvent(typeComboBox, ActionEvent.ACTION_PERFORMED, "Selection Changed");
                            for (ActionListener listener : actionListeners) {
                                listener.actionPerformed(event);
                            }
                        }

                        String email = (String) customersTable.getValueAt(selectedRow, 4);
                        String phone = (String) customersTable.getValueAt(selectedRow, 5);
                        typeComboBox.setEnabled(false);

                        String finalName = name;
                        SwingUtilities.invokeLater(() -> {
                            // Display the data in the text fields
                            nameTextField.setText(finalName);
                            surnameTextField.setText(surname);
                            emailTextField.setText(email);
                            phoneTextField.setText(phone);
                        });
                    }
                }
            }
        });


        typeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (typeComboBox.getSelectedItem().equals("Individual")) {
                    nameLabel.setText("Name:");
                    surnameTextField.setEnabled(true);
                    surnameTextField.setBackground(Color.WHITE);
                    surnameLabel.setForeground(Color.BLACK);
                } else {
                    nameLabel.setText("Company:");
                    surnameTextField.setEnabled(false);
                    surnameTextField.setBackground(new Color(217, 219, 219));
                    surnameLabel.setForeground(null);
                }
            }
        });

        displayCustomers();


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


    // ---------------------------- DISPLAY METHODS ----------------------------
    private void displayCars() {
        try {
            this.cars = this.carDAO.findAll();
            SwingUtilities.invokeLater(() -> {
                cars.stream().forEach(car -> carsModel.addRow(car));
            });
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Došlo je do greške prilikom komunikacije sa bazom podataka",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayCustomers() {
        try {
            this.customers = this.customerDAO.findAll();
            SwingUtilities.invokeLater(() -> {
                customers.stream().forEach(customer -> customersModel.addRow(customer));
            });
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Došlo je do greške prilikom komunikacije sa bazom podataka",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayOrders() {
        try {
            this.orders = this.orderDAO.findAll();
            SwingUtilities.invokeLater(() -> {
                orders.stream().forEach(order -> ordersModel.addRow(order));
            });
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Došlo je do greške prilikom komunikacije sa bazom podataka",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //---------------------------- CHECK AND CREATE METHODS ----------------------------

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

    private Customer customerCheckAndCreate() {
        String type = (String) typeComboBox.getSelectedItem();
        String name = nameTextField.getText();
        if (name.isBlank()) {
            JOptionPane.showMessageDialog(null, "Insert the name", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
        String surname = surnameTextField.getText();
        if (surname.isBlank() && "Individual".equals(type)) {
            JOptionPane.showMessageDialog(null, "Insert the surname", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
        String email = emailTextField.getText();
        if (email.isBlank()) {
            JOptionPane.showMessageDialog(null, "Insert the email", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
        String phone = phoneTextField.getText();
        if (phone.isBlank()) {
            JOptionPane.showMessageDialog(null, "Insert the phone", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }

        if ("Individual".equals(type)) {
            return new Individual(name, surname, email, phone);
        } else if ("Company".equals(type)) {
            return new Company(name, email, phone);
        } else {
            return null;
        }

    }


    private Order orderCheckAndCreate(){
        Car car = (Car) carComboBox.getSelectedItem();
        Customer customer = (Customer) customerComboBox.getSelectedItem();

        String quantity = quantityTextField.getText();
        if (quantity.isBlank()) {
            JOptionPane.showMessageDialog(null, "Insert the quantity", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
        String deliveryDate = deliveryDateTextField.getText();
        if (deliveryDate.isBlank()) {
            JOptionPane.showMessageDialog(null, "Insert the delivery date", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return new Order(customer,car, LocalDate.now().toString(), deliveryDateTextField.getText(), Integer.parseInt(quantityTextField.getText()),
                ((Car)carComboBox.getSelectedItem()).getPrice() * Integer.parseInt(quantityTextField.getText()));
    }


    // ---------------------------- CLEAR METHODS ----------------------------

    private void clearCarFields() {
        brandTextField.setText("");
        modelTextField.setText("");
        yearTextField.setText("");
        priceTextField.setText("");
        imageURLtextField.setText("");
    }

    private void clearCustomerFields() {
        typeComboBox.setEnabled(true);
        typeComboBox.setSelectedIndex(0);
        nameTextField.setText("");
        surnameTextField.setText("");
        emailTextField.setText("");
        phoneTextField.setText("");
    }

    private void clearOrderFields() {
        carComboBox.setSelectedIndex(0);
        customerComboBox.setSelectedIndex(0);
        quantityTextField.setText("");
        deliveryDateTextField.setText("");
    }


    private void refreshCarComboBox(){
        carComboBox.removeAllItems();
        try {
            this.cars = this.carDAO.findAll();
            cars.stream().forEach(car -> carComboBox.addItem(car));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Došlo je do greške prilikom komunikacije sa bazom podataka",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refreshCustomerComboBox(){
        customerComboBox.removeAllItems();
        try {
            this.customers = this.customerDAO.findAll();
            customers.stream().forEach(customer -> customerComboBox.addItem(customer));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Došlo je do greške prilikom komunikacije sa bazom podataka",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
