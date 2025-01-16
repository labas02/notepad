package com.example.notepad

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import java.io.BufferedReader
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStreamReader
import java.util.stream.IntStream.range


class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")

    var dailyTaskOffset = 0
    private var weeklyTaskOffset = 0
    private var monthlyTaskOffset = 0
    private var yearlyTaskOffset = 0
    private var customTaskOffset = 0

    private lateinit var dailyQuestGallery: LinearLayout
    private lateinit var weeklyQuestGallery: LinearLayout
    private lateinit var monthlyQuestGallery: LinearLayout
    private lateinit var yearlyQuestGallery: LinearLayout
    private lateinit var customQuestGallery: LinearLayout

    private lateinit var dailyTaskArray: MutableList<layouts_set>
    private lateinit var weeklyTaskArray: MutableList<layouts_set>
    private lateinit var monthlyTaskArray: MutableList<layouts_set>
    private lateinit var yearlyTaskArray: MutableList<layouts_set>
    private lateinit var customTaskArray: MutableList<layouts_set>

    @SuppressLint("RtlHardcoded")

    fun reset_data() {
        dailyTaskArray = create_tasks("daily.csv")
        weeklyTaskArray = create_tasks("weekly.csv")

        monthlyTaskArray = create_tasks("monthly.csv")
        yearlyTaskArray = create_tasks("yearly.csv")
        customTaskArray = create_tasks("custom.csv")



        move_gallery(0, dailyQuestGallery, dailyTaskArray, 1)
        move_gallery(0, weeklyQuestGallery, weeklyTaskArray, 2)

              move_gallery(0,monthlyQuestGallery,monthlyTaskArray,3)
              move_gallery(0,yearlyQuestGallery,yearlyTaskArray,4)
              move_gallery(0,customQuestGallery,customTaskArray,5)



    }


    @SuppressLint("RtlHardcoded")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
