package org.example.week1;

import java.util.Objects;

// 계산기 유형이 DIGITAL, ENGINEER
public abstract class Calculator {
    private CalculatorType type;

    public Calculator(CalculatorType type) {
        this.type = type;
    }

    public int add(Integer x, Integer y){
        validateInput(x, y);
        return validateOutput(x+y);
    }

    public int subtract(Integer x, Integer y){
        validateInput(x,+ y);
        return validateOutput(x-y);
    }

    public int multiply(Integer x, Integer y){
        validateInput(x, y);
        return validateOutput(x*y);
    }

    public int divide(Integer x, Integer y){
        if(y==0){
            throw new ArithmeticException("0으로 나눌 수 없습니다");
        }
        validateInput(x, y);
        return validateOutput(x/y);
    }

    protected void validateInput(Integer x, Integer y){
        if(Objects.isNull(x) || Objects.isNull(y)){
            throw new IllegalArgumentException("입력 값을 입력해야 합니다.");
        }
    }

    protected Integer validateOutput(Integer result){
        if(result>100000 || result<0){
            throw new RuntimeException("결과 값이 계산기가 표현할 수 있는 범위가 아닙니다.");
        }
        return result;
    }
}
