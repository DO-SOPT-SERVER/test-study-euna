package org.example.week1;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculatorTest {

    @Test
    @DisplayName("입력 값을 넣으면 더한 값을 출력해준다.")
    void add(){
        DigitalCalculator calculator = new DigitalCalculator();
        int action = calculator.add(1,1);
        Assertions.assertThat(action).isEqualTo(2);
    }

    @Test
    // @DisplayName("x나 y가 Null이면 에러가 발생한다.") -> 이런식으로 작성 X
    @DisplayName("입력 중에 NULL이 있으면 계산이 이루어지지 않는다")
    void addWithNullInput() {
        DigitalCalculator calculator = new DigitalCalculator();
        Assertions.assertThatThrownBy(
                () -> {calculator.add(null, 1);}
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("NULL");
    }

    @Test
    @DisplayName("입력 값을 더했을 때 10만이 넘으면 예외가 발생한다")
    void addResultExceed100000(){
        DigitalCalculator calculator = new DigitalCalculator();
        Assertions.assertThatThrownBy(
                () -> {calculator.add(50000, 50001);}
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("LIMIT");
    }
}