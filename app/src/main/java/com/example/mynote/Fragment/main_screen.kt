package com.example.mynote.Fragment

import android.app.AlertDialog
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mynote.Adapter.NoteAdapter
import com.example.mynote.R
import com.example.mynote.Room.Note
import com.example.mynote.Room.NoteViewModel
import kotlinx.android.synthetic.main.fragment_edit_note.view.*
import kotlinx.android.synthetic.main.fragment_main_screen.view.*


class main_screen : Fragment() {
    lateinit var adapter: NoteAdapter
    private var list:ArrayList<Note>?=null
    lateinit var noteViewModel:NoteViewModel
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
        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initComponents()
        view.fltAdd.setOnClickListener {
            val action = main_screenDirections.actionMainScreenToEditNote(null)
            Navigation.findNavController(requireActivity(),R.id.fragment).navigate(action);
        }
        view?.fltDeleteAll?.setOnClickListener {
            val dialog = AlertDialog.Builder(context)
            dialog.setTitle("NOTIFY")
            dialog.setMessage("Do you want delete it ?")
            dialog.setPositiveButton("YES") { _, _ ->
                noteViewModel.deleteAllList()
                Toast.makeText(requireContext(), "Delete Successfully", Toast.LENGTH_LONG)
                    .show()
            }
            dialog.setNegativeButton("NO") { _, _ ->

            }
            dialog.show()

        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initComponents(){
        list = ArrayList()
        adapter = context?.let { NoteAdapter(it) }!!
        noteViewModel.getAllListNote.observe(viewLifecycleOwner, {
           adapter.setData(it)
        })
        val layout = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        view?.listNote?.layoutManager = layout
        view?.listNote?.adapter = adapter
    }


}