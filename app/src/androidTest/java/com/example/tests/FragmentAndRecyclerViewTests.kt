package com.example.tests

import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.FragmentScenario
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tests.ui.list.SearchListFragment
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ru.dk.mydictionary.ui.adapters.SearchListAdapter

@RunWith(AndroidJUnit4::class)
class FragmentAndRecyclerViewTests {

    private lateinit var scenario: FragmentScenario<SearchListFragment>

    @Before
    fun setup() {
        scenario = FragmentScenario.Companion.launchInContainer(
            SearchListFragment::class.java,
            null,
            R.style.Theme_Tests,
            FragmentFactory()
        )
    }

    @Test
    fun fragment_Test() {
        scenario.moveToState(Lifecycle.State.RESUMED)
        onView(withId(R.id.search_fab)).check(matches(isDisplayed()))
    }

    @Test
    fun fragmentScrollTo_Test() {
        loadList()
        onView(withId(R.id.search_list_rv)).perform(
            RecyclerViewActions.scrollTo<SearchListAdapter.SearchListViewHolder>(
                hasDescendant(
                    withText("test 12")
                )
            )
        )
    }

    @Test
    fun recyclerViewClickAtPosition_Test() {
        loadList()
        onView(withId(R.id.search_list_rv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<SearchListAdapter.SearchListViewHolder>(
                15,
                click()
            )
        )
    }

    @Test
    fun recyclerViewClickOnItem_Test() {
        loadList()
        onView(withId(R.id.search_list_rv)).perform(
            RecyclerViewActions.scrollTo<SearchListAdapter.SearchListViewHolder>(
                hasDescendant(
                    withText("test 18")
                )
            )
        )
        onView(withId(R.id.search_list_rv)).perform(
            RecyclerViewActions.actionOnItem<SearchListAdapter.SearchListViewHolder>(
                hasDescendant(withText("test 16")), click()
            )
        )
    }

    private fun loadList() {
        onView(withId(R.id.search_fab)).perform(click())
        onView(withId(R.id.search_btn)).perform(click())
    }

    @After
    fun close() {
        scenario.close()
    }
}