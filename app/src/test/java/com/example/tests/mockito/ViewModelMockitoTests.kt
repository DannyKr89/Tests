package com.example.tests.mockito

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.tests.TestCoroutineRule
import com.example.tests.ui.viewmodels.SearchListViewModel
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import ru.dk.mydictionary.data.SearchListRepo
import ru.dk.mydictionary.data.state.AppState

const val TEST = "test"
const val EMPTY_LIST = "empty"

class ViewModelMockitoTests {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var testCoroutineRule = TestCoroutineRule()

    private lateinit var viewModel: SearchListViewModel

    @Mock
    private lateinit var repository: SearchListRepo

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = SearchListViewModel(repository)
    }

    @Test
    fun liveDataTest() {
        val observer = Observer<AppState> {}
        val liveData = viewModel.getLiveData()
        try {
            liveData.observeForever(observer)
            viewModel.requestData(TEST)
            assertNotNull(liveData.value)
        } finally {
            liveData.removeObserver(observer)
        }
    }

    @Test
    fun coroutinesReturnValueIsErrorTest() {
        testCoroutineRule.runBlockingTest {
            val observer = Observer<AppState> {}
            val liveData = viewModel.getLiveData()
            `when`(repository.getData(TEST)).thenReturn(
                flowOf(listOf())
            )
            try {
                liveData.observeForever(observer)
                viewModel.requestData(TEST)
                val value: AppState.Error = liveData.value as AppState.Error
                Assert.assertEquals(value.throwable.message, EMPTY_LIST)
            } finally {
                liveData.removeObserver(observer)
            }
        }
    }

}