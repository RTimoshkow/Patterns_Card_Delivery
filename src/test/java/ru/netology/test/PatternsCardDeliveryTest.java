package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.data.Info;


import static com.codeborne.selenide.Condition.ownText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static ru.netology.faker.DataGenerator.Registration.generateInfo;

public class PatternsCardDeliveryTest {

    @BeforeEach
    void preliminaryWork() {
        open("http://localhost:9999");
        $("[data-test-id=\"date\"] input").sendKeys(Keys.CONTROL, "a", Keys.DELETE);
    }

    @Test
    void shouldValidValues() {
        Info user = generateInfo(3);
        Info additionalDate = generateInfo(4);
        $x(".//span[@data-test-id='city']//input").val(user.getCity());
        $x(".//span[@data-test-id='date']//input").val(user.getDate());
        $x(".//span[@data-test-id='name']//input").val(user.getName());
        $x(".//span[@data-test-id='phone']//input").val(user.getPhoneNumber());
        $x(".//label[@data-test-id='agreement']").click();
        $x(".//button/child::span/child::span[text()='Запланировать']").click();
        $x(".//div[@data-test-id=\"success-notification\"]/div[@class=\"notification__title\"]").shouldBe(text("Успешно!"));
        $x(".//div[@data-test-id=\"success-notification\"]/div[@class=\"notification__content\"]").shouldBe(text("Встреча успешно запланирована на " + user.getDate()));
        $x(".//span[@data-test-id=\"date\"]//input").sendKeys(Keys.CONTROL, "a", Keys.DELETE);
        $x(".//span[@data-test-id='date']//input").val(additionalDate.getDate());
        $x(".//button/child::span/child::span[text()='Запланировать']").click();
        $x(".//div[@data-test-id=\"replan-notification\"]/div[@class=\"notification__title\"]").shouldBe(text("Необходимо подтверждение"));
        $x(".//div[@data-test-id=\"replan-notification\"]/div[@class=\"notification__content\"]").shouldBe(ownText("У вас уже запланирована встреча"));
        $x(".//div[@data-test-id=\"replan-notification\"]//button/child::span/span[text()='Перепланировать']").click();
        $x(".//div[@data-test-id=\"success-notification\"]/div[@class=\"notification__title\"]").shouldBe(text("Успешно!"));
        $x(".//div[@data-test-id=\"success-notification\"]/div[@class=\"notification__content\"]").shouldBe(text("Встреча успешно запланирована на " + additionalDate.getDate()));
    }

    @Test
    void shouldSameDate() {
        Info user = generateInfo(3);
        $x(".//span[@data-test-id='city']//input").val(user.getCity());
        $x(".//span[@data-test-id='date']//input").val(user.getDate());
        $x(".//span[@data-test-id='name']//input").val(user.getName());
        $x(".//span[@data-test-id='phone']//input").val(user.getPhoneNumber());
        $x(".//label[@data-test-id='agreement']").click();
        $x(".//button/child::span/child::span[text()='Запланировать']").click();
        $x(".//div[@data-test-id=\"success-notification\"]/div[@class=\"notification__title\"]").shouldBe(text("Успешно!"));
        $x(".//div[@data-test-id=\"success-notification\"]/div[@class=\"notification__content\"]").shouldBe(text("Встреча успешно запланирована на " + user.getDate()));
        $x(".//span[@data-test-id=\"date\"]//input").sendKeys(Keys.CONTROL, "a", Keys.DELETE);
        $x(".//span[@data-test-id='date']//input").val(user.getDate());
        $x(".//button/child::span/child::span[text()='Запланировать']").click();
        $x(".//div[@data-test-id=\"replan-notification\"]/div[@class=\"notification__title\"]").shouldBe(text("Необходимо подтверждение"));
        $x(".//div[@data-test-id=\"replan-notification\"]/div[@class=\"notification__content\"]").shouldBe(ownText("У вас уже запланирована встреча"));
        $x(".//div[@data-test-id=\"replan-notification\"]//button/child::span/span[text()='Перепланировать']").click();
        $x(".//div[@data-test-id=\"success-notification\"]/div[@class=\"notification__title\"]").shouldBe(text("Успешно!"));
        $x(".//div[@data-test-id=\"success-notification\"]/div[@class=\"notification__content\"]").shouldBe(text("Встреча успешно запланирована на " + user.getDate()));
    }
}
