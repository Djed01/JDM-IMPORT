package org.unibl.etf;

import org.unibl.etf.GUI.LoginFrame;

public class Main {
    public static LoginFrame loginFrame;

    public static void main(String[] args) {
        loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
    }
}