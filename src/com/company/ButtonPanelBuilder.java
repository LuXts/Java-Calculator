// ButtonPanelBuilder.java
package com.company;

import javax.swing.*;
import java.awt.*;

/**
 * The type Button panel builder.
 */
public class ButtonPanelBuilder {
    private final JPanel panel = new JPanel();


    /**
     * Sets size.
     *
     * @param width  the width
     * @param height the height
     * @return the size
     */
    public ButtonPanelBuilder setSize(int width, int height) {
        panel.setPreferredSize(new Dimension(width, height));
        return this;
    }

    /**
     * Sets grid layout.
     *
     * @param layout the layout
     * @return the grid layout
     */
    public ButtonPanelBuilder setGridLayout(GridLayout layout) {
        panel.setLayout(layout);
        return this;
    }


    /**
     * Add item button panel builder.
     *
     * @param comp the comp
     * @return the button panel builder
     */
    public ButtonPanelBuilder addItem(Component comp) {
        panel.add(comp);
        return this;
    }

    /**
     * Build finalize j panel.
     *
     * @return the j panel
     */
    public JPanel buildFinalize() {
        return panel;
    }
}
