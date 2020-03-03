package es.iessaladillo.pedrojoya.stroop.data.local.entity

import androidx.annotation.DrawableRes
import androidx.room.*


@Entity(
    tableName = "player",
    indices = [
        Index(
            name = "ID_INDEX_UNIQUE",
            value = ["id"],
            unique = true
        )]
)
data class Player(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(
        name = "id",
        index = true
    )
    val id: Long,
    @ColumnInfo(name = "nombre")
    var nombre: String,
    @ColumnInfo(name = "avatar")
   @DrawableRes var avatar: Int
) {

}