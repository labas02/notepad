import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.DialogFragment

class CustomSizeDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        dialog.setOnShowListener { dialogInterface ->
            val dialogWindow = (dialogInterface as Dialog).window
            dialogWindow?.let {
                val layoutParams = it.attributes
                layoutParams.width = 1600 // Custom width in pixels
                layoutParams.height = 1600 // Custom height in pixels
                it.attributes = layoutParams
                it.setBackgroundDrawableResource(android.R.color.transparent) // Optional: transparent background
            }
        }

        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LinearLayout(requireContext()).apply {
            setBackgroundColor(Color.CYAN)
            orientation = LinearLayout.VERTICAL
            setPadding(16, 16, 16, 16)

            addView(TextView(context).apply {
                text = "Custom Size Dialog"
                textSize = 20f
                gravity = Gravity.CENTER
            })

            addView(Button(context).apply {
                text = "Close"
                setOnClickListener { dismiss() }
            })
        }
    }
}
