package com.example.tests


import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.tests.ui.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Test


const val TEST_WORD = "String"

class EspressoTests {


    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }


    @Test
    fun recyclerView_IsDisplayed() {
        onView(withId(R.id.search_list_rv)).check(matches(isDisplayed()))
    }

    @Test
    fun searchDialog_IsDisplayed() {
        onView(withId(R.id.search_fab)).perform(click())
        onView(withId(R.id.search_dialog)).check(matches(isDisplayed()))
    }

    @Test
    fun clearImageView_IsVisible() {
        onView(withId(R.id.search_fab)).perform(click())
        onView(withId(R.id.search_et)).perform(ViewActions.typeText(TEST_WORD))
        onView(withId(R.id.clear_iv)).check(
            matches(
                withEffectiveVisibility(
                    ViewMatchers.Visibility
                        .VISIBLE
                )
            )
        )
    }

    @Test
    fun searching_IsWorking() {
        onView(withId(R.id.search_fab)).perform(click())
        onView(withId(R.id.search_et)).perform(ViewActions.typeText(TEST_WORD))
        onView(withId(R.id.search_et)).perform(pressImeActionButton())
        onView(isRoot()).perform(delay())
        onView(withId(R.id.search_list_rv)).check(matches(isCompletelyDisplayed()))
    }

    private fun delay(): ViewAction {
        return object : ViewAction {
            override fun getConstraints() = isRoot()
            override fun getDescription(): String = "wait for $2 seconds"
            override fun perform(uiController: UiController, v: View?) {
                uiController.loopMainThreadForAtLeast(2000)
            }
        }
    }


    @After
    fun close() {
        scenario.close()
    }
}