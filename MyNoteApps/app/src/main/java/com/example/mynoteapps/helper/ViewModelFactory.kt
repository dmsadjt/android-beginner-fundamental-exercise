package com.example.mynoteapps.helper

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mynoteapps.ui.insert.NoteAddUpdateViewModel
import com.example.mynoteapps.ui.main.MainViewModel

class ViewModelFactory private constructor(private val mApp: Application) : ViewModelProvider.NewInstanceFactory(){
    companion object{
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(app: Application): ViewModelFactory {
            if (INSTANCE == null){
                synchronized(ViewModelFactory::class.java){
                    INSTANCE = ViewModelFactory(app)
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) : T{
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(mApp) as T
        } else if(modelClass.isAssignableFrom(NoteAddUpdateViewModel::class.java)) {
            return NoteAddUpdateViewModel(mApp) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}