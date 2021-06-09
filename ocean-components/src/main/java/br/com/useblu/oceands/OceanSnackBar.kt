package br.com.useblu.oceands

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import com.google.android.material.snackbar.Snackbar

class OceanSnackBar(
    private val container: View,
    private val message: String,
    private val type: OceanSnackBarType
) {
    fun show() {
        Snackbar.make(container, message, Snackbar.LENGTH_LONG).apply {

            this.duration = 5000
            this.view.background =
                ContextCompat.getDrawable(context, (R.drawable.ocean_snackbar_background))
            this.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text).apply {
                when (type) {
                    is OceanSnackBarType.Information -> {
                        setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.icon_information_snackbar,
                            0,
                            0,
                            0
                        )
                    }
                    is OceanSnackBarType.Error -> {
                        setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.icon_error_snackbar,
                            0,
                            0,
                            0
                        )
                    }
                    is OceanSnackBarType.Success -> {
                        setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.icon_sucess_snackbar,
                            0,
                            0,
                            0
                        )
                    }
                    is OceanSnackBarType.Warning -> {
                        setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.icon_warning_snackbar,
                            0,
                            0,
                            0
                        )
                    }
                }
                compoundDrawablePadding =
                    resources.getDimension(R.dimen.ocean_spacing_inline_xs).toInt()
                this.setTextColor(getColor(context, R.color.ocean_color_interface_light_pure))
            }

            show()
        }
    }

    sealed class OceanSnackBarType {
        object Information : OceanSnackBarType()
        object Error : OceanSnackBarType()
        object Success : OceanSnackBarType()
        object Warning : OceanSnackBarType()
    }
}