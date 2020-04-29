package com.zoctan;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;

import static com.zoctan.Data.isDebug;
import static javax.swing.JOptionPane.*;

/**
 * 菜单栏
 *
 * @author Zoctan
 */
public class MenuBar extends JMenuBar implements ActionListener {
    private JMenuItem openMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem exitMenuItem;
    private JMenuItem profileMenuItem;
    private JMenuItem skinMenuItem;
    private JMenuItem helpMenuItem;
    private JMenuItem aboutMenuItem;
    private JCheckBoxMenuItem musicMenuItem;
    private JCheckBoxMenuItem debugMenuItem;

    public MenuBar() {
        add(createFileMenu());
        add(createSettingMenu());
        add(createHelpMenu());
    }

    private JMenu createFileMenu() {
        JMenu menu = new Menu("文件(F)", this);
        // 设置快速访问符
        menu.setMnemonic(KeyEvent.VK_F);
        openMenuItem = new JMenuItem("载入存档(L)", KeyEvent.VK_L);
        openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
        menu.add(openMenuItem);
        menu.addSeparator();
        saveMenuItem = new JMenuItem("进度存档(S)", KeyEvent.VK_S);
        saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        menu.add(saveMenuItem);
        menu.addSeparator();
        exitMenuItem = new JMenuItem("退出(E)", KeyEvent.VK_E);
        exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
        menu.add(exitMenuItem);
        return menu;
    }

    private JMenu createSettingMenu() {
        JMenu menu = new Menu("设置(S)", this);
        menu.setMnemonic(KeyEvent.VK_S);
        profileMenuItem = new JMenuItem("配置(P)", KeyEvent.VK_P);
        profileMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
        menu.add(profileMenuItem);
        menu.addSeparator();
        skinMenuItem = new JMenuItem("皮肤(S)", KeyEvent.VK_S);
        skinMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        menu.add(skinMenuItem);
        menu.addSeparator();
        musicMenuItem = new JCheckBoxMenuItem("背景音乐(M)");
        musicMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK));
        menu.add(musicMenuItem);
        menu.addSeparator();
        debugMenuItem = new JCheckBoxMenuItem("调试(D)");
        debugMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
        menu.add(debugMenuItem);
        return menu;
    }

    private JMenu createHelpMenu() {
        JMenu menu = new Menu("帮助(H)", this);
        menu.setMnemonic(KeyEvent.VK_H);
        helpMenuItem = new JMenuItem("文档(D)", KeyEvent.VK_D);
        menu.add(helpMenuItem);
        menu.addSeparator();
        aboutMenuItem = new JMenuItem("关于(A)", KeyEvent.VK_A);
        menu.add(aboutMenuItem);
        return menu;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == openMenuItem) {
            JFileChooser chooseFile = new JFileChooser();
            int returnVal = chooseFile.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File f = chooseFile.getSelectedFile();
                int confirm = showDialog("confirm", "选择文件：" + chooseFile.getName(f));
                // todo
                // 读取数据
            }
        } else if (event.getSource() == saveMenuItem) {
            JFileChooser chooseFile = new JFileChooser();
            int returnVal = chooseFile.showSaveDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File f = chooseFile.getSelectedFile();
                int confirm = showDialog("confirm", "保存到文件：" + chooseFile.getName(f));
                // todo
                // 保存数据
            }
        } else if (event.getSource() == exitMenuItem) {
            int confirm = showDialog("confirm", "确定退出吗？");
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        } else if (event.getSource() == profileMenuItem) {
            Form form = new Form();
            // todo
            // 读取数据到配置表单
        } else if (event.getSource() == skinMenuItem) {
            // todo
            // 换皮肤
        } else if (event.getSource() == musicMenuItem) {
            // todo
            // 背景音乐
        } else if (event.getSource() == debugMenuItem) {
            isDebug = debugMenuItem.getState();
        } else if (event.getSource() == helpMenuItem) {
            showDialog("message", "<!DOCTYPE><html lang='zh'><head><meta charset='utf-8'><style>.red{color:red}</style></head><body><div>方向键控制：</div><div><span>上：<span class='red'>↑</span></span></div><div><span>下：<span class='red'>↓</span></span></div><div><span>左：<span class='red'>←</span></span></div><div><span>右：<span class='red'>→</span></span></div></body></html>");
        } else if (event.getSource() == aboutMenuItem) {
            showDialog("message", "<!DOCTYPE><html lang='zh'><head><meta charset='utf-8'><style>.blue{color:blue}</style></head><body><span>作者：<span class='blue'>https://github.com/Zoctan</body></html>");
        }
    }

    private int showDialog(String type, String content) {
        switch (type) {
            case "confirm":
                return showConfirmDialog(this.getParent(), content, Data.appName, JOptionPane.YES_NO_OPTION, QUESTION_MESSAGE);
            case "message":
                JLabel label = new JLabel();
                JEditorPane editorPane = new JEditorPane("text/html", content);
                editorPane.setEditable(false);
                editorPane.setBackground(label.getBackground());
                showMessageDialog(this.getParent(), editorPane, Data.appName, PLAIN_MESSAGE);
                break;
            default:
                break;
        }
        return 0;
    }
}
