package com.tecno.tecnomoviles

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.MyApplication
import com.tecno.tecnomoviles.fragments.HeaderFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import services.ProductService
import services.ServiceGenerator
import services.dataClasses.ProductDTO


class HomeActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)
        supportActionBar?.hide()

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<HeaderFragment>(R.id.fragment_header)
            }

            val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
            val adapter = CustomAdapter()
            adapter.onItemClick = {
                val intent = Intent(this, DetailedActivity::class.java)
                intent.putExtra("producto", it)
                startActivity(intent)
            }

            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = adapter

            /*
            val recyclerViewSecond = findViewById<RecyclerView>(R.id.recyclerViewSecond)
            val adapterSecond = CustomAdapterSecond()

            recyclerViewSecond.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
            recyclerViewSecond.adapter = adapterSecond
            adapterSecond.onItemClick = {
                val intent = Intent(this, DetailedActivity::class.java)
                intent.putExtra("producto",it)
                startActivity(intent)
            }
            */


            val serviceGenerator = ServiceGenerator.buildService(ProductService::class.java)
            val call = serviceGenerator.getProductList()

            val recyclerViewDbSecond = findViewById<RecyclerView>(R.id.recyclerViewSecond)

            call.enqueue(object : Callback<MutableList<ProductDTO>> {

                override fun onResponse(
                    call: Call<MutableList<ProductDTO>>,
                    response: Response<MutableList<ProductDTO>>
                ) {

                    if(response.isSuccessful){
                        recyclerViewDbSecond.apply {
                            layoutManager = LinearLayoutManager(this@HomeActivity,LinearLayoutManager.HORIZONTAL,false)
                            recyclerViewDbSecond.adapter = productJsonAdapter(response.body()!!)
                        }
                    }
                }

                override fun onFailure(call: Call<MutableList<ProductDTO>>, t: Throwable) {
                    //TODO("Implementation")
                    t.printStackTrace()
                    Log.e("error", t.message.toString())
                }

            })




        }
        MyApplication.preferences.setActivityName("HomeActivity")
    }
}