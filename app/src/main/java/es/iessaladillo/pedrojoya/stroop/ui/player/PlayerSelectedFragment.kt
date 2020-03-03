package es.iessaladillo.pedrojoya.stroop.ui.player

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.stroop.*

import es.iessaladillo.pedrojoya.stroop.base.OnToolbarAvailableListener
import es.iessaladillo.pedrojoya.stroop.data.local.AppDatabase
import es.iessaladillo.pedrojoya.stroop.data.local.entity.Player
import es.iessaladillo.pedrojoya.stroop.ui.main.MainActivityViewModel
import es.iessaladillo.pedrojoya.stroop.ui.main.MainActivityViewModelFactory
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_player_selected.*

class PlayerSelectedFragment : Fragment(R.layout.fragment_player_selected) {

    lateinit var currentPlayer: Player

    private val navController by lazy { findNavController() }

    private val listAdapter: PlayerSelectedFragmentAdapter = PlayerSelectedFragmentAdapter().apply {
        setOnItemClickListener { showPlayer(it) }
    }


    private val preferencesSettings: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(activity)
    }


    private val viewModel: MainActivityViewModel by viewModels {
        MainActivityViewModelFactory(
            AppDatabase.getInstance(requireContext()),
            requireActivity().application
        )
    }

    @SuppressLint("CommitPrefEdits")
    private fun showPlayer(position: Int) {
        currentPlayer = viewModel.queryPlayer(listAdapter.getItemId(position)).value!!
        imageViewAvatarPlayerSelected.setImageResource(currentPlayer.avatar)
        editTextAvatarPlayerSelected.text = currentPlayer.nombre
        preferencesSettings.edit().apply{
            putLong(PREF_KEY_CURRENT_PLAYER_ID_KEY, currentPlayer.id)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupToolbar()
        setupViews()
        observePlayer()
    }


    private fun setupViews() {
        setupImgText()
        setupRecyclerView()
        btnEdit.setOnClickListener { navigateToEdit() }
        fabAddPlayer.setOnClickListener { navigateToAdd() }
        lblEmptyPlayer.setOnClickListener{ navigateToAdd()}
    }

    private fun setupImgText() {

        if (preferencesSettings.getLong(PREF_KEY_CURRENT_PLAYER_ID_KEY, NO_PLAYER) == NO_PLAYER) {
            imageViewAvatarPlayerSelected.setImageResource(R.drawable.logo)
                editTextAvatarPlayerSelected.text = getString(R.string.player_selection_no_player_selected)
            btnEdit.visibility =
                 View.INVISIBLE
        }else {
            currentPlayer = viewModel.queryPlayer(preferencesSettings.getLong(PREF_KEY_CURRENT_PLAYER_ID_KEY, NO_PLAYER)).value!!
            imageViewAvatarPlayerSelected.setImageResource(currentPlayer.avatar)
            editTextAvatarPlayerSelected.text = currentPlayer.nombre
            btnEdit.visibility = View.VISIBLE
        }
    }

    private fun setupRecyclerView() {
        lstPlayers.run {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), 3, RecyclerView.HORIZONTAL, false)
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    RecyclerView.VERTICAL
                )
            )
            adapter = listAdapter
        }
    }

    private fun observePlayer() {
        viewModel.playerList.observe(this) { showPlayers(it) }
    }

    private fun showPlayers(player: List<Player>) {
        listAdapter.submitList(player)
        lblEmptyPlayer.visibility =
            if (player.isEmpty()) View.VISIBLE else View.INVISIBLE
    }

    private fun navigateToEdit() {
        this.navController.navigate(R.id.playerEditFragmentDestination)
    }

    private fun navigateToAdd() {
        this.navController.navigate(R.id.playerCreateFragmentDestination)
    }

    private fun setupToolbar() {
        (requireActivity() as OnToolbarAvailableListener).onToolbarCreated(toolbarPlayerSelected)
        toolbarPlayerSelected.inflateMenu(R.menu.menu)
        toolbarPlayerSelected.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.mnuInfo -> {
                    show(
                        requireContext(),
                        getString(R.string.player_selection_help_description)
                    )
                }
            }
            true
        }
    }
}
