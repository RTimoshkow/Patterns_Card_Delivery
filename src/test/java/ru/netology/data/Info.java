package ru.netology.data;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class Info {
    private final String city;
    private final String date;
    private final String name;
    private final String phoneNumber;
}
