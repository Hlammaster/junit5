import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Configuration.browserSize;
import static com.codeborne.selenide.Selenide.$;

public class ParameterizedTests {
    @BeforeAll
    static void beforeAll() {
        browserSize = "1920 x 1080";
    }

    @BeforeEach
    void beforeEach() {
        Selenide.open("https://www.old-games.ru/");
    }


    @CsvSource(value = {
            "Max Payne, Gathering of Developers",
            "Arx Fatalis, JoWooD Productions Software"
    })
    // @CsvFileSource(resources = "/oldgamestestdata")

    @ParameterizedTest(name = "Поиск по запросу {0} отображет текст {0} на странице")
    public void SearchTest(String testData, String expectedText) {
        $("#quicksearchgame").setValue(testData).pressEnter();
        $(".main-content").shouldHave(Condition.text(expectedText));
    }

    @ValueSource(strings = {"Max Payne", "Arx Fatalis"})
    @ParameterizedTest(name = "Поиск по запросу {0} отображет текст {0} на странице")
    public void SearchTest2(String testData) {
        $("#quicksearchgame").setValue(testData).pressEnter();
        $(".main-content").shouldHave(Condition.text(testData));
    }

    static Stream<Arguments> SearchTest3(){
        return Stream.of(
                Arguments.of("Max Payne", "Arx Fatalis")

        );
    }
    @MethodSource
    @ParameterizedTest(name = "Поиск по запросу {0} отображет текст {0} на странице")
    public void SearchTest3(String testData) {
        $("#quicksearchgame").setValue(testData).pressEnter();
        $(".main-content").shouldHave(Condition.text(testData));
    }
}


