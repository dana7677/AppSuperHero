package com.example.superhero

import android.animation.ObjectAnimator
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.util.Log
import android.view.MenuItem
import android.view.View
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

        val resultID = intent.extras?.getInt("extra_ID")
        if(resultID!=null) {
            heroId=resultID.toString()
            searchSuperHeroes(heroId)
        }
        val resultName = intent.extras?.getString("extra_Name")
        if(resultName!=null) { heroName=resultName
        supportActionBar?.title=heroName}
        val resultUrl = intent.extras?.getString("extra_Url")
        if(resultUrl!=null) { heroImgName=resultUrl }

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

        //BIO
        binding.fullNameTxt.setText(heroData.biography.fullName)
        binding.alterEgoTxt.setText("Alter Ego: ${heroData.biography.alterEgo}")
        binding.aliasTxt.setText("Alias : ${listString(heroData.biography.alias)}")
        binding.birthdayPlaceTxt.setText("BirthDay Place: ${heroData.biography.placeBirth}")
        binding.firstAppearTxt.setText("First Appearence:${heroData.biography.firstAppearance}")
        binding.publisherTxt.setText("Publisher:${heroData.biography.publisher}")
        binding.alignmentTxt.setText("Alignment:${heroData.biography.alignment}")
        //Appearence
        binding.GenderTxt.setText("Gender: ${heroData.appearance.gender}")
        binding.raceTxt.setText("Race: ${heroData.appearance.race}")
        binding.heightTxt.setText("Height: ${heroData.appearance.height[0]} Ft  ${heroData.appearance.height[1]} Cm")
        binding.GenderTxt.setText("Weight: ${heroData.appearance.gender[0]} Lbs ${heroData.appearance.gender[0]} Kg")
        binding.eyeColorTxt.setText("Eyes Color: ${heroData.appearance.eyesColor}")
        binding.hairColorTxt.setText("Hair Color: ${heroData.appearance.hairColor}")
        //Work
        binding.OcuppationTxt.setText("Ocuppation: ${heroData.work.ocuppation}")
        binding.baseOpperationTxt.setText("Base of Opperation: ${heroData.work.base}")
        //Conections
        binding.groupAffiliationsTxt.setText("Groups: ${heroData.connections.group}")
        binding.relativesTxt.setText("Relatives: ${heroData.connections.relatives}")

        binding.biographyButton.setOnClickListener {
            binding.biographyLinear.visibility= View.VISIBLE
            binding.appearenceLinear.visibility= View.GONE
            binding.workLinear.visibility= View.GONE
            binding.connectionsLinear.visibility= View.GONE

        }

        binding.appearenceButton.setOnClickListener {
            binding.biographyLinear.visibility= View.GONE
            binding.appearenceLinear.visibility= View.VISIBLE
            binding.workLinear.visibility= View.GONE
            binding.connectionsLinear.visibility= View.GONE

        }

        binding.workButton.setOnClickListener {
            binding.biographyLinear.visibility= View.GONE
            binding.appearenceLinear.visibility= View.GONE
            binding.workLinear.visibility= View.VISIBLE
            binding.connectionsLinear.visibility= View.GONE

        }

        binding.conexionButton.setOnClickListener {
            binding.biographyLinear.visibility= View.GONE
            binding.appearenceLinear.visibility= View.GONE
            binding.workLinear.visibility= View.GONE
            binding.connectionsLinear.visibility= View.VISIBLE

        }

        //Bar

        val progressBar=10
        binding.inteligenceBar.max=10
        ObjectAnimator.ofInt(binding.inteligenceBar,"progress",heroData.powerStats.intelligence.toInt())
            .setDuration(2000)
            .start()


    }
    private fun getMyStringFromData(stringPass:String):String
    {
        return stringPass
    }
    private fun listString(StringList:List<String>):String
    {
      return StringList.joinToString (" - ","","",-1,"...")
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