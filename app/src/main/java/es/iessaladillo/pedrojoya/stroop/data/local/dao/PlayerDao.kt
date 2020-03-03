package es.iessaladillo.pedrojoya.stroop.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import es.iessaladillo.pedrojoya.stroop.data.local.entity.Player

@Dao
interface PlayerDao {


    @Query("SELECT * FROM player")
    fun queryAllPlayers() : LiveData<List<Player>>

    @Query("SELECT * FROM player WHERE player.id =:idPlayer")
    fun queryPlayer(idPlayer:Long): LiveData<Player>

    @Insert
    fun insertPlayer(player: Player)

    @Update
    fun editPlayer(player: Player)

    @Delete
    fun deletePlayer(player: Player)
}