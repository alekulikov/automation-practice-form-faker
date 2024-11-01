package qa.guru.tests;

import org.junit.jupiter.api.Test;
import qa.guru.pages.PracticeFormPage;
import qa.guru.utils.RandomUtils;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

class PracticeFormTests extends TestBase {

    public PracticeFormPage practiceFormPage = new PracticeFormPage();
    public RandomUtils randomUtils = new RandomUtils();
    public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM,yyyy", Locale.ENGLISH);

    @Test
    void fullFieldsTest() {
        var firstName = randomUtils.getRandomFirstName();
        var lastName = randomUtils.getRandomLastName();
        var email = randomUtils.getRandomEmail();
        var currentAddress = randomUtils.getRandomAddress();
        var phoneNumber = randomUtils.getRandomPhoneNumber(10);
        var state = randomUtils.getRandomState();
        var city = randomUtils.getCity(state);
        var picture = randomUtils.getRandomPicture();
        var gender = randomUtils.getRandomGender();
        var birthDate = randomUtils.getRandomBirthDay(18, 100);
        var subject = randomUtils.getRandomSubject();
        var hobby = randomUtils.getRandomHobby();

        practiceFormPage.openPage()
                .removeBanners()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setUserNumber(phoneNumber)
                .setDateOfBirth(birthDate)
                .setSubject(subject)
                .setHobby(hobby)
                .setPicture(picture)
                .setCurrentAddress(currentAddress)
                .setState(state)
                .setCity(city)
                .submit();

        practiceFormPage
                .checkResultValue("Student Name", firstName + " " + lastName)
                .checkResultValue("Student Email", email)
                .checkResultValue("Gender", gender)
                .checkResultValue("Mobile", phoneNumber)
                .checkResultValue("Date of Birth", birthDate.format(formatter))
                .checkResultValue("Subjects", subject)
                .checkResultValue("Hobbies", hobby)
                .checkResultValue("Picture", picture)
                .checkResultValue("Address", currentAddress)
                .checkResultValue("State and City", state + " " + city);
    }

    @Test
    void onlyRequestedFields() {
        var firstName = randomUtils.getRandomFirstName();
        var lastName = randomUtils.getRandomLastName();
        var phoneNumber = randomUtils.getRandomPhoneNumber(10);
        var gender = randomUtils.getRandomGender();
        var birthDate = randomUtils.getRandomBirthDay(18, 100);

        practiceFormPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setUserNumber(phoneNumber)
                .setDateOfBirth(birthDate)
                .submit();

        practiceFormPage
                .checkResultValue("Student Name", firstName + " " + lastName)
                .checkResultValueIsEmpty("Student Email")
                .checkResultValue("Gender", gender)
                .checkResultValue("Mobile", phoneNumber)
                .checkResultValue("Date of Birth", birthDate.format(formatter))
                .checkResultValueIsEmpty("Subjects")
                .checkResultValueIsEmpty("Hobbies")
                .checkResultValueIsEmpty("Picture")
                .checkResultValueIsEmpty("Address")
                .checkResultValueIsEmpty("State and City");
    }

    @Test
    void incorrectPhoneNumberTest() {
        var firstName = randomUtils.getRandomFirstName();
        var lastName = randomUtils.getRandomLastName();
        var gender = randomUtils.getRandomGender();
        var birthDate = randomUtils.getRandomBirthDay(18, 100);

        practiceFormPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setUserNumber("5553535")
                .setDateOfBirth(birthDate)
                .submit();

        practiceFormPage.checkResultIsNotVisible();
    }
}