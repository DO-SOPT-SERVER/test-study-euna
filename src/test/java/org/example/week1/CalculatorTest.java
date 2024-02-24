package org.example.week1;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculatorTest {

    @Test
    @DisplayName("입력 값을 넣으면 더한 값을 출력해준다.")
    void add(){
        DigitalCalculator calculator = new DigitalCalculator();
        int result = calculator.add(1,1);
        Assertions.assertThat(result).isEqualTo(2);
    }

    @Test
    // @DisplayName("x나 y가 Null이면 에러가 발생한다.") -> 이런식으로 작성 X
    @DisplayName("입력 중에 NULL이 있으면 계산이 이루어지지 않는다")
    void addWithNullInput() {
        DigitalCalculator calculator = new DigitalCalculator();
        Assertions.assertThatThrownBy(
                () -> {calculator.add(null, 1);}
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력 값을 입력해야 합니다.");
    }

    @Test
    @DisplayName("입력 값을 더했을 때 10만이 넘으면 계산 결과를 표현하지 않는다")
    void addResultExceed100000(){
        DigitalCalculator calculator = new DigitalCalculator();
        Assertions.assertThatThrownBy(
                () -> {calculator.add(50000, 50001);}
        ).isInstanceOf(RuntimeException.class)
                .hasMessage("결과 값이 계산기가 표현할 수 있는 범위가 아닙니다.");
    }

    // 여기서부터 10개 추가

    @Test
    @DisplayName("입력 값을 더했을 때 0보다 작으면 계산 결과를 표현하지 않는다")
    void addResultLessThanZero(){
        DigitalCalculator calculator = new DigitalCalculator();
        Assertions.assertThatThrownBy(
                () -> {calculator.add(-1, -1);}
        ).isInstanceOf(RuntimeException.class)
                .hasMessage("결과 값이 계산기가 표현할 수 있는 범위가 아닙니다.");
    }

    @Test
    @DisplayName("입력 값을 나누면 나눈 값을 출력해준다")
    void divide(){
        DigitalCalculator calculator = new DigitalCalculator();
        int result = calculator.divide(10, 2);
        Assertions.assertThat(result).isEqualTo(5);
    }

    @Test
    @DisplayName("처음 입력 값을 0으로 나누면 예외가 발생한다")
    void divideByZero(){
        DigitalCalculator calculator = new DigitalCalculator();
        Assertions.assertThatThrownBy(
                () -> {calculator.divide(10, 0);}
        ).isInstanceOf(ArithmeticException.class)
                .hasMessage("0으로 나눌 수 없습니다");
    }

    @Test
    @DisplayName("입력 중에 NULL이 있으면 계산이 이루어지지 않는다")
    void divideWithNullInput(){
        DigitalCalculator calculator = new DigitalCalculator();
        Assertions.assertThatThrownBy(
                () -> {calculator.divide(null, 1);}
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력 값을 입력해야 합니다.");
    }

    @Test
    @DisplayName("입력 값을 나눈 결과가 10만이 넘으면 계산 결과를 표현하지 않는다")
    void divideResultExceed100000(){
        DigitalCalculator calculator = new DigitalCalculator();
        Assertions.assertThatThrownBy(
                () -> {calculator.divide(200002, 2);}
        ).isInstanceOf(RuntimeException.class)
                .hasMessage("결과 값이 계산기가 표현할 수 있는 범위가 아닙니다.");
    }

    @Test
    @DisplayName("입력 값의 나눈 결과가 0보다 작으면 계산 결과를 표현하지 않는다")
    void divideResultLessThanZero(){
        DigitalCalculator calculator = new DigitalCalculator();
        Assertions.assertThatThrownBy(
                () -> {calculator.divide(-10, 2);}
        ).isInstanceOf(RuntimeException.class)
                .hasMessage("결과 값이 계산기가 표현할 수 있는 범위가 아닙니다.");
    }

    @Test
    @DisplayName("밑변과 높이를 입력하면 삼각형의 넓이가 계산된다")
    void triangleArea(){
        EngineeringCalculator calculator = new EngineeringCalculator();
        int result = calculator.triangleArea(10, 2);
        Assertions.assertThat(result).isEqualTo(10);
    }

    @Test
    @DisplayName("삼각형의 넓이가 10만이 넘으면 계산 결과를 표현하지 않는다")
    void triangleAreaResultExceed100000(){
        EngineeringCalculator calculator = new EngineeringCalculator();
        Assertions.assertThatThrownBy(
                () -> {calculator.triangleArea(100001, 2);}
        ).isInstanceOf(RuntimeException.class)
                .hasMessage("결과 값이 계산기가 표현할 수 있는 범위가 아닙니다.");
    }

    @Test
    @DisplayName("삼각형의 넓이가 0보다 작으면 계산 결과를 표현하지 않는다")
    void triangleAreaResultLessThanZero(){
        EngineeringCalculator calculator = new EngineeringCalculator();
        Assertions.assertThatThrownBy(
                () -> {calculator.triangleArea(-10, 2);}
        ).isInstanceOf(RuntimeException.class)
                .hasMessage("결과 값이 계산기가 표현할 수 있는 범위가 아닙니다.");
    }

    @Test
    @DisplayName("삼각형의 넓이를 계산할 때 입력 값 중 NULL이 있으면 계산이 이루어지지 않는다")
    void triangleAreaWithNullInput(){
        EngineeringCalculator calculator = new EngineeringCalculator();
        Assertions.assertThatThrownBy(
                () -> {calculator.triangleArea(null, 2);}
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력 값을 입력해야 합니다.");
    }

}