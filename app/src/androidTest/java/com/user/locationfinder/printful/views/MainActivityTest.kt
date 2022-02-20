package com.user.locationfinder.printful.views

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4ClassRunner ::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @JvmField
    var uiDevice: UiDevice = UiDevice.getInstance(getInstrumentation())

    @Test
    fun isMapLoaded(){
        val uiDevice = UiDevice.getInstance(getInstrumentation())
        uiDevice.wait(Until.findObject(By.desc("")),5000)
    }

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
