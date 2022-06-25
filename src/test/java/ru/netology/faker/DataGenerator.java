package ru.netology.faker;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;
import ru.netology.data.Info;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@UtilityClass
public class DataGenerator {

    @UtilityClass
    public static class Registration {
        public static Info generateInfo(int days) {

            Faker faker = new Faker(new Locale("ru"));
            return new Info(
                    faker.address().cityName(),
                    LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                    faker.name().firstName() + " " + faker.name().lastName(),
                    faker.phoneNumber().phoneNumber()
            );
        }
    }
}