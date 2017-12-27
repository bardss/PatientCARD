package com.patientcard.logic.services

import com.google.gson.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.LocalTime
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.IOException


object ServiceFactory {
    fun <T> createRetrofitService(clazz: Class<T>, endPoint: String): T =
            createRetrofitService(clazz, endPoint, false)

    fun <T> createRetrofitService(clazz: Class<T>, endPoint: String, isRefreshToken: Boolean): T =
            createRetrofitService(clazz, endPoint, isRefreshToken, true)

    fun <T> createRetrofitService(clazz: Class<T>, endPoint: String, isRefreshToken: Boolean, authorizationHeader: Boolean): T {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = if (authorizationHeader) createHttpClient(isRefreshToken, interceptor) else createNoAuthHttpClient(interceptor)

        val retrofit = Retrofit.Builder()
                .baseUrl(endPoint)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()

        return retrofit.create(clazz)
    }

    private fun createHttpClient(isRefreshToken: Boolean, interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addNetworkInterceptor(AddHeaderInterceptor(isRefreshToken))
                .addInterceptor(interceptor)
                .build()
    }

    private fun createNoAuthHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
    }

    private fun getGson(): Gson {
        return GsonBuilder()
                .setLenient()
                .registerTypeAdapter(LocalDateTime::class.java, JsonDeserializer { json, typeOfT, context -> LocalDateTime.parse(json.asString) })
                .registerTypeAdapter(LocalDateTime::class.java, JsonSerializer<LocalDateTime> { src, typeOfSrc, context -> JsonPrimitive(src.toString()) })
                .registerTypeAdapter(LocalDate::class.java, JsonDeserializer { json, typeOfT, context -> LocalDate.parse(json.asString) })
                .registerTypeAdapter(LocalDate::class.java, JsonSerializer<LocalDate> { src, typeOfSrc, context -> JsonPrimitive(src.toString()) })
                .registerTypeAdapter(LocalTime::class.java, JsonDeserializer { json, typeOfT, context -> LocalTime.parse(json.asString) })
                .registerTypeAdapter(LocalTime::class.java, JsonSerializer<LocalTime> { src, typeOfSrc, context -> JsonPrimitive(src.toString()) })
                .serializeNulls()
                .create()
    }
}

class AddHeaderInterceptor(internal var isRefreshToken: Boolean) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val builder = chain.request().newBuilder()

        return chain.proceed(builder.build())
    }

}