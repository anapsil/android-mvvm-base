package net.anapsil.androidmvvmbase.repo;

import android.util.Log;

import net.anapsil.androidmvvmbase.domain.MarvelApi;
import net.anapsil.androidmvvmbase.domain.model.Character;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import io.reactivex.observers.TestObserver;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.assertEquals;

public class CharactersRepositoryTest {
    private final MockWebServer mockServer = new MockWebServer();
    private MockResponse mockResponse;
    private CharactersRepository repository;

    @Before
    public void setUp() throws Exception {
        mockServer.start();
        final MarvelApi marvelApi = getRetrofit(new OkHttpClient()).create(MarvelApi.class);
        mockResponse = new MockResponse();
        repository = new CharactersRepository(marvelApi);
    }

    @Test
    public void shouldGetCharactersSuccessfully() throws InterruptedException {
        TestObserver<List<Character>> testObserver = new TestObserver<>();
        mockResponse.setResponseCode(200)
                .setBody(getJson("json/GET_characters_200.json"));
        mockServer.enqueue(mockResponse);

        repository.getCharacters("1234", "5678")
                .subscribe(testObserver);

        testObserver.assertNoErrors();
        testObserver.assertNoTimeout();
        testObserver.assertValueCount(1);
        testObserver.assertComplete();
        RecordedRequest recordedRequest = mockServer.takeRequest();
        assertEquals("GET", recordedRequest.getMethod());
        assertEquals("/v1/public/characters?apikey=816cd99cb7dd3773042e097cf873d979&ts=1234&hash=5678", recordedRequest.getPath());
    }

    @Test
    public void getCharactersById() throws InterruptedException {
        TestObserver<Character> testObserver = new TestObserver<>();
        mockResponse.setResponseCode(200)
                .setBody(getJson("json/GET_characters_1017100_200.json"));
        mockServer.enqueue(mockResponse);

        repository.getCharactersById("1234", "5678", 1017100)
                .subscribe(testObserver);

        testObserver.assertNoErrors();
        testObserver.assertNoTimeout();
        testObserver.assertValueCount(1);
        testObserver.assertComplete();
        RecordedRequest recordedRequest = mockServer.takeRequest();
        assertEquals("GET", recordedRequest.getMethod());
        assertEquals("/v1/public/characters/1017100?apikey=816cd99cb7dd3773042e097cf873d979&ts=1234&hash=5678", recordedRequest.getPath());
    }

    @After
    public void tearDown() throws IOException {
        mockServer.close();
    }
    private String getJson(String path) {
        String json = null;
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(path);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            Log.e("getJson", ex.getMessage());
        }

        return json;
    }

    private Retrofit getRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(mockServer.url("/").toString())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}