package es.iessaladillo.pedrojoya.stroop.ui.player

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.stroop.OnItemClickListener
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.avatars
import es.iessaladillo.pedrojoya.stroop.data.local.entity.Player
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.avatar_item.*
import kotlinx.android.synthetic.main.player_item.*


class AvatarAdapterFragment: RecyclerView.Adapter<AvatarAdapterFragment.ViewHolder>() {

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    private var data: List<Int> = avatars
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.player_item, parent, false)
        return ViewHolder(itemView, onItemClickListener)
    }

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long = data[position].toLong()

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    class ViewHolder(override val containerView: View, onItemClickListener: OnItemClickListener?) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        init {
            containerView.setOnClickListener{
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener?.invoke(position)
                }
            }
        }

        fun bind(avatar: Int) {
            imageViewAvatar.setImageResource(avatar)
        }
    }

}