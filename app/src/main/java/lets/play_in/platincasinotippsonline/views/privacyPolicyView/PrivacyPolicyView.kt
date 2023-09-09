package lets.play_in.platincasinotippsonline.views.privacyPolicyView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import lets.play_in.platincasinotippsonline.R
import lets.play_in.platincasinotippsonline.views.ViewFragment

class PrivacyPolicyView: ViewFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflateDefault(R.layout.view_privacy_policy, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindTopBar(needBindHome = true, needBindPrivacy = false)
        view.findViewById<ImageButton>(R.id.privacy_policy_b).apply {
            setImageResource(R.drawable.privacy_policy_inactive_b)
            isEnabled = !isEnabled
        }
    }
}