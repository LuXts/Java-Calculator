package com.company.window;

import com.company.JFrameBuilder;
import com.company.LabelBuilder;
import com.company.button.ButtonPanelBuilder;
import com.company.data.CalculatorData;

import javax.swing.*;
import java.awt.*;

public class BaseWindowFactory {
    public JFrame createThemeWindow() {
        // JFrame 创建者
        JFrameBuilder frameBuilder = new JFrameBuilder();

        LabelBuilder labelBuilder = new LabelBuilder();
        // 存放 lhs 的 Label
        JLabel lhsLabel = labelBuilder.setText("")
                .setFontAndSize(20)
                .setSize(320, 72)
                .setPreferredSize(300, 50)
                .build();

        labelBuilder = new LabelBuilder();
        // 存放 rhs 的 Label
        JLabel rhsLabel = labelBuilder.setText("0")
                .setFontAndSize(35)
                .setPreferredSize(300, 50)
                .build();

        // Calculator 数据类
        CalculatorData calculatorData = new CalculatorData();


        // Button 面板创建者
        ButtonPanelBuilder buttonPanelBuilder = new ButtonPanelBuilder();
        JPanel buttonPanel = buttonPanelBuilder.setSize(300, 300)
                .setGridLayout(new GridLayout(5, 4, 1, 1))
                .setCalculatorData(calculatorData)
                .bindLhsAndRhsLabel(lhsLabel, rhsLabel)
                .build();


        // 设置标题和尺寸
        JFrame frame = frameBuilder.addItem(lhsLabel)
                .addItem(rhsLabel)
                .addItem(buttonPanel)
                .setTitle("计算器")
                .setSize(320, 468)
                .build();
        frame.setIconImage(new ImageIcon("icon.png").getImage());
        return frame;
    }
}
