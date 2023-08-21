package slikoo.kvrae.slikoo.data.api


import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import slikoo.kvrae.slikoo.R

interface RestApiService {

}

class RetrofitInstance {
    companion object {

        fun getRetrofitInstance(context: Context): Retrofit {
            val baseUrl = context.getString(R.string.base_url)

            val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val client: OkHttpClient = OkHttpClient.Builder().apply {
                addInterceptor(interceptor)
            }.build()

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}