package com.example.tests.automator

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.By.*
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 18)
class InitialTest {

    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val packageName = context.packageName
    private val uiDevice: UiDevice = UiDevice.getInstance(getInstrumentation())

    @Before
    fun setup() {
        uiDevice.pressHome()
        val intent = context.packageManager.getLaunchIntentForPackage(packageName)
        intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)
        uiDevice.wait(hasObject(pkg(packageName).depth(0)), TIMEOUT)
    }

    companion object {
        private const val TIMEOUT = 5000L
    }


    @Test
    fun test_DeviceNotNull() {
        assertNotNull(uiDevice)
    }

    @Test
    fun test_AppPackageNotNull() {
        assertNotNull(packageName)
    }

    @Test
    fun test_MainActivityIsStarted() {
        val searchBtn = uiDevice.findObject(res(packageName, "search_fab"))
        assertNotNull(searchBtn)
    }

    @Test
    fun test_SearchIsPositive() {
        val searchFab = uiDevice.findObject(res(packageName, "search_fab"))
        searchFab.click()
        val editText = uiDevice.findObject(res(packageName, "search_et"))
        editText.text = "test"
        val searchBtn = uiDevice.findObject(res(packageName, "search_btn"))
        searchBtn.click()
        val recyclerView = uiDevice.wait(
            findObject(res(packageName, "search_list_rv")),
            TIMEOUT
        )
        assertTrue(recyclerView.isFocused)
    }
}