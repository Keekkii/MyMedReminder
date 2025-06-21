package com.example.mymedreminder;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MedicationIntegrationTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void addMedication_ShowsOnHomeAndMedsList() {
        // Dodaj lijek
        onView(withId(R.id.addMedicationButton)).perform(click());
        onView(withId(R.id.medNameInput)).perform(typeText("TestMed"), closeSoftKeyboard());
        onView(withId(R.id.medDosageInput)).perform(typeText("100mg"), closeSoftKeyboard());
        onView(withId(R.id.medTimeInput)).perform(typeText("10:00"), closeSoftKeyboard());
        onView(withId(R.id.saveMedButton)).perform(click());

        // Provjeri home ekran
        onView(withId(R.id.nextMedName)).check(matches(withText("TestMed 100mg")));
        onView(withId(R.id.nextMedTime)).check(matches(withSubstring("10:00")));

        // Provjeri Meds ekran
        onView(withId(R.id.nav_meds)).perform(click());
        onView(withText("TestMed")).check(matches(isDisplayed()));
    }
}
