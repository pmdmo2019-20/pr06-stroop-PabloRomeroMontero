package es.iessaladillo.pedrojoya.stroop.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import es.iessaladillo.pedrojoya.stroop.data.local.AppDatabase
import es.iessaladillo.pedrojoya.stroop.data.local.entity.Game
import es.iessaladillo.pedrojoya.stroop.data.local.entity.Player

class MainActivityViewModel(val repository: AppDatabase) : ViewModel() {

     val gameList: LiveData<List<Game>> = gameMode.switchMap {
        if (it == null) {
            repository.GameDao.queryAllGame()
        } else {
            repository.GameDao.queryGameModeGame(it)
        }
    }


     val playerList: LiveData<List<Player>> = repository.playerDao.queryAllPlayers()

    //Filtro
    private val _gameMode: MutableLiveData<Boolean?> = MutableLiveData(null)
    val gameMode: LiveData<Boolean?>
        get() = _gameMode


//    PLAYERDAO

    fun queryAllPlayers(): LiveData<List<Player>> {
        return repository.playerDao.queryAllPlayers()
    }

    fun queryPlayer(idPlayer: Long): LiveData<Player> =
         repository.playerDao.queryPlayer(idPlayer)


    fun insertPlayer(player: Player) {
        repository.playerDao.insertPlayer(player)
    }

    fun editPlayer(player: Player) {
        repository.playerDao.editPlayer(player)
    }

    fun deletePlayer(player: Player){
        repository.playerDao.deletePlayer(player)
    }









//    GAMEDAO
    fun inserGame(game: Game){
        repository.GameDao.inserGame(game)
    }

    fun queryAllGame() : LiveData<List<Game>>{
        return repository.GameDao.queryAllGame()
    }

    fun queryGameModeGame(gamemode: Boolean): LiveData<List<Game>>{
        return repository.GameDao.queryGameModeGame(gamemode)
    }

    fun queryGame(idGame:Long): LiveData<Game>{
        return repository.GameDao.queryGame(idGame)
    }

}