package org.example.week1;

public class EngineeringCalculator extends Calculator {
    public EngineeringCalculator() {
        super(CalculatorType.ENGINEERING);
    }

    public int triangleArea(Integer base, Integer height){
        validateInput(base, height);
        return validateOutput(divide(multiply(base, height), 2));
    }
}
