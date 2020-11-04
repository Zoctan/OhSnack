package com.zoctan;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * 选项
 *
 * @author Zoctan
 */
public class Radio extends JRadioButton {

    public Radio(String name, ActionListener listener) {
        super(name);
        this.addActionListener(listener);
    }

    public Radio(String iconPath, boolean selected, ActionListener listener) {
        super(iconPath, selected);
        this.addActionListener(listener);
    }
}
