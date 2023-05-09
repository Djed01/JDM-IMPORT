package org.unibl.etf.GUI;

import org.unibl.etf.Main;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class LoginFrame extends JFrame {
    private JPanel contentPane;
    private JTextField UsernameTextField;
    private JPasswordField PasswordTextField;

    public LoginFrame() {
        BufferedImage myPicture = null;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 600);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(47, 47, 47));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        try {
            myPicture = ImageIO.read(new File("JDM-LOGO.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Image newImage = myPicture.getScaledInstance(300, 150, Image.SCALE_DEFAULT);
        JLabel LogoLabel = new JLabel(new ImageIcon(newImage));
        LogoLabel.setBounds(170, 183, 300, 150); //Sets the location of the image
        contentPane.add(LogoLabel);

        JLabel UsernameLabel = new JLabel("Username:");
        UsernameLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        UsernameLabel.setForeground(Color.WHITE);
        UsernameLabel.setBounds(513, 135, 145, 35);
        contentPane.add(UsernameLabel);

        UsernameTextField = new JTextField();
        UsernameTextField.setBounds(513, 181, 189, 35);
        contentPane.add(UsernameTextField);
        UsernameTextField.setColumns(10);

        JLabel PasswordLabel = new JLabel("Password:");
        PasswordLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        PasswordLabel.setForeground(Color.WHITE);
        PasswordLabel.setBounds(513, 227, 145, 35);
        contentPane.add(PasswordLabel);

        PasswordTextField = new JPasswordField();
        PasswordTextField.setBounds(513, 273, 189, 35);
        contentPane.add(PasswordTextField);
        PasswordTextField.setColumns(10);

        JButton LoginButton = new JButton("Login");
        LoginButton.setBackground(new Color(184, 134, 11));
        LoginButton.setForeground(Color.WHITE);
        LoginButton.setBounds(550, 352, 117, 44);
        contentPane.add(LoginButton);

        JLabel ErrorLabel = new JLabel("Incorrect username or password.");
        ErrorLabel.setForeground(Color.RED);
        ErrorLabel.setBounds(513, 319, 212, 14);
        ErrorLabel.setVisible(false);
        contentPane.add(ErrorLabel);

        LoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO Provjera u bazi da li posotoji korisnik sa tim username i passwordom
                if(!"admin".equals(UsernameTextField.getText())){
                    ErrorLabel.setVisible(true);
                } else{
                    //TODO Nova Stranica
                    MainFrame mainFrame = new MainFrame();
                    mainFrame.setVisible(true);
                    Main.loginFrame.dispose();
                }
            }
        });

    }
}
