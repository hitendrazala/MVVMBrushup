package com.example.mvvmbrushup.ui.coin_list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mvvmbrushup.common.Resource
import com.example.mvvmbrushup.data.model.CoinData
import com.example.mvvmbrushup.repository.CoinRepository
import com.example.mvvmbrushup.ui.MainDispatcherRule
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CoinListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineTestRule = MainDispatcherRule()

    @Mock
    private lateinit var repository: CoinRepository
    private lateinit var viewModel: CoinListViewModel


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        // it throws error like repository returned null
//      viewModel = CoinListViewModel(repository)
    }

    @Test
    fun `fetchCoinData emits loading and success`() = runBlocking {
        val coinList = listOf(
            CoinData(
                "1",
                true,
                false,
                "Bitcoin",
                1,
                "BTC",
                "coin"
            )
        )
        val flow = flowOf(
            Resource.Loading<List<CoinData>>(),
            Resource.Success<List<CoinData>>(coinList)
        )

        `when`(repository.getCoins()).thenReturn(flow)
        viewModel = CoinListViewModel(repository)

        viewModel.fetchCoinData()

       // advanceUntilIdle()

        val state = viewModel.coinsData.value
        assert(state is Resource.Success)
        assertEquals(coinList, state.data)
    }

    @Test
    fun `fetchCoinData should emit loading and error states`() = runBlocking {
        // Given
        val errorMessage = "Couldn't reach server. Check your internet connection"
        `when`(repository.getCoins()).thenReturn(flow {
            emit(Resource.Loading())
            emit(Resource.Error(errorMessage))
        })

        // When
        val viewModel = CoinListViewModel(repository)
        viewModel.fetchCoinData()

        // Then
        val result = viewModel.coinsData.value
        assert(result is Resource.Error && result.message == errorMessage)
    }
}