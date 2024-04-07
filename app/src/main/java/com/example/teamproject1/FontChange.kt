package com.example.teamproject1

import android.app.AlertDialog
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat


object FontManager {
    private const val FONT_PREFS_KEY = "selectedFont"

    fun getSelectedFont(context: Context): String {
        val sharedPrefs = context.getSharedPreferences("FontPrefs", Context.MODE_PRIVATE)
        return sharedPrefs.getString(FONT_PREFS_KEY, "기본 폰트") ?: "기본 폰트"
    }

    fun setSelectedFont(context: Context, selectedFont: String) {
        val sharedPrefs = context.getSharedPreferences("FontPrefs", Context.MODE_PRIVATE)
        sharedPrefs.edit().putString(FONT_PREFS_KEY, selectedFont).apply()
    }
}

class FontChange(private val context: Context) {

    fun applyFontToTextView(selectedFont: String, view: View) {
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val childView = view.getChildAt(i)
                applyFontToTextView(selectedFont, childView)
            }
        } else if (view is TextView) { // ViewGroup이 아닌 경우에만 TextView일 때 폰트를 적용
            val fontResId = getFontResourceId(selectedFont)
            if (fontResId != 0) {
                val typeface = ResourcesCompat.getFont(context, fontResId)
                view.typeface = typeface
            }
        }
    }

    val bukk = context.getString(R.string.bukk)
    val snow = context.getString(R.string.snow)
    val chosun = context.getString(R.string.chosun)
    val ongle = context.getString(R.string.ongle)
    val fontchange = context.getString(R.string.fontchange)

    fun getFontResourceId(selectedFont: String): Int {


        return when (selectedFont) {
            "$bukk" -> R.font.bukk_font
            "$snow" -> R.font.sf_snow_font
            "$chosun" -> R.font.chosun_font
            "$ongle" -> R.font.ongle_font
            else -> 0
        }
    }

    fun showFontSelectionDialog(
        fontOptions: Array<String>, onFontSelected: (String) -> Unit) {
        AlertDialog.Builder(context)
            .setTitle("$fontchange")
            .setItems(fontOptions) { dialog, which ->
                val selectedFont = fontOptions[which]
                onFontSelected(selectedFont)
                dialog.dismiss()
            }
            .show()
    }
}
