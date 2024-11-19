package pedrotura.com.github.view_model

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TipViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TipViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TipViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}