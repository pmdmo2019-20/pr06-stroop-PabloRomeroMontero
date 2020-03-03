package es.iessaladillo.pedrojoya.stroop.ui.assistant

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import es.iessaladillo.pedrojoya.stroop.NUMBER_FRAGMENTS_VIEWPAGER
import es.iessaladillo.pedrojoya.stroop.R

class AssistantFragmentAdapter(parent: Fragment) : FragmentStateAdapter(parent) {

    override fun getItemCount(): Int {
        return NUMBER_FRAGMENTS_VIEWPAGER
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> Fragment(R.layout.fragment_viewpager_1)
            1 -> Fragment(R.layout.fragment_viewpager_2)
            2 -> Fragment(R.layout.fragment_viewpager_3)
            3 -> Fragment(R.layout.fragment_viewpager_4)
            4 -> Fragment(R.layout.fragment_viewpager_5)
            5 -> Fragment(R.layout.fragment_viewpager_6)
            6 -> Fragment(R.layout.fragment_viewpager_7)
            7 -> Fragment(R.layout.fragment_viewpager_8)
            else -> throw IndexOutOfBoundsException("Invalid fragment index")
        }
    }
}