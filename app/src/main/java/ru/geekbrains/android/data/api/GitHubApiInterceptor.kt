package ru.geekbrains.android.data.api

import okhttp3.*
import ru.geekbrains.android.BuildConfig.GITHUB_USER_NAME
import ru.geekbrains.android.BuildConfig.GITHUB_USER_PASSWORD

object GitHubApiInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response =
        chain.proceed(
            chain.request()
                .newBuilder()
                .header("Authorization", Credentials.basic(GITHUB_USER_NAME, GITHUB_USER_PASSWORD))
                .build()
        )

}