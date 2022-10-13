// Main.java
package com.company;

import com.formdev.flatlaf.intellijthemes.FlatMaterialDesignDarkIJTheme;

import javax.swing.*;
import java.awt.*;

/**
 * The type Main.
 */
public class Main {
    // 创建 GUI
    private static void createAndShowGUI() {
        // 主题美化 https://www.formdev.com/flatlaf/
        FlatMaterialDesignDarkIJTheme.setup();
        UIManager.put("Button.arc", 5);

        // JFrame 工厂
        JFrameBuilder frameBuilder = new JFrameBuilder();

        // GridBagLayout 配套设置
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(10, 10, 10, 10);

        // 存放 lhs 的 Label
        JLabel lhsLabel = new JLabel("");
        lhsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lhsLabel.setSize(320, 72);
        lhsLabel.setPreferredSize(new Dimension(300, 50));
        lhsLabel.setFont(new java.awt.Font("微软雅黑", Font.BOLD, 20));

        // 存放 rhs 的 Label
        JLabel rhsLabel = new JLabel("0");
        rhsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        rhsLabel.setFont(new java.awt.Font("微软雅黑", Font.BOLD, 35));
        rhsLabel.setPreferredSize(new Dimension(300, 50));
        frameBuilder.addItem(lhsLabel, constraints).addItem(rhsLabel, constraints);

        // Button 面板工厂
        ButtonPanelBuilder buttonPanelBuilder = new ButtonPanelBuilder();
        buttonPanelBuilder.setSize(300, 300)
                .setGridLayout(new GridLayout(5, 4, 1, 1));

        // Calculator 类
        Calculator calculator = new Calculator();

        // 监听器
        ButtonActionListener actionListener = new ButtonActionListener(calculator, lhsLabel, rhsLabel);

        // 按钮字符
        String[] list = {
                "CE",    "C",    "Del",  "+",
                "7",     "8",    "9",    "-",
                "4",     "5",    "6",    "×",
                "1",     "2",    "3",    "÷",
                "0",     ".",    "+/-",  "="
        };
        // 生成按钮并绑定监听器
        for (String str : list) {
            JButton button = new JButton(str);
            // 设置字体
            button.setFont(new java.awt.Font("微软雅黑", Font.BOLD, 20));
            // 绑定监听器
            button.addActionListener(actionListener);
            // 添加按钮
            buttonPanelBuilder.addItem(button);
        }

        // 往 JFrame 工厂里面添加 Button Panel
        constraints.anchor = GridBagConstraints.SOUTH;
        frameBuilder.addItem(buttonPanelBuilder.buildFinalize(), constraints);

        // 设置标题和尺寸
        JFrame frame = frameBuilder.setTitle("计算器")
                .setSize(320, 468)
                .buildFinalize();

        // 设置图标
        frame.setIconImage(new ImageIcon("icon.png").getImage());
        // 显示
        frame.setVisible(true);
    }


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        // Swing 线程问题
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }


}