package br.com.airescovit.clim.ui.clients

/**
 * Created by Murilo Aires on 09/02/2018.
 */
interface ClientsListAction {
    fun getList(): List<Any?>
    fun onAddClientClick()
    fun isSelection(): Boolean
    fun onAddTaskClick(position: Int)
    fun onItemClick(position: Int)
    fun getClientName(position: Int): String
    fun getClientStateCity(position: Int): String
    fun getClientStreetNeighborhood(position: Int): String
    fun getClientZipCode(position: Int): String

}