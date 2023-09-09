package lets.play_in.platincasinotippsonline.views.categoriesView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import lets.play_in.platincasinotippsonline.R
import lets.play_in.platincasinotippsonline.views.ViewFragment

class CategoriesView: ViewFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflateDefault(R.layout.view_categories, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindTopBar(needBindHome = false, needBindPrivacy = true)
        view.findViewById<ImageButton>(R.id.hudo_legacy).setOnClickListener {
            val title = resources.getStringArray(R.array.necessary_titles)[0]
            val text = resources.getStringArray(R.array.necessary_texts)[0]
            findNavController().navigate(R.id.toInfo, bundleOf(
                "title_text" to title,
                "text" to text
            ))
        }
        for(imageButton in listOf(view.findViewById<ImageButton>(R.id.news_and_affairs_ib), view.findViewById<ImageButton>(R.id.news_and_affairs_learn_more_ib))) {
            imageButton.setOnClickListener {
                findNavController().navigate(R.id.toNewsAndAffairsView)
            }
        }
        for(imageButton in listOf(view.findViewById<ImageButton>(R.id.top_slot_machines_ib), view.findViewById<ImageButton>(R.id.top_slot_machines_learn_more_ib))) {
            imageButton.setOnClickListener {
                findNavController().navigate(R.id.toTopSlotMachinesView)
            }
        }
    }
}