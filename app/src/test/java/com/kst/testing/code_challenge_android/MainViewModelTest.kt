package com.kst.testing.code_challenge_android

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.JsonSyntaxException
import com.kst.testing.code_challenge_android.mockData.MockPropertiesService
import com.kst.testing.code_challenge_android.network.PropertiesService
import com.kst.testing.code_challenge_android.network.UIState
import com.kst.testing.code_challenge_android.repository.PropertiesRepository
import com.kst.testing.code_challenge_android.repository.PropertiesRepositoryImpl
import com.kst.testing.code_challenge_android.ui.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsNull
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class MainViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    lateinit var service: PropertiesService

    lateinit var propertiesRepository: PropertiesRepository

    private lateinit var viewModel: MainViewModel

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun dropdown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `retrieve successful when Repository return correct data set`() {
        runBlocking {
            setupModel("properties.json")
            viewModel.getNewData()

            val uiState: UIState = viewModel.uiState.value
            MatcherAssert.assertThat(uiState, CoreMatchers.`is`(IsNull.notNullValue()))
            MatcherAssert.assertThat(
                uiState,
                CoreMatchers.instanceOf(UIState.Success::class.java)
            )
            uiState as UIState.Success
            MatcherAssert.assertThat(uiState.average, CoreMatchers.`is`(410280))
        }
    }

    @Test
    fun `retrieve NullPointerException when Repository return null data set`() {
        runBlocking {

            setupModel("null.json")
            viewModel.getNewData()
            val uiState: UIState = viewModel.uiState.value as UIState.Error

            MatcherAssert.assertThat(uiState, CoreMatchers.`is`(IsNull.notNullValue()))
            MatcherAssert.assertThat(
                uiState,
                CoreMatchers.instanceOf(UIState.Error::class.java)
            )
            uiState as UIState.Error
            MatcherAssert.assertThat(
                uiState.exception,
                CoreMatchers.instanceOf(NullPointerException::class.java)
            )
        }
    }

    @Test
    fun `retrieve JsonSyntaxException when Repository return array format`() {
        runBlocking {
            setupModel("propertiesArray.json")
            viewModel.getNewData()
            val uiState: UIState = viewModel.uiState.value
            MatcherAssert.assertThat(uiState, CoreMatchers.`is`(IsNull.notNullValue()))
            MatcherAssert.assertThat(uiState, CoreMatchers.instanceOf(UIState.Error::class.java))
            uiState as UIState.Error
            MatcherAssert.assertThat(
                uiState.exception,
                CoreMatchers.instanceOf(JsonSyntaxException::class.java)
            )
        }
    }

    private fun setupModel(fileName: String) {
        service = MockPropertiesService(fileName)
        propertiesRepository = PropertiesRepositoryImpl(service)
        viewModel = MainViewModel(propertiesRepository, testDispatcher)
    }


    @Test
    fun `retrieve successful value when Repository return incorrect number`() {
        runBlocking {
            setupModel("propertiesWrongData.json")
            viewModel.getNewData()

            val uiState: UIState = viewModel.uiState.value
            MatcherAssert.assertThat(uiState, CoreMatchers.`is`(IsNull.notNullValue()))
            MatcherAssert.assertThat(
                uiState,
                CoreMatchers.instanceOf(UIState.Success::class.java)
            )
            uiState as UIState.Success
            MatcherAssert.assertThat(uiState.average, CoreMatchers.`is`(5748233))
        }
    }
}