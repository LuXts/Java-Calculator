// ButtonActionListener.java
package com.company.button;

import com.company.button.functions.BaseButtonFunction;
import com.company.data.CalculatorData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The type Button action listener.
 */
public class ButtonActionListener implements ActionListener {

    private final JLabel lhsLabel;
    private final JLabel rhsLabel;
    private final ButtonFunctionFactory factory;
    private CalculatorData calculatorData;

    /**
     * Instantiates a new Button action listener.
     *
     * @param calculatorData the calculator
     * @param lhsLabel       the lhs label
     * @param rhsLabel       the rhs label
     */
    public ButtonActionListener(CalculatorData calculatorData, ButtonFunctionFactory factory, JLabel lhsLabel, JLabel rhsLabel) {
        this.calculatorData = calculatorData;
        this.factory = factory;
        this.lhsLabel = lhsLabel;
        this.rhsLabel = rhsLabel;
    }


    private void setLabel() {
        lhsLabel.setText(calculatorData.getLhsText());
        rhsLabel.setText(calculatorData.getRhsText());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();

        // 根据按钮字符调用不同函数。
//        switch (button.getText()) {
//            case "C" -> calculator.clear();
//            case "CE" -> calculator.clearEnter();
//            case "Del" -> calculator.delNumber();
//            case "=" -> calculator.calculationResults();
//            case "+/-" -> calculator.changePositiveNegative();
//            case "+", "-", "×", "÷" -> calculator.addSignOperation(button.getText().charAt(0));
//            default -> calculator.addNumber(button.getText().charAt(0));
//        }
        BaseButtonFunction function = factory.getFunction(button.getText());
        calculatorData = function.exec(calculatorData);
        setLabel();
    }
}
