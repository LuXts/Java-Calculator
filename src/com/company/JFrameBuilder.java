// JFrameBuilder.java
package com.company;

import javax.swing.*;
import java.awt.*;

/**
 * The type JFrame builder.
 */
public class JFrameBuilder {

    private final JPanel panel = new JPanel();
    private final GridBagLayout gridBagLayout = new GridBagLayout();
    private final GridBagConstraints constraints;
    private String title;

    /**
     * Instantiates a new JFrame builder.
     */
    public JFrameBuilder() {
        title = "";
        panel.setLayout(gridBagLayout);
        this.constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.anchor = GridBagConstraints.SOUTH;
    }

    /**
     * Sets title.
     *
     * @param title the title
     * @return the title
     */
    public JFrameBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Sets size.
     *
     * @param width  the width
     * @param height the height
     * @return the size
     */
    public JFrameBuilder setSize(int width, int height) {
        panel.setPreferredSize(new Dimension(width, height));
        return this;
    }

    /**
     * Add item JFrame builder.
     *
     * @param comp the comp
     * @return the JFrame builder
     */
    public JFrameBuilder addItem(Component comp) {
        gridBagLayout.setConstraints(comp, this.constraints);
        panel.add(comp);
        return this;
    }

    /**
     * Build finalize JFrame.
     *
     * @return the JFrame
     */
    public JFrame build() {
        JFrame frame = new JFrame(title);
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
        return frame;
    }
}
