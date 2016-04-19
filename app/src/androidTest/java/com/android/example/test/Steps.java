package com.android.example.test;

import android.app.Activity;
import android.test.InstrumentationTestCase;
import com.android.example.R;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.lang.reflect.Field;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class Steps extends InstrumentationTestCase {

  private Activity currentActivity;

  @Given("^I've launched \"([^\"]*)\"$") public void I_ve_launched_(String activityClassName)
      throws Throwable {
    String targetPackage = getInstrumentation().getTargetContext().getPackageName();
    Class<? extends Activity> activityClass =
        (Class<? extends Activity>) Class.forName(activityClassName);

    currentActivity = launchActivity(targetPackage, activityClass, null);
  }

  @When("^I click (.*)") public void I_click_(String id) throws Throwable {
    onView(withId(resolve(id))).perform(click());
  }

  @Then("^I should see \"([^\"]*)\"$") public void I_should_see_(String text) {
    onView(withText(text)).check(matches(isDisplayed()));
  }

  private int resolve(String id) throws NoSuchFieldException, IllegalAccessException {
    Class<?> clazz = R.id.class;
    Field field = clazz.getField(id);

    return field.getInt(field);
  }
}
