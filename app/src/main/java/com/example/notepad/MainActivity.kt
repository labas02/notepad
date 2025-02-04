package com.example.notepad

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.view.Gravity
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.HorizontalScrollView
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
    var pressStartTime = 0L
    var isHolding = false
    private lateinit var colorhandler: Handler


    private lateinit var dailyQuestGallery: LinearLayout
    private lateinit var weeklyQuestGallery: LinearLayout
    private lateinit var monthlyQuestGallery: LinearLayout
    private lateinit var yearlyQuestGallery: LinearLayout
    private lateinit var customQuestGallery: LinearLayout

    private lateinit var dailyTaskArray: MutableList<LinearLayout>
    private lateinit var weeklyTaskArray: MutableList<LinearLayout>
    private lateinit var monthlyTaskArray: MutableList<LinearLayout>
    private lateinit var yearlyTaskArray: MutableList<LinearLayout>
    private lateinit var customTaskArray: MutableList<LinearLayout>

    @SuppressLint("RtlHardcoded")

    fun reset_data() {
        dailyTaskArray = create_tasks("daily.csv")
        weeklyTaskArray = create_tasks("weekly.csv")

        monthlyTaskArray = create_tasks("monthly.csv")
        yearlyTaskArray = create_tasks("yearly.csv")
        customTaskArray = create_tasks("custom.csv")



        load_data()
    }


    @SuppressLint("RtlHardcoded")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fileOutputStream: FileOutputStream =
            this.openFileOutput("daily.csv", Context.MODE_APPEND)

        fileOutputStream.write("hello,fuck you".toByteArray())
        fileOutputStream.write(System.lineSeparator().toByteArray())


        fileOutputStream.close()


        dailyTaskArray = create_tasks("daily.csv")
        weeklyTaskArray = create_tasks("weekly.csv")
        monthlyTaskArray = create_tasks("monthly.csv")
        yearlyTaskArray = create_tasks("yearly.csv")
        customTaskArray = create_tasks("custom.csv")


        val constraintLayout = ConstraintLayout(this).apply {
            setBackgroundColor(Color.BLACK)

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

        val quest_layout = LinearLayout(this).apply {
            id = ConstraintLayout.generateViewId()
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT)
        }
        val daily_layout = HorizontalScrollView(this).apply {
            id = View.generateViewId()
            setBackgroundColor(Color.MAGENTA)
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
        }

        dailyQuestGallery = LinearLayout(this).apply {
            setBackgroundColor(Color.RED)
            id = View.generateViewId()
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

        }

        daily_layout.addView(dailyQuestGallery)

        val weekly_layout = HorizontalScrollView(this).apply {
            id = View.generateViewId()
            setBackgroundColor(Color.MAGENTA)
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
        }

        weeklyQuestGallery = LinearLayout(this).apply {
            setBackgroundColor(Color.RED)
            id = View.generateViewId()
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

        }

        weekly_layout.addView(weeklyQuestGallery)

        val monthly_layout = HorizontalScrollView(this).apply {
            id = View.generateViewId()
            setBackgroundColor(Color.MAGENTA)
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
        }



        monthlyQuestGallery = LinearLayout(this).apply {
            setBackgroundColor(Color.RED)
            id = View.generateViewId()
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

        }
        monthly_layout.addView(monthlyQuestGallery)


        val yearly_layout = HorizontalScrollView(this).apply {
            id = View.generateViewId()
            setBackgroundColor(Color.MAGENTA)
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
        }



        yearlyQuestGallery = LinearLayout(this).apply {
            setBackgroundColor(Color.RED)
            id = View.generateViewId()
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

        }

        yearly_layout.addView(yearlyQuestGallery)


        val custom_layout = HorizontalScrollView(this).apply {
            id = View.generateViewId()
            setBackgroundColor(Color.MAGENTA)
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
        }

        customQuestGallery = LinearLayout(this).apply {
            setBackgroundColor(Color.RED)
            id = View.generateViewId()
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

        }
        custom_layout.addView(customQuestGallery)


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
        load_data()


        bottom_menu.addView(bottom_button_left)
        bottom_menu.addView(bottom_button_center)
        bottom_menu.addView(bottom_button_right)


        // Add views to the ConstraintLayout
        constraintLayout.addView(topLayout)

        quest_layout.addView(daily_layout)
        quest_layout.addView(weekly_layout)
        quest_layout.addView(monthly_layout)
            quest_layout.addView(yearly_layout)
        quest_layout.addView(custom_layout)

        constraintLayout.addView(quest_layout)

        constraintLayout.addView(bottom_menu)

        // Define constraints
        val set = ConstraintSet()
        set.clone(constraintLayout)


