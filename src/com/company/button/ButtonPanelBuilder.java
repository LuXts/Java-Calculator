// ButtonPanelBuilder.java
package com.company.button;

import com.company.data.CalculatorData;

import javax.swing.*;
import java.awt.*;

/**
 * The type Button panel builder.
 */
public class ButtonPanelBuilder {
    private final JPanel panel = new JPanel();
    private CalculatorData calculatorData = null;
    private JLabel lhsLabel = null;
    private JLabel rhsLabel = null;


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

    public ButtonPanelBuilder setCalculatorData(CalculatorData calculatorData) {
        this.calculatorData = calculatorData;
        return this;
    }

    public ButtonPanelBuilder bindLhsAndRhsLabel(JLabel lhsLabel, JLabel rhsLabel) {
        this.lhsLabel = lhsLabel;
        this.rhsLabel = rhsLabel;
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
    public JPanel build() {
        // 监听器
        ButtonFunctionFactory factory = new ButtonFunctionFactory();

        ButtonActionListener actionListener = new ButtonActionListener(this.calculatorData, factory, this.lhsLabel, this.rhsLabel);

        // 按钮字符
        String[] list = {
                "CE", "C", "Del", "+",
                "7", "8", "9", "-",
                "4", "5", "6", "×",
                "1", "2", "3", "÷",
                "+/-", "0", ".", "="
        };
        // 生成按钮并绑定监听器
        for (String str : list) {
            JButton button = new JButton(str);
            // 设置字体
            button.setFont(new java.awt.Font("微软雅黑", Font.BOLD, 20));
            // 绑定监听器
            button.addActionListener(actionListener);
            // 添加按钮
            this.addItem(button);
        }
        return panel;
    }
}
