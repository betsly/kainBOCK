import com.example.kainBOCK.bl.BMICalc;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BMITest {

    @Test
    public void calculateBMITest(){
        int height = 192;
        double weight = 80.0;
        double result = BMICalc.getBMI(height, weight);
        Assertions.assertEquals(4.999, result);
    }
}
