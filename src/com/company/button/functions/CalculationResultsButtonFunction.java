package com.company.button.functions;

import com.company.data.CalculatorData;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class CalculationResultsButtonFunction implements BaseButtonFunction {
    @Override
    public CalculatorData exec(CalculatorData data) {
        // 重置运算符标记
        data.setMark(true);
        data.setDot(false);
        ArrayList<Character> lhsText = data.getLhsTextRaw();
        ArrayList<Character> rhsText = data.getRhsTextRaw();
        // 如果运算符为 null，那么就直接把 rhsText 拷贝到 lhsText ，并在末尾加上 '=' ，
        // rhsText 本身啥也不用改。
        if (data.getSignOperation() == null) {
            if (rhsText.contains('.')) {
                while (rhsText.get(rhsText.size() - 1) == '0') {
                    rhsText.remove(rhsText.size() - 1);
                }
            }
            if (rhsText.get(rhsText.size() - 1) == '.') {
                rhsText.remove(rhsText.size() - 1);
            }
            lhsText.clear();
            // 根据正负号添加 '-'
            if (!data.getPn()) {
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
            BigDecimal rhs = new BigDecimal(data.getRhsText());

            // 趁着 rhsText 还没被改变，生成新的 lhsText 内容
            // 根据正负号标记加上 ”(- )“ ，变成 "(-4)" 这样的形式
            if (!data.getPn()) {
                lhsText.add('(');
                lhsText.add('-');
            }
            lhsText.addAll(rhsText);
            if (!data.getPn()) {
                lhsText.add(')');
            }
            lhsText.add('=');

            // temp 是运算结果的字符串
            String temp = "";
            // 根据 signOperation 对 lhs 和 rhs 进行运算
            switch (data.getSignOperation()) {
                case '+' -> temp = lhs.add(rhs).stripTrailingZeros().toPlainString();
                case '-' -> temp = lhs.subtract(rhs).stripTrailingZeros().toPlainString();
                case '×' -> temp = lhs.multiply(rhs).stripTrailingZeros().toPlainString();
                case '÷' -> {
                    // 如果除数为 0 ，那么就直接 CE
                    if (rhs.compareTo(new BigDecimal(0)) == 0) {
                        ClearEnterButtonFunction function = new ClearEnterButtonFunction();
                        return function.exec(data);
                    }
                    // 正常的除法，设置保留小数点后 12 位，而且去除末尾 0 （避免输出 8.0 这种情况）
                    temp = lhs.divide(rhs, 12, RoundingMode.HALF_UP).stripTrailingZeros().toEngineeringString();
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
                data.setPn(false);
                // 并且删除这个符号（因为负号会在 pn 为 false 的时候自动输出，不需要存在 rhsText 里面）
                rhsText.remove(0);
            } else {
                // 如果不带负号，就置为 true
                data.setPn(true);
            }
            // 将储存的运算符置为 null
            data.setSignOperation(null);
        }
        data.setLhsTextRaw(lhsText);
        data.setRhsTextRaw(rhsText);
        return data;
    }
}
