package com.resrplatin.ccasi.notipps.views.infoView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.resrplatin.ccasi.notipps.R
import com.resrplatin.ccasi.notipps.util.getBannerImageByTitle
import com.resrplatin.ccasi.notipps.views.ViewFragment

class InfoView: ViewFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflateDefault(R.layout.info_view, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindTopBar()
        val banner = view.findViewById<ImageView>(R.id.banner_iv)
        val title = view.findViewById<TextView>(R.id.info_title_tv)
        val type = view.findViewById<ImageView>(R.id.info_type_iv)
        val text = view.findViewById<TextView>(R.id.info_text_tv)
        arguments?.let { arguments ->
            val titleText = arguments.getString("title_text", "Error")
            title.text = titleText

            banner.setImageResource(getBannerImageByTitle(titleText))
            if(arguments.getString("type", "platincasinotippsonline.notipps.R") != "platincasinotippsonline.notipps.R") {
                type.setImageResource(R.drawable.current_category_i)
            }
            text.text = arguments.getString("text", "Nothing...")
        }
    }
}