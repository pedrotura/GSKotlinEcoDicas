package pedrotura.com.github.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TipModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String
)