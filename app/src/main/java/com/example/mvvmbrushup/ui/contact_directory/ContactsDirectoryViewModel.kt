package com.example.mvvmbrushup.ui.contact_directory

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.mvvmbrushup.data.model.Contact
import com.example.mvvmbrushup.utils.FileUtils
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ContactsDirectoryViewModel @Inject constructor(private val application: Application) : ViewModel() {
    private val _contacts = MutableStateFlow<List<Contact>>(emptyList())

    // Track sorting state: true = A to Z, false = Z to A
    private val _isAscending = MutableStateFlow(true)
    val isAscending: StateFlow<Boolean> = _isAscending

    init {
        loadContacts()
    }

    private fun loadContacts() {
        val json = FileUtils.loadJsonFromAssets(application, "contact_list.json")
        val moshi = Moshi.Builder().build()
        val listType = Types.newParameterizedType(List::class.java, Contact::class.java)
        val adapter = moshi.adapter<List<Contact>>(listType)

        adapter.fromJson(json)?.let { contactList ->
            _contacts.value = contactList
        }
    }

    fun sortContactsDescending() {
        _contacts.update { it.sortedByDescending { contact -> contact.name } }
    }

    fun getMainContacts(): List<Contact> {
        return _contacts.value.filter { it.preOrder == 0 || it.preOrder == 1 }
    }

    fun getAllContacts(): List<Contact> {
        return _contacts.value.filter { it.preOrder == 2 }
    }

    fun toggleSortOrder() {
        _isAscending.value = !_isAscending.value
        _contacts.update { currentContacts ->
            if (_isAscending.value) {
                currentContacts.sortedBy { it.name }
            } else {
                currentContacts.sortedByDescending { it.name }
            }
        }
    }

}