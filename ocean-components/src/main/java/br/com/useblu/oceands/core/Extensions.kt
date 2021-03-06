package br.com.useblu.oceands.core

import android.content.Context
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

fun String.formatterDateBR(): String {
    val inFormat = SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy", Locale.US)
    val outFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val date = inFormat.parse(this)
    return date?.let { outFormat.format(it) } ?: ""
}

fun Date.oceanFormatDefault(): String = oceanFormat("dd/MM/yyyy")

fun Date.oceanFormat(pattern: String): String =
    SimpleDateFormat(pattern, Locale.getDefault()).format(this)

fun Double.oceanFormatWithCurrency() = FormatTypes.FORMAT_VALUE_WITH_SYMBOL.format(this.toString())

fun String.clearSpacing() = replace("\\s+".toRegex(), "").trim()

val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

val Float.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

fun Context.getSupportFragmentManager() = when (this) {
    is Fragment -> this.requireActivity().supportFragmentManager
    else -> (this as AppCompatActivity).supportFragmentManager
}
