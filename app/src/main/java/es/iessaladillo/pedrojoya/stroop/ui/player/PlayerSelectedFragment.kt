package es.iessaladillo.pedrojoya.stroop.ui.player

import android.os.Bundle
import androidx.fragment.app.Fragment

import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.base.OnToolbarAvailableListener
import kotlinx.android.synthetic.main.fragment_player_selected.*

class PlayerSelectedFragment : Fragment(R.layout.fragment_player_selected) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupToolbar()
    }

    private fun setupToolbar() {
        (requireActivity() as OnToolbarAvailableListener).onToolbarCreated(toolbarPlayerSelected)
    }
}
