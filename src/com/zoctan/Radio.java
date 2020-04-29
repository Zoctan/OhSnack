package com.zoctan;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Radio extends JRadioButton {

    public Radio(String name, ActionListener listener) {
        super(name);
        this.addActionListener(listener);
    }
}
