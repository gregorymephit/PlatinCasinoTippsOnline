package com.resrplatin.ccasi.notipps.views.gettingDataView

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.resrplatin.ccasi.notipps.R
import com.resrplatin.ccasi.notipps.gsonObjects.ForGettingData
import com.resrplatin.ccasi.notipps.views.ViewFragment
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
            val fileUrl = requireActivity().getSharedPreferences("url_file", AppCompatActivity.MODE_PRIVATE).getString("url", "")
            if(fileUrl != "" && fileUrl != null) {
                Log.i("Connection", "Url from file.")
                view?.handler?.post {
                    findNavController().navigate(R.id.toGameView, bundleOf("url" to fileUrl))
                }
            }
            val url = URL("https://gist.githubusercontent.com/gregorymephit/a832a9d7caa8baa9eebc30eeb0083ad1/raw/for_platinCasino")
            val connection = url.openConnection() as HttpURLConnection
            val inputStream = BufferedInputStream(connection.inputStream)
            val reader = BufferedReader(InputStreamReader(inputStream))
            val resultOfReader = reader.readLines().joinToString("\n")
            connection.disconnect()
            val convertedResult = Gson().fromJson(resultOfReader, ForGettingData::class.java)
            if(convertedResult.canGo && convertedResult.necessaryUrl.contains(Regex("(http)|(/)"))) {
                Log.i("Connection", "Good url.")
                view?.handler?.post {
                    requireActivity().getSharedPreferences("url_file", AppCompatActivity.MODE_PRIVATE).edit().putString("url", convertedResult.necessaryUrl).apply()
                    findNavController().navigate(R.id.toGameView, bundleOf("url" to convertedResult.necessaryUrl))
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