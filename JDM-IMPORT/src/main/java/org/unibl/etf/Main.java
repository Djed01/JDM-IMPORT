package org.unibl.etf;

import org.unibl.etf.GUI.LoginFrame;
import org.unibl.etf.GUI.MainFrame;

public class Main {
    public static LoginFrame loginFrame;

    public static void main(String[] args) {
//        loginFrame = new LoginFrame();
//        loginFrame.setVisible(true);
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }
}