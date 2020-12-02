package com.example.mynote.Fragment

import android.app.AlertDialog
import android.app.Application
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mynote.R
import com.example.mynote.Room.Note
import com.example.mynote.Room.NoteViewModel
import kotlinx.android.synthetic.main.fragment_edit_note.*
import kotlinx.android.synthetic.main.fragment_edit_note.view.*


class edit_note : Fragment() {
    val args: edit_noteArgs by navArgs()
    lateinit var noteViewModel: NoteViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_note, container, false)
        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun init() {
        val note = args.sendNote
        if (note == null) {
            view?.fltEdit?.setOnClickListener {
                val titleNote = edtTitle.text?.toString()?.trim()
                val contentNote = edtContent.text?.toString()?.trim()
                if (titleNote!!.isEmpty()) {
                    edtTitle.error = "Please fill out"
                    edtTitle.requestFocus()
                } else {
                    Toast.makeText(requireContext(), "Successfully Added Note ~", Toast.LENGTH_LONG)
                        .show()
                    val note = Note(0, titleNote, contentNote!!, convertChipToInt())
                    noteViewModel.addNote(note)
                    findNavController().navigate(R.id.main_screen)

                }
            }
            view?.fltDelete?.setOnClickListener {
                Toast.makeText(requireContext(), "Delete Failure", Toast.LENGTH_LONG).show()
            }
        } else {
            view?.edtTitle?.setText(note.title)
            view?.edtContent?.setText(note.content)
            convertIntToChip(note.priority)
            view?.fltEdit?.setOnClickListener {
                val titleNote = edtTitle.text?.toString()?.trim()
                val contentNote = edtContent.text?.toString()?.trim()
                if (titleNote!!.isEmpty()) {
                    edtTitle.error = "Please fill out"
                    edtTitle.requestFocus()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Successfully Update Note ~",
                        Toast.LENGTH_LONG
                    ).show()
                    note.content = contentNote!!
                    note.title = titleNote
                    note.priority = convertChipToInt()
                    noteViewModel.updateNote(note)
                    findNavController().navigate(R.id.main_screen)

                }
            }
            view?.fltDelete?.setOnClickListener {
                val dialog = AlertDialog.Builder(context)
                dialog.setTitle("NOTIFY")
                dialog.setMessage("Do you want delete it ?")
                dialog.setPositiveButton("YES") { _, _ ->
                        noteViewModel.deleteNote(note)
                        findNavController().navigate(R.id.main_screen)
                        Toast.makeText(requireContext(), "Delete Successfully", Toast.LENGTH_LONG)
                            .show()
                }
                dialog.setNegativeButton("NO") { _, _ ->

                }
                dialog.show()

            }
        }
    }

    private fun convertIntToChip(i: Int) {
        when (i) {
            0 -> view?.chipLow?.isChecked = true
            1 -> view?.chipNormal?.isChecked = true
            2 -> view?.chipHigh?.isChecked = true
        }
    }

    private fun convertChipToInt(): Int {
        if (chipLow.isChecked) return 0;
        if (chipNormal.isChecked) return 1;
        if (chipHigh.isChecked) return 2;

        return -1;
    }

}