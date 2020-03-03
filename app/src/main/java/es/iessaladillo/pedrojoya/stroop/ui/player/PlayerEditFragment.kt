package es.iessaladillo.pedrojoya.stroop.ui.player

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
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
import es.iessaladillo.pedrojoya.stroop.PREF_KEY_CURRENT_PLAYER_ID_KEY

import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.avatars
import es.iessaladillo.pedrojoya.stroop.base.OnToolbarAvailableListener
import es.iessaladillo.pedrojoya.stroop.data.local.AppDatabase
import es.iessaladillo.pedrojoya.stroop.data.local.entity.Player
import es.iessaladillo.pedrojoya.stroop.show
import es.iessaladillo.pedrojoya.stroop.ui.main.MainActivityViewModel
import es.iessaladillo.pedrojoya.stroop.ui.main.MainActivityViewModelFactory
import kotlinx.android.synthetic.main.fragment_player_create.*
import kotlinx.android.synthetic.main.fragment_player_edit.*
import kotlinx.android.synthetic.main.fragment_player_selected.*

class PlayerEditFragment : Fragment(R.layout.fragment_player_edit) {

    lateinit var currentPlayer: Player

    private val navController by lazy { findNavController() }

    private val listAdapter: AvatarAdapterFragment = AvatarAdapterFragment().apply {
        setOnItemClickListener { setPlayer(it) }
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
    private fun setPlayer(position: Int) {
        imageViewAvatarPlayerSelected.setImageResource( listAdapter.getItemId(position) )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupToolbar()
        setupViews()
    }


    private fun setupViews() {
        setupAvatarText()
        setupRecyclerView()
        fabEditPlayer.setOnClickListener { editPlayer() }
    }

    private fun setupAvatarText() {


        editTextAvatarPlayerEdit.setText(currentPlayer.nombre)
        imageViewAvatarPlayerEdit.setImageResource(currentPlayer.avatar)
    }

    private fun setupRecyclerView() {
        lstAvatar.run {
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

    private fun editPlayer() {
        if (editTextAvatarPlayerCreate.text.isNotEmpty()) {
            viewModel.editPlayer(Player(0, editTextAvatarPlayerCreate.text.toString(), 0))
            this.navController.navigateUp()

        }
    }

    private fun setupToolbar() {
        (requireActivity() as OnToolbarAvailableListener).onToolbarCreated(toolbarPlayerEdit)
        toolbarPlayerEdit.inflateMenu(R.menu.menu_delete)
        toolbarPlayerEdit.inflateMenu(R.menu.menu)

        toolbarPlayerEdit.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.mnuInfo -> {
                    show(
                        requireContext(),
                        getString(R.string.player_creation_help_description)
                    )
                true
                }
                R.id.mnuDelete -> {
                    AlertDialog.Builder(context)
                        .setTitle(R.string.player_deletion_title)
                        .setMessage(R.string.player_deletion_message)
                        .setPositiveButton(R.string.player_deletion_yes) {_,_ ->
                            run {
                                viewModel.deletePlayer(currentPlayer)
                                navController.navigateUp()
                            }
                        }
                        .setNegativeButton(R.string.player_deletion_no){ _, _ -> }
                        .create()
                        .show()
                    true
            }
                else -> false
            }
    }
    }
}
