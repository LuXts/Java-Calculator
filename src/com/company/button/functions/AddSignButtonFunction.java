package com.company.button.functions;

import com.company.data.CalculatorData;

import java.util.ArrayList;

public class AddSignButtonFunction implements BaseButtonFunction {
    private final Character c;

    public AddSignButtonFunction(Character c) {
        this.c = c;
    }

    @Override
    public CalculatorData exec(CalculatorData data) {
        ArrayList<Character> lhsText = data.getLhsTextRaw();
        ArrayList<Character> rhsText = data.getRhsTextRaw();
        // 根据 mark 的值决定是修改 lhsText 末尾的运算符还是生成新的 lhsText 出来
        if (data.getMark()) {
            // 这里是计算新的 lhsText
            // 首先调用一次运算，实现按下 5 * 5 * 5 的时候最终结果为 25 * 5 的效果
            BaseButtonFunction function = new CalculationResultsButtonFunction();
            data = function.exec(data);
            // lhsText 清空

            lhsText.clear();
            // 根据正负号标记加上负号
            if (!data.getPn()) {
                lhsText.add('-');
            }
            // 把 rhsText 整个拷贝到 lhsText
            lhsText.addAll(rhsText);
            // 末尾添加运算符
            lhsText.add(c);

            // 记录运算符
            data.setSignOperation(c);

            // 这里要做的事情和 clear 差不多，都是清理 rhsText
            // 除了 mark 要修改，所以直接调用 clear
            function = new ClearButtonFunction();
            data = function.exec(data);
            data.setMark(false);
        } else {
            // 这里是修改 lhs 末尾的运算符
            lhsText.remove(lhsText.size() - 1);
            lhsText.add(c);
            // 记录新的运算符
            data.setSignOperation(c);
        }
        data.setRhsTextRaw(rhsText);
        data.setLhsTextRaw(lhsText);
        return data;
    }
}
