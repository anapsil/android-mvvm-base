package net.anapsil.androidmvvmbase.repo;

import com.google.gson.Gson;

import net.anapsil.androidmvvmbase.domain.MarvelApi;
import net.anapsil.androidmvvmbase.domain.model.Character;
import net.anapsil.androidmvvmbase.domain.model.Response;
import net.anapsil.mvvmbase.test.RepositoryTest;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import io.reactivex.observers.TestObserver;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.RecordedRequest;

import static java.net.HttpURLConnection.HTTP_OK;
import static org.junit.Assert.assertEquals;

public class CharactersRepositoryTest extends RepositoryTest {
    private static final String TIMESTAMP = "1234";
    private static final String HASH = "5678";
    private static final int CHARACTER_ID = 1017100;
    private static final String GET_CHARACTERS_200_JSON = "json/GET_characters_200.json";
    private static final String GET_CHARACTERS_1017100_200_JSON = "json/GET_characters_1017100_200.json";
    private CharactersRepository repository;

    @Before
    public void setUp() {
        final MarvelApi marvelApi = getRetrofit(new OkHttpClient()).create(MarvelApi.class);
        repository = new CharactersRepository(marvelApi);
    }

    @Test
    public void shouldGetCharactersSuccessfully() throws InterruptedException {
        TestObserver<List<Character>> testObserver = new TestObserver<>();
        Response expectedResponse = new Gson().fromJson(getJson(GET_CHARACTERS_200_JSON), Response.class);

        mockResponse.setResponseCode(HTTP_OK)
                .setBody(getJson(GET_CHARACTERS_200_JSON));
        mockServer.enqueue(mockResponse);

        repository.getCharacters(TIMESTAMP, HASH)
                .subscribe(testObserver);

        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);
        testObserver.assertComplete();
        testObserver.assertValue(expectedResponse.getData().getResults());
        RecordedRequest recordedRequest = mockServer.takeRequest();
        assertEquals("GET", recordedRequest.getMethod());
        assertEquals("/v1/public/characters?apikey=816cd99cb7dd3773042e097cf873d979&ts=1234&hash=5678", recordedRequest.getPath());
    }

    @Test
    public void shouldGetCharactersByIdSuccessfully() throws InterruptedException {
        TestObserver<Character> testObserver = new TestObserver<>();
        Response expectedResponse = new Gson().fromJson(getJson(GET_CHARACTERS_1017100_200_JSON), Response.class);
        mockResponse.setResponseCode(HTTP_OK)
                .setBody(getJson(GET_CHARACTERS_1017100_200_JSON));
        mockServer.enqueue(mockResponse);

        repository.getCharactersById(TIMESTAMP, HASH, CHARACTER_ID)
                .subscribe(testObserver);

        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);
        testObserver.assertComplete();
        testObserver.assertValue(expectedResponse.getData().getResults().get(0));
        RecordedRequest recordedRequest = mockServer.takeRequest();
        assertEquals("GET", recordedRequest.getMethod());
        assertEquals("/v1/public/characters/1017100?apikey=816cd99cb7dd3773042e097cf873d979&ts=1234&hash=5678", recordedRequest.getPath());
    }
}