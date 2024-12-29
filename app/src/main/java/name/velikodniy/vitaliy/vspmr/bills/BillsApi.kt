package name.velikodniy.vitaliy.vspmr.bills

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

interface BillsApi {
    @GET("list")
    suspend fun getBills(@Query("conv") conv: String): List<Bill>

    @GET("init")
    suspend fun getBillDetails(
        @Query("conv") conv: String,
        @Query("number") number: String
    ): BillDetails
}

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://vspmr.vitaliy.velikodniy.name/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideBillsApi(retrofit: Retrofit): BillsApi {
        return retrofit.create(BillsApi::class.java)
    }
}