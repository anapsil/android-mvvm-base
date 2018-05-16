package net.anapsil.mvvmbase.test;

import android.util.Log;

import org.junit.After;
import org.junit.Before;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RepositoryTest {
    protected final MockWebServer mockServer = new MockWebServer();
    protected MockResponse mockResponse;

    @Before
    public void setup() throws IOException {
        mockServer.start();
        mockResponse = new MockResponse();
    }

    @After
    public void tearDown() throws IOException {
        mockServer.close();
    }

    protected Retrofit getRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(mockServer.url("/").toString())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    protected String getJson(String path) {
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
