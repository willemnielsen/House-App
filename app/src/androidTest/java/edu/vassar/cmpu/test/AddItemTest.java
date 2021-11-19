package edu.vassar.cmpu.test;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.CoordinatesProvider;
import androidx.test.espresso.action.GeneralClickAction;
import androidx.test.espresso.action.Press;
import androidx.test.espresso.action.Tap;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import androidx.test.espresso.contrib.PickerActions;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;

public class AddItemTest {

    @Rule
    public ActivityScenarioRule<ControllerActivity> activityRule = new ActivityScenarioRule<>(ControllerActivity.class);

    @Test
    public void testJoinHouse(){

        ViewInteraction name = Espresso.onView(ViewMatchers.withId(R.id.houseName))
                .perform(ViewActions.typeText("Th - 42"));

        ViewInteraction qty = Espresso.onView(ViewMatchers.withId(R.id.newMembersName))
                .perform(ViewActions.typeText("Tom"));

        ViewInteraction closekb = Espresso.onView(ViewMatchers.withId(R.id.newMembersName))
                .perform(ViewActions.closeSoftKeyboard());

        ViewInteraction button = Espresso.onView(ViewMatchers.withId(R.id.join_house_button))
                .perform(ViewActions.click());

        //lineItems.check(ViewAssertions.matches(ViewMatchers.withSubstring("900 units of avocado")));

    }

