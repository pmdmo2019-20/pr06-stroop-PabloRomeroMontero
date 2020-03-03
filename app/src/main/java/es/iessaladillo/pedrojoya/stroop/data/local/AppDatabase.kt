package es.iessaladillo.pedrojoya.stroop.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import es.iessaladillo.pedrojoya.stroop.data.local.dao.GameDao
import es.iessaladillo.pedrojoya.stroop.data.local.dao.PlayerDao
import es.iessaladillo.pedrojoya.stroop.data.local.entity.Game
import es.iessaladillo.pedrojoya.stroop.data.local.entity.Player

@Database(
    entities = [
        Player::class,
        Game::class], version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract val playerDao: PlayerDao
    abstract val GameDao: GameDao


    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java,
                            "app_database"
                        )
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}