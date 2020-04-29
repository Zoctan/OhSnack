package com.zoctan;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * 菜单
 *
 * @author Zoctan
 */
public class Menu extends JMenu {
    private ActionListener listener;

    public Menu(String name, ActionListener listener) {
        super(name);
        this.listener = listener;
    }

    @Override
    public JMenuItem add(JMenuItem item) {
        item.addActionListener(listener);
        return super.add(item);
    }
}
