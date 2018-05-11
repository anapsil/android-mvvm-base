package net.anapsil.androidmvvmbase.repo;

import android.util.Log;

import com.google.gson.Gson;

import net.anapsil.androidmvvmbase.domain.MarvelApi;
import net.anapsil.androidmvvmbase.domain.model.Character;
import net.anapsil.androidmvvmbase.domain.model.Response;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CharactersRepositoryTest {
    @Mock
    MarvelApi marvelApi;
    private CharactersRepository repository;
    private TestObserver<List<Character>> testObserver;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        repository = new CharactersRepository(marvelApi);
        testObserver = new TestObserver<>();
    }

    @Test
    public void shouldGetCharactersSuccessfully() {
        Response result = new Gson().fromJson(getJson("json/GET_characters_200.json"), Response.class);

        when(marvelApi.getCharacters(anyString(), anyString(), anyString())).thenReturn(Single.just(result));

        repository.getCharacters("", "").subscribe(testObserver);
        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);

        testObserver.assertValue(result.getData().getResults());
    }

    @Test
    public void getCharactersById() {
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
}