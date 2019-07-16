package com.movie.app

import android.os.SystemClock
import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.movie.app.mvvm.activity.MovieListActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.Matcher
import org.hamcrest.Description

@LargeTest
@RunWith(AndroidJUnit4::class)
class MovieListActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MovieListActivity::class.java)

    @Test
    fun movieDetailActivityTest() {
        val cardView = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.recyclerView),
                        childAtPosition(
                            withClassName(`is`("androidx.cardview.widget.CardView")),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        SystemClock.sleep(1500)
        cardView.perform(click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>,
        position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent) &&
                        view == parent.getChildAt(position)
            }
        }
    }
}