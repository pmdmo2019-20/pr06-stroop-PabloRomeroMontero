package es.iessaladillo.pedrojoya.stroop.ui.main

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import es.iessaladillo.pedrojoya.stroop.*
import es.iessaladillo.pedrojoya.stroop.base.OnToolbarAvailableListener
import es.iessaladillo.pedrojoya.stroop.data.local.AppDatabase
import es.iessaladillo.pedrojoya.stroop.data.local.entity.Player
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private val navController by lazy { findNavController() }

    private val preferencesSettings: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(activity)
    }


    private val viewModel: MainActivityViewModel by viewModels {
        MainActivityViewModelFactory(
            AppDatabase.getInstance(requireContext()),
            requireActivity().application
        )
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    private fun setupViews() {
        if (preferencesSettings.getLong(PREF_KEY_FIRST_TIME, 0) == 0L) {
            navController.navigate(R.id.assistantFragmentDestination)
        }

        if (preferencesSettings.getLong(PREF_KEY_CURRENT_PLAYER_ID_KEY, NO_PLAYER) == NO_PLAYER) {
            imageViewAvatarDashboard.setImageResource(R.drawable.logo)
            textViewAvatarDashboard.text = getString(R.string.player_selection_no_player_selected)
        } else {
            var player: Player? = viewModel.queryPlayer(
                preferencesSettings.getLong(
                    PREF_KEY_CURRENT_PLAYER_ID_KEY, 0L
                )
            ).value
            imageViewAvatarDashboard.setImageResource(player?.avatar!!)
            textViewAvatarDashboard.text = player?.nombre
        }
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
                    show(
                        requireContext(),
                        getString(R.string.dashboard_help_description)
                    )
                }
            }
            true
        }
    }

}
