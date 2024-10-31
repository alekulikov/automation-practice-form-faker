package qa.guru.tests;

import org.junit.jupiter.api.Test;
import qa.guru.pages.PracticeFormPage;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static qa.guru.utils.RandomUtils.*;

class PracticeFormTests extends TestBase {

    public PracticeFormPage practiceFormPage = new PracticeFormPage();
    public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM,yyyy", Locale.ENGLISH);

    @Test
    void fullFieldsTest() {
        var firstName = getRandomFirstName();
        var lastName = getRandomLastName();
        var email = getRandomEmail();
        var currentAddress = getRandomAddress();
        var phoneNumber = getRandomPhoneNumber(10);
        var state = getRandomState();
        var city = getCity(state);
        var picture = getRandomPicture();
        var gender = getRandomGender();
        var birthDate = getRandomBirthDay(18, 100);
        var subject = getRandomSubject();
        var hobby = getRandomHobby();

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
    public void onlyRequestedFields() {
        var firstName = getRandomFirstName();
        var lastName = getRandomLastName();
        var phoneNumber = getRandomPhoneNumber(10);
        var gender = getRandomGender();
        var birthDate = getRandomBirthDay(18, 100);

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
        var firstName = getRandomFirstName();
        var lastName = getRandomLastName();
        var gender = getRandomGender();
        var birthDate = getRandomBirthDay(18, 100);

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