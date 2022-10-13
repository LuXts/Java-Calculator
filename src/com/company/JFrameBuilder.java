// JFrameBuilder.java
package com.company;

import javax.swing.*;
import java.awt.*;

/**
 * The type JFrame builder.
 */
public class JFrameBuilder {

    private final JPanel panel = new JPanel();
    private String title;
    private final GridBagLayout gridBagLayout = new GridBagLayout();

    /**
     * Instantiates a new JFrame builder.
     */
    public JFrameBuilder() {
        title = "";
        panel.setLayout(gridBagLayout);
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
     * @param comp        the comp
     * @param constraints the constraints
     * @return the JFrame builder
     */
    public JFrameBuilder addItem(Component comp, GridBagConstraints constraints) {
        gridBagLayout.setConstraints(comp, constraints);
        panel.add(comp);
        return this;
    }

    /**
     * Build finalize JFrame.
     *
     * @return the JFrame
     */
    public JFrame buildFinalize() {
        JFrame frame = new JFrame(title);
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
        return frame;
    }
}
