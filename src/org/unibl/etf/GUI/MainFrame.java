package org.unibl.etf.GUI;

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

public class MainFrame extends JFrame {
    private JPanel contentPane;

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
    public MainFrame() {
        BufferedImage myPicture = null;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 569);
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
            myPicture = ImageIO.read(new File("JDM-LOGO.png"));
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
        tabbedPane.setBounds(195, 0, 789, 530);
        contentPane.add(tabbedPane);

        JPanel CarsTabbedPane = new JPanel();
        CarsTabbedPane.setBackground(new Color(81, 86, 88));
        tabbedPane.addTab("CARS", null, CarsTabbedPane, null);


        JPanel OrdersPanel = new JPanel();
        OrdersPanel.setBackground(new Color(81, 86, 88));
        tabbedPane.addTab("ORDERS", null, OrdersPanel, null);


        JPanel CustomersPanel = new JPanel();
        CustomersPanel.setBackground(new Color(81, 86, 88));
        tabbedPane.addTab("CUSTOMERS", null, CustomersPanel, null);



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
}
