package com.danielebottillo.blog

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.danielebottillo.blog.config.EspressoTestsMatchers.withDrawable
import com.danielebottillo.blog.config.EspressoTestsMatchers.withRecyclerView
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ListActivityTest {

    @Rule
    @JvmField
    var mActivityRule = ActivityTestRule(ListActivity::class.java)

    @Test
    fun testSetupCorrect() {
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
        (0..4).forEach {
            onView(withRecyclerView(R.id.recycler_view).atPosition(it))
                    .check(matches(hasDescendant(withText("Row $it"))))
            val drawable = if (it % 2 == 0) R.drawable.ic_arrow_drop_down_circle_black_24dp else R.drawable.ic_autorenew_black_24dp
            onView(withRecyclerView(R.id.recycler_view).atPosition(it))
                    .check(matches(hasDescendant(withDrawable(drawable))))
        }
    }

}