package com.example.mvvmcrudtutorial

import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmcrudtutorial.db.Subscriber
import com.example.mvvmcrudtutorial.db.SubscriberRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SubscriberViewModel(private val repository: SubscriberRepository) : ViewModel() {

    val subscribers = repository.subscribers

    @Bindable
    val inputName = MutableLiveData<String?>()

    @Bindable
    val inputEmail = MutableLiveData<String?>()

    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()

    @Bindable
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    fun saveOrUpdate() {
        val name = inputName.value!!
        val email = inputEmail.value!!
        // 0 as id because the id is auto-incremental and room ignore the value 0 for all the subscriber
        insert(Subscriber(0, name, email))
        inputName.value = null
        inputEmail.value = null
    }

    fun clearAllOrDelete() {
        clearAll()
    }

//    fun insert(subscriber: Subscriber) {
//        viewModelScope.launch {
//            repository.insert(subscriber)
//        }
//    }
    // This is the most concise alternative
    fun insert(subscriber: Subscriber): Job = viewModelScope.launch {
        repository.insert(subscriber)
    }

    fun update(subscriber: Subscriber): Job = viewModelScope.launch {
        repository.update(subscriber)
    }

    fun delete(subscriber: Subscriber): Job = viewModelScope.launch {
        repository.delete(subscriber)
    }

    fun clearAll(): Job = viewModelScope.launch {
        repository.deleteAll()
    }
}