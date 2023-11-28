package slikoo.kvrae.slikoo.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.lifecycle.LiveData


class NetworkConnection (private val context : Context) : LiveData<Boolean>() {

    private val connectivityManager : ConnectivityManager = context
        .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private lateinit var networkConnectionCallback : ConnectivityManager.NetworkCallback
    override fun onActive() {
        super.onActive()
        updateConnection()
        when {
            android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N -> {
                connectivityManager.registerDefaultNetworkCallback(connectionCallback())
            }
            else -> {
                context.registerReceiver(
                    networkReciever,
                    android.content.IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
            }
        }
    }

    override fun onInactive() {
        super.onInactive()
        connectivityManager.unregisterNetworkCallback(connectionCallback())
    }

    private fun connectionCallback(): ConnectivityManager.NetworkCallback{
        networkConnectionCallback = object : ConnectivityManager.NetworkCallback(){
            override fun onAvailable(network: android.net.Network) {
                super.onAvailable(network)
                postValue(true)
            }

            override fun onLost(network: android.net.Network) {
                super.onLost(network)
                postValue(false)
            }
        }
        return networkConnectionCallback
    }

    private fun updateConnection(){
        val networkConnection = connectivityManager.activeNetworkInfo
        postValue(networkConnection?.isConnected == true)
    }

    private val networkReciever = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            updateConnection()
        }
    }




}