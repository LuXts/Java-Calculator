// Main.java
package com.company;

import com.company.window.BaseWindowFactory;
import com.company.window.DarkIJThemeWindowFactory;

import javax.swing.*;

/**
 * The type Main.
 */
public class Main {
    // 创建 GUI
    private static void createAndShowGUI() {
        BaseWindowFactory factory = new DarkIJThemeWindowFactory();

        JFrame frame = factory.createThemeWindow();

        frame.setVisible(true);
    }


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        // Swing 线程问题
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }


}