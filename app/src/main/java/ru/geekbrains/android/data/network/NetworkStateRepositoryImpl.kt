package ru.geekbrains.android.data.network

import android.content.Context
import io.reactivex.Observable
import ru.geekbrains.android.NetworkState
import ru.geekbrains.android.NetworkStateObservable

class NetworkStateRepositoryImpl(private val context: Context) : NetworkStateRepository {

    override fun watchForNetworkState(): Observable<NetworkState> =
        NetworkStateObservable(context)
            .cacheWithInitialCapacity(1)
            .skip(1)

}