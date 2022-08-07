package com.demoqa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.demoqa.DataForm.FirstName;
import static com.demoqa.DataForm.LastName;
import static com.demoqa.DataForm.Email;
import static com.demoqa.DataForm.MobileNumber;
import static com.demoqa.DataForm.CurrentAddress;


import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PracticeFormTest {

    @BeforeAll
    static void configure() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @AfterAll
    static void tearDown(){
    Selenide.closeWindow();
    }

    @Test
    void fillForm() {
        open("/automation-practice-form");
        Selenide.zoom(0.80);
        $("#firstName").setValue(FirstName);
        $("#lastName").setValue(LastName);
        $("#userEmail").setValue(Email);
        $(byText("Female")).click();
        $("#userNumber").setValue(MobileNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionContainingText("March");
        $(".react-datepicker__year-select").selectOptionContainingText("1990");
        $(".react-datepicker__day--013").click();
        $("#subjectsContainer").click();
        $("#subjectsInput").setValue("Comp").pressEnter();
        $("[for='hobbies-checkbox-3']").click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/cat.jpeg"));
        $("#currentAddress").setValue(CurrentAddress);
        $("#state").click();
        $(byText("Uttar Pradesh")).click();
        $("#city").click();
        $(byText("Agra")).click();
        $("#submit").click();

        $(".modal-content").isDisplayed();
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".modal-body").shouldHave(
                text(FirstName + " " + LastName),
                text(Email),
                text(Email),
                text("Female"),
                text(MobileNumber),
                text("13 March,1990"),
                text("Computer Science"),
                text("Music"),
                text("cat.jpeg"),
                text(CurrentAddress),
                text("Uttar Pradesh Agra"));

        $("#closeLargeModal").scrollIntoView(true);
        $("#closeLargeModal").click();

    }
}
