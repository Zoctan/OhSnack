package com.zoctan;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;

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
    private AudioPlayer audioPlayer;

    public MenuBar() {
        this.add(this.createFileMenu());
        this.add(this.createSettingMenu());
        this.add(this.createHelpMenu());
        // 背景音乐
        this.musicMenuItem.setState(App.data.isMusic);
        this.onMusicChange();
    }

    private JMenu createFileMenu() {
        JMenu menu = new Menu("文件(F)", this);
        // 设置快速访问符
        menu.setMnemonic(KeyEvent.VK_F);
        this.openMenuItem = new JMenuItem("载入存档(L)", KeyEvent.VK_L);
        this.openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
        menu.add(this.openMenuItem);
        menu.addSeparator();
        this.saveMenuItem = new JMenuItem("进度存档(S)", KeyEvent.VK_S);
        this.saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        menu.add(this.saveMenuItem);
        menu.addSeparator();
        this.exitMenuItem = new JMenuItem("退出(E)", KeyEvent.VK_E);
        this.exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
        menu.add(this.exitMenuItem);
        return menu;
    }

    private JMenu createSettingMenu() {
        JMenu menu = new Menu("设置(S)", this);
        menu.setMnemonic(KeyEvent.VK_S);
        this.profileMenuItem = new JMenuItem("配置(P)", KeyEvent.VK_P);
        this.profileMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
        menu.add(this.profileMenuItem);
        menu.addSeparator();
        this.skinMenuItem = new JMenuItem("皮肤(S)", KeyEvent.VK_S);
        this.skinMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        menu.add(this.skinMenuItem);
        menu.addSeparator();
        this.musicMenuItem = new JCheckBoxMenuItem("背景音乐(M)");
        this.musicMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK));
        menu.add(this.musicMenuItem);
        menu.addSeparator();
        this.debugMenuItem = new JCheckBoxMenuItem("调试(D)");
        this.debugMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
        menu.add(this.debugMenuItem);
        return menu;
    }

    private JMenu createHelpMenu() {
        JMenu menu = new Menu("帮助(H)", this);
        menu.setMnemonic(KeyEvent.VK_H);
        this.helpMenuItem = new JMenuItem("文档(D)", KeyEvent.VK_D);
        menu.add(this.helpMenuItem);
        menu.addSeparator();
        this.aboutMenuItem = new JMenuItem("关于(A)", KeyEvent.VK_A);
        menu.add(this.aboutMenuItem);
        return menu;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == this.openMenuItem) {
            JFileChooser chooseFile = new JFileChooser();
            int returnVal = chooseFile.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File f = chooseFile.getSelectedFile();
                int confirm = this.showDialog("confirm", "选择文件：" + chooseFile.getName(f));
                if (confirm == 0) {
                    // 读取数据
                    App.data = (Data) IoUtil.readFromFile(chooseFile.getSelectedFile().getPath());
                    // 速度读取
                    App.timer.stop();
                    App.timer.setDelay(1000 / App.data.frames);
                    App.timer.start();
                    // 菜单栏选项状态
                    this.musicMenuItem.setState(App.data.isMusic);
                    this.onMusicChange();
                    this.debugMenuItem.setState(App.data.isDebug);
                    // 重绘游戏
                    App.setFrame(App.data.windowsSize.getX(), App.data.windowsSize.getY());
                    App.panel.repaint();
                }
            }
        } else if (event.getSource() == this.saveMenuItem) {
            JFileChooser chooseFile = new JFileChooser();
            int returnVal = chooseFile.showSaveDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File f = chooseFile.getSelectedFile();
                int confirm = this.showDialog("confirm", "保存到文件：" + chooseFile.getName(f));
                if (confirm == 0) {
                    // 保存数据
                    IoUtil.save2File(App.data, chooseFile.getSelectedFile().getPath());
                }
            }
        } else if (event.getSource() == this.exitMenuItem) {
            int confirm = this.showDialog("confirm", "确定退出吗？");
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        } else if (event.getSource() == this.profileMenuItem) {
            new Profile("配置");
        } else if (event.getSource() == this.skinMenuItem) {
            new Skin("皮肤");
        } else if (event.getSource() == this.musicMenuItem) {
            this.onMusicChange();
        } else if (event.getSource() == this.debugMenuItem) {
            App.data.isDebug = this.debugMenuItem.getState();
        } else if (event.getSource() == this.helpMenuItem) {
            this.showDialog("message", "<!DOCTYPE><html lang='zh'><head><meta charset='utf-8'><style>.red{color:red}</style></head><body><div>方向键控制：</div><div><span>上：<span class='red'>↑</span></span></div><div><span>下：<span class='red'>↓</span></span></div><div><span>左：<span class='red'>←</span></span></div><div><span>右：<span class='red'>→</span></span></div></body></html>");
        } else if (event.getSource() == this.aboutMenuItem) {
            this.showDialog("message", "<!DOCTYPE><html lang='zh'><head><meta charset='utf-8'><style>.blue{color:blue}</style></head><body><span>作者：<span class='blue'>https://github.com/Zoctan</body></html>");
        }
    }

    private int showDialog(String type, String content) {
        switch (type) {
            case "confirm":
                return showConfirmDialog(this.getParent(), content, App.data.appName, JOptionPane.YES_NO_OPTION, QUESTION_MESSAGE);
            case "message":
                JLabel label = new JLabel();
                JEditorPane editorPane = new JEditorPane("text/html", content);
                editorPane.setEditable(false);
                editorPane.setBackground(label.getBackground());
                showMessageDialog(this.getParent(), editorPane, App.data.appName, PLAIN_MESSAGE);
                break;
            default:
                break;
        }
        return 0;
    }

    private void onMusicChange() {
        // 背景音乐
        if (this.musicMenuItem.getState()) {
            if (this.audioPlayer == null) {
                this.audioPlayer = new AudioPlayer(App.data.backgroundMusicUrl);
            }
            this.audioPlayer.start();
            App.data.isMusic = true;
        } else {
            if (this.audioPlayer != null) {
                this.audioPlayer.close();
                this.audioPlayer = null;
            }
            App.data.isMusic = false;
        }
    }
}