// Top layout takes 20% height
        set.constrainPercentHeight(topLayout.id, 0.05f)
        set.connect(topLayout.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
        set.connect(topLayout.id, ConstraintSet.BOTTOM, daily_layout.id, ConstraintSet.TOP)
        set.connect(topLayout.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
        set.connect(topLayout.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)

      //quest layout
        set.connect(quest_layout.id,ConstraintLayout.LayoutParams.TOP,topLayout.id,ConstraintLayout.LayoutParams.BOTTOM)
        set.connect(quest_layout.id,ConstraintLayout.LayoutParams.BOTTOM,bottom_menu.id, ConstraintLayout.LayoutParams.BOTTOM)
        set.connect(quest_layout.id,ConstraintLayout.LayoutParams.START,ConstraintLayout.LayoutParams.PARENT_ID,ConstraintLayout.LayoutParams.START)
        set.connect(quest_layout.id,ConstraintLayout.LayoutParams.END,ConstraintLayout.LayoutParams.PARENT_ID,ConstraintLayout.LayoutParams.END)
        //bottom menu
        set.constrainPercentHeight(bottom_menu.id,0.08f)
        set.connect(bottom_menu.id, ConstraintSet.TOP, custom_layout.id, ConstraintSet.BOTTOM)
        set.connect(
            bottom_menu.id,
            ConstraintSet.BOTTOM,
            ConstraintSet.PARENT_ID,
            ConstraintSet.BOTTOM
        )
        set.connect(
            bottom_menu.id,
            ConstraintSet.START,
            ConstraintSet.PARENT_ID,
            ConstraintSet.START
        )
        set.connect(bottom_menu.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)

        set.applyTo(constraintLayout)


        // Set the layout as the content view
        setContentView(constraintLayout)

    }


    private fun load_data() {

        dailyQuestGallery.removeAllViews()
        for (i in range(0, dailyTaskArray.size)) {
            dailyQuestGallery.addView(dailyTaskArray[i])
            println("added new")
        }
        weeklyQuestGallery.removeAllViews()
        for (i in range(0, weeklyTaskArray.size)) {
            weeklyQuestGallery.addView(weeklyTaskArray[i])
            println("added new")
        }
        monthlyQuestGallery.removeAllViews()
        for (i in range(0, monthlyTaskArray.size)) {
            monthlyQuestGallery.addView(monthlyTaskArray[i])
            println("added new")
        }
        yearlyQuestGallery.removeAllViews()
        for (i in range(0, yearlyTaskArray.size)) {
            yearlyQuestGallery.addView(yearlyTaskArray[i])
            println("added new")
        }
        customQuestGallery.removeAllViews()
        for (i in range(0, customTaskArray.size)) {
            customQuestGallery.addView(customTaskArray[i])
            println("added new")
        }

    }

    private fun create_tasks(which_task: String): MutableList<LinearLayout> {
        val tasks: MutableList<LinearLayout>
        val width_regulation = 4.0
        val height_regulation = 7.0

        tasks = create_set(width_regulation, height_regulation, which_task)
        println(tasks.toString())
        return tasks
    }


    @SuppressLint("SuspiciousIndentation", "ClickableViewAccessibility")
    fun create_set(
        widthRegulation: Double,
        heightRegulation: Double,
        data_type: String
    ): MutableList<LinearLayout> {
        var layouts = mutableListOf<LinearLayout>()
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
        println(data_length)
        for (i in range(0, data_length)) {
            layouts += LinearLayout(this).apply {
                setBackgroundColor(Color.GRAY)
                orientation = LinearLayout.VERTICAL
                layoutParams = LinearLayout.LayoutParams(
                    (resources.displayMetrics.widthPixels / widthRegulation).toInt(),
                    (resources.displayMetrics.heightPixels / heightRegulation).toInt()
                ).apply {
                    setMargins(20, 20, 20, 20)
                }
                colorhandler = Handler(Looper.getMainLooper())

                // Define the runnable for updating the hold duration and background color
                val holdRunnable = object : Runnable {
                    override fun run() {
                        if (isHolding) {
                            val holdDuration = System.currentTimeMillis() - pressStartTime
                            val progress = (holdDuration / 3000f).coerceIn(0f, 1f).let { easedProgress(it) }
                            val color = interpolateColor(Color.WHITE, Color.GREEN, progress)
                            setBackgroundColor(color)
                            colorhandler.postDelayed(this, 16) // Update every 16ms (~60fps)
                        }
                    }
                }

                setOnTouchListener { _, event ->
                    when (event.action) {
                        MotionEvent.ACTION_DOWN -> {
                            pressStartTime = System.currentTimeMillis()
                            isHolding = true
                            handler.post(holdRunnable)
                        }


                        MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                            setBackgroundColor(Color.WHITE)
                            handler.removeCallbacks(holdRunnable)
                            // Calculate and display the hold duration when released
                            if (isHolding) {
                                val holdDuration = SystemClock.elapsedRealtime() - pressStartTime
                                println(holdDuration)
                                isHolding = false

                            }
                        }
                    }
                    true
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

            }

        }

        return layouts
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
    private fun interpolateColor(colorStart: Int, colorEnd: Int, progress: Float): Int {
        val startRed = Color.red(colorStart)
        val startGreen = Color.green(colorStart)
        val startBlue = Color.blue(colorStart)

        val endRed = Color.red(colorEnd)
        val endGreen = Color.green(colorEnd)
        val endBlue = Color.blue(colorEnd)

        val red = (startRed + (endRed - startRed) * progress).toInt()
        val green = (startGreen + (endGreen - startGreen) * progress).toInt()
        val blue = (startBlue + (endBlue - startBlue) * progress).toInt()

        return Color.rgb(red, green, blue)
    }

    private fun easedProgress(t: Float): Float {
        return if (t < 0.5f) {
            2 * t * t // Ease-in
        } else {
            -1 + (4 - 2 * t) * t // Ease-out
        }
    }
}



