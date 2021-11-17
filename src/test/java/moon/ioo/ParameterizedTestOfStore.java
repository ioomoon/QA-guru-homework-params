package moon.ioo;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static moon.ioo.TestData.TEST_CITE;

public class ParameterizedTestOfStore extends BaseTest {

    @DisplayName("Отображение элементов сайдбара")
    @Owner("ioomoon")
    @Tag("blocker")
    @ValueSource(strings = {"Орехи",
            "Сухофрукты",
            "Сушёные ягоды",
            "Цукаты",
            "Сладости",
            "Семечки и семена",
            "Специи и пряности",
            "Фруктовые чипсы",
            "Крупа и бобовые",
            "Снеки и закуски",
            "Чай",
            "Растительное масло",
            "Суперфуды",
            "Кофе",
            "Варенье, сиропы",
            "Кокосовое молоко"
    })
    @ParameterizedTest
    void testSideBar(String searchQuery) {
        open(TEST_CITE);
        $$("li.sidebar__list__element")
                .find(Condition.text(searchQuery))
                .shouldBe(Condition.visible);
    }

    static Stream<Arguments> testBentoMenu() {
        return Stream.of(
                Arguments.of("Орехи"),
                Arguments.of("Сухофрукты"),
                Arguments.of("Сушёные ягоды"),
                Arguments.of("Цукаты"),
                Arguments.of("Сладости"),
                Arguments.of("Семечки и семена"),
                Arguments.of("Специи и пряности"),
                Arguments.of("Фруктовые чипсы"),
                Arguments.of("Крупа и бобовые"),
                Arguments.of("Снеки и закуски"),
                Arguments.of("Чай"),
                Arguments.of("Растительное масло"),
                Arguments.of("Суперфуды"),
                Arguments.of("Кофе"),
                Arguments.of("Подарочные наборы из орехов и сухофруктов")
        );
    }

    @DisplayName("Отображение элементов плитки")
    @Owner("ioomoon")
    @Tag("blocker")
    @MethodSource
    @ParameterizedTest
    void testBentoMenu(String searchQuery) {
        open(TEST_CITE);
        $$("div.common__catalog__item")
                .find(Condition.text(searchQuery))
                .shouldBe(Condition.visible);
    }

    @DisplayName("Отображение товаров в категориях")
    @Owner("ioomoon")
    @Tag("blocker")
    @CsvSource(value = {
            "Орехи| Миндаль очищенный (жареный)",
            "Сухофрукты| Подарочный набор сухофруктов № 17 (1 кг.)",
            "Сушёные ягоды| Клюква сушеная (вяленая)",
            "Цукаты| Цукаты из имбиря в сахаре",
            "Сладости| Арахис в кокосовой глазури",
            "Семечки и семена| Семечки тыквенные очищенные (сырые)",
            "Специи и пряности| Мускатный орех целый",
            "Фруктовые чипсы| Дыня сушеная (чипсы)",
            "Крупа и бобовые| Рис для плова",
            "Снеки и закуски| Курут (курт)",
            "Чай| Чай Таёжный сбор",
            "Растительное масло| Масло конопляное 0,5 л.",
    },
            delimiter = '|')
    @ParameterizedTest
    void testCategoryContent(String searchQuery, String product) {
        open(TEST_CITE);
        $$("div.common__catalog__item")
                .find(Condition.text(searchQuery))
                .click();
        $(byText(product)).shouldBe(Condition.visible);

    }

}
