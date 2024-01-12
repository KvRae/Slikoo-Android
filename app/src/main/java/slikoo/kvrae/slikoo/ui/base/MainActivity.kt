package slikoo.kvrae.slikoo.ui.base

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.core.view.WindowCompat
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.pages.TextWithImageScreen
import slikoo.kvrae.slikoo.ui.theme.SlikooTheme
import slikoo.kvrae.slikoo.utils.App


class MainActivity : ComponentActivity() {

    private val connectivityReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (isConnected()) {
                // There is an internet connection
                setContent {
                    SlikooTheme {
                        App()
                    }
                }
            } else {
                // No internet connection
                setContent {
                    TextWithImageScreen(
                        imageVector = ImageVector.vectorResource(id = R.drawable.signal_wifi_off),
                        text = stringResource(id = R.string.no_internet_connection)
                    )
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // Register the BroadcastReceiver to listen for network changes
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(connectivityReceiver, filter)

        // Check the initial network state
        if (isConnected()) {
            setContent {
                SlikooTheme {
                    App()
                }
            }
        } else {
            setContent {
                TextWithImageScreen(
                    imageVector = ImageVector.vectorResource(id = R.drawable.signal_wifi_off),
                    text = stringResource(id = R.string.no_internet_connection)
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        // Unregister the BroadcastReceiver to avoid memory leaks
        unregisterReceiver(connectivityReceiver)
    }

    private fun isConnected(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager

        if (connectivityManager != null) {
            val network = connectivityManager.activeNetwork
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network)

            return networkCapabilities != null && networkCapabilities.hasCapability(
                NetworkCapabilities.NET_CAPABILITY_INTERNET)
        }
        return false
    }
}
