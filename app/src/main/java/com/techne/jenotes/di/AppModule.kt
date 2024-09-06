package com.techne.jenotes.di

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.techne.jenotes.data.model.ErrorMessage
import com.techne.jenotes.data.remote.AppService
import com.techne.jenotes.data.util.RemoteDataAccess
import com.techne.jenotes.data.util.RequestHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private fun provideOkhttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }


    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi): Retrofit = Retrofit.Builder()
        .baseUrl("https://jande-backend-production.up.railway.app/")
        .client(provideOkhttpClient())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    @Singleton
    fun provideErrorMessageAdapter(moshi: Moshi): JsonAdapter<ErrorMessage> {
        return moshi.adapter(ErrorMessage::class.java)
    }



    @Provides
    fun provideRemoteDataAccess(errorMessageAdapter: JsonAdapter<ErrorMessage>): RemoteDataAccess {
        return RemoteDataAccess(errorMessageAdapter)
    }

    @Provides
    fun provideRequestHelper(moshi: Moshi): RequestHelper {
        return RequestHelper(moshi)
    }

    @Provides
    @Singleton
    fun provideAppService(retrofit: Retrofit): AppService =
        retrofit.create(AppService::class.java)


//    @Singleton
//    @Provides
//    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
//        Room.databaseBuilder(context, AppDatabase::class.java, "notes_db")
//            .fallbackToDestructiveMigration()
//            .build()

//    @Singleton
//    @Provides
//    fun provideWeatherDao(database: AppDatabase) = database.weatherDao()


}