package com.company.button.functions;

import com.company.data.CalculatorData;

// P/N 指改变正负号
public class ChangePNButtonFunction implements BaseButtonFunction {
    @Override
    public CalculatorData exec(CalculatorData data) {
        // 修改正负号标记
        data.setPn(!data.getPn());
        // 重置运算符标记
        data.setMark(true);
        return data;
    }
}
