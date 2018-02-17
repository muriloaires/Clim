package br.com.airescovit.clim.ui.tasks

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.task_item.view.*

/**
 * Created by Murilo Aires on 07/02/2018.
 */
class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val textTaskTitle = view.textTaskTitle
    val textClientName = view.textClientName
    val textStreetNeighborhood = view.textStreetNeighborhood
    val textStateCity = view.textStateCity
    val textServiceFee = view.textServiceFee
    val textDia = view.textDia
    val btnReagendar = view.btnReagendar

}