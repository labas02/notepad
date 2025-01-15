package com.example.notepad

import android.R
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import java.io.FileOutputStream
import java.util.stream.IntStream.range

class Delete_task(val data: MutableList<List<String>>, val file: String, val avoid: Int) : DialogFragment(){
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
                layoutParams.width = (requireContext().resources.displayMetrics.widthPixels*0.6).toInt() // Custom width in pixels
                layoutParams.height = (requireContext().resources.displayMetrics.heightPixels*0.3).toInt()
                it.attributes = layoutParams
                it.setBackgroundDrawableResource(R.color.transparent) // Optional: transparent background
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

            addView(TextView(requireContext()).apply {
                setTextColor(Color.BLACK)
                text = "are you sure?"
            })
            addView(Button(requireContext()).apply {
                text = "it has a family"
                setOnClickListener {
                    remove_task()
                    dismiss()
                }
            })
        }
    }

    @SuppressLint("SuspiciousIndentation")
    fun remove_task() {
            val fileOutputStream: FileOutputStream = requireContext().openFileOutput(file, Context.MODE_PRIVATE)
                    for (i in range(0,data.size )){
                        if (i != avoid) {
                            fileOutputStream.write((data[i][0] + "," + data[i][1]).toByteArray())
                            fileOutputStream.write(System.lineSeparator().toByteArray())
                        }
                    }
                    fileOutputStream.close()
            }


}

