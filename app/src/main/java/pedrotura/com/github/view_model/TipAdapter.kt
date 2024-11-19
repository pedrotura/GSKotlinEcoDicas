import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pedrotura.com.github.R
import pedrotura.com.github.model.TipModel

class TipsAdapter(private val onTipRemoved: (TipModel) -> Unit) : RecyclerView.Adapter<TipsAdapter.TipViewHolder>() {
    private var tips = listOf<TipModel>()
    inner class TipViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tipTitle = view.findViewById<TextView>(R.id.tipItemTitle)
        val tipDescription = view.findViewById<TextView>(R.id.tipItemDescription)
        val button = view.findViewById<ImageButton>(R.id.imageButton)

        fun bind(tip: TipModel) {
            tipTitle.text = tip.title
            tipDescription.text = tip.description
            button.setOnClickListener {
                onTipRemoved(tip)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.tip_item, parent, false)
        return TipViewHolder(view)
    }

    override fun getItemCount(): Int = tips.size

    override fun onBindViewHolder(holder: TipViewHolder, position: Int) {
        val tip = tips[position]
        holder.bind(tip)
    }

    fun updateTips(newTips: List<TipModel>) {
        tips = newTips
        notifyDataSetChanged()
    }
}