package com.example.webapi

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.webapi.Retrofit.TablePOJO
import com.example.webapi.databinding.ItemTableBinding

class RecViewTableAdapter : RecyclerView.Adapter<RecViewTableAdapter.TableHolder>() {
    var tables = mutableListOf<TablePOJO>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableHolder {
        val binding = DataBindingUtil.inflate<ItemTableBinding>(LayoutInflater.from(parent.context),R.layout.item_table,parent, false)
        return TableHolder(binding)
    }

    override fun onBindViewHolder(holder: TableHolder, position: Int) {
        holder.onBind(tables[position])
    }

    override fun getItemCount(): Int {
        return tables.size
    }

    fun addTablePOJO(tablePOJO: TablePOJO){
        tables.add(tablePOJO)
        //TODO() нотифай?
        notifyDataSetChanged()
    }

    class TableHolder(private val binding: ItemTableBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(tablePOJO: TablePOJO){
            val model=TableItemViewModel(tablePOJO.number,tablePOJO.date,tablePOJO.comment)
            binding.value=model
            Log.d("tut_bind","on Bind $tablePOJO")
        }
    }
}