import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;

public class ParameterizedTests {

    public void SearchTest{
       @Test
       Selenide.open("https://adultmult.club/cartoon-collection.html");

    }
}
