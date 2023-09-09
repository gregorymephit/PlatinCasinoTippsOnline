package lets.play_in.platincasinotippsonline.views

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import lets.play_in.platincasinotippsonline.R

abstract class ViewFragment: Fragment() {
    fun LayoutInflater.inflateDefault(id: Int, container: ViewGroup?): View? = inflate(id, container, false)
    fun bindTopBar(needBindHome: Boolean = true, needBindPrivacy: Boolean = true) {
        view?.let { view ->
            val navController = findNavController()
            var accessTimes = 0
            if(needBindHome) {
                view.findViewById<ImageButton>(R.id.home_b).setOnClickListener {
                    navController.popBackStack()
                }
                accessTimes += 1
            }
            if(needBindPrivacy) {
                view.findViewById<ImageButton>(R.id.privacy_policy_b).setOnClickListener {
                    navController.navigate(R.id.toPrivacyPolicy)
                }
                accessTimes += 1
            }
            Log.i("Access times", "$accessTimes")
        }
    }
}