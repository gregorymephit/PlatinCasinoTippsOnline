package lets.play_in.platincasinotippsonline.views.newsAndAffairsView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.view.allViews
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import lets.play_in.platincasinotippsonline.R
import lets.play_in.platincasinotippsonline.recycler.ElementsRecyclerViewAdapter
import lets.play_in.platincasinotippsonline.views.ViewFragment

class NewsAndAffairsView: ViewFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflateDefault(R.layout.view_elements, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindTopBar()
        view.findViewById<TextView>(R.id.elements_title).text = getString(R.string.nachrichten_aktuelles)
        view.findViewById<ImageView>(R.id.elements_i).setImageResource(R.drawable.news_and_affairs_view_i)
        val elements = arrayListOf(
            R.drawable.lightning_roulette_preview_i,
            R.drawable.crazy_time_preview_i,
            R.drawable.football_studio_preview_i,
            R.drawable.monopoly_live_preview_i,
            R.drawable.crazy_coin_flip_preview_i,
            R.drawable.xxxtreme_lightning_preview_i)
        val adapter = ElementsRecyclerViewAdapter(elements) {
            val title = resources.getStringArray(R.array.necessary_titles)[1 + it]
            val text = resources.getStringArray(R.array.necessary_texts)[1 + it]
            findNavController().navigate(R.id.toInfo, bundleOf(
                "title_text" to title,
                "text" to text,
                "type" to "current"
            ))
        }
        view.allViews.filter {it is RecyclerView }.map {it as RecyclerView}.firstOrNull()?.apply {
            this.adapter = adapter
            this.layoutManager = GridLayoutManager(context, 2)
        }
    }
}