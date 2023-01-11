package com.kaisersakhi.hackathonparty.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaisersakhi.hackathonparty.R
import com.kaisersakhi.hackathonparty.data.models.OutletSearchQuery
import com.kaisersakhi.hackathonparty.data.models.YelpSearchResponse
import com.kaisersakhi.hackathonparty.repository.YelpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val repository: YelpRepository) : ViewModel() {
    val outlets = MutableLiveData<YelpSearchResponse>()

    fun search(searchTerm: String, context: Context) {
        viewModelScope.launch {
            repository.getOutlets(OutletSearchQuery(
                context.resources.getString(R.string.office_latitude).toDouble(),
                context.resources.getString(R.string.office_longitude).toDouble(),
                searchTerm)) {
                outlets.postValue(it)
            }
        }
    }
}