    @Test
    public void testCalendar(){

        testJoinHouse();

        //        Check all entry fields are set to default text

        ViewInteraction calendarButton = Espresso.onView(ViewMatchers.withId(R.id.open_calender_button))
                .perform(ViewActions.click());

        ViewInteraction goAddEventButton = Espresso.onView(ViewMatchers.withId(R.id.goAddEventScreen))
                .perform(ViewActions.click());

        //        Check all entry fields are set to default text

        ViewInteraction name = Espresso.onView(ViewMatchers.withId(R.id.type_event_name));
        name.check(matches(ViewMatchers.withText("")));

        ViewInteraction eventMonth = Espresso.onView(ViewMatchers.withId(R.id.type_month));
        eventMonth.check(matches(ViewMatchers.withText("")));

        ViewInteraction eventDay = Espresso.onView(ViewMatchers.withId(R.id.type_day));
        eventDay.check(matches(ViewMatchers.withText("")));

        ViewInteraction eventYear = Espresso.onView(ViewMatchers.withId(R.id.type_year));
        eventYear.check(matches(ViewMatchers.withText("")));

        ViewInteraction startHour = Espresso.onView(ViewMatchers.withId(R.id.hourText));
        startHour.check(matches(ViewMatchers.withText("01")));

        ViewInteraction startMin = Espresso.onView(ViewMatchers.withId(R.id.minText));
        startMin.check(matches(ViewMatchers.withText("00")));

        ViewInteraction startAP = Espresso.onView(ViewMatchers.withId(R.id.ampmText));
        startAP.check(matches(ViewMatchers.withText("AM")));

        ViewInteraction endHour = Espresso.onView(ViewMatchers.withId(R.id.hourText2));
        endHour.check(matches(ViewMatchers.withText("01")));

        ViewInteraction endMin = Espresso.onView(ViewMatchers.withId(R.id.minText2));
        endMin.check(matches(ViewMatchers.withText("00")));

        ViewInteraction endAP = Espresso.onView(ViewMatchers.withId(R.id.ampmText2));
        endAP.check(matches(ViewMatchers.withText("AM")));

        ViewInteraction recur = Espresso.onView(ViewMatchers.withId(R.id.recText));
        recur.check(matches(ViewMatchers.withText("Once")));

        //        Check that program did not proceed with empty fields/program reset

        ViewInteraction addEventButton = Espresso.onView(ViewMatchers.withId(R.id.addEventButton))
                .perform(ViewActions.click());

        ViewInteraction didNotContinue = Espresso.onView(ViewMatchers.withText(R.string.event_name_t))
                .check(matches(ViewMatchers.isDisplayed()));

        name.perform(ViewActions.typeText("Soccer"));

        ViewInteraction closekb = Espresso.onView(ViewMatchers.withId(R.id.type_event_name))
                .perform(ViewActions.closeSoftKeyboard());
        name.check(matches(ViewMatchers.withText("Soccer")));

        addEventButton.perform(ViewActions.click());

        didNotContinue.check(matches(ViewMatchers.isDisplayed()));
        name.check(matches(ViewMatchers.withText("")));
        eventMonth.check(matches(ViewMatchers.withText("")));
        eventDay.check(matches(ViewMatchers.withText("")));
        eventYear.check(matches(ViewMatchers.withText("")));

        ViewInteraction eventDate = Espresso.onView(ViewMatchers.withClassName(Matchers.equalTo(CalendarView.class.getName())))
                .perform(clickXY(750, 500));

        eventMonth.check(matches(ViewMatchers.withText("11")));
        eventDay.check(matches(ViewMatchers.withText("19")));
        eventYear.check(matches(ViewMatchers.withText("2021")));

        addEventButton.perform(ViewActions.click());

        didNotContinue.check(matches(ViewMatchers.isDisplayed()));
        name.check(matches(ViewMatchers.withText("")));
        eventMonth.check(matches(ViewMatchers.withText("")));
        eventDay.check(matches(ViewMatchers.withText("")));
        eventYear.check(matches(ViewMatchers.withText("")));


        //        Add weekly Soccer Event with all housemates

        name.perform(ViewActions.typeText("Soccer"));
        closekb.perform(ViewActions.closeSoftKeyboard());
        name.check(matches(ViewMatchers.withText("Soccer")));

        eventDate.perform(clickXY(750, 500));
        eventMonth.check(matches(ViewMatchers.withText("11")));
        eventDay.check(matches(ViewMatchers.withText("19")));
        eventYear.check(matches(ViewMatchers.withText("2021")));

        ViewInteraction clickStartHour = Espresso.onView(ViewMatchers.withId(R.id.sthour))
                .perform(ViewActions.click());
        ViewInteraction selectStartHour = Espresso.onData(allOf(is(instanceOf(String.class)), is("03")))
                .perform(ViewActions.click());
        startHour.check(matches(ViewMatchers.withText("03")));

        ViewInteraction clickStartMin = Espresso.onView(ViewMatchers.withId(R.id.stmin))
                .perform(ViewActions.click());
        ViewInteraction selectStartMin = Espresso.onData(allOf(is(instanceOf(String.class)), is("00")))
                .perform(ViewActions.click());
        startMin.check(matches(ViewMatchers.withText("00")));

        ViewInteraction clickStartAP = Espresso.onView(ViewMatchers.withId(R.id.ampm))
                .perform(ViewActions.click());
        ViewInteraction selectStartAP = Espresso.onData(allOf(is(instanceOf(String.class)), is("PM")))
                .perform(ViewActions.click());
        startAP.check(matches(ViewMatchers.withText("PM")));

        ViewInteraction clickEndHour = Espresso.onView(ViewMatchers.withId(R.id.ethour))
                .perform(ViewActions.click());
        ViewInteraction selectEndHour = Espresso.onData(allOf(is(instanceOf(String.class)), is("05")))
                .perform(ViewActions.click());
        endHour.check(matches(ViewMatchers.withText("05")));

        ViewInteraction clickEndMin = Espresso.onView(ViewMatchers.withId(R.id.etmin))
                .perform(ViewActions.click());
        ViewInteraction selectEndMin = Espresso.onData(allOf(is(instanceOf(String.class)), is("30")))
                .perform(ViewActions.click());
        endMin.check(matches(ViewMatchers.withText("30")));

        ViewInteraction clickEndAP = Espresso.onView(ViewMatchers.withId(R.id.ampm2))
                .perform(ViewActions.click());
        ViewInteraction selectEndAP = Espresso.onData(allOf(is(instanceOf(String.class)), is("PM")))
                .perform(ViewActions.click());
        endAP.check(matches(ViewMatchers.withText("PM")));

        ViewInteraction clickRecur = Espresso.onView(ViewMatchers.withId(R.id.recurrence))
                .perform(ViewActions.click());
        ViewInteraction selectRecur = Espresso.onData(allOf(is(instanceOf(String.class)), is("Weekly")))
                .perform(ViewActions.click());
        recur.check(matches(ViewMatchers.withText("Weekly")));

        addEventButton.perform(ViewActions.click());

        ViewInteraction selectHousemate1 = Espresso.onView(ViewMatchers.withText("memberName1"))
                .perform(ViewActions.click());

        ViewInteraction selectHousemate2 = Espresso.onView(ViewMatchers.withText("memberName2"))
                .perform(ViewActions.click());

        ViewInteraction selectHousemate3 = Espresso.onView(ViewMatchers.withText("Tom"))
                .perform(ViewActions.click());

        ViewInteraction addHMs = Espresso.onView(ViewMatchers.withText("Done"))
                .perform(ViewActions.click());

        //        Check that program reset

        name.check(matches(ViewMatchers.withText("")));
        eventMonth.check(matches(ViewMatchers.withText("")));
        eventDay.check(matches(ViewMatchers.withText("")));
        eventYear.check(matches(ViewMatchers.withText("")));
        startHour.check(matches(ViewMatchers.withText("01")));
        startMin.check(matches(ViewMatchers.withText("00")));
        startAP.check(matches(ViewMatchers.withText("AM")));
        endHour.check(matches(ViewMatchers.withText("01")));
        endMin.check(matches(ViewMatchers.withText("00")));
        endAP.check(matches(ViewMatchers.withText("AM")));
        recur.check(matches(ViewMatchers.withText("Once")));

        //        Add Daily Event with 2 Housemates

        name.perform(ViewActions.typeText("Dinner"));
        closekb.perform(ViewActions.closeSoftKeyboard());
        name.check(matches(ViewMatchers.withText("Dinner")));

        eventDate.perform(clickXY(750, 500));
        eventMonth.check(matches(ViewMatchers.withText("11")));
        eventDay.check(matches(ViewMatchers.withText("19")));
        eventYear.check(matches(ViewMatchers.withText("2021")));

        clickStartHour.perform(ViewActions.click());
        selectStartHour = Espresso.onData(allOf(is(instanceOf(String.class)), is("06")))
                .perform(ViewActions.click());
        startHour.check(matches(ViewMatchers.withText("06")));

        clickStartMin.perform(ViewActions.click());
        selectStartMin = Espresso.onData(allOf(is(instanceOf(String.class)), is("15")))
                .perform(ViewActions.click());
        startMin.check(matches(ViewMatchers.withText("15")));

        clickStartAP.perform(ViewActions.click());
        selectStartAP.perform(ViewActions.click());
        startAP.check(matches(ViewMatchers.withText("PM")));

        clickEndHour.perform(ViewActions.click());
        selectEndHour = Espresso.onData(allOf(is(instanceOf(String.class)), is("07")))
                .perform(ViewActions.click());
        endHour.check(matches(ViewMatchers.withText("07")));

        clickEndMin.perform(ViewActions.click());
        selectEndMin = Espresso.onData(allOf(is(instanceOf(String.class)), is("20")))
                .perform(ViewActions.click());
        endMin.check(matches(ViewMatchers.withText("20")));

        clickEndAP.perform(ViewActions.click());
        selectEndAP.perform(ViewActions.click());
        endAP.check(matches(ViewMatchers.withText("PM")));

        clickRecur.perform(ViewActions.click());
        selectRecur = Espresso.onData(allOf(is(instanceOf(String.class)), is("Daily")))
                .perform(ViewActions.click());
        recur.check(matches(ViewMatchers.withText("Daily")));

        addEventButton.perform(ViewActions.click());

        selectHousemate1.perform(ViewActions.click());

        selectHousemate3.perform(ViewActions.click());

        addHMs.perform(ViewActions.click());

        //        Check that program reset

        name.check(matches(ViewMatchers.withText("")));
        eventMonth.check(matches(ViewMatchers.withText("")));
        eventDay.check(matches(ViewMatchers.withText("")));
        eventYear.check(matches(ViewMatchers.withText("")));
        startHour.check(matches(ViewMatchers.withText("01")));
        startMin.check(matches(ViewMatchers.withText("00")));
        startAP.check(matches(ViewMatchers.withText("AM")));
        endHour.check(matches(ViewMatchers.withText("01")));
        endMin.check(matches(ViewMatchers.withText("00")));
        endAP.check(matches(ViewMatchers.withText("AM")));
        recur.check(matches(ViewMatchers.withText("Once")));

        //        Add Once-Occurring Event with 1 Housemates


        name.perform(ViewActions.typeText("Party"));
        closekb.perform(ViewActions.closeSoftKeyboard());
        name.check(matches(ViewMatchers.withText("Party")));

        eventDate.perform(clickXY(800, 150));
        eventDate.perform(clickXY(800, 300));
        eventMonth.check(matches(ViewMatchers.withText("12")));
        eventDay.check(matches(ViewMatchers.withText("4")));
        eventYear.check(matches(ViewMatchers.withText("2021")));

        clickStartHour.perform(ViewActions.click());
        selectStartHour = Espresso.onData(allOf(is(instanceOf(String.class)), is("10")))
                .perform(ViewActions.click());
        startHour.check(matches(ViewMatchers.withText("10")));

        clickStartMin.perform(ViewActions.click());
        selectStartMin = Espresso.onData(allOf(is(instanceOf(String.class)), is("10")))
                .perform(ViewActions.click());
        startMin.check(matches(ViewMatchers.withText("10")));

        clickStartAP.perform(ViewActions.click());
        selectStartAP.perform(ViewActions.click());
        startAP.check(matches(ViewMatchers.withText("PM")));

        clickEndHour.perform(ViewActions.click());
        selectEndHour = Espresso.onData(allOf(is(instanceOf(String.class)), is("02")))
                .perform(ViewActions.click());
        endHour.check(matches(ViewMatchers.withText("02")));

        clickEndMin.perform(ViewActions.click());
        selectEndMin = Espresso.onData(allOf(is(instanceOf(String.class)), is("00")))
                .perform(ViewActions.click());
        endMin.check(matches(ViewMatchers.withText("00")));

        endAP.check(matches(ViewMatchers.withText("AM")));

        clickRecur.perform(ViewActions.click());
        selectRecur = Espresso.onData(allOf(is(instanceOf(String.class)), is("Once")))
                .perform(ViewActions.click());
        recur.check(matches(ViewMatchers.withText("Once")));

        addEventButton.perform(ViewActions.click());

        selectHousemate3.perform(ViewActions.click());

        addHMs.perform(ViewActions.click());

        //        Check that program reset

        name.check(matches(ViewMatchers.withText("")));
        eventMonth.check(matches(ViewMatchers.withText("")));
        eventDay.check(matches(ViewMatchers.withText("")));
        eventYear.check(matches(ViewMatchers.withText("")));
        startHour.check(matches(ViewMatchers.withText("01")));
        startMin.check(matches(ViewMatchers.withText("00")));
        startAP.check(matches(ViewMatchers.withText("AM")));
        endHour.check(matches(ViewMatchers.withText("01")));
        endMin.check(matches(ViewMatchers.withText("00")));
        endAP.check(matches(ViewMatchers.withText("AM")));
        recur.check(matches(ViewMatchers.withText("Once")));

        //        Check that all events are displayed in start date order on their given day

        ViewInteraction backButton = Espresso.onView(ViewMatchers.withId(R.id.back))
                .perform(ViewActions.click());

        ViewInteraction currDate = Espresso.onView(ViewMatchers.withClassName(Matchers.equalTo(CalendarView.class.getName())))
                .perform(clickXY(750, 500));



        ViewInteraction eventList = Espresso.onView(ViewMatchers.withId(R.id.eventsList));
        eventList.check(matches(ViewMatchers.withText("Fri Nov 19 00:00:00 EST 2021\n" +
                "memberName1, memberName2, Tom has Soccer from 3:00PM to 5:30PM. \n\n" +
                "memberName1, Tom has Dinner from 6:15PM to 7:20PM. \n\n")));











    }

    public static ViewAction clickXY(final int x, final int y){
        return new GeneralClickAction(
                Tap.SINGLE,
                new CoordinatesProvider() {
                    @Override
                    public float[] calculateCoordinates(View view) {

                        final int[] screenPos = new int[2];
                        view.getLocationOnScreen(screenPos);

                        final float screenX = screenPos[0] + x;
                        final float screenY = screenPos[1] + y;
                        float[] coordinates = {screenX, screenY};

                        return coordinates;
                    }
                },
                Press.FINGER);
    }

}
