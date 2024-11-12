package com.example.superhero

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.superhero.Adapters.HeroAdapter
import com.example.superhero.databinding.ActivityMainBinding
import com.example.superhero.retrofit.DataHero
import com.example.superhero.retrofit.SuperHeroeResponse
import com.example.superhero.retrofit.superHeroService
import com.example.superhero.utils.RetrofitProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var adapter: HeroAdapter
    lateinit var searchView:SearchView
    var superheroList: List<DataHero> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = HeroAdapter(superheroList)

        binding.recyclerHero.adapter = adapter
        binding.recyclerHero.layoutManager = GridLayoutManager(this, 2)


    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main_activity,menu)

        val menuItem=menu?.findItem(R.id.menuSearch)!!
        searchView= menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener
        {
            override fun onQueryTextChange(newText: String?): Boolean {

                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query!=null)
                {
                    searchSuperHeroes(query)
                    return true
                }
                return false
            }
        })

        return true
    }
    private fun searchSuperHeroes(query:String)
    {
        val service = RetrofitProvider.makeRetrofitService()
        CoroutineScope(Dispatchers.IO).launch {
            try
            {
                        //Log.d("MainActivity",dataofHeros.listHero.toString())
                var result = service.getHeroeName(query)
                CoroutineScope(Dispatchers.Main).launch {
                    if(result.success=="error")
                    {
                        //TODO MOSTRAR MENSAJE DE QUE HAY QUE HACER ALGO
                    }else
                    {
                        adapter.setFilteredList(result.listHero)
                    }
                }


            }
            catch (e: Exception) {
                Log.e("MainActivity",e.stackTraceToString())
            }

        }

    }
}