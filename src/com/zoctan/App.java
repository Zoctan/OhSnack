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
    public static Timer timer;
    public static JFrame frame;

    public static void main(String[] args) {
        data = new Data();
        panel = new Panel();
        timer = new Timer(1000 / App.data.frames, panel);
        timer.start();
        // 窗口
        frame = new JFrame(data.appName);
        frame.setIconImage(data.icon.getImage());

        setFrame(data.windowsSize.getX(), data.windowsSize.getY());

        //菜单栏
        frame.setJMenuBar(new MenuBar());

        // 面板
        frame.add(panel);

        frame.setVisible(true);
    }

    public static void setFrame(int x, int y) {
        frame.setBounds(0, 0, x, y);
        // 居中
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
