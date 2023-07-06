package com.example.tests

import com.example.tests.presenters.SearchListPresenterImpl
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response
import ru.dk.mydictionary.data.SearchListRepo
import ru.dk.mydictionary.data.model.DictionaryModel
import ru.dk.mydictionary.data.state.AppState
import ru.dk.mydictionary.presenters.SearchListPresenter
import ru.dk.mydictionary.ui.list.SearchListView

class MockitoTest {
    private lateinit var presenter: SearchListPresenter

    @Mock
    private lateinit var repository: SearchListRepo

    @Mock
    private lateinit var searchListView: SearchListView


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        presenter = SearchListPresenterImpl(repository)
    }

    @Test
    fun presenterTest() {
        presenter.attach(searchListView)
        presenter.requestData("word")
        Mockito.verify(repository, Mockito.times(1)).getData("word")
    }

    @Test
    fun searchListViewTest() {
        val appState = AppState.Loading
        presenter.attach(searchListView)
        presenter.requestData("w")
        Mockito.verify(searchListView, Mockito.times(1)).renderData(appState)
    }

    @Test
    fun responseFalseTest() {
        val response = Mockito.mock(Response::class.java)
        Mockito.`when`(response.isSuccessful).thenReturn(false)
        assertFalse(response.isSuccessful)
    }

    @Test
    fun responseTrueTest() {
        val response = Mockito.mock(Response::class.java) as Response<List<DictionaryModel>>
        Mockito.`when`(response.isSuccessful).thenReturn(true)
        Mockito.`when`(response.body())
            .thenReturn(listOf(DictionaryModel(text = "", meanings = listOf())))
        assertTrue(response.isSuccessful)
        assertFalse(response.body()?.isEmpty() ?: true)
    }

    @Test
    fun verifyAttachTest() {
        presenter.attach(searchListView)
        assertEquals(searchListView, presenter.currentView)
    }

    @Test
    fun verifyDetachTest() {
        presenter.detach()
        assertNull(presenter.currentView)
    }


}
