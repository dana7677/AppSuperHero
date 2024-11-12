package com.example.superhero.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.superhero.retrofit.DataHero
import com.example.superhero.databinding.ViewHeroItemBinding
import com.squareup.picasso.Picasso


class HeroAdapter (private var heroList:List<DataHero>):
    RecyclerView.Adapter<ViewHolder>()
    {
        //SearchViewConfig

        fun setFilteredList(filterList: List<DataHero>) {
            this.heroList = filterList
            notifyDataSetChanged()

        }

        //Componenente dentro del ViewHolder que va a componer la vista


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

            //De Context coge a la vista de su padre que lo ha creado.


            val binding =
                ViewHeroItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(binding)

        }

        //Devolver automaticamente la cantidad de simbolos en nuestro Lista del zodiaco
        override fun getItemCount() = heroList.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            holder.bind(heroList[position])


            /*
            holder.itemView.setOnClickListener {
                onItemClickListener(position)
            }
            */

        }

    }
class ViewHolder(private val binding: ViewHeroItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(heroData: DataHero) {

        //val context = itemView.context
        binding.heroeName.setText(heroData.nameHero)
        Picasso.get().load(heroData.urlImage.url).into(binding.imgHero)
    }
}
