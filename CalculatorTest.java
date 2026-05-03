import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CalculatorTest {

    @Test
    public void testCalculate() {

        // Arrange
        String expression = "10+5*4+3";
        String expectedResult = "33.0";

        // Act
        String actualResult = Calculator.Run(expression);

        // Assert
        assertEquals(expectedResult, actualResult);
    }

}
