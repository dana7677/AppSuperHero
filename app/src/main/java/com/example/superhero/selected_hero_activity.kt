package com.example.superhero

import android.os.Bundle
import android.provider.ContactsContract.Data
import android.util.Log
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.superhero.databinding.ActivityMainBinding
import com.example.superhero.databinding.ActivitySelectedHeroBinding
import com.example.superhero.retrofit.DataHero
import com.example.superhero.utils.RetrofitProvider
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class selected_hero_activity : AppCompatActivity() {

    lateinit var heroId:String
    lateinit var heroName:String
    lateinit var heroImgName:String
    lateinit var heroData:DataHero
    lateinit var binding: ActivitySelectedHeroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySelectedHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setContentView(R.layout.activity_selected_hero)

        val resultID = intent.extras?.getString("extra_ID")
        if(resultID!=null) {
            heroId=resultID
            searchSuperHeroes(heroId)
        }
        val resultName = intent.extras?.getString("extra_Name")
        if(resultName!=null) { heroName=resultName
        supportActionBar?.title=heroName}
        val resultUrl = intent.extras?.getString("extra_Url")
        if(resultUrl!=null) { heroImgName=resultUrl }

        binding.heroeNameSelected.setText(heroName)
        Picasso.get().load(heroImgName).into(binding.ImgHeroSelected)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

            when(item.itemId)
            {
                android.R.id.home->
                {
                    finish()
                    return true
                }
            }
        return super.onOptionsItemSelected(item)
    }
    private fun setMoreDataHeroe()
    {
       // binding.
    }
    private fun searchSuperHeroes(query:String)
    {
        val service = RetrofitProvider.makeRetrofitService()
        CoroutineScope(Dispatchers.IO).launch {
            try
            {
                //Log.d("MainActivity",dataofHeros.listHero.toString())
                var result = service.getHeroeID(query)
                CoroutineScope(Dispatchers.Main).launch {
                    if(result.success=="error")
                    {
                        //TODO MOSTRAR MENSAJE DE QUE HAY QUE HACER ALGO
                    }else
                    {
                        heroData=result
                        setMoreDataHeroe()
                    }
                }
            }
            catch (e: Exception) {
                Log.e("MainActivity",e.stackTraceToString())
            }

        }

    }
}