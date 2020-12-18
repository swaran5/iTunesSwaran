package com.example.itunesswaran

import android.util.Log
import androidx.lifecycle.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WordViewModel(private val repository: WordRepository) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
//    val allWord: LiveData<List<Word>> = repository.allWords.asLiveData()


    var filteredArtists: MutableLiveData<List<Word>> = MutableLiveData<List<Word>>()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(word: Word) = viewModelScope.launch {
        repository.insert(word)
    }


    fun makerequest(query: String) {
        val request = ServiceBuilder.buildService(Endpoints::class.java)
        val call = request.getSongs(query)

        call.enqueue(object : Callback<Songs> {
            override fun onResponse(call: Call<Songs>, response: Response<Songs>) {
                val result = response.body()?.results

                result?.forEach {
                    val word = Word(it.artistName, it.artworkUrl100, it.trackName ?: "")
                    insert(word)
                }

                viewModelScope.launch(Dispatchers.IO) {
                    filteredArtists.postValue(repository.getFilteredArtist(query))
                }
            }
            override fun onFailure(call: Call<Songs>, t: Throwable) {
                viewModelScope.launch(Dispatchers.IO) {
                    filteredArtists.postValue(repository.getFilteredArtist(query))
                }
            }
        })
    }
}

class WordViewModelFactory(private val repository: WordRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WordViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}