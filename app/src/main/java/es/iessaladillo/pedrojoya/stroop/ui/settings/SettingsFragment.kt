package es.iessaladillo.pedrojoya.stroop.ui.settings

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.PreferenceFragmentCompat

import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.base.OnToolbarAvailableListener
import es.iessaladillo.pedrojoya.stroop.ui.show
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.settings_fragment.*

class SettingsFragment : Fragment(R.layout.settings_fragment) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupToolbar()
    }

    private fun setupToolbar() {
        (requireActivity() as OnToolbarAvailableListener).onToolbarCreated(toolbarSettings)

        toolbarSettings.inflateMenu(R.menu.menu)
        (requireActivity() as OnToolbarAvailableListener).onToolbarCreated(toolbarSettings)
        toolbarSettings.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.mnuInfo -> {
                    show(requireContext(), getString(R.string.settings_help_description))
                }
            }
            true
        }
    }

}
