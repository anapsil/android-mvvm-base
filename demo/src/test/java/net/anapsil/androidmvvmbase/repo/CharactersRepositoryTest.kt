package net.anapsil.androidmvvmbase.repo

import com.google.gson.Gson
import io.reactivex.observers.TestObserver
import net.anapsil.androidmvvmbase.domain.MarvelApi
import net.anapsil.androidmvvmbase.domain.model.Character
import net.anapsil.androidmvvmbase.domain.model.Response
import net.anapsil.mvvmbase.test.RepositoryTest
import okhttp3.OkHttpClient
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.HttpURLConnection.HTTP_OK

class CharactersRepositoryTest : RepositoryTest() {
    val marvelApi by lazy { getRetrofit(OkHttpClient()).create(MarvelApi::class.java) }
    val repository by lazy { CharactersRepository(marvelApi) }

    val TIMESTAMP = "1234"
    val HASH = "5678"
    val CHARACTER_ID = 1017100
    val GET_CHARACTERS_200_JSON = "json/GET_characters_200.json"
    val GET_CHARACTERS_1017100_200_JSON = "json/GET_characters_1017100_200.json"

    @Test
    @Throws(InterruptedException::class)
    fun shouldGetCharactersSuccessfully() {
        val testObserver = TestObserver<List<Character>>()
        val expectedResponse = Gson().fromJson(getJson(GET_CHARACTERS_200_JSON), Response::class.java)

        mockResponse.setResponseCode(HTTP_OK)
                .setBody(getJson(GET_CHARACTERS_200_JSON)!!)
        mockServer.enqueue(mockResponse)

        repository.getCharacters(TIMESTAMP, HASH)
                .subscribe(testObserver)

        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        testObserver.assertComplete()
        testObserver.assertValue(expectedResponse.data.results)
        val recordedRequest = mockServer.takeRequest()
        assertEquals("GET", recordedRequest.method)
        assertEquals("/v1/public/characters?apikey=816cd99cb7dd3773042e097cf873d979&ts=1234&hash=5678", recordedRequest.path)
    }

    @Test
    @Throws(InterruptedException::class)
    fun shouldGetCharactersByIdSuccessfully() {
        val testObserver = TestObserver<Character>()
        val expectedResponse = Gson().fromJson(getJson(GET_CHARACTERS_1017100_200_JSON), Response::class.java)
        mockResponse.setResponseCode(HTTP_OK)
                .setBody(getJson(GET_CHARACTERS_1017100_200_JSON)!!)
        mockServer.enqueue(mockResponse)

        repository.getCharactersById(TIMESTAMP, HASH, CHARACTER_ID)
                .subscribe(testObserver)

        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        testObserver.assertComplete()
        testObserver.assertValue(expectedResponse.data.results[0])
        val recordedRequest = mockServer.takeRequest()
        assertEquals("GET", recordedRequest.method)
        assertEquals("/v1/public/characters/1017100?apikey=816cd99cb7dd3773042e097cf873d979&ts=1234&hash=5678", recordedRequest.path)
    }
}