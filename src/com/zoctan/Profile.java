package com.zoctan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 配置界面
 *
 * @author Zoctan
 */
public class Profile extends JFrame implements ActionListener {

    private final JRadioButton[] speedRadio = new JRadioButton[3];
    private final JTextField xField;
    private final JTextField yField;
    private final Font defaultFont = new Font("微软雅黑", Font.BOLD, 16);

    public Profile(String name) {
        super(name);
        Container contentPane = this.getContentPane();

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(3, 1));

        Box box1 = Box.createHorizontalBox();
        JLabel speedLabel = new JLabel("速度：");
        speedLabel.setFont(this.defaultFont);
        this.createDirectionRadio();
        box1.add(Box.createHorizontalStrut(8));
        box1.add(speedLabel);

        ButtonGroup group = new ButtonGroup();
        for (JRadioButton jRadioButton : this.speedRadio) {
            group.add(jRadioButton);
            box1.add(jRadioButton);
        }

        Box box2 = Box.createHorizontalBox();
        JLabel sizeLabel = new JLabel("大小：");
        sizeLabel.setFont(this.defaultFont);
        this.xField = new JTextField(String.format("%s", App.data.windowsSize.getX()), 1);
        this.yField = new JTextField(String.format("%s", App.data.windowsSize.getY()), 1);
        this.xField.setFont(this.defaultFont);
        this.yField.setFont(this.defaultFont);
        box2.add(Box.createHorizontalStrut(8));
        box2.add(sizeLabel);
        box2.add(this.xField);
        box2.add(this.yField);

        Box box3 = Box.createHorizontalBox();
        JButton submitButton = new JButton("确定");
        submitButton.addActionListener((e) -> this.onSubmit());
        box3.add(Box.createHorizontalStrut(8));
        box3.add(submitButton);

        northPanel.add(box1);
        northPanel.add(box2);
        northPanel.add(box3);
        contentPane.add(northPanel, BorderLayout.NORTH);

        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(250, 130);
        this.setVisible(true);
    }

    private void createDirectionRadio() {
        this.speedRadio[0] = new Radio("0.5x", this);
        this.speedRadio[1] = new Radio("1x", this);
        this.speedRadio[2] = new Radio("2x", this);
        this.speedRadio[0].setFont(this.defaultFont);
        this.speedRadio[1].setFont(this.defaultFont);
        this.speedRadio[2].setFont(this.defaultFont);
        if (App.data.frames == 7 / 2) {
            this.speedRadio[0].setSelected(true);
        } else if (App.data.frames == 7) {
            this.speedRadio[1].setSelected(true);
        } else {
            this.speedRadio[2].setSelected(true);
        }
    }

    private void onSubmit() {
        // 界面大小
        App.data.windowsSize.setX(Integer.parseInt(this.xField.getText()));
        App.data.windowsSize.setY(Integer.parseInt(this.yField.getText()));
        App.data.headerSize.setX(App.data.windowsSize.getX());
        App.data.mapSize.setX(App.data.windowsSize.getX());
        App.data.mapSize.setY(App.data.windowsSize.getY() - App.data.headerSize.getY() - App.data.cellSize - 10);
        App.setFrame(App.data.windowsSize.getX(), App.data.windowsSize.getY());
        // 速度重设
        App.timer.stop();
        App.timer.setDelay(1000 / App.data.frames);
        App.timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == this.speedRadio[0]) {
            App.data.frames = 7 / 2;
        } else if (event.getSource() == this.speedRadio[1]) {
            App.data.frames = 7;
        } else if (event.getSource() == this.speedRadio[2]) {
            App.data.frames = 7 * 2;
        }
    }
}