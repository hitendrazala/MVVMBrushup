package com.example.mvvmbrushup.ui.coin_detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.example.mvvmbrushup.common.Constant
import com.example.mvvmbrushup.common.Resource
import com.example.mvvmbrushup.data.model.CoinDetailsDto
import com.example.mvvmbrushup.repository.CoinDetailRepository
import com.example.mvvmbrushup.ui.MainDispatcherRule
import com.example.mvvmbrushup.util.CoinDetailsDtoBuilder
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CoinDetailViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineTestRule = MainDispatcherRule()

    @Mock
    private lateinit var coinDetailRepository: CoinDetailRepository
    private lateinit var viewModel: CoinDetailViewModel

    private val savedStateHandle = SavedStateHandle().apply {
        set(Constant.PARAM_COIN_ID, "bitcoin")
    }

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `state is initially loading`(): Unit = runBlocking {
        // Given
        val flow = flow {
            emit(Resource.Loading<CoinDetailsDto>())
        }
        whenever(coinDetailRepository.getCoinDetail("bitcoin")).thenReturn(flow)
        viewModel = CoinDetailViewModel(coinDetailRepository, savedStateHandle)

        val state = viewModel.state.value
        assertEquals(CoinDetailState(isLoading = true, coins = null, error = ""), state)

    }

    @Test
    fun `state is success when repository returns data`() : Unit = runBlocking {
        // Given
        val sampleCoinDetailsDto = CoinDetailsDtoBuilder().build()

        val flow = flow {
            emit(Resource.Success(sampleCoinDetailsDto))
        }
        whenever(coinDetailRepository.getCoinDetail("bitcoin")).thenReturn(flow)
        viewModel = CoinDetailViewModel(coinDetailRepository, savedStateHandle)

        val state = viewModel.state.value
        assertEquals(CoinDetailState(coins = sampleCoinDetailsDto), state)

    }

    @Test
    fun `state is error when repository returns error`() : Unit = runBlocking {
        // Given
        val sampleCoinDetailsDto = CoinDetailsDtoBuilder().build()
        val errorMessage = "Network Error"
        val flow = flow {
            emit(Resource.Error(errorMessage,sampleCoinDetailsDto))
        }
        whenever(coinDetailRepository.getCoinDetail("bitcoin")).thenReturn(flow)
        viewModel = CoinDetailViewModel(coinDetailRepository, savedStateHandle)

        val state = viewModel.state.value
        assertEquals(CoinDetailState(error = errorMessage), state)

    }
}