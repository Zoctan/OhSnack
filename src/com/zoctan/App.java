package com.zoctan;

import javax.swing.*;

/**
 * 应用
 *
 * @author Zoctan
 */
public class App {

    public static Data data;
    public static Panel panel;

    public static void main(String[] args) {
        data = new Data();
        panel = new Panel();
        // 窗口
        JFrame frame = new JFrame(data.appName);
        frame.setIconImage(data.icon.getImage());
        frame.setBounds(0, 0, data.windowsSize.getX(), data.windowsSize.getY());
        // 居中
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //菜单栏
        frame.setJMenuBar(new MenuBar());

        // 面板
        frame.add(panel);

        frame.setVisible(true);
    }
}