/*
        val fileOutputStream: FileOutputStream = this.openFileOutput("custom.csv", Context.MODE_PRIVATE)

                fileOutputStream.write("hello,fuck you".toByteArray())
                fileOutputStream.write(System.lineSeparator().toByteArray())


        fileOutputStream.close()
*/

        dailyTaskArray = create_tasks("daily.csv")
        weeklyTaskArray = create_tasks("weekly.csv")
        monthlyTaskArray = create_tasks("monthly.csv")
        yearlyTaskArray = create_tasks("yearly.csv")
        customTaskArray = create_tasks("custom.csv")

        val constraintLayout = ConstraintLayout(this).apply {
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT
            )
        }

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



        dailyQuestGallery = LinearLayout(this).apply {
            setBackgroundColor(Color.BLACK)
            id = View.generateViewId()
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )

        }

        val daily_left_arrow = LinearLayout(this).apply {
            setBackgroundColor(Color.BLUE)
            id = View.generateViewId()
            gravity = Gravity.START
            layoutParams = ConstraintLayout.LayoutParams(
                (context.resources.displayMetrics.widthPixels * 0.15).toInt(),
                ConstraintLayout.LayoutParams.MATCH_PARENT
            )

            setOnClickListener {
                this@MainActivity.move_gallery(-1, dailyQuestGallery, dailyTaskArray, 1)
            }

        }

        val daily_right_arrow = LinearLayout(this).apply {
            setBackgroundColor(Color.BLUE)
            id = View.generateViewId()
            gravity = Gravity.START
            layoutParams = ConstraintLayout.LayoutParams(
                (context.resources.displayMetrics.widthPixels * 0.15).toInt(),
                ConstraintLayout.LayoutParams.MATCH_PARENT
            )

            setOnClickListener {
                this@MainActivity.move_gallery(1, dailyQuestGallery, dailyTaskArray, 1)
            }

        }

        move_gallery(0, dailyQuestGallery, dailyTaskArray, 1)

        daily_layout.addView(daily_left_arrow)
        daily_layout.addView(dailyQuestGallery)
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

        weeklyQuestGallery = LinearLayout(this).apply {
            setBackgroundColor(Color.BLACK)
            id = View.generateViewId()
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )

        }

        val weekly_left_arrow = LinearLayout(this).apply {
            setBackgroundColor(Color.BLUE)
            id = View.generateViewId()
            gravity = Gravity.START
            layoutParams = ConstraintLayout.LayoutParams(
                (context.resources.displayMetrics.widthPixels * 0.15).toInt(),
                ConstraintLayout.LayoutParams.MATCH_PARENT
            )

            setOnClickListener {
                this@MainActivity.move_gallery(-1, weeklyQuestGallery, weeklyTaskArray, 2)
            }

        }

        val weekly_right_arrow = LinearLayout(this).apply {
            setBackgroundColor(Color.BLUE)
            id = View.generateViewId()
            gravity = Gravity.START
            layoutParams = ConstraintLayout.LayoutParams(
                (context.resources.displayMetrics.widthPixels * 0.15).toInt(),
                ConstraintLayout.LayoutParams.MATCH_PARENT
            )

            setOnClickListener {
                this@MainActivity.move_gallery(1, weeklyQuestGallery, weeklyTaskArray, 2)
            }

        }



        move_gallery(0, weeklyQuestGallery, weeklyTaskArray, 2)

        weekly_layout.addView(weekly_left_arrow)
        weekly_layout.addView(weeklyQuestGallery)
        weekly_layout.addView(weekly_right_arrow)

        val monthly_layout = LinearLayout(this).apply {
            id = View.generateViewId()
            setBackgroundColor(Color.MAGENTA)
            setHorizontalGravity(Gravity.CENTER)
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
        }



        monthlyQuestGallery = LinearLayout(this).apply {
            setBackgroundColor(Color.BLACK)
            id = View.generateViewId()
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )

        }

        val monthly_left_arrow = LinearLayout(this).apply {
            setBackgroundColor(Color.BLUE)
            id = View.generateViewId()
            gravity = Gravity.START
            layoutParams = ConstraintLayout.LayoutParams(
                (context.resources.displayMetrics.widthPixels * 0.15).toInt(),
                ConstraintLayout.LayoutParams.MATCH_PARENT
            )

            setOnClickListener {
                this@MainActivity.move_gallery(-1, monthlyQuestGallery, monthlyTaskArray, 3)
            }

        }

        val monthly_right_arrow = LinearLayout(this).apply {
            setBackgroundColor(Color.BLUE)
            id = View.generateViewId()
            gravity = Gravity.START
            layoutParams = ConstraintLayout.LayoutParams(
                (context.resources.displayMetrics.widthPixels * 0.15).toInt(),
                ConstraintLayout.LayoutParams.MATCH_PARENT
            )

            setOnClickListener {
                this@MainActivity.move_gallery(1, monthlyQuestGallery, monthlyTaskArray, 3)
            }

        }

        move_gallery(0, monthlyQuestGallery, monthlyTaskArray, 3)

        monthly_layout.addView(monthly_left_arrow)
        monthly_layout.addView(monthlyQuestGallery)
        monthly_layout.addView(monthly_right_arrow)

        val yearly_layout = LinearLayout(this).apply {
            id = View.generateViewId()
            setBackgroundColor(Color.MAGENTA)
            setHorizontalGravity(Gravity.CENTER)
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
        }



        yearlyQuestGallery = LinearLayout(this).apply {
            setBackgroundColor(Color.BLACK)
            id = View.generateViewId()
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )

        }

        val yearly_left_arrow = LinearLayout(this).apply {
            setBackgroundColor(Color.BLUE)
            id = View.generateViewId()
            gravity = Gravity.START
            layoutParams = ConstraintLayout.LayoutParams(
                (context.resources.displayMetrics.widthPixels * 0.15).toInt(),
                ConstraintLayout.LayoutParams.MATCH_PARENT
            )

            setOnClickListener {
                this@MainActivity.move_gallery(-1, yearlyQuestGallery, yearlyTaskArray, 4)
            }

        }

        val yearly_right_arrow = LinearLayout(this).apply {
            setBackgroundColor(Color.BLUE)
            id = View.generateViewId()
            gravity = Gravity.START
            layoutParams = ConstraintLayout.LayoutParams(
                (context.resources.displayMetrics.widthPixels * 0.15).toInt(),
                ConstraintLayout.LayoutParams.MATCH_PARENT
            )

            setOnClickListener {
                this@MainActivity.move_gallery(1, yearlyQuestGallery, yearlyTaskArray, 4)
            }

        }

        move_gallery(0, yearlyQuestGallery, yearlyTaskArray, 4)

        yearly_layout.addView(yearly_left_arrow)
        yearly_layout.addView(yearlyQuestGallery)
        yearly_layout.addView(yearly_right_arrow)

        val custom_layout = LinearLayout(this).apply {
            id = View.generateViewId()
            setBackgroundColor(Color.MAGENTA)
            setHorizontalGravity(Gravity.CENTER)
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
        }



        customQuestGallery = LinearLayout(this).apply {
            setBackgroundColor(Color.BLACK)
            id = View.generateViewId()
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )

        }

        val custom_left_arrow = LinearLayout(this).apply {
            setBackgroundColor(Color.BLUE)
            id = View.generateViewId()
            gravity = Gravity.START
            layoutParams = ConstraintLayout.LayoutParams(
                (context.resources.displayMetrics.widthPixels * 0.15).toInt(),
                ConstraintLayout.LayoutParams.MATCH_PARENT
            )

            setOnClickListener {
                this@MainActivity.move_gallery(-1, customQuestGallery, customTaskArray, 5)
            }

        }

        val custom_right_arrow = LinearLayout(this).apply {
            setBackgroundColor(Color.BLUE)
            id = View.generateViewId()
            gravity = Gravity.START
            layoutParams = ConstraintLayout.LayoutParams(
                (context.resources.displayMetrics.widthPixels * 0.15).toInt(),
                ConstraintLayout.LayoutParams.MATCH_PARENT
            )

            setOnClickListener {
                this@MainActivity.move_gallery(1, customQuestGallery, customTaskArray, 5)
            }

        }

        move_gallery(0, customQuestGallery, customTaskArray, 5)

        custom_layout.addView(custom_left_arrow)
        custom_layout.addView(customQuestGallery)
        custom_layout.addView(custom_right_arrow)

        LinearLayout(this).apply {
            id = View.generateViewId()
            setBackgroundColor(Color.BLACK)
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
            )
        }


        val button_layout = ConstraintLayout(this).apply {
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
                (context.resources.displayMetrics.widthPixels * 0.333333).toInt(),
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            setOnClickListener {
                val dialog = Create_task()
                dialog.setOnDismissFunction { reset_data() }
                dialog.show(supportFragmentManager, "CustomSizeBottomSheetDialog")

            }

        }

        val bottom_button_right = LinearLayout(this).apply {
            id = View.generateViewId()
            setBackgroundColor(Color.CYAN)
            gravity = Gravity.RIGHT
            layoutParams = LinearLayout.LayoutParams(
                (context.resources.displayMetrics.widthPixels * 0.333333).toInt(),
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
        constraintLayout.addView(monthly_layout)
        constraintLayout.addView(yearly_layout)
        constraintLayout.addView(custom_layout)
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
        set.constrainPercentHeight(daily_layout.id, 0.20f)
        set.constrainPercentWidth(daily_layout.id, 1f)
        set.connect(daily_layout.id, ConstraintSet.TOP, topLayout.id, ConstraintSet.BOTTOM)
        set.connect(
            daily_layout.id,
            ConstraintSet.START,
            ConstraintSet.PARENT_ID,
            ConstraintSet.START
        )
        set.connect(daily_layout.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)

//weekly layout
        set.constrainPercentHeight(weekly_layout.id, 0.20f)
        set.constrainPercentWidth(weekly_layout.id, 1f)
        set.connect(weekly_layout.id, ConstraintSet.TOP, daily_layout.id, ConstraintSet.BOTTOM)
        set.connect(
            weekly_layout.id,
            ConstraintSet.START,
            ConstraintSet.PARENT_ID,
            ConstraintSet.START
        )
        set.connect(weekly_layout.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
        //monthly
        set.constrainPercentHeight(monthly_layout.id, 0.20f)
        set.constrainPercentWidth(monthly_layout.id, 1f)
        set.connect(monthly_layout.id, ConstraintSet.TOP, weekly_layout.id, ConstraintSet.BOTTOM)
        set.connect(
            monthly_layout.id,
            ConstraintSet.START,
            ConstraintSet.PARENT_ID,
            ConstraintSet.START
        )
        set.connect(monthly_layout.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
        //yearly
        set.constrainPercentHeight(yearly_layout.id, 0.20f)
        set.constrainPercentWidth(yearly_layout.id, 1f)
        set.connect(yearly_layout.id, ConstraintSet.TOP, monthly_layout.id, ConstraintSet.BOTTOM)
        set.connect(
            yearly_layout.id,
            ConstraintSet.START,
            ConstraintSet.PARENT_ID,
            ConstraintSet.START
        )
        set.connect(yearly_layout.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
        //custom
        set.constrainPercentHeight(custom_layout.id, 0.20f)
        set.constrainPercentWidth(custom_layout.id, 1f)
        set.connect(custom_layout.id, ConstraintSet.TOP, yearly_layout.id, ConstraintSet.BOTTOM)
        set.connect(
            custom_layout.id,
            ConstraintSet.START,
            ConstraintSet.PARENT_ID,
            ConstraintSet.START
        )
        set.connect(custom_layout.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)

        // Bottom layout
        set.connect(bottom_menu.id, ConstraintSet.TOP, custom_layout.id, ConstraintSet.BOTTOM)
        set.connect(
            bottom_menu.id,
            ConstraintSet.START,
            ConstraintSet.PARENT_ID,
            ConstraintSet.START
        )
        set.connect(bottom_menu.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
        set.connect(
            bottom_menu.id,
            ConstraintSet.BOTTOM,
            ConstraintSet.PARENT_ID,
            ConstraintSet.BOTTOM
        )
        //set.constrainPercentWidth(bottomLeftLayout.id, 0.5f) // Half the width


        // button menu
        //set.constrainPercentWidth(button_layout.id,0.2f)
        set.connect(
            button_layout.id,
            ConstraintSet.START,
            ConstraintSet.PARENT_ID,
            ConstraintSet.START
        )
        set.connect(button_layout.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
        set.connect(button_layout.id, ConstraintSet.BOTTOM, daily_layout.id, ConstraintSet.TOP)
        set.connect(button_layout.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)

        // Apply constraints
        set.applyTo(constraintLayout)

        // Set the layout as the content view
        setContentView(constraintLayout)

    }


    private fun move_gallery(
        int: Int,
        quest_gallery: LinearLayout,
        task_array: MutableList<layouts_set>,
        which_array: Int
    ) {
        println("moved")
        quest_gallery.removeAllViews()
        when (which_array) {
            1 -> {
                //daily_offset
                dailyTaskOffset += int
                if (dailyTaskOffset > task_array.size-1) {
                    dailyTaskOffset = 0
                }
                if (dailyTaskOffset <= 0) {
                    dailyTaskOffset = task_array.size-1
                }
                val current_set = task_array[dailyTaskOffset]
                quest_gallery.addView(current_set.layout1)
                quest_gallery.addView(current_set.layout2)
                quest_gallery.addView(current_set.layout3)
            }

            2 -> {
                //weekly_offset
                weeklyTaskOffset += int
                if (weeklyTaskOffset > task_array.size-1) {
                    weeklyTaskOffset = 0
                }
                if (weeklyTaskOffset < 0) {
                    weeklyTaskOffset = task_array.size-1
                }
                val current_set = task_array[weeklyTaskOffset]
                quest_gallery.addView(current_set.layout1)
                quest_gallery.addView(current_set.layout2)
                quest_gallery.addView(current_set.layout3)

            }
            3 -> {
                monthlyTaskOffset += int
                if (monthlyTaskOffset > task_array.size-1) {
                    monthlyTaskOffset = 0
                }
                if (monthlyTaskOffset < 0) {
                    monthlyTaskOffset = task_array.size-1
                }
                val current_set = task_array[monthlyTaskOffset]
                quest_gallery.addView(current_set.layout1)
                quest_gallery.addView(current_set.layout2)
                quest_gallery.addView(current_set.layout3)

            }
            4 -> {
                //weekly_offset
                yearlyTaskOffset += int
                if (yearlyTaskOffset > task_array.size-1) {
                    yearlyTaskOffset = 0
                }
                if (yearlyTaskOffset < 0) {
                    yearlyTaskOffset = task_array.size-1
                }
                val current_set = task_array[yearlyTaskOffset]
                quest_gallery.addView(current_set.layout1)
                quest_gallery.addView(current_set.layout2)
                quest_gallery.addView(current_set.layout3)

            }
            5 -> {
                customTaskOffset += int
                if (customTaskOffset > task_array.size-1) {
                    customTaskOffset = 0
                }
                if (customTaskOffset < 0) {
                    customTaskOffset = task_array.size-1
                }
                val current_set = task_array[customTaskOffset]
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

    private fun create_tasks(which_task: String): MutableList<layouts_set> {
        val tasks: MutableList<layouts_set>
        val width_regulation = 4.5
        val height_regulation = 7.8

        tasks = create_set(width_regulation, height_regulation, which_task)
        return tasks
    }


    @SuppressLint("SuspiciousIndentation")
    fun create_set(
        widthRegulation: Double,
        heightRegulation: Double,
        data_type: String
    ): MutableList<layouts_set> {
        var layouts = emptyArray<LinearLayout>()
        var data = read_from_file(data_type)
        if (data.isNullOrEmpty()) {
            println("is empty")
            val tmp_data = ""
            try {
                val fileOutputStream: FileOutputStream =
                    this.openFileOutput(data_type, Context.MODE_PRIVATE)
                fileOutputStream.write(tmp_data.toByteArray())
                fileOutputStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            data = read_from_file(data_type)
        }

        var data_length = data.size
        val sets = mutableListOf<layouts_set>() // List to store all daily layout sets
        while (data_length % 3 != 0) {
            data_length += 1
        }
        println(data_length)
        for (i in range(0, data_length)) {
            if (i < data.size) {
                layouts += LinearLayout(this).apply {
                    setBackgroundColor(Color.GRAY)
                    orientation = LinearLayout.VERTICAL
                    layoutParams = LinearLayout.LayoutParams(
                        (resources.displayMetrics.widthPixels / widthRegulation).toInt(),
                        (resources.displayMetrics.heightPixels / heightRegulation).toInt()
                    ).apply {
                        setMargins(20, 20, 20, 20)
                    }

                    addView(
                        RelativeLayout(this@MainActivity).apply {
                            layoutParams = LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                150
                            )

                            addView(TextView(this@MainActivity).apply {
                                text = data[i][0]
                                layoutParams = RelativeLayout.LayoutParams(
                                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                                    RelativeLayout.LayoutParams.WRAP_CONTENT
                                ).apply {
                                    addRule(RelativeLayout.ALIGN_PARENT_TOP)
                                    addRule(RelativeLayout.ALIGN_PARENT_START)
                                }
                            })

                            addView(TextView(this@MainActivity).apply {
                                text = "X"
                                textSize = 25f
                                layoutParams = RelativeLayout.LayoutParams(
                                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                                    RelativeLayout.LayoutParams.WRAP_CONTENT
                                ).apply {
                                    addRule(RelativeLayout.ALIGN_PARENT_TOP)
                                    addRule(RelativeLayout.ALIGN_PARENT_END)
                                }
                                setOnClickListener {
                                    val dialog = Delete_task(data, data_type, i)
                                    dialog.setOnDismissFunction {
                                        reset_data()
                                    }
                                    dialog.show(
                                        supportFragmentManager,
                                        "CustomSizeBottomSheetDialog"
                                    )
                                }
                            })
                        })


                    val check_box = CheckBox(this@MainActivity).apply {
                        text = "complete"
                        isChecked = false
                    }
                }

                if (layouts.size >= 3) {
                    sets.add(layouts_set(layouts[0], layouts[1], layouts[2]))
                    layouts = emptyArray<LinearLayout>()
                }
            } else {
                layouts += LinearLayout(this).apply {
                    setBackgroundColor(Color.GRAY)
                    orientation = LinearLayout.VERTICAL
                    layoutParams = LinearLayout.LayoutParams(
                        (resources.displayMetrics.widthPixels / widthRegulation).toInt(),
                        (resources.displayMetrics.heightPixels / heightRegulation).toInt()
                    ).apply {
                        setMargins(20, 20, 20, 20)
                    }

                    addView(TextView(this@MainActivity).apply {
                        text = ""
                        textSize = 35F
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                        layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            150
                        )
                    })

                    addView(CheckBox(this@MainActivity).apply {
                        text = ""
                        isChecked = false
                    })
                }

                if (layouts.size >= 3) {
                    sets.add(layouts_set(layouts[0], layouts[1], layouts[2]))
                    layouts = emptyArray<LinearLayout>()
                }
            }
        }

        return sets
    }


    private fun read_from_file(file: String): MutableList<List<String>> {

        if (!this.getFileStreamPath(file).exists()) {
            val data = ""
            try {
                val fileOutputStream: FileOutputStream =
                    this.openFileOutput(file, Context.MODE_PRIVATE)
                fileOutputStream.write(data.toByteArray())
                fileOutputStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        val data = mutableListOf<List<String>>()

        try {
            // Open the file for reading
            this.openFileInput(file).use { inputStream ->
                val reader = BufferedReader(InputStreamReader(inputStream))

                // Read each line and split by commas
                reader.forEachLine { line ->
                    val row = line.split(",")  // Split the line by commas
                    data.add(row)
                }

                // Automatically closes the reader and input stream
                reader.close()
            }

            println("Data successfully read from internal storage.")

        } catch (e: Exception) {
            e.printStackTrace()
            println("Error reading from file.")
        }
        println(data.toString())
        return data

    }

}
