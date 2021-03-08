package com.example.webapi

import android.app.ProgressDialog
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.webapi.Retrofit.RetrofitHelper
import com.example.webapi.Retrofit.TablePOJO
import com.example.webapi.Retrofit.WebAPI
import com.example.webapi.databinding.ActivityMainBinding
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers


class MainActivity : AppCompatActivity() {

    private val binding:ActivityMainBinding by lazy{DataBindingUtil.setContentView(
        this,
        R.layout.activity_main
    )}
    private val disposables:CompositeDisposable = CompositeDisposable()
    private val adapter: RecViewTableAdapter by lazy{RecViewTableAdapter()}
    private val progressDialog:ProgressDialog by lazy {ProgressDialog(this)}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progressDialog.setCancelable(false)
        binding.recView.adapter = adapter
        binding.recView.layoutManager = LinearLayoutManager(this)
        initButtons()
    }

    private fun initButtons(){
        disposables.add(RxView.clicks(binding.downloadTable).subscribe() {
            setupRecView()
        })
        disposables.add(RxView.clicks(binding.sendTable).subscribe() {
            sendTable()
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }

    private fun sendTable(){
        progressDialog.show()
        val obs:Observable<String> = RetrofitHelper.useTable(WebAPI::class.java)
                .sendTable(adapter.tables.filter { it.comment.isNotEmpty() })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        val observer:DisposableObserver<String> =object:DisposableObserver<String>(){
            override fun onNext(value: String) {
                Log.d("tut_send", value)
                Toast.makeText(this@MainActivity, getString(R.string.done_send), Toast.LENGTH_SHORT).show()
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                Toast.makeText(
                    this@MainActivity,
                    getString(R.string.not_done_send),
                    Toast.LENGTH_SHORT
                ).show()
                progressDialog.dismiss()
            }

            override fun onComplete() {
                progressDialog.dismiss()
            }
        }
        obs.subscribe(observer)
    }

    private fun setupRecView(){
        adapter.tables.clear()
        progressDialog.show()
        val obs: Observable<TablePOJO> = RetrofitHelper.useTable(WebAPI::class.java)
                .getTable()
                .subscribeOn(Schedulers.io())
                .flatMapIterable { it }
                .observeOn(AndroidSchedulers.mainThread())
        val observer:DisposableObserver<TablePOJO> =object:DisposableObserver<TablePOJO>(){
            override fun onNext(value: TablePOJO?) {
                if (value != null) {
                    Log.d("tut",value.date)
                    adapter.addTablePOJO(value)
                }
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                progressDialog.dismiss()
            }

            override fun onComplete() {
                progressDialog.dismiss()
            }
        }
        obs.subscribe(observer)
    }
}