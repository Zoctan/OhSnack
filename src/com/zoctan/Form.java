package com.zoctan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * 表单
 *
 * @author Zoctan
 */
public class Form extends JFrame implements ActionListener {

    private JRadioButton[] directionRadio = new JRadioButton[4];

    public Form() {
        Container contentPane = getContentPane();

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(2, 1));

        Box box1 = Box.createHorizontalBox();

        JLabel directionLabel = new JLabel("头方向：");
        createDirectionRadio();

        box1.add(Box.createHorizontalStrut(5));
        box1.add(directionLabel);

        ButtonGroup group = new ButtonGroup();
        for (JRadioButton jRadioButton : directionRadio) {
            group.add(jRadioButton);
            box1.add(jRadioButton);
        }

        northPanel.add(box1);
        contentPane.add(northPanel, BorderLayout.NORTH);

        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setVisible(true);
    }

    private void createDirectionRadio() {
        directionRadio[0] = new Radio("上", this);
        directionRadio[1] = new Radio("下", this);
        directionRadio[2] = new Radio("左", this);
        directionRadio[3] = new Radio("右", this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == directionRadio[0]) {
            System.out.println("上");
        } else if (event.getSource() == directionRadio[1]) {
            System.out.println("下");
        } else if (event.getSource() == directionRadio[2]) {
            System.out.println("左");
        } else if (event.getSource() == directionRadio[3]) {
            System.out.println("右");
        }
    }
}