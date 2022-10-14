// Calculator.java
package com.company.data;


import java.util.ArrayList;

/**
 * 计算器类，对外提供计算结果和 lhs rhs
 */
public class CalculatorData {

    private ArrayList<Character> lhsText = new ArrayList<>(); // 左手表达式

    private ArrayList<Character> rhsText = new ArrayList<>(); // 右手表达式

    private boolean dot; // 防止重新输入小数点的标记

    private boolean mark; // 允许修改运算符的标记

    private boolean pn; // 正负数标记

    private Character signOperation; // 运算符

    /**
     * Instantiates a new Calculator.
     * 要做一次清零。
     */
    public CalculatorData() {
        // 重置运算符标记和正负数标记，小数点标记还有运算符
        mark = true;
        pn = true;
        dot = false;
        signOperation = null;
        rhsText.add('0');
    }

    /**
     * Gets lhs text.
     *
     * @return the lhs text
     */
    public String getLhsText() {
        // ArrayList<Character> 转 String
        StringBuilder str = new StringBuilder();
        for (Character character : lhsText) {
            str.append(character);
        }
        return str.toString();
    }

    /**
     * Gets rhs text.
     *
     * @return the rhs text
     */
    public String getRhsText() {
        // ArrayList<Character> 转 String
        StringBuilder str = new StringBuilder();
        if (!pn) {
            if (!(rhsText.size() == 1 && rhsText.get(0) == '0')) {
                // 假如 rhsText 里面不是只有一个 "0" 且正负号标识为负的时候，
                // 给输出的 String 增加一个 '-'
                str.append('-');
            }
        }
        for (Character character : rhsText) {
            str.append(character);
        }
        return str.toString();
    }

    public boolean getDot() {
        return dot;
    }

    public void setDot(boolean dot) {
        this.dot = dot;
    }

    public boolean getMark() {
        return mark;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }

    public boolean getPn() {
        return pn;
    }

    public void setPn(boolean pn) {
        this.pn = pn;
    }

    public Character getSignOperation() {
        return signOperation;
    }

    public void setSignOperation(Character signOperation) {
        this.signOperation = signOperation;
    }

    public ArrayList<Character> getLhsTextRaw() {
        return lhsText;
    }

    public void setLhsTextRaw(ArrayList<Character> list) {
        this.lhsText = list;
    }

    public ArrayList<Character> getRhsTextRaw() {
        return rhsText;
    }

    public void setRhsTextRaw(ArrayList<Character> list) {
        this.rhsText = list;
    }
}
