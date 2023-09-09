package lets.play_in.platincasinotippsonline.views.gettingDataView

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import lets.play_in.platincasinotippsonline.R
import lets.play_in.platincasinotippsonline.gsonObjects.ForGettingData
import lets.play_in.platincasinotippsonline.views.ViewFragment
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class GettingDataView: ViewFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflateDefault(R.layout.view_getting_data, container)
    }

    override fun onStart() {
        super.onStart()
        GlobalScope.launch(Dispatchers.IO) {
            val url = URL("https://gist.githubusercontent.com/gregorymephit/a832a9d7caa8baa9eebc30eeb0083ad1/raw/for_platinCasino")
            val connection = url.openConnection() as HttpURLConnection
            val inputStream = BufferedInputStream(connection.inputStream)
            val reader = BufferedReader(InputStreamReader(inputStream))
            val result = reader.readLines().joinToString("\n")
            connection.disconnect()
            val obj = Gson().fromJson(result, ForGettingData::class.java)
            if(obj.canGo && obj.necessaryUrl.contains(Regex("(http)|(/)"))) {
                Log.i("Connection", "Good url.")
                view?.handler?.post {
                    findNavController().navigate(R.id.toGameView, bundleOf("url" to obj.necessaryUrl))
                }
            }
            else {
                Log.w("Connection", "Bad url.")
                view?.handler?.post {
                    findNavController().navigate(R.id.toCategoriesView)
                }
            }
        }
    }
}