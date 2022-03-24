package com.example.imageapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imageapp.domain.model.ImageDomainModel
import com.example.imageapp.domain.usecase.GetImageUseCase
import com.example.imageapp.utils.StatusLoading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(private val getImageUseCase: GetImageUseCase) :
    ViewModel() {

    private val _imageList = MutableLiveData<List<ImageDomainModel>>()
    val imageList: LiveData<List<ImageDomainModel>> = _imageList

    private val _statusLoad = MutableLiveData<StatusLoading>()
    val statusLoad: LiveData<StatusLoading> = _statusLoad

    init {
        getDataRemoteSource()
    }

    fun getDataRemoteSource() {
        if (_statusLoad.value != StatusLoading.LOADING) {
            viewModelScope.launch {
                _statusLoad.value = StatusLoading.LOADING
                try {
                    loadingData()
                } catch (e: Exception) {
                    errorLoadingData()
                }
            }
        }
    }

    private suspend fun loadingData() {
        _statusLoad.value = StatusLoading.LOADING
        _imageList.value = getImageUseCase.getImage()
        _statusLoad.value = StatusLoading.DONE
    }

    private fun errorLoadingData() {
        _imageList.value = listOf()
        _statusLoad.value = StatusLoading.ERROR
    }
}

