package pedrotura.com.github.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import pedrotura.com.github.model.TipModel

@Dao
interface TipDao {
    @Query("SELECT * FROM TipModel")
    fun getAll(): LiveData<List<TipModel>>

    @Insert
    fun insert(item: TipModel)

    @Delete
    fun delete(item: TipModel)
}