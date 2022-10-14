package com.company;

import javax.swing.*;
import java.awt.*;

public class LabelBuilder {
    private final JLabel label = new JLabel("");

    public LabelBuilder setText(String text) {
        label.setText(text);
        return this;
    }

    public LabelBuilder setSize(int width, int height) {
        label.setSize(width, height);
        return this;
    }

    public LabelBuilder setPreferredSize(int width, int height) {
        label.setPreferredSize(new Dimension(width, height));
        return this;
    }

    public LabelBuilder setFontAndSize(int size) {
        label.setFont(new java.awt.Font("微软雅黑", Font.BOLD, size));
        return this;
    }

    public JLabel build() {
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        return label;
    }
}
