package api

import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RClient {
    private const val  BASE_URL = "https://api.github.com/"

    fun getInterceptor() : OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return  okHttpClient
    }

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(getInterceptor())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val instanceApi = retrofit.create(Api::class.java)

}