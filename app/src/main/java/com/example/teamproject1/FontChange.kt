package com.example.teamproject1

import android.app.Activity
import android.app.AlertDialog
import android.app.Application
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


    fun getFontResourceId(selectedFont: String): Int {
        return when (selectedFont) {
            "부크크" -> R.font.bukk_font
            "스노우" -> R.font.sf_snow_font
            "조선체" -> R.font.chosun_font
            "온글잎" -> R.font.ongle_font
            else -> 0
        }
    }

    fun showFontSelectionDialog(
        fontOptions: Array<String>,
        onFontSelected: (String) -> Unit
    ) {
        AlertDialog.Builder(context)
            .setTitle("폰트 선택")
            .setItems(fontOptions) { dialog, which ->
                val selectedFont = fontOptions[which]
                onFontSelected(selectedFont)
                FontManager.getSelectedFont(context)
                dialog.dismiss()
            }
            .show()
    }
}
