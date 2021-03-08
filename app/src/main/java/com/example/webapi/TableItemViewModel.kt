package com.example.webapi

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

data class TableItemViewModel(@Bindable val num:String, @Bindable val date:String, @Bindable val comment:String): BaseObservable()