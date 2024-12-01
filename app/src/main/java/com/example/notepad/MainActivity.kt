
package com.example.notepad

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create the parent FrameLayout
        val parentLayout = FrameLayout(this).apply {
            layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }

        // Create a vertical LinearLayout for content
        val contentLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(Color.BLACK)
            setPadding(16, 16, 16, 16)

            // Add TextView
            addView(TextView(this@MainActivity).apply {
                text = "Hello, Kotlin UI!"
                textSize = 24f
                setTextColor(Color.WHITE)
                setPadding(0, 0, 0, 16)
            })

            // Create and add EditText (customizable field)
            val editText = EditText(this@MainActivity).apply {
                hint = "Enter some text here"
                setPadding(0, 200, 0, 50)
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
            }
            addView(editText)

            // Load content from file and set it to EditText
            val fileContent = readFromFile(this@MainActivity, "myFile.txt")
            if (fileContent.isNotEmpty()) {
                editText.setText(fileContent)
            }

            // Create a button that will write to file
            val submitButton = Button(this@MainActivity).apply {
                text = "Submit"
                setBackgroundColor(Color.GRAY)
                setOnClickListener {
                    val content = editText.text.toString()
                    if (content.isNotEmpty()) {
                        writeToFile(this@MainActivity, "myFile.txt", content)
                    }
                }
            }
            addView(submitButton)
        }

        // Create the button bar to stay at the bottom
        val buttonBar = LinearLayout(this).apply {
            setBackgroundColor(Color.parseColor("#880090"))
            orientation = LinearLayout.VERTICAL
            layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = Gravity.BOTTOM
            }

            // Add a button to the bar (not doing anything for now)
            addView(Button(this@MainActivity).apply {
                text = "Don't Click Me"
                layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            })
        }

        // Add content and button bar to the parent FrameLayout
        parentLayout.addView(contentLayout)
        parentLayout.addView(buttonBar)

        // Set the parent layout as the content view
        setContentView(parentLayout)
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
}
