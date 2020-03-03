package es.iessaladillo.pedrojoya.stroop.ui

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.preference.PreferenceManager
import es.iessaladillo.pedrojoya.stroop.NO_PLAYER
import es.iessaladillo.pedrojoya.stroop.PREF_KEY_FIRST_TIME
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.base.OnToolbarAvailableListener
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.lang.reflect.Array.get
import java.lang.reflect.Array.set

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private val navController by lazy { findNavController() }

//    private val preferencesSettings: SharedPreferences by lazy {
//        PreferenceManager.getDefaultSharedPreferences(activity)
//    }





    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    private fun setupViews() {
//        if (preferencesSettings.getLong(PREF_KEY_FIRST_TIME,0) == 0L){
//            navController.navigate(R.id.assistantFragmentDestination)
//        }
        setupListener()
        setupToolbar()

    }


    private fun setupListener() {
        cardViewAbout.setOnClickListener { navController.navigate(R.id.aboutFragmentDestination) }
        cardViewAssistant.setOnClickListener { navController.navigate(R.id.assistantFragmentDestination) }
        cardViewRanking.setOnClickListener { navController.navigate(R.id.rankingFragmentDestination) }
        cardViewSettings.setOnClickListener { navController.navigate(R.id.settingsFragmentDestination) }
        cardViewPlayer.setOnClickListener { navController.navigate(R.id.playerFragmentSelectionDestination) }
        cardViewPlay.setOnClickListener { navController.navigate(R.id.playerFragmentSelectionDestination) }
    }

    private fun setupToolbar() {
        toolbarMain.inflateMenu(R.menu.menu)

        (requireActivity() as OnToolbarAvailableListener).onToolbarCreated(toolbarMain)
        toolbarMain.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.mnuInfo -> {
                    show(requireContext(), getString(R.string.dashboard_help_description))
                }
            }
            true
        }
    }

}
