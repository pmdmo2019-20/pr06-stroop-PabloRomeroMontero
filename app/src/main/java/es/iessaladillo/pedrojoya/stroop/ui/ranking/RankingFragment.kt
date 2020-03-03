package es.iessaladillo.pedrojoya.stroop.ui.ranking

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.base.OnToolbarAvailableListener
import kotlinx.android.synthetic.main.fragment_dashboard.*


class RankingFragment : Fragment(R.layout.fragment_ranking) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupToolbar()
    }

    private fun setupToolbar() {
//        (requireActivity() as OnToolbarAvailableListener).onToolbarCreated(toolbarMain)
    }
}
