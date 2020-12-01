package com.example.mynote.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.GridLayout
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mynote.Adapter.NoteAdapter
import com.example.mynote.R
import com.example.mynote.Room.Note
import kotlinx.android.synthetic.main.fragment_main_screen.view.*
import kotlinx.android.synthetic.main.fragment_splash_screen.view.*


class main_screen : Fragment() {
    lateinit var adapter: NoteAdapter
    private var list:ArrayList<Note>?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main_screen,container,false)
        view.mainLayoutHome.startAnimation(
            AnimationUtils.loadAnimation(
                context,
                R.anim.anim_alpha_fragment
            )
        )
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initComponents()
        view.fltAdd.setOnClickListener {
            val action = main_screenDirections.actionMainScreenToEditNote(null)
            Navigation.findNavController(requireActivity(),R.id.fragment).navigate(action);
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initComponents(){
        list = ArrayList()
        list!!.add(Note(1,"Content","Content",1))
        list!!.add(Note(1,"ContentContentContentContent","ContentContentContentContent",0))
        list!!.add(Note(1,"ContentContentContentContent","ContentContentContentContent",0))
        list!!.add(Note(1,"Content","Content",1))
        list!!.add(Note(1,"ContentContentContentContentContentContentContentContent","ContentContentContentContentContentContentContentContent",2))
        list!!.add(Note(1,"ContentContentContentContent","ContentContentContentContent",0))
        list!!.add(Note(1,"ContentContentContentContentContentContentContentContent","ContentContentContentContentContentContentContentContent",2))
        adapter = context?.let { NoteAdapter(it, list!!) }!!
        val layout = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        view?.listNote?.layoutManager = layout
        view?.listNote?.adapter = adapter
    }

}