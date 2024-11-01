package qa.guru.tests;

import org.junit.jupiter.api.Test;
import qa.guru.pages.TextBoxPage;
import qa.guru.utils.RandomUtils;

class TextBoxTests extends TestBase {

    TextBoxPage textBoxPage = new TextBoxPage();
    public RandomUtils randomUtils = new RandomUtils();

    @Test
    void fillFormTest() {
        var firstName = randomUtils.getRandomFirstName();
        var email = randomUtils.getRandomEmail();
        var currentAddress = randomUtils.getRandomAddress();
        var permanentAddress = randomUtils.getRandomAddress();

        textBoxPage.openPage()
                .setName(firstName)
                .setEmail(email)
                .setCurrentAddress(currentAddress)
                .setPermanentAddress(permanentAddress)
                .submit();

        textBoxPage
                .checkResultValue(firstName)
                .checkResultValue(email)
                .checkResultValue(currentAddress)
                .checkResultValue(permanentAddress);
    }
}