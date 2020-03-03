package es.iessaladillo.pedrojoya.stroop.data.local.entity

import androidx.room.*
import androidx.room.ForeignKey.CASCADE


@Entity(tableName = "game",
    foreignKeys = [
    ForeignKey(
        entity = Player::class,
        parentColumns = ["id"],
        childColumns = ["idplayer"],
        onUpdate = CASCADE,
        onDelete = CASCADE
    )],
    indices = [
    Index(
        name="GAME_INDEX",
        value = ["id","idplayer"]
    ),
    Index("idplayer")])
data class Game(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "respuestascorrectas")
    var respuestasCorrectas: Int,
    @ColumnInfo(name = "respuestasincorrectas")
    var respuestasIncorrectas: Int,
    @ColumnInfo(name = "palabras_totales")
    var palabrasTotales: Int,
    @ColumnInfo(name = "score")
    var score: Long,
    @ColumnInfo(name = "tiempo")
    var tiempo: Int,
    @ColumnInfo(name = "mododejuego")
    var modoDeJuego: Boolean,
    @ColumnInfo(name = "idplayer")
    var idPlayer: Long){

}