package com.example.tests

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import ru.dk.mydictionary.ui.MainActivity
import ru.dk.mydictionary.ui.list.SearchListFragment

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.TIRAMISU])
class RobolectricTest {

    lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @After
    fun close() {
        scenario.close()
    }

    @Test
    fun activity_AssertNotNull() {
        scenario.onActivity {
            assertNotNull(it)
        }
    }

    @Test
    fun fragment_AssertNotNull() {
        val fragment: Fragment = SearchListFragment.newInstance()
        scenario.onActivity {
            it.supportFragmentManager
                .beginTransaction()
                .add(R.id.main_container, fragment)
                .commitNow()
            assertNotNull(fragment)
        }
    }

    @Test
    fun test() {
        val fragment = launchFragmentInContainer<SearchListFragment>(
            Bundle(),
            R.style.Theme_Tests
        )
        fragment.onFragment {
            assertNotNull(it)
        }
    }
}