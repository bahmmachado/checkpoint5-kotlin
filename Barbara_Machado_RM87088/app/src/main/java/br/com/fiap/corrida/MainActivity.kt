package br.com.fiap.corrida

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.fiap.corrida.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CorridaViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(CorridaViewModel::class.java)

        binding.botaoStart.setOnClickListener {
            viewModel.iniciaCorrida()
        }

        binding.botaoStop.setOnClickListener {
            viewModel.paraCorrida()
        }

        viewModel.progressCavaloPink.observe(this, Observer { progress ->
            binding.cavaloPinkProgressBar.progress = progress
        })

        viewModel.progressCavaloPurple.observe(this, Observer { progress ->
            binding.cavaloPurpleProgressBar.progress = progress
        })

        viewModel.vencedor.observe(this, Observer { winner ->
            if (winner.isNotEmpty()) {
                binding.vencedorTextView.text = "O $winner venceu!"
            }
        })
    }
}
