package edu.vassar.cmpu.test;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Test;

public class AddItemTest {

    @org.junit.Rule
    ActivityScenarioRule<ControllerActivity> activityRule = new ActivityScenarioRule<>(ControllerActivity.class);

    @Test
    public void testAddItems(){

        ViewInteraction name = Espresso.onView(ViewMatchers.withId(R.id.houseName))
                .perform(ViewActions.typeText("Th - 42"));

        ViewInteraction qty = Espresso.onView(ViewMatchers.withId(R.id.newMembersName))
                .perform(ViewActions.typeText("Tom"));

        ViewInteraction button = Espresso.onView(ViewMatchers.withId(R.id.join_house_button))
                .perform(ViewActions.click());

        //lineItems.check(ViewAssertions.matches(ViewMatchers.withSubstring("900 units of avocado")));

    }

}
