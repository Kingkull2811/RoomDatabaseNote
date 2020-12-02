package com.example.mynote.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Filter
import android.widget.Filterable
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.mynote.Fragment.main_screenDirections
import com.example.mynote.R
import com.example.mynote.Room.Note
import kotlinx.android.synthetic.main.row_layout.view.*
import java.util.*
import kotlin.collections.ArrayList


class NoteAdapter(context: Context, listNote: List<Note>) :
    RecyclerView.Adapter<NoteAdapter.MyViewHolder>(), Filterable {
    private var list = listNote
    private var fullList: ArrayList<Note> = ArrayList(listNote)
    var context: Context = context
    private var lastPosition = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_layout, null)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteAdapter.MyViewHolder, position: Int) {
        val item = list[position]
        holder.setData(item)
        setAnimation(holder.itemView, position, false)
        holder.itemView.setOnClickListener {
            val action = main_screenDirections.actionMainScreenToEditNote(item)
            Navigation.findNavController(holder.itemView).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return list.size;
    }
    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        public fun setData(note: Note){
            itemView.tvTitle.text = note.title
            itemView.tvContent.text = note.content
            itemView.cvPriority.setCardBackgroundColor(ConvertIntToColor(note.priority))
        }
    }

    private fun setAnimation(viewToAnimate: View, position: Int, options: Boolean) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (options) {
            if (position > lastPosition) {
                val animation = AnimationUtils.loadAnimation(context, R.anim.anim_bottom_to_top)
                viewToAnimate.startAnimation(animation)
                lastPosition = position
            }
        } else {
            val animation = AnimationUtils.loadAnimation(context, R.anim.anim_bottom_to_top)
            viewToAnimate.startAnimation(animation)
        }
    }

    private fun ConvertIntToColor(i: Int): Int {
        when (i) {
            0 -> return context.getColor(R.color.colorGreen)
            1 -> return context.getColor(R.color.colorYellow)
            2 -> return context.getColor(R.color.colorRed)
        }
        return -1;
    }

    private var filter = object : Filter() {
        //run Worker Thread

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val list = ArrayList<Note>()
            if (constraint!!.isEmpty()) {
                list.addAll(fullList)
                Log.d("TAG", "list: " + list.size + " text is empty ")
            } else {
                for (item in fullList) {
                    if (item.title.toLowerCase(Locale.ROOT)
                            .contains(constraint.toString().toLowerCase(Locale.ROOT))
                    )
                        list.add(item)
                }
                Log.d("TAG", "list: " + list.size + " text: " + constraint)
            }
            val reusult = FilterResults()
            reusult.values = list
            return reusult
        }

        //run UI thread
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            (list as ArrayList).clear()
            try {
                (list as ArrayList<Note>).addAll(results?.values as Collection<Note>)
                Log.d("TAG", "publishResults: " + list.size)
            } catch (e: Exception) {

            }

            notifyDataSetChanged()
        }

    }

    override fun getFilter(): Filter {
        return filter
    }

}