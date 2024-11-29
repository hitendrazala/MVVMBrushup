package com.example.mvvmbrushup.repository

import com.example.mvvmbrushup.common.Resource
import com.example.mvvmbrushup.data.model.CoinData
import com.example.mvvmbrushup.network.CoinApiInterface
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.HttpException
import java.io.IOException
@OptIn(ExperimentalStdlibApi::class)
class CoinRepositoryTest{

    @Mock
    private lateinit var apiInterface: CoinApiInterface
    private lateinit var repository: CoinRepository

    @Before
    fun setUp(){
        MockitoAnnotations.openMocks(this)
        repository = CoinRepository(apiInterface)
    }

    @Test
    fun `getCoins returns Success When API Call Is Successful`() = runTest {
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

        Mockito.`when`(apiInterface.getCoins()).thenReturn(coinList)

        // Act
        val result = repository.getCoins().toList()

        assertTrue(result[0] is Resource.Loading)
        assertTrue(result[1] is Resource.Success)
        assertEquals((result[1] as Resource.Success).data, coinList)
    }

    @Test
    fun `getCoins returns Error when HttpException is thrown`() = runTest {
        // Arrange
        val exception = Mockito.mock(HttpException::class.java)
        Mockito.`when`(apiInterface.getCoins()).thenThrow(exception)

        // Act
        val result = repository.getCoins().toList()

        // Assert
        assertTrue(result[0] is Resource.Loading)
        assertTrue(result[1] is Resource.Error)
        assertEquals((result[1] as Resource.Error).message, "An unexpected error occurred")
    }

    @Test
    fun `getCoins returns Error when IOException is thrown`() = runBlocking {
        // Arrange
        Mockito.`when`(apiInterface.getCoins()).thenThrow(IOException())

        // Act
        val result = repository.getCoins().toList()

        // Assert
        assertTrue(result[0] is Resource.Loading)
        assertTrue(result[1] is Resource.Error)
        assertEquals((result[1] as Resource.Error).message, "Couldn't reach server. Check your internet connection")
    }

    @Test
    fun `getCoins returns Error when generic Exception is thrown`() = runBlocking {
        // Arrange
        Mockito.`when`(apiInterface.getCoins()).thenThrow(RuntimeException("Simulated Exception"))

        // Act
        val result = repository.getCoins().toList()

        // Assert
        assertTrue(result[0] is Resource.Loading)
        assertTrue(result[1] is Resource.Error)
        assertEquals((result[1] as Resource.Error).message, "Something went wrong")
    }
}