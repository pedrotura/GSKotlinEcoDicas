package pedrotura.com.github

import TipsAdapter
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import pedrotura.com.github.view_model.TipViewModel
import pedrotura.com.github.view_model.TipViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: TipViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "EcoDicas"

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val tipsAdapter = TipsAdapter { tip ->
            viewModel.removeTip(tip)
        }
        recyclerView.adapter = tipsAdapter

        val button = findViewById<Button>(R.id.addButton)
        val tipTitle = findViewById<EditText>(R.id.tipTitle)
        val tipDescription = findViewById<EditText>(R.id.tipDescription)

        button.setOnClickListener {
            if (tipTitle.text.isEmpty()) {
                tipTitle.error = "O título da dica é obrigatório"
                return@setOnClickListener
            }

            if (tipDescription.text.isEmpty()) {
                tipDescription.error = "A descrição é obrigatória"
                return@setOnClickListener
            }

            viewModel.addTip(tipTitle.text.toString(), tipDescription.text.toString())
            tipTitle.text.clear()
            tipDescription.text.clear()

            val viewModelFactory = TipViewModelFactory(application)
            viewModel = ViewModelProvider(this, viewModelFactory).get(TipViewModel::class.java)

            viewModel.tipLiveData.observe(this) { tips ->
                tipsAdapter.updateTips(tips)
            }

        }
    }
}