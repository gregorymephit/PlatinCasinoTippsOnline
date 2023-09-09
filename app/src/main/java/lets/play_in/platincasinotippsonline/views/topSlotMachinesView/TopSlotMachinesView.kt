package lets.play_in.platincasinotippsonline.views.topSlotMachinesView

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

class TopSlotMachinesView: ViewFragment() {

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
        view.findViewById<TextView>(R.id.elements_title).text = getString(R.string.top_spielautomaten)
        view.findViewById<ImageView>(R.id.elements_i).setImageResource(R.drawable.top_slots_machines_view_i)
        val elements = arrayListOf(
            R.drawable.book_of_dead_preview_i,
            R.drawable.book_of_demi_gods_preview_i,
            R.drawable.gates_of_olympus_preview_i,
            R.drawable.the_dog_house_preview_i,
            R.drawable.legacy_of_dead_preview_i,
            R.drawable.sweet_bonanza_preview_i)
        val adapter = ElementsRecyclerViewAdapter(elements) {
            val title = resources.getStringArray(R.array.necessary_titles)[7 + it]
            val text = resources.getStringArray(R.array.necessary_texts)[7 + it]
            findNavController().navigate(R.id.toInfo, bundleOf(
                "title_text" to title,
                "text" to text
            ))
        }
        view.allViews.filter {it is RecyclerView }.map {it as RecyclerView}.firstOrNull()?.apply {
            this.adapter = adapter
            this.layoutManager = GridLayoutManager(context, 2)
        }
    }
}