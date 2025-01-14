package com.example.notepad

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import java.io.FileOutputStream
import java.io.IOException

class Create_task() : DialogFragment(){
    val title = ""
    val description = ""
    val dificulty = ""
    val time_limit = ""
    @SuppressLint("UseRequireInsteadOfGet")

    interface OnTaskActionListener {
        fun onMoveGallery(direction: Int)
    }

    private var listener: OnTaskActionListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        dialog.setOnShowListener { dialogInterface ->
            val dialogWindow = (dialogInterface as Dialog).window
            dialogWindow?.let {
                val layoutParams = it.attributes
                layoutParams.width = (requireContext().resources.displayMetrics.widthPixels*0.9).toInt() // Custom width in pixels
                layoutParams.height = (requireContext().resources.displayMetrics.heightPixels*0.9).toInt()
                it.attributes = layoutParams
                it.setBackgroundDrawableResource(android.R.color.transparent) // Optional: transparent background
            }
        }

        return dialog
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return LinearLayout(requireContext()).apply {
            setBackgroundColor(Color.CYAN)
            orientation = LinearLayout.VERTICAL
            setPadding(16, 16, 16, 16)

            addView(TextView(context).apply {
                text = "create new task"
                textSize = 20f
                gravity = Gravity.CENTER
            })
            addView(TextView(context).apply {
                text = "set title"
            })
            addView(EditText(context).apply {

            })
            addView(Button(context).apply {
                text = "Close"
                setOnClickListener {
                    add_task()
                    listener?.onMoveGallery(0)
                    dismiss()
                }
            })
        }
    }

    @SuppressLint("SuspiciousIndentation")
    fun add_task() {
        println("updated")
    var data = "1,fuck,hello"
            try {
                val fileOutputStream: FileOutputStream = requireContext().openFileOutput("weekly", Context.MODE_PRIVATE)
                fileOutputStream.write(data.toByteArray())
                fileOutputStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }


    }

