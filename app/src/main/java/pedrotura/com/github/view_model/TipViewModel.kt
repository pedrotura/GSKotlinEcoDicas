package pedrotura.com.github.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pedrotura.com.github.data.TipDao
import pedrotura.com.github.data.TipDatabase
import pedrotura.com.github.model.TipModel

class TipViewModel(application: Application) : AndroidViewModel(application) {
    private val tipDao: TipDao
    val tipLiveData: LiveData<List<TipModel>>

    init {
        val database = Room.databaseBuilder(
            getApplication(),
            TipDatabase::class.java,
            "tip_database"
        ).build()

        tipDao = database.tipDao()

        tipLiveData = tipDao.getAll()
    }

    fun addTip(title: String, description: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val newTip = TipModel(title = title, description = description)
            tipDao.insert(newTip)
        }
    }

    fun removeTip(item: TipModel) {
        viewModelScope.launch(Dispatchers.IO) {
            tipDao.delete(item)
        }
    }
}