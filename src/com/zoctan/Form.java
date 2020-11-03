package com.zoctan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 表单
 *
 * @author Zoctan
 */
public class Form extends JFrame implements ActionListener {

    private JRadioButton[] directionRadio = new JRadioButton[4];

    public Form() {
        Container contentPane = this.getContentPane();

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(2, 1));

        Box box1 = Box.createHorizontalBox();

        JLabel directionLabel = new JLabel("头方向：");
        this.createDirectionRadio();
        
        box1.add(Box.createHorizontalStrut(5));
        box1.add(directionLabel);

        ButtonGroup group = new ButtonGroup();
        for (JRadioButton jRadioButton : this.directionRadio) {
            group.add(jRadioButton);
            box1.add(jRadioButton);
        }

        northPanel.add(box1);
        contentPane.add(northPanel, BorderLayout.NORTH);

        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400, 300);
        this.setVisible(true);
    }

    private void createDirectionRadio() {
        this.directionRadio[0] = new Radio("上", this);
        this.directionRadio[1] = new Radio("下", this);
        this.directionRadio[2] = new Radio("左", this);
        this.directionRadio[3] = new Radio("右", this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == this.directionRadio[0]) {
            System.out.println("上");
        } else if (event.getSource() == this.directionRadio[1]) {
            System.out.println("下");
        } else if (event.getSource() == this.directionRadio[2]) {
            System.out.println("左");
        } else if (event.getSource() == this.directionRadio[3]) {
            System.out.println("右");
        }
    }
}