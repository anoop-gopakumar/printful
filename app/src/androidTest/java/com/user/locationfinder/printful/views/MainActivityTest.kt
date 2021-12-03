package com.user.locationfinder.printful.views

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.uiautomator.UiObjectNotFoundException

import androidx.test.uiautomator.UiSelector
import androidx.test.uiautomator.UiDevice

@LargeTest
@RunWith(AndroidJUnit4ClassRunner ::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        val uiDevice = UiDevice.getInstance(getInstrumentation())
        val marker = uiDevice.findObject(UiSelector().descriptionContains("Jānis Bērziņš"))
        try {
            marker.click()
        } catch (e: UiObjectNotFoundException) {
            e.printStackTrace()
        }

    }
}
