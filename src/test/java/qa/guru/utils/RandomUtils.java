package qa.guru.utils;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Map;

public class RandomUtils {

    private final Faker faker;
    private final String[] genders = {"Male", "Female", "Other"};
    private static final String[] states = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};
    private static final Map<String, String[]> citiesByState = Map.of(
            "NCR", new String[]{"Delhi", "Gurgaon", "Noida"},
            "Uttar Pradesh", new String[]{"Agra", "Lucknow", "Merrut"},
            "Haryana", new String[]{"Karnal", "Panipat"},
            "Rajasthan", new String[]{"Jaipur", "Jaiselmer"}
    );
    private static final String[] subjects = {"English", "Chemistry", "Computer Science",
            "Commerce", "Economics", "Social Studies", "Arts", "History",
            "Maths", "Accounting", "Physics", "Biology", "Hindi", "Civics"};
    private static final String[] hobbies = {"Sports", "Reading", "Music"};
    private static final String[] pictures = {"img.png"};

    public RandomUtils() {
        this(Locale.ENGLISH);
    }

    public RandomUtils(Locale locale) {
        faker = new Faker(locale);
    }

    public String getRandomFirstName() {
        return faker.name().firstName();
    }

    public String getRandomLastName() {
        return faker.name().lastName();
    }

    public String getRandomGender() {
        return faker.options().option(genders);
    }

    public LocalDate getRandomBirthDay(int minAge, int maxAge) {
        var birthDay = faker.date().birthday(minAge, maxAge);

        return LocalDate.ofInstant(birthDay.toInstant(), ZoneId.systemDefault());
    }

    public String getRandomPhoneNumber(int length) {
        return faker.phoneNumber().subscriberNumber(length);
    }

    public String getRandomEmail() {
        return faker.internet().emailAddress();
    }

    public String getRandomAddress() {
        return String.format("%s, %s, %s",
                faker.address().streetAddressNumber(),
                faker.address().city(),
                faker.address().country());
    }

    public String getRandomState() {
        return faker.options().option(states);
    }

    public String getCity(String state) {
        var cities = citiesByState.get(state);

        return faker.options().option(cities);
    }

    public String getRandomSubject() {
        return faker.options().option(subjects);
    }

    public String getRandomHobby() {
        return faker.options().option(hobbies);
    }

    public String getRandomPicture() {
        return faker.options().option(pictures);
    }

}
