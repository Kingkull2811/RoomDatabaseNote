package com.example.mynote.Adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.mynote.Fragment.main_screenDirections
import com.example.mynote.R
import com.example.mynote.Room.Note
import kotlinx.android.synthetic.main.row_layout.view.*


class NoteAdapter(context: Context):
    RecyclerView.Adapter<NoteAdapter.MyViewHolder>() {
    private var list = emptyList<Note>()
    var context:Context = context
    private var lastPosition = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_layout, null)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteAdapter.MyViewHolder, position: Int) {
        val item = list[position]
        holder.setData(item)
        setAnimation(holder.itemView,position)
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

    private fun setAnimation(viewToAnimate: View, position: Int) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            val animation = AnimationUtils.loadAnimation(context, R.anim.anim_bottom_to_top)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }
    private fun ConvertIntToColor(i: Int):Int{
        when(i){
            0 -> return context.getColor(R.color.colorGreen)
            1 -> return context.getColor(R.color.colorYellow)
            2 -> return context.getColor(R.color.colorRed)
        }
        return -1;
    }

    fun setData(list:List<Note>){
        this.list = list
        notifyDataSetChanged()
    }

}