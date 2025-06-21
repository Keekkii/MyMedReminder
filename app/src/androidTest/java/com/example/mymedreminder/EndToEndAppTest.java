package com.example.mymedreminder;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class EndToEndAppTest {

    @Rule
    public ActivityScenarioRule<LoginActivity> activityRule =
            new ActivityScenarioRule<>(LoginActivity.class);

    @Test
    public void fullUserJourney() {
        // 1. Logiranje
        onView(withId(R.id.username)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("admin123"), closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());

        // 2. Dodaj 1. lijek
        onView(withId(R.id.addMedicationButton)).check(matches(isDisplayed()));
        onView(withId(R.id.addMedicationButton)).perform(click());
        onView(withId(R.id.medNameInput)).perform(typeText("Lijek1"), closeSoftKeyboard());
        onView(withId(R.id.medDosageInput)).perform(typeText("10mg"), closeSoftKeyboard());
        onView(withId(R.id.medTimeInput)).perform(typeText("08:00"), closeSoftKeyboard());
        onView(withId(R.id.saveMedButton)).perform(click());

        // 3. Dodaj 2. lijek
        onView(withId(R.id.addMedicationButton)).check(matches(isDisplayed()));
        onView(withId(R.id.addMedicationButton)).perform(click());
        onView(withId(R.id.medNameInput)).perform(typeText("Lijek2"), closeSoftKeyboard());
        onView(withId(R.id.medDosageInput)).perform(typeText("20mg"), closeSoftKeyboard());
        onView(withId(R.id.medTimeInput)).perform(typeText("12:00"), closeSoftKeyboard());
        onView(withId(R.id.saveMedButton)).perform(click());

        // 4. Obriši 1. lijek (Lijek1)
        onView(withId(R.id.nav_meds)).perform(click());
        onView(Matchers.allOf(withId(R.id.deleteButton), hasSibling(withText("Lijek1")))).perform(click());
        onView(withText("Delete")).perform(click()); // potvrdi dijalog

        // VRATI SE NA HOME PRIJE DALJNJIH AKCIJA
        onView(withId(R.id.nav_home)).perform(click());

        // 5. Dodaj 3. lijek
        onView(withId(R.id.addMedicationButton)).check(matches(isDisplayed()));
        onView(withId(R.id.addMedicationButton)).perform(click());
        onView(withId(R.id.medNameInput)).perform(typeText("Lijek3"), closeSoftKeyboard());
        onView(withId(R.id.medDosageInput)).perform(typeText("30mg"), closeSoftKeyboard());
        onView(withId(R.id.medTimeInput)).perform(typeText("16:00"), closeSoftKeyboard());
        onView(withId(R.id.saveMedButton)).perform(click());

        // 6. Dodaj 4. lijek
        onView(withId(R.id.addMedicationButton)).check(matches(isDisplayed()));
        onView(withId(R.id.addMedicationButton)).perform(click());
        onView(withId(R.id.medNameInput)).perform(typeText("Lijek4"), closeSoftKeyboard());
        onView(withId(R.id.medDosageInput)).perform(typeText("40mg"), closeSoftKeyboard());
        onView(withId(R.id.medTimeInput)).perform(typeText("20:00"), closeSoftKeyboard());
        onView(withId(R.id.saveMedButton)).perform(click());

        // 7. Prikaži sve lijekove (klik na Meds)
        onView(withId(R.id.nav_meds)).perform(click());
        onView(withText("Lijek2")).check(matches(isDisplayed()));
        onView(withText("Lijek3")).check(matches(isDisplayed()));
        onView(withText("Lijek4")).check(matches(isDisplayed()));

        // 8. Brisanje cijele baze (u Settings)
        onView(withId(R.id.nav_settings)).perform(click());
        onView(withId(R.id.btn_delete_all)).perform(click());
        onView(withText("Delete")).perform(click()); // potvrdi dijalog

        // 9. Provjeri da nema lijekova
        onView(withId(R.id.nav_meds)).perform(click());
        onView(withText("Lijek2")).check(doesNotExist());
        onView(withText("Lijek3")).check(doesNotExist());
        onView(withText("Lijek4")).check(doesNotExist());
    }
}
