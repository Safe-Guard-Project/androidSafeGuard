    package tn.esprit.safeguardapplication.repository

    import okhttp3.OkHttpClient
    import okhttp3.logging.HttpLoggingInterceptor
    import retrofit2.Retrofit
    import retrofit2.converter.gson.GsonConverterFactory
    import tn.esprit.safeguardapplication.api.UserApiService

    object RetrofitInstance {
        val api: UserApiService by lazy {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            Retrofit.Builder()
                .baseUrl("http://192.168.1.13:9090/user/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UserApiService::class.java)
        }

        }