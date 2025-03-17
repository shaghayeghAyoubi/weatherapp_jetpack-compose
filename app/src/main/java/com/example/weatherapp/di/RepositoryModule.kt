package com.example.weatherapp.di


import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "https://api.weatherapi.com/v1/"
    private const val AUTH_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4OGU5ZDc1M2VhOTNkODMzZWIxZGRiY2MyZGVmMWZjMSIsIm5iZiI6MTYzNDk3MTkxNS42MTEsInN1YiI6IjYxNzNiMTBiZmQ3YWE0MDA0MzVjOTllYSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.jNYW4TIO2fjl_hfytjfuD3AVMyKrTmLCwCCeolJavEk"
    private const val API_KEY = "fb5c4da780974f518fa103102251703"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val originRequest = chain.request()
                val originUrl = originRequest.url

                val newUrl = originUrl.newBuilder()
                    .addQueryParameter("api_key", API_KEY)
                    .build()

                val newRequest = originRequest.newBuilder()
                    .url(newUrl)
                    .build()

                chain.proceed(newRequest)

            }
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieApiService(retrofit: Retrofit): MovieApiService {
        return retrofit.create(MovieApiService::class.java)
    }
}