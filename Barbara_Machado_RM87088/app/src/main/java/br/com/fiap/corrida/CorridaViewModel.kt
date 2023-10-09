package br.com.fiap.corrida

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CorridaViewModel : ViewModel() {

    private val _progressCavaloPink = MutableLiveData<Int>(0)
    val progressCavaloPink: LiveData<Int> get() = _progressCavaloPink

    private val _progressCavaloPurple = MutableLiveData<Int>(0)
    val progressCavaloPurple: LiveData<Int> get() = _progressCavaloPurple

    private val _vencedor = MutableLiveData<String>("")
    val vencedor: LiveData<String> get() = _vencedor

    private var job: Job? = null

    fun iniciaCorrida() {
        job = viewModelScope.launch {
            while (_progressCavaloPink.value!! < 100 && _progressCavaloPurple.value!! < 100) {
                delay(1500)
                _progressCavaloPink.postValue(_progressCavaloPink.value!! + (0..20).random())
                _progressCavaloPurple.postValue(_progressCavaloPurple.value!! + (0..20).random())

                if (_progressCavaloPink.value!! >= 100) _vencedor.postValue("Cavalo Rosa")
                if (_progressCavaloPurple.value!! >= 100) _vencedor.postValue("Cavalo Roxo")
            }
        }
    }

    fun paraCorrida() {
        job?.cancel()
    }
}
