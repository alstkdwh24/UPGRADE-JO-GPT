package com.example.jo_edu.Icon;

import javax.swing.*;

public class IconExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        // Create a JLabel with an icon
        ImageIcon icon = new ImageIcon("path/to/your/icon.png");

        JButton button = new JButton(icon);

        frame.add(button);
        frame.setVisible(true);
    }
}
