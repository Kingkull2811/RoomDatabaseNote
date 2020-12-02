package com.example.mynote.Fragment

import android.app.AlertDialog
import android.app.Application
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.AdapterView
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
import kotlinx.android.synthetic.main.fragment_main_screen.*
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
            Navigation.findNavController(requireActivity(), R.id.fragment).navigate(action);
        }
        view.fltDeleteAll?.setOnClickListener {
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
        spinnerPriority.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {
                        noteViewModel.getAllListNote.observe(viewLifecycleOwner, {
                            list?.clear()
                            list?.addAll(it)
                            adapter.notifyDataSetChanged()
                        })
                    }
                    1 -> {
                        noteViewModel.getALlListLowToHigh.observe(viewLifecycleOwner, {
                            list?.clear()
                            list?.addAll(it)
                            adapter.notifyDataSetChanged()
                        })
                    }
                    else -> {
                        noteViewModel.getALlListHighToLow.observe(viewLifecycleOwner, {
                            list?.clear()
                            list?.addAll(it)
                            adapter.notifyDataSetChanged()
                        })
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
        view.edtSearch.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter.filter(s)
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initComponents(){
        list = ArrayList()
        val layout = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        view?.listNote?.layoutManager = layout
        noteViewModel.getAllListNote.observe(viewLifecycleOwner, {
            list!!.addAll(it)
            adapter = context?.let { it1 -> NoteAdapter(it1, list!!) }!!
            view?.listNote?.adapter = adapter
        })
    }


}