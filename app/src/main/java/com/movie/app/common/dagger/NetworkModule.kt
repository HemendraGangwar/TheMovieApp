package com.movie.app.common.dagger

import androidx.annotation.NonNull
import com.movie.app.common.constants.AppConstant
import com.movie.app.data.webApi.CallAdapterFactory
import com.movie.app.data.webApi.MovieService
import com.movie.app.data.webApi.DataInterceptor
import com.movie.app.data.webApi.ApiRequestService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * For Network related dependency
 * Created by hemendrag on 15/07/2019.
 */
@Module
class NetworkModule {

  @Provides
  @Singleton
  fun provideRetrofit(@NonNull okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
      .client(okHttpClient)
      .baseUrl(AppConstant.obj.BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(CallAdapterFactory())
      .build()
  }


  @Provides
  @Singleton
  fun provideHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
      .addInterceptor(DataInterceptor())
      .build()
  }


  @Provides
  @Singleton
  fun provideDiscoverService(@NonNull retrofit: Retrofit): ApiRequestService {
    return retrofit.create(ApiRequestService::class.java)
  }

  @Provides
  @Singleton
  fun provideMovieService(@NonNull retrofit: Retrofit): MovieService {
    return retrofit.create(MovieService::class.java)
  }


}
