package com.berivan.movieapp.util

import android.content.Context
import android.content.SharedPreferences
import com.berivan.movieapp.data.model.MovieItem
import com.berivan.movieapp.util.Constant.KEY_DATA
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class LocalDataManager(context: Context) {

    var localListData: List<MovieItem>?
        set(value) {
            val json = Gson().toJson(value)
            pref.edit().putString(KEY_DATA, json).apply()
        }
        get() {
            val json = pref.getString(KEY_DATA, null)
            val type : Type = object  : TypeToken<List<MovieItem>?>() {}.type
            return Gson().fromJson(json, type)
        }


    private val pref: SharedPreferences by lazy {
        context.getSharedPreferences(
            context.packageName,
            Context.MODE_PRIVATE
        )
    }

    fun clearData() = pref.edit().clear().apply()

    companion object {
        private var instance: LocalDataManager? = null

        @Synchronized
        @JvmStatic
        fun getInstance(): LocalDataManager {
            return instance!!
        }

        @JvmStatic
        fun init(context: Context) {
            if (instance == null) {
                instance = LocalDataManager(context)
            }
        }
    }
}