package com.example.notepad

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
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

class Create_task : DialogFragment(){
    val title = ""
    val description = ""
    val dificulty = ""
    val time_limit = ""
    @SuppressLint("UseRequireInsteadOfGet")
    private var onDismissFunction: () -> Unit = {}

    fun setOnDismissFunction(block: () -> Unit){
        onDismissFunction = block
    }

    override fun onDismiss(dialog: DialogInterface) {
        onDismissFunction()
        super.onDismiss(dialog)
    }

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

            val title = TextView(context).apply {
                setTextColor(Color.BLACK)
                text = "create new task"
                textSize = 20f
                gravity = Gravity.CENTER
            }
            val title_title = TextView(context).apply {
                setTextColor(Color.BLACK)
                text = "set title"
            }
            val title_text = EditText(context).apply {
                setTextColor(Color.BLACK)
            }
            val description_title = TextView(context).apply {
                text = "set description"
                setTextColor(Color.BLACK)
            }
            val description_text = EditText(context).apply {
                setTextColor(Color.BLACK)
            }

            val submit_button = Button(context).apply {
                text = "Close"
                setTextColor(Color.BLACK)
                setOnClickListener {
                    add_task((title_text.text.toString()+","+description_text.text.toString()))
                    println("should first")
                    dismiss()
                }
            }
            addView(title)
            addView(title_title)
            addView(title_text)
            addView(description_title)
            addView(description_text)
            addView(submit_button)
        }
    }

    @SuppressLint("SuspiciousIndentation")
    fun add_task(data:String) {
        println("updated")
            try {
                val fileOutputStream: FileOutputStream = requireContext().openFileOutput("weekly.csv", Context.MODE_APPEND)
                fileOutputStream.write(data.toByteArray())
                fileOutputStream.write(System.lineSeparator().toByteArray())
                fileOutputStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }


    }

