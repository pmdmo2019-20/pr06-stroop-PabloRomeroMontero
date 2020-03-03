package es.iessaladillo.pedrojoya.stroop.ui.player

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.base.OnToolbarAvailableListener
import es.iessaladillo.pedrojoya.stroop.data.local.AppDatabase
import es.iessaladillo.pedrojoya.stroop.data.local.entity.Player
import es.iessaladillo.pedrojoya.stroop.show
import es.iessaladillo.pedrojoya.stroop.ui.main.MainActivityViewModel
import es.iessaladillo.pedrojoya.stroop.ui.main.MainActivityViewModelFactory
import kotlinx.android.synthetic.main.fragment_player_create.*
import kotlinx.android.synthetic.main.fragment_player_edit.*

class PlayerCreateFragment : Fragment(R.layout.fragment_player_create) {
    lateinit var currentPlayer: Player
    private val navController by lazy { findNavController() }

    private val listAdapter: AvatarAdapterFragment = AvatarAdapterFragment().apply {
        setOnItemClickListener { setupAvatarText(it) }
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

    private fun setupAvatarText(position: Int) {
        imageViewAvatarPlayerCreate.setImageResource(listAdapter.getItemId(position).toInt())
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupToolbar()
        setupViews()
    }


    private fun setupViews() {
        setupRecyclerView()
        fabCreatePlayer.setOnClickListener { savePlayer() }

    }

    private fun setupRecyclerView() {
        lstAvatarCreate.run {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), 3, RecyclerView.VERTICAL, false)
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

    private fun savePlayer() {
        if (editTextAvatarPlayerCreate.text.isNotEmpty()) {
            this.navController.navigateUp()
            viewModel.insertPlayer(Player(0, editTextAvatarPlayerCreate.text.toString(), 0))
        }
    }

        private fun setupToolbar() {
            (requireActivity() as OnToolbarAvailableListener).onToolbarCreated(toolbarPlayerCreate)
            toolbarPlayerCreate.inflateMenu(R.menu.menu)
            toolbarPlayerCreate.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.mnuInfo -> {
                        show(
                            requireContext(),
                            getString(R.string.player_creation_help_description)
                        )
                    }
                }
                true
            }
        }
    }
