// Calculator.java
package com.company;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 * 计算器类，对外提供计算结果和 lhs rhs
 */
public class Calculator {

    private final ArrayList<Character> lhsText = new ArrayList<>(); // 左手表达式

    private final ArrayList<Character> rhsText = new ArrayList<>(); // 右手表达式

    private boolean dot = false; // 防止重新输入小数点的标记

    private boolean mark = true; // 允许修改运算符的标记

    private boolean pn = true; // 正负数标记

    private Character signOperation = null; // 运算符

    /**
     * Instantiates a new Calculator.
     * 要做一次清零。
     */
    public Calculator() {
        clearEnter();
    }

    /**
     * 删除 rhsText ， 按下 C 按钮的时候调用
     */
    public void clear() {
        // 重置运算符标记和正负数标记，小数点标记
        mark = true;
        pn = true;
        dot = false;
        if (lhsText.size() > 1) {
            if (lhsText.get(lhsText.size() - 1) == '=') {
                // 如果目前 rhsText 里面放着上次运算的结果的话，调用 CE 操作
                clearEnter();
                return;
            }
        }
        // 清理rhsText，添加 "0"
        rhsText.clear();
        rhsText.add('0');
    }

    /**
     * 按下 CE 按钮调用的函数
     */
    public void clearEnter() {
        // 重置运算符标记和正负数标记，小数点标记还有运算符
        mark = true;
        pn = true;
        dot = false;
        signOperation = null;
        // 清理 rhsText 和 lhsText
        lhsText.clear();
        rhsText.clear();
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


    /**
     * 添加数字。
     *
     * @param c "0"-"9"，还包括了 "."
     */
    public void addNumber(Character c) {
        // 重置运算符标记
        mark = true;
        // 如果现在正好是上次运算完成后，那么先进行一次 CE
        if (lhsText.size() != 0) {
            if (lhsText.get(lhsText.size() - 1) == '=') {
                clearEnter();
            }
        }
        // 输入数字
        if (rhsText.size() == 1 && rhsText.get(0) == '0') {
            // 如果目前 rhsText 是 "0"，
            if (c!='.') {
                // 先删掉 "0" 再添加数字
                rhsText.clear();
            } else {
                // 那么对 "." 输入做特殊处理
                dot = true;
            }
        } else {
            // 对 dot 做处理
            if (c=='.') {
                if (dot) {
                    // 如果 dot 是 true 且输入是 "." 就直接返回
                    return;
                } else {
                    // 否则把 dot 置为 true
                    dot = true;
                }
            }
            // rhsText 尾部添加输入
        }
        // rhsText 尾部加上输入的内容
        rhsText.add(c);
    }

    /**
     * 点击删除按钮的时候调用
     */
    public void delNumber() {
        // 重置运算符标记
        mark = true;
        // 如果 rhsText 只有一个字符了，那就直接 clear
        if (rhsText.size() == 1) {
            clear();
            return;
        }
        // 如果删掉的目标字符是 '.' 那么还要重置 dot 标志。
        Character c = rhsText.get(rhsText.size() - 1);
        if (c == '.') {
            dot = false;
        }
        // 删除字符
        rhsText.remove(rhsText.size() - 1);
    }

    /**
     * 添加运算符
     *
     * @param c 运算符
     */
    public void addSignOperation(Character c) {
        // 根据 mark 的值决定是修改 lhsText 末尾的运算符还是生成新的 lhsText 出来
        if (mark) {
            // 这里是计算新的 lhsText
            // 首先调用一次运算，实现按下 5 * 5 * 5 的时候最终结果为 25 * 5 的效果
            calculationResults();
            // lhsText 清空
            lhsText.clear();
            // 根据正负号标记加上负号
            if (!pn) {
                lhsText.add('-');
            }
            // 把 rhsText 整个拷贝到 lhsText
            lhsText.addAll(rhsText);
            // 末尾添加运算符
            lhsText.add(c);

            // 记录运算符
            signOperation = c;

            // 这里要做的事情和 clear 差不多，都是清理 rhsText
            // 除了 mark 要修改，所以直接调用 clear
            clear();
            mark = false;
        } else {
            // 这里是修改 lhs 末尾的运算符
            lhsText.remove(lhsText.size() - 1);
            lhsText.add(c);
            // 记录新的运算符
            signOperation = c;
        }

    }

    /**
     * 计算结果的函数，按下 = 按钮的时候会调用 （实际上按下运算符的时候也会调用）
     */
    public void calculationResults() {
        // 重置运算符标记
        mark = true;
        dot = false;
        // 如果运算符为 null，那么就直接把 rhsText 拷贝到 lhsText ，并在末尾加上 '=' ，
        // rhsText 本身啥也不用改。
        if (signOperation == null) {
            if(rhsText.get(rhsText.size()-1)=='.'){
                rhsText.remove(rhsText.size()-1);
            }
            lhsText.clear();
            // 根据正负号添加 '-'
            if (!pn) {
                lhsText.add('-');
            }
            lhsText.addAll(rhsText);
            // 添加 '='
            lhsText.add('=');
        } else {
            // 从 lhsText 里面排除最后一个符号（这个符号总是某个运算符），转变为字符串，然后再转变为 BigDecimal
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < lhsText.size() - 1; i++) {
                str.append(lhsText.get(i));
            }

            // 提取出 lhs 和 rhs 用于等下的运算
            BigDecimal lhs = new BigDecimal(str.toString());
            BigDecimal rhs = new BigDecimal(getRhsText());

            // 趁着 rhsText 还没被改变，生成新的 lhsText 内容
            // 根据正负号标记加上 ”(- )“ ，变成 "(-4)" 这样的形式
            if (!pn) {
                lhsText.add('(');
                lhsText.add('-');
            }
            lhsText.addAll(rhsText);
            if (!pn) {
                lhsText.add(')');
            }
            lhsText.add('=');

            // temp 是运算结果的字符串
            String temp = "";
            // 根据 signOperation 对 lhs 和 rhs 进行运算
            switch (signOperation) {
                case '+' -> temp = lhs.add(rhs).stripTrailingZeros().toString();
                case '-' -> temp = lhs.subtract(rhs).stripTrailingZeros().toString();
                case '×' -> temp = lhs.multiply(rhs).stripTrailingZeros().toString();
                case '÷' -> {
                    // 如果除数为 0 ，那么就直接 CE
                    if (rhs.compareTo(new BigDecimal(0)) == 0) {
                        clearEnter();
                        return;
                    }
                    // 正常的除法，设置保留小数点后 12 位，而且去除末尾 0 （避免输出 8.0 这种情况）
                    temp = lhs.divide(rhs, 12, RoundingMode.HALF_UP).stripTrailingZeros().toString();
                }

            }
            // 清空 rhsText
            rhsText.clear();
            // temp 拷贝到 rhsText
            for (int i = 0; i < temp.length(); i++) {
                rhsText.add(temp.charAt(i));
            }
            // 检查运算结果是否带负号
            if (rhsText.get(0) == '-') {
                // 如果带负号，要把 pn 置为 false
                pn = false;
                // 并且删除这个符号（因为负号会在 pn 为 false 的时候自动输出，不需要存在 rhsText 里面）
                rhsText.remove(0);
            } else {
                // 如果不带负号，就置为 true
                pn = true;
            }
            // 将储存的运算符置为 null
            signOperation = null;
        }

    }

    /**
     * 修改正负号
     */
    public void changePositiveNegative() {
        // 修改正负号标记
        pn = !pn;
        // 重置运算符标记
        mark = true;
    }

}
