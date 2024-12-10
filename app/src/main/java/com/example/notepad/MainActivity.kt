
package com.example.notepad

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.AssetManager
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.marginTop
import androidx.core.view.setMargins
import androidx.core.view.setPadding
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.FileWriter
import java.io.IOException
import java.io.InputStreamReader
import java.nio.charset.Charset
import java.sql.Struct

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val assetManager: AssetManager = assets
        val inputStream = assetManager.open("daily.csv")
        val reader = BufferedReader(InputStreamReader(inputStream))
        val data = mutableListOf<List<String>>()

        reader.forEachLine { line ->
            val row = line.split(",") // Split the line by commas
            data.add(row)
        }
        reader.close()


        // Create a parent ConstraintLayout
        val constraintLayout = ConstraintLayout(this).apply {
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT
            )
        }

        // Top blue layout
        val topLayout = FrameLayout(this).apply {
            id = View.generateViewId()
            setBackgroundColor(Color.BLUE)
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
            )
        }
        val daily_layout = FrameLayout(this).apply {
            id = View.generateViewId()
            setBackgroundColor(Color.MAGENTA)
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
            )
        }

        val daily_quest_gallery = FrameLayout(this).apply {
            id = View.generateViewId()
            setBackgroundColor(Color.BLACK)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            ).apply {
                setMargins(20,20,20,20)
            }

        }
        daily_layout.addView(daily_quest_gallery)

        val button_layout = ConstraintLayout(this).apply{
            id = View.generateViewId()
            setBackgroundColor(Color.BLACK)
            layoutParams = ConstraintLayout.LayoutParams(
                (resources.displayMetrics.widthPixels * 0.1f).toInt(),
                (resources.displayMetrics.heightPixels * 0.05f).toInt()
            )
            setOnClickListener({
                val popupMenu = PopupMenu(this@MainActivity, it)

                // Dynamically add menu items to the PopupMenu
                val menu = popupMenu.menu
                menu.add(0, 1, 0, "Item 1")
                menu.add(0, 2, 1, "Item 2")
                menu.add(0, 3, 2, "Item 3")

                // Set a listener for the menu items
                popupMenu.setOnMenuItemClickListener { item: MenuItem ->
                    when (item.itemId) {
                        1 -> {
                            // Handle "Item 1" click
                            true
                        }
                        2 -> {
                            // Handle "Item 2" click
                            true
                        }
                        3 -> {
                            // Handle "Item 3" click
                            true
                        }
                        else -> false
                    }
                }

                // Show the menu
                popupMenu.show()
            })
        }
topLayout.addView(button_layout)

        // Bottom-left layout
        val bottomLeftLayout = LinearLayout(this).apply {
            id = View.generateViewId()
            setBackgroundColor(Color.RED)
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
            )
        }

        val scroll = ScrollView(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
        }

        val linear = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }
        for (nums in 0..50){
        val textView = TextView(this).apply {
            text = data.toString()
            textSize = 18f
            setTextColor(Color.BLACK)
        }
            linear.addView(textView)
    }
        scroll.addView(linear)

        bottomLeftLayout.addView(scroll)

        // Bottom-right layout
        val bottomRightLayout = FrameLayout(this).apply {
            id = View.generateViewId()
            setBackgroundColor(Color.GREEN)
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
            )
        }

        // Add views to the ConstraintLayout
        constraintLayout.addView(topLayout)
        constraintLayout.addView(daily_layout)
        constraintLayout.addView(bottomLeftLayout)
        constraintLayout.addView(bottomRightLayout)

        // Define constraints
        val set = ConstraintSet()
        set.clone(constraintLayout)

        // Top layout takes 20% height
        set.constrainPercentHeight(topLayout.id, 0.05f)
        set.connect(topLayout.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
        set.connect(topLayout.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
        set.connect(topLayout.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)

        set.constrainPercentHeight(daily_layout.id,0.13f)
        set.connect(daily_layout.id,ConstraintSet.TOP,topLayout.id,ConstraintSet.BOTTOM)
        set.connect(daily_layout.id,ConstraintSet.START,ConstraintSet.PARENT_ID,ConstraintSet.START)
        set.connect(daily_layout.id,ConstraintSet.END,ConstraintSet.PARENT_ID,ConstraintSet.END)

        // Bottom-left layout
        set.connect(bottomLeftLayout.id, ConstraintSet.TOP, daily_layout.id, ConstraintSet.BOTTOM)
        set.connect(bottomLeftLayout.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
        set.connect(bottomLeftLayout.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
        set.connect(bottomLeftLayout.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)
        //set.constrainPercentWidth(bottomLeftLayout.id, 0.5f) // Half the width

        // button menu
        //set.constrainPercentWidth(button_layout.id,0.2f)
        set.connect(button_layout.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
        set.connect(button_layout.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
        set.connect(button_layout.id, ConstraintSet.BOTTOM, daily_layout.id, ConstraintSet.TOP)
        set.connect(button_layout.id, ConstraintSet.TOP,ConstraintSet.PARENT_ID,ConstraintSet.TOP)

        // Apply constraints
        set.applyTo(constraintLayout)

        // Set the layout as the content view
        setContentView(constraintLayout)
    }

    // Function to write content to a file
    private fun writeToFile(context: Context, fileName: String, content: String) {
        try {
            // Open a file output stream to the app's internal storage
            val fileOutputStream: FileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE)
            fileOutputStream.write(content.toByteArray())
            fileOutputStream.close()  // Close the file stream
        } catch (e: IOException) {
            e.printStackTrace()  // Handle any errors
        }
    }

    // Function to read content from a file
    private fun readFromFile(context: Context, fileName: String): String {
        var content = ""
        try {
            // Open the file input stream
            val fileInputStream: FileInputStream = context.openFileInput(fileName)
            val byteArray = ByteArray(fileInputStream.available())
            fileInputStream.read(byteArray)
            content = String(byteArray, Charset.defaultCharset()) // Convert byte array to string

            fileInputStream.close()  // Close the file stream
        } catch (e: IOException) {
            e.printStackTrace()  // Handle any errors
        }
        return content
    }

    data class daily_layouts_set(
        val daily1: LinearLayout,
        val daily2: LinearLayout,
        val daily3: LinearLayout
    )

    fun crete_daily_tasks(){
        val daily = mutableListOf<daily_layouts_set>()
        val tmp1 = LinearLayout(this).apply {  };
        val tmp2 = LinearLayout(this).apply {  };
        val tmp3 = LinearLayout(this).apply {  };
        daily.add(daily_layouts_set(tmp1,tmp3,tmp3))
        return
    }

    fun createEmptyFile(fileName: String): Boolean {
        val file = File(fileName)

        // Create the file if it doesn't exist, returns true if created, false if already exists
        return file.createNewFile()
    }
}
