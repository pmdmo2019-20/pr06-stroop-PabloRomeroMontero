package es.iessaladillo.pedrojoya.stroop.ui.assistant

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import es.iessaladillo.pedrojoya.stroop.PREF_KEY_FIRST_TIME
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.base.OnToolbarAvailableListener
import kotlinx.android.synthetic.main.assistant_fragment.*
import kotlinx.android.synthetic.main.fragment_viewpager_8.*

class AssistantFragment : Fragment(R.layout.assistant_fragment) {

    val navCtrl: NavController by lazy {
        findNavController()
    }
    private val preferencesSettings: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(activity)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupToolbar()
        setupViewPager()

    }

    private fun setupViewPager() {
        val listAdapter = AssistantFragmentAdapter(this)
        btnFinish?.setOnClickListener { navCtrl.navigateUp() }
        viewPager.adapter = listAdapter
        setupTabLayoutMediator()
        for (x in 0 until listAdapter.itemCount) {
            tabLayout.newTab().setIcon(R.drawable.tab_as_circle_indicator)
        }

    }

    private fun setupToolbar() {
        (requireActivity() as OnToolbarAvailableListener).onToolbarCreated(toolbarAssistant)
        if (preferencesSettings.getLong(PREF_KEY_FIRST_TIME, 0) == 0L) {
            toolbarAssistant.setTitle(R.string.app_name)
            preferencesSettings.edit().putLong(PREF_KEY_FIRST_TIME, 1).apply()
        } else{
            toolbarAssistant.setTitle(R.string.assistant_title)
        }
    }

    private fun setupTabLayoutMediator() {

        TabLayoutMediator(tabLayout, viewPager) { tab, _ ->
            tab.setIcon(R.drawable.tab_as_circle_indicator)
        }.attach()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab) {
                btnFinish?.setOnClickListener { navCtrl.navigateUp() }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }
}