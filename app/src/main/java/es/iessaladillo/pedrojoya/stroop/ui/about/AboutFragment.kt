package es.iessaladillo.pedrojoya.stroop.ui.about


import android.os.Bundle
import androidx.fragment.app.Fragment


import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.base.OnToolbarAvailableListener
import kotlinx.android.synthetic.main.fragment_about.*

class AboutFragment : Fragment(R.layout.fragment_about) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupToolbar()

    }

    private fun setupToolbar() {
        (requireActivity() as OnToolbarAvailableListener).onToolbarCreated(toolbarAbout)
        toolbarAbout.inflateMenu(R.menu.menu)
    }


}