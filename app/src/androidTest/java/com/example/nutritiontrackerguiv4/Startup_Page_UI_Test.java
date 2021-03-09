package com.example.nutritiontrackerguiv4;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class Startup_Page_UI_Test {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void startup_Page_UI_Test() {
        ViewInteraction materialCheckBox = onView(
                allOf(withId(R.id.allergy_soybeans_start_page), withText("Soybeans"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                10),
                        isDisplayed()));
        materialCheckBox.perform(click());

        ViewInteraction materialCheckBox2 = onView(
                allOf(withId(R.id.allergy_soybeans_start_page), withText("Soybeans"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                10),
                        isDisplayed()));
        materialCheckBox2.perform(click());

        ViewInteraction materialCheckBox3 = onView(
                allOf(withId(R.id.allergy_milk_start_page), withText("Milk"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        materialCheckBox3.perform(click());

        ViewInteraction materialCheckBox4 = onView(
                allOf(withId(R.id.allergy_milk_start_page), withText("Milk"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        materialCheckBox4.perform(click());

        ViewInteraction materialCheckBox5 = onView(
                allOf(withId(R.id.allergy_milk_start_page), withText("Milk"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        materialCheckBox5.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.username_input_Start_Page),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("User"), closeSoftKeyboard());

        ViewInteraction editText = onView(
                allOf(withId(R.id.username_input_Start_Page), withText("User"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        editText.check(matches(withText("User")));

        ViewInteraction checkBox = onView(
                allOf(withId(R.id.allergy_milk_start_page), withText("Milk"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        checkBox.check(matches(isDisplayed()));

        ViewInteraction checkBox2 = onView(
                allOf(withId(R.id.allergy_eggs_start_page), withText("Eggs"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        checkBox2.check(matches(isDisplayed()));

        ViewInteraction checkBox3 = onView(
                allOf(withId(R.id.allergy_fish_start_page), withText("Fish"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        checkBox3.check(matches(isDisplayed()));

        ViewInteraction checkBox4 = onView(
                allOf(withId(R.id.allergy_shellfish_start_page), withText("Shellfish"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        checkBox4.check(matches(isDisplayed()));

        ViewInteraction checkBox5 = onView(
                allOf(withId(R.id.allergy_tree_nuts_start_page), withText("Tree Nuts"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        checkBox5.check(matches(isDisplayed()));

        ViewInteraction checkBox6 = onView(
                allOf(withId(R.id.allergy_peanuts_start_page), withText("Peanuts"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        checkBox6.check(matches(isDisplayed()));

        ViewInteraction checkBox7 = onView(
                allOf(withId(R.id.allergy_wheat_start_page), withText("Wheat"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        checkBox7.check(matches(isDisplayed()));

        ViewInteraction checkBox8 = onView(
                allOf(withId(R.id.allergy_soybeans_start_page), withText("Soybeans"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        checkBox8.check(matches(isDisplayed()));

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.other_allergens_edit_text_start_page),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        editText2.check(matches(withText("Grass")));

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.other_allergens_edit_text_start_page),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        editText3.check(matches(withText("Grass")));

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.username_input_Start_Page), withText("User"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText2.perform(click());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.username_input_Start_Page), withText("User"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText3.perform(pressImeActionButton());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.other_allergens_edit_text_start_page),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                12),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText("Grass"), closeSoftKeyboard());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.other_allergens_edit_text_start_page), withText("Grass"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                12),
                        isDisplayed()));
        appCompatEditText5.perform(pressImeActionButton());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.continue_button), withText("Continue"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        materialButton.perform(click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
