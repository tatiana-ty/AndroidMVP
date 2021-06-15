package ru.geekbrains.android.data.network

import io.reactivex.Observable
import ru.geekbrains.android.NetworkState

interface NetworkStateRepository {

    fun watchForNetworkState(): Observable<NetworkState>

}