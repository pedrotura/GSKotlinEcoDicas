package pedrotura.com.github.data

import androidx.room.Database
import androidx.room.RoomDatabase
import pedrotura.com.github.model.TipModel

@Database(entities = [TipModel::class], version = 1)
abstract class TipDatabase : RoomDatabase() {
    abstract fun tipDao(): TipDao
}