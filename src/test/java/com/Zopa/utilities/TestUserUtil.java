package com.Zopa.utilities;

import com.Zopa.utilities.model.dto.User;
import com.github.javafaker.Faker;

import java.util.Locale;

public class TestUserUtil {

    private static final Faker faker = new Faker();

    public static User createUser() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = (firstName + lastName).toLowerCase() +"@zopa.com";
        String title = faker.job().title();
        String firstline = faker.address().buildingNumber() + " " +faker.address().streetName();
        String town = faker.address().city();
        String postCode = faker.address().zipCode();

        if (title.length() >= 30) {
            title = title.substring(0,29);
        }

        return User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .jobTitle(title)
                .firstLine(firstline)
                .town(town)
                .postCode(postCode)
                .build();
    }

}
