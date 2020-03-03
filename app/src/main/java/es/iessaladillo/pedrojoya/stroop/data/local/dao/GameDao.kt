package es.iessaladillo.pedrojoya.stroop.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import es.iessaladillo.pedrojoya.stroop.data.local.entity.Game
import es.iessaladillo.pedrojoya.stroop.data.local.entity.Player

@Dao
interface GameDao{
    @Insert
    fun inserGame(game: Game)

    @Query("SELECT * FROM game")
    fun queryAllGame() : LiveData<List<Game>>

    @Query("SELECT * FROM game WHERE game.mododejuego=:gamemode")
    fun queryGameModeGame(gamemode: Boolean): LiveData<List<Game>>

    @Query("SELECT * FROM game WHERE game.id =:idGame")
    fun queryGame(idGame:Long): LiveData<Game>

}