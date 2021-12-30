package com.kst.testing.code_challenge_android.ui


import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.runner.AndroidJUnit4
import com.kst.testing.code_challenge_android.MockServer
import com.kst.testing.code_challenge_android.R
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    lateinit var mockWebServer: MockWebServer

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.setDispatcher(MockServer.ResponseDispatcher())
        mockWebServer.start(8080)
    }

    @After
    fun dropdown() {
        mockWebServer.shutdown()
    }

    @Test
    fun mainActivityTest() {
        launchActivity()

        val textView = onView(
            allOf(
                withId(R.id.textview_title), withText("What is the average property price?"),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        textView.check(matches(isDisplayed()))
        textView.check(matches(withText("What is the average property price?")))


        val averageView = onView(
            allOf(
                withId(R.id.textview_average), withText("421392"),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        averageView.check(matches(isDisplayed()))
        averageView.check(matches(withText("421392")))
    }

    private fun launchActivity(): ActivityScenario<MainActivity>? =
        ActivityScenario.launch(MainActivity::class.java)
}
