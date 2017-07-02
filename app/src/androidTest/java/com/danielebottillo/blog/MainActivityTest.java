package com.danielebottillo.blog;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.danielebottillo.blog.config.EspressoTestsMatchers.hasDrawable;
import static com.danielebottillo.blog.config.EspressoTestsMatchers.withDrawable;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void displayRightImageAtStart() {
        // Type text and then press the button.
        onView(withId(R.id.beta)).check(matches(withDrawable(R.drawable.left_beta)));
        onView(withId(R.id.beta)).check(matches(hasDrawable()));
    }

    @Test
    public void secondTest() {
        // Type text and then press the button.
        onView(withId(R.id.second_image)).check(matches(withDrawable(R.drawable.ic_autorenew_black_24dp)));
        onView(withId(R.id.second_image)).check(matches(hasDrawable()));
    }

}