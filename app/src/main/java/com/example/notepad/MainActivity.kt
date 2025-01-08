
package com.example.notepad

import CustomSizeDialogFragment
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.res.AssetManager
import android.graphics.Color
import android.os.Bundle
import android.telephony.AccessNetworkConstants.GeranBand
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.PopupWindow
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")

    var daily_task_offset = 0
    var weekly_task_offset = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var daily_task_array = crete_tasks()
        var weekly_task_array = crete_tasks()

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

        val daily_layout = LinearLayout(this).apply {
            id = View.generateViewId()
            setBackgroundColor(Color.MAGENTA)
            setHorizontalGravity(Gravity.CENTER)
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
        }

        val daily_quest_gallery = LinearLayout(this).apply {
            setBackgroundColor(Color.BLACK)
            id = View.generateViewId()
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT)

        }

        val daily_left_arrow = LinearLayout(this).apply {
            setBackgroundColor(Color.BLUE)
            id = View.generateViewId()
            gravity = Gravity.START
            layoutParams = ConstraintLayout.LayoutParams(
                (context.resources.displayMetrics.widthPixels * 0.15).toInt(),
                ConstraintLayout.LayoutParams.MATCH_PARENT )

            setOnClickListener({
                this@MainActivity.move_gallery(1, daily_quest_gallery,daily_task_array,1)
            })

        }

        val daily_right_arrow = LinearLayout(this).apply {
            setBackgroundColor(Color.BLUE)
            id = View.generateViewId()
            gravity = Gravity.START
            layoutParams = ConstraintLayout.LayoutParams(
                (context.resources.displayMetrics.widthPixels * 0.15).toInt(),
                ConstraintLayout.LayoutParams.MATCH_PARENT )

            setOnClickListener {
                this@MainActivity.move_gallery(-1, daily_quest_gallery, daily_task_array,1)
            }

        }

        move_gallery(0,daily_quest_gallery,daily_task_array,1)

        daily_layout.addView(daily_left_arrow)
        daily_layout.addView(daily_quest_gallery)
        daily_layout.addView(daily_right_arrow)

        val weekly_layout = LinearLayout(this).apply {
            id = View.generateViewId()
            setBackgroundColor(Color.MAGENTA)
            setHorizontalGravity(Gravity.CENTER)
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
        }

        val weekly_quest_gallery = LinearLayout(this).apply {
            setBackgroundColor(Color.BLACK)
            id = View.generateViewId()
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT)

        }

        val weekly_left_arrow = LinearLayout(this).apply {
            setBackgroundColor(Color.BLUE)
            id = View.generateViewId()
            gravity = Gravity.START
            layoutParams = ConstraintLayout.LayoutParams(
                (context.resources.displayMetrics.widthPixels * 0.15).toInt(),
                ConstraintLayout.LayoutParams.MATCH_PARENT )

            setOnClickListener({
                this@MainActivity.move_gallery(1, weekly_quest_gallery,weekly_task_array,2)
            })

        }

        val weekly_right_arrow = LinearLayout(this).apply {
            setBackgroundColor(Color.BLUE)
            id = View.generateViewId()
            gravity = Gravity.START
            layoutParams = ConstraintLayout.LayoutParams(
                (context.resources.displayMetrics.widthPixels * 0.15).toInt(),
                ConstraintLayout.LayoutParams.MATCH_PARENT )

            setOnClickListener {
                this@MainActivity.move_gallery(-1, weekly_quest_gallery, weekly_task_array,2)
            }

        }



        move_gallery(0,weekly_quest_gallery,weekly_task_array,2)

        weekly_layout.addView(weekly_left_arrow)
        weekly_layout.addView(weekly_quest_gallery)
        weekly_layout.addView(weekly_right_arrow)

        val spacer = LinearLayout(this).apply {
            id = View.generateViewId()
            setBackgroundColor(Color.BLACK)
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
            )
        }


        val button_layout = ConstraintLayout(this).apply{
            id = View.generateViewId()
            setBackgroundColor(Color.BLACK)
            layoutParams = ConstraintLayout.LayoutParams(
                (resources.displayMetrics.widthPixels * 0.1f).toInt(),
                (resources.displayMetrics.heightPixels * 0.05f).toInt()
            )
            setOnClickListener {
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
            }
        }
