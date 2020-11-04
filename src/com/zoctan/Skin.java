package com.zoctan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * 皮肤界面
 *
 * @author Zoctan
 */
public class Skin extends JFrame implements ActionListener {

    private final JRadioButton[] skinRadio = new JRadioButton[6];

    private final Object[][] skins = new Object[][]{
            new Object[]{"喵喵", "/resource/role/cat/"},
            new Object[]{"汪汪", "/resource/role/dog/"},
            new Object[]{"钢铁侠", "/resource/role/ironman/"},
            new Object[]{"胖丁", "/resource/role/jigglypuff/"},
            new Object[]{"皮卡丘", "/resource/role/pikachu/"},
            new Object[]{"灭霸", "/resource/role/thanos/"}
    };

    public Skin(String name) {
        super(name);
        Container contentPane = this.getContentPane();

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(1, 1));

        Box box1 = Box.createHorizontalBox();
        for (int i = 0; i < this.skins.length; i++) {
            URL head = this.getClass().getResource(this.skins[i][1] + "upHead.png");
            String html = "<html><table><tr><td><img src='" + head + "' /></td></tr><tr><td>" + this.skins[i][0] + "</td></tr></html>";
            this.skinRadio[i] = new Radio(html, ((String) this.skins[i][1]).equalsIgnoreCase(App.data.roleUrl), this);
        }
        box1.add(Box.createHorizontalStrut(8));

        ButtonGroup group = new ButtonGroup();
        for (JRadioButton jRadioButton : this.skinRadio) {
            group.add(jRadioButton);
            box1.add(jRadioButton);
        }

        northPanel.add(box1);
        contentPane.add(northPanel, BorderLayout.NORTH);

        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(450, 100);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        for (int i = 0; i < this.skinRadio.length; i++) {
            if (event.getSource() == this.skinRadio[i]) {
                App.data.roleUrl = (String) this.skins[i][1];
                App.data.upHeadIcon = App.data.getIcon("upHead.png");
                App.data.downHeadIcon = App.data.getIcon("downHead.png");
                App.data.leftHeadIcon = App.data.getIcon("leftHead.png");
                App.data.rightHeadIcon = App.data.getIcon("rightHead.png");
                App.data.bodyIcon = App.data.getIcon("body.png");
                App.data.foodIcon = App.data.getIcon("food.png");
                App.panel.repaint();
                break;
            }
        }
    }
}