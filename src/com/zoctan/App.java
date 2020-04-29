package com.zoctan;

import javax.swing.*;

/**
 * 应用
 *
 * @author Zoctan
 */
public class App {

    public static void main(String[] args) {
        // 窗口
        JFrame frame = new JFrame(Data.appName);
        frame.setIconImage(Data.icon.getImage());
        frame.setBounds(0, 0, Data.windowsSize.getX(), Data.windowsSize.getY());
        // 居中
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //菜单栏
        frame.setJMenuBar(new MenuBar());

        // 面板
        frame.add(new Panel());

        frame.setVisible(true);
    }
}
