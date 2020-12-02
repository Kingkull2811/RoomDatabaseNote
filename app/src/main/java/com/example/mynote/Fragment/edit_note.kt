package com.example.mynote.Fragment

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.example.mynote.R
import com.example.mynote.Room.Note
import com.example.mynote.Room.NoteViewModel
import kotlinx.android.synthetic.main.fragment_edit_note.*
import kotlinx.android.synthetic.main.fragment_edit_note.view.*


class edit_note : Fragment() {
    val args: edit_noteArgs by navArgs()
    lateinit var noteViewModel:NoteViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_note, container, false)
        noteViewModel = NoteViewModel(requireContext() as Application)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
        super.onViewCreated(view, savedInstanceState)
    }
    private fun init(){
        val note = args.sendNote
        if(note==null){
            var titleNote = edtTitle.text?.trim()
            var contentNote = edtContent.text?.trim()
            if(titleNote!!.isEmpty()){
                edtTitle.error = "Please fill out"
                edtTitle.requestFocus()
            }else{
                val note = Note(null, titleNote as String, titleNote as String,convertChipToInt())
                noteViewModel.addNote(note)
            }
        }else{
            view?.edtTitle?.setText(note.title)
            view?.edtContent?.setText(note.content)
            Toast.makeText(context,note.priority.toString(),Toast.LENGTH_LONG).show()
            convertIntToChip(note.priority)
        }
    }
    private fun convertIntToChip(i:Int){
        when(i){
            0-> view?.chipLow?.isChecked = true
            1->view?.chipNormal?.isChecked = true
            2->view?.chipHigh?.isChecked = true
        }
    }
    private fun convertChipToInt():Int{
        if(chipLow.isChecked) return 0;
        if(chipNormal.isChecked) return 1;
        if(chipNormal.isChecked) return 2;

        return -1;
    }

}