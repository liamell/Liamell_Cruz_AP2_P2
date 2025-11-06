package edu.ucne.liamell_cruz_ap2_p2.presentation.navigation
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://gestionhuacalesapi.azurewebsites.net/"

    val api: GastosApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(GastosApiService::class.java)
    }


}
