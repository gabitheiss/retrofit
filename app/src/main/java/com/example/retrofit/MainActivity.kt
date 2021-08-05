package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.adapter.AdapterMarcas
import com.example.retrofit.endpoint.RetrofitBuilder
import com.example.retrofit.model.Marca
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), Callback<List<Marca>> {

    private val adapterMarcas = AdapterMarcas()
    private lateinit var listaRecyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var errorMessage: TextView
    val call by lazy {
        val service = RetrofitBuilder.getServiceCarInstance()
        service.buscarMarcas()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadComponent()

        call.enqueue(this)
    }

    fun loadComponent() {
        listaRecyclerView = findViewById(R.id.listaDeMarcas)
        progressBar = findViewById(R.id.progressBar)
        errorMessage = findViewById(R.id.errorMessageTextView)

        listaRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        listaRecyclerView.adapter = adapterMarcas
    }


    override fun onResponse(call: Call<List<Marca>>, response: Response<List<Marca>>) {
        progressBar.visibility = View.GONE
        response.body()?.apply {
            adapterMarcas.update(this)
        }

    }

    override fun onFailure(call: Call<List<Marca>>, t: Throwable) {
        progressBar.visibility = View.GONE
        errorMessage.visibility = View.VISIBLE
    }

}
