package com.example.moviapp.adapter

import android.content.Context
import android.icu.util.Calendar
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.moviapp.R
import com.example.moviapp.databinding.ItemRvBinding
import com.example.moviapp.db.MyDbHelper
import com.example.moviapp.models.Movie
import java.text.SimpleDateFormat
import java.util.Locale

class MovieAdapter(val context: Context, val list: ArrayList<Movie>, val click : RvClick) : RecyclerView.Adapter<MovieAdapter.Vh>() {

    inner class Vh(var itemRvBinding: ItemRvBinding) : RecyclerView.ViewHolder(itemRvBinding.root) {

        fun onBind(movie: Movie, position: Int) {
            itemRvBinding.nameTextView.text = movie.name
            itemRvBinding.authorsTextView.text = movie.authors
            itemRvBinding.dateTextView.text = movie.date

            itemRvBinding.root.setOnClickListener {
                click.onClick(position)
            }
            val joriyVaqt = Calendar.getInstance().time

            // Formatlash uchun SimpleDateFormat obyektini yaratish
            val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())

            // Joriy vaqtni sozlash
            val formattedJoriyVaqt = formatter.format(joriyVaqt)

            itemRvBinding.dateTextView.text = formattedJoriyVaqt

            itemRvBinding.deleteButton.setOnClickListener {
                val myDbHelper = MyDbHelper(context)
                myDbHelper.delete(movie)
                list.removeAt(position)
                this@MovieAdapter.notifyItemRemoved(position)
            }

            itemRvBinding.editButton.setOnClickListener {
                itemRvBinding.root.findNavController().navigate(R.id.editFragment, bundleOf("keyMovie" to movie))
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)

    }

    interface RvClick{
        fun onClick(position: Int)
    }

}