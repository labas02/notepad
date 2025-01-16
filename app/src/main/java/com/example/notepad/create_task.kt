package com.example.notepad

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import java.io.FileOutputStream
import java.io.IOException

class Create_task : DialogFragment(){
   private var which_file = ""
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
            val select_file = EditText(requireContext()).apply{
                id = View.generateViewId()
                setOnClickListener {
                    val popupMenu = PopupMenu(requireContext(), it)

                    // Dynamically add menu items to the PopupMenu
                    val menu = popupMenu.menu
                    menu.add(0, 1, 0, "daily")
                    menu.add(0, 2, 1, "weekly")
                    menu.add(0, 3, 2, "yearly")
                    menu.add(0, 4, 3, "custom")

                    // Set a listener for the menu items
                    popupMenu.setOnMenuItemClickListener { item: MenuItem ->
                        when (item.itemId) {
                            1 -> {
                                which_file = "daily.csv"
                                true
                            }

                            2 -> {
                                which_file = "weekly.csv"
                                true
                            }

                            3 -> {
                                which_file = "yearly.csv"
                                true
                            }
                            4 -> {
                                which_file = "custom.csv"
                                true
                            }

                            else -> false
                        }
                    }

                    // Show the menu
                    popupMenu.show()
                }
            }

            val submit_button = Button(context).apply {
                text = "Close"
                setTextColor(Color.BLACK)
                setOnClickListener {
                    add_task((title_text.text.toString()+","+description_text.text.toString()),which_file)
                    println("should first")
                    dismiss()
                }
            }
            addView(title)
            addView(title_title)
            addView(title_text)
            addView(description_title)
            addView(description_text)
            addView(select_file)
            addView(submit_button)
        }
    }

    @SuppressLint("SuspiciousIndentation")
    fun add_task(data:String,file:String) {
        println("updated")
            try {

                val fileOutputStream: FileOutputStream = requireContext().openFileOutput(file, Context.MODE_APPEND)
                fileOutputStream.write(data.toByteArray())

                fileOutputStream.write(System.lineSeparator().toByteArray())
                fileOutputStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }


    }

