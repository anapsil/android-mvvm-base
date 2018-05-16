package net.anapsil.mvvmbase.test

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.nio.charset.Charset

open class RepositoryTest {
    protected val mockServer = MockWebServer()
    protected val mockResponse = MockResponse()

    @Before
    @Throws(IOException::class)
    fun setup() {
        mockServer.start()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        mockServer.close()
    }

    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(mockServer.url("/").toString())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    protected fun getJson(path: String): String? {
        var json: String? = null
        try {
            val inputStream = javaClass.classLoader.getResourceAsStream(path)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charset.forName("UTF-8"))
        } catch (ex: IOException) {
            Log.e("getJson", ex.message)
        }

        return json
    }
}
