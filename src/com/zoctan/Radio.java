package com.zoctan;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * 选项
 *
 * @author Zoctan
 */
public class Radio extends JRadioButton {

    public Radio(String text, ActionListener listener) {
        super(text);
        this.addActionListener(listener);
    }

    public Radio(String text, boolean selected, ActionListener listener) {
        super(text, selected);
        this.addActionListener(listener);
    }
}
