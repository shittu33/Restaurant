package org.abdulmujeeb.application.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapi.RestaurantAdapter
import org.abdulmujeeb.application.api.ApiDao
import org.abdulmujeeb.application.R
import org.abdulmujeeb.application.api.Restaurant
import org.abdulmujeeb.application.api.Restaurants
import org.abdulmujeeb.application.helper.ConfigHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: RestaurantAdapter
    private lateinit var rcv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("API URL : ", ConfigHelper.getConfigValue(this, "api_url"));
        rcv = findViewById(R.id.rcv)
        val editSearch: EditText  = findViewById(R.id.edit_search)
        initAdapter()

        editSearch.doAfterTextChanged {
            val newText = it.toString()
            if (it != null && newText.isNotEmpty()) {
                ApiDao.getSearchedRes(":$newText").enqueue(object : Callback<Restaurants> {
                    override fun onResponse(
                        call: Call<Restaurants>,
                        response: Response<Restaurants>
                    ) {
                        response.body().let {
                            if (it != null) {
                                adapter.apply {
                                    addRestaurants(it.results)
                                    notifyDataSetChanged()
                                    Log.i("adapter", "adapter")
                                }
                            } else {
                                Toast.makeText(applicationContext, "Api is empty", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    }

                    override fun onFailure(call: Call<Restaurants>, t: Throwable) {
                    }

                })
            }
        }
        ApiDao.getAllRestaurants().enqueue(object : Callback<Restaurants> {
            override fun onResponse(call: Call<Restaurants>, response: Response<Restaurants>) {
//                txtResult.text= response.body()?.results.toString()
                response.body().let {
                    if (it != null) {
                        adapter.apply {
                            addRestaurants(it.results)
                            notifyDataSetChanged()
                            Log.i("adapter", "adapter")
                        }
                    } else {
                        Toast.makeText(applicationContext, "Api is empty", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }

            override fun onFailure(call: Call<Restaurants>, t: Throwable) {
            }
        })

    }

    private fun initAdapter() {
        val onItemClick: (Restaurant) -> Unit = { restaurant ->
            Toast.makeText(this, "${restaurant.name} is Clicked!", Toast.LENGTH_SHORT).show()
        }
        rcv.layoutManager = LinearLayoutManager(this)
        adapter = RestaurantAdapter(arrayListOf(), onItemClick = onItemClick)
        rcv.addItemDecoration(
            DividerItemDecoration(
                rcv.context,
                (rcv.layoutManager as LinearLayoutManager).orientation
            )
        )
        rcv.adapter = adapter
    }
}
