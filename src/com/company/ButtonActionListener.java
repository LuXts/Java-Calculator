// ButtonActionListener.java
package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The type Button action listener.
 */
public class ButtonActionListener implements ActionListener {

    private final Calculator calculator;

    private final JLabel lhsLabel;

    private final JLabel rhsLabel;

    /**
     * Instantiates a new Button action listener.
     *
     * @param calculator the calculator
     * @param lhsLabel   the lhs label
     * @param rhsLabel   the rhs label
     */
    public ButtonActionListener(Calculator calculator, JLabel lhsLabel, JLabel rhsLabel) {
        this.calculator = calculator;
        this.lhsLabel = lhsLabel;
        this.rhsLabel = rhsLabel;
    }


    private void setLabel() {
        lhsLabel.setText(calculator.getLhsText());
        rhsLabel.setText(calculator.getRhsText());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();

        // 根据按钮字符调用不同函数。
        switch (button.getText()) {
            case "C" -> calculator.clear();
            case "CE" -> calculator.clearEnter();
            case "Del" -> calculator.delNumber();
            case "=" -> calculator.calculationResults();
            case "+/-" -> calculator.changePositiveNegative();
            case "+", "-", "×", "÷" -> calculator.addSignOperation(button.getText().charAt(0));
            default -> calculator.addNumber(button.getText().charAt(0));
        }
        setLabel();
    }
}