topLayout.addView(button_layout)

        val bottom_menu = LinearLayout(this).apply {
            id = View.generateViewId()
            setBackgroundColor(Color.CYAN)
            layoutParams = LinearLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
            )
        }
        val bottom_button_center = LinearLayout(this).apply {
            id = View.generateViewId()
            gravity = Gravity.CENTER
            setBackgroundColor(Color.GREEN)
            layoutParams = LinearLayout.LayoutParams(
                (context.resources.displayMetrics.widthPixels * 0.33333).toInt(),
                LinearLayout.LayoutParams.MATCH_PARENT
            )
        }


        val bottom_button_left = LinearLayout(this).apply {
            id = View.generateViewId()
            gravity = Gravity.LEFT
            setBackgroundColor(Color.BLUE)
            layoutParams = LinearLayout.LayoutParams(
                (context.resources.displayMetrics.widthPixels*0.333333).toInt(),
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            setOnClickListener({
                val dialog = CustomSizeDialogFragment()
                dialog.show(supportFragmentManager, "CustomSizeBottomSheetDialog")

            })

        }
        val bottom_button_right = LinearLayout(this).apply {
            id = View.generateViewId()
            setBackgroundColor(Color.CYAN)
            gravity = Gravity.RIGHT
            layoutParams = LinearLayout.LayoutParams(
                (context.resources.displayMetrics.widthPixels*0.333333).toInt(),
                LinearLayout.LayoutParams.MATCH_PARENT
            )

        }

        bottom_menu.addView(bottom_button_left)
        bottom_menu.addView(bottom_button_center)
        bottom_menu.addView(bottom_button_right)



        // Add views to the ConstraintLayout
        constraintLayout.addView(topLayout)
        constraintLayout.addView(daily_layout)
        constraintLayout.addView(weekly_layout)
        constraintLayout.addView(spacer)
        constraintLayout.addView(bottom_menu)

        // Define constraints
        val set = ConstraintSet()
        set.clone(constraintLayout)


// Top layout takes 20% height
        set.constrainPercentHeight(topLayout.id, 0.05f)
        set.connect(topLayout.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
        set.connect(topLayout.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
        set.connect(topLayout.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
//daily layout
        set.constrainPercentHeight(daily_layout.id,0.20f)
        set.constrainPercentWidth(daily_layout.id,1f)
        set.connect(daily_layout.id,ConstraintSet.TOP,topLayout.id,ConstraintSet.BOTTOM)
        set.connect(daily_layout.id,ConstraintSet.START,ConstraintSet.PARENT_ID,ConstraintSet.START)
        set.connect(daily_layout.id,ConstraintSet.END,ConstraintSet.PARENT_ID,ConstraintSet.END)

//weekly layout
        set.constrainPercentHeight(weekly_layout.id,0.20f)
        set.constrainPercentWidth(weekly_layout.id,1f)
        set.connect(weekly_layout.id,ConstraintSet.TOP,daily_layout.id,ConstraintSet.BOTTOM)
        set.connect(weekly_layout.id,ConstraintSet.START,ConstraintSet.PARENT_ID,ConstraintSet.START)
        set.connect(weekly_layout.id,ConstraintSet.END,ConstraintSet.PARENT_ID,ConstraintSet.END)

        //spacer
        set.constrainPercentWidth(spacer.id,0.20f)
        set.constrainPercentHeight(spacer.id,0.6f)
        set.connect(spacer.id,ConstraintSet.TOP,weekly_layout.id,ConstraintSet.BOTTOM)
        set.connect(spacer.id,ConstraintSet.START,ConstraintSet.PARENT_ID,ConstraintSet.START)
        set.connect(spacer.id,ConstraintSet.END,ConstraintSet.PARENT_ID,ConstraintSet.END)
        // Bottom-left layout
        set.connect(bottom_menu.id, ConstraintSet.TOP, spacer.id, ConstraintSet.BOTTOM)
        set.connect(bottom_menu.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
        set.connect(bottom_menu.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
        set.connect(bottom_menu.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)
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

    private fun move_gallery(int: Int, quest_gallery:LinearLayout, task_array:MutableList<layouts_set>, which_array:Int) {
        quest_gallery.removeAllViews()
        when(which_array){
            1 ->{
                //daily_offset
                daily_task_offset += int
                val current_set = task_array[daily_task_offset]
                quest_gallery.addView(current_set.layout1)
                quest_gallery.addView(current_set.layout2)
                quest_gallery.addView(current_set.layout3)
            }
            2 ->{
                //weekly_offset
                weekly_task_offset += int
                val current_set = task_array[weekly_task_offset]
                quest_gallery.addView(current_set.layout1)
                quest_gallery.addView(current_set.layout2)
                quest_gallery.addView(current_set.layout3)
            }
        }

    }

    data class layouts_set(
        val layout1: LinearLayout,
        val layout2: LinearLayout,
        val layout3: LinearLayout
    )

    @SuppressLint("SetTextI18n", "SuspiciousIndentation")
    private fun crete_tasks(): MutableList<layouts_set> {
        val daily: MutableList<layouts_set>
        val width_regulation = 4.5
        val height_regulation = 7.8

            daily = create_set(width_regulation,height_regulation)
        return daily
    }

    @SuppressLint("SetTextI18n")
    fun create_set(widthRegulation: Double, heightRegulation: Double): MutableList<layouts_set> {
        val data = read_from_file() // Read all rows from the CSV file
        val dailySets = mutableListOf<layouts_set>() // List to store all daily layout sets

        for (row in data) {
            if (row.size < 3) continue // Skip rows with insufficient data

            val layouts = row.map { taskText ->
                LinearLayout(this).apply {
                    setBackgroundColor(Color.GRAY)
                    orientation = LinearLayout.VERTICAL
                    layoutParams = LinearLayout.LayoutParams(
                        (resources.displayMetrics.widthPixels / widthRegulation).toInt(),
                        (resources.displayMetrics.heightPixels / heightRegulation).toInt()
                    ).apply {
                        setMargins(20, 20, 20, 20)
                    }

                    addView(TextView(this@MainActivity).apply {
                        text = taskText
                        textSize = 35F
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                        layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            150
                        )
                    })

                    addView(CheckBox(this@MainActivity).apply {
                        text = "complete"
                        isChecked = false
                    })
                }
            }

            if (layouts.size >= 3) {
                dailySets.add(layouts_set(layouts[0], layouts[1], layouts[2]))
            }
        }

        return dailySets
    }


    fun createEmptyFile(fileName: String): Boolean {
        val file = File(fileName)

        // Create the file if it doesn't exist, returns true if created, false if already exists
        return file.createNewFile()
    }

    private fun read_from_file():MutableList<List<String>>{
        val assetManager: AssetManager = assets
        val inputStream = assetManager.open("daily.csv")
        val reader = BufferedReader(InputStreamReader(inputStream))
        val data = mutableListOf<List<String>>()

        reader.forEachLine { line ->
            val row = line.split(",") // Split the line by commas
            data.add(row)
        }
        reader.close()
    return data
    }
}
