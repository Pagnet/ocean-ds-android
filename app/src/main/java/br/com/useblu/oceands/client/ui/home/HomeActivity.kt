package br.com.useblu.oceands.client.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import br.com.useblu.oceands.*
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityHomeBinding
import br.com.useblu.oceands.client.ui.alert.AlertActivity
import br.com.useblu.oceands.client.ui.buttons.ButtonsActivity
import br.com.useblu.oceands.client.ui.input.InputActivity
import br.com.useblu.oceands.client.ui.listitem.ListItemActivity
import br.com.useblu.oceands.client.ui.toobar.TopbarActivity
import br.com.useblu.oceands.client.ui.typography.TypographyActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun onClickTypography(view: View) {
        val intent = Intent(this, TypographyActivity::class.java)
        startActivity(intent)
    }

    fun onClickButtons(view: View) {
        val intent = Intent(this, ButtonsActivity::class.java)
        startActivity(intent)
    }

    fun onClickInputs(view: View) {
        val intent = Intent(this, InputActivity::class.java)
        startActivity(intent)
    }

    fun onClickAlert(view: View) {
        val intent = Intent(this, AlertActivity::class.java)
        startActivity(intent)
    }

    fun topBarClick(view: View) {
        val intent = Intent(this, TopbarActivity::class.java)
        startActivity(intent)
    }


    fun listItems(view: View) {
        val intent = Intent(this, ListItemActivity::class.java)
        startActivity(intent)
    }

    fun onClickBottomSheetVertical(view: View) {
        OceanBottomSheet(this)
            .withTitle("Title")
            .withMessage("Message")
            .withIcon(R.drawable.icon_generic_black)
            .withOrientationButtons(OceanBottomSheet.Orientation.Vertical)
            .withDismiss(true)
            .withActionPositive(R.string.all_button_confirm) {
            }
            .withActionNegative(R.string.all_button_cancel) {
            }
            .show()
    }

    fun onClickBottomSheetHorizontal(view: View) {
        OceanBottomSheet(this)
            .withTitle("Title")
            .withMessage("Message")
            .withIcon(R.drawable.icon_generic_black)
            .withOrientationButtons(OceanBottomSheet.Orientation.Horizontal)
            .withDismiss(true)
            .withActionPositive(R.string.all_button_confirm) {
            }
            .withActionNegative(R.string.all_button_cancel) {
            }
            .show()
    }

    fun onClickBottomSheetCritical(view: View) {
        OceanBottomSheet(this)
            .withTitle("Title")
            .withMessage("Message")
            .withIcon(R.drawable.icon_generic_black)
            .withOrientationButtons(OceanBottomSheet.Orientation.Horizontal)
            .withDismiss(true)
            .withCritical(true)
            .withActionPositive(R.string.all_button_confirm) {
            }
            .withActionNegative(R.string.all_button_cancel) {
            }
            .show()
    }

    fun onOceanBottomListSheet(view: View) {
        val options = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")
        OceanBottomListSheet(this)
            .withTitle("Title")
            .withSimpleList(
                items = options,
                onItemSelect = {
                    Toast.makeText(
                        this,
                        "O Item selecionado foi \"${options[it]}\"",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            ).show()
    }

    fun onOceanBottomListSheetIcon(view: View) {
        val drawableIcon = ContextCompat.getDrawable(this, R.drawable.icon_generic_primary)!!
        val options = listOf(
            OceanBottomListSheetUIModel(drawableIcon, "Title 1", "description 1"),
            OceanBottomListSheetUIModel(drawableIcon, "Title 2", "description 2"),
            OceanBottomListSheetUIModel(drawableIcon, "Title 3", "description 3"),
        )
        OceanBottomListSheet(this)
            .withTitle("Title")
            .withCustomList(
                items = options,
                onItemSelect = {
                    Toast.makeText(
                        this,
                        "O Item selecionado foi \"${options[it].title}\"",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            ).show()
    }

    fun onOceanBottomListSheetWithSearch(view: View) {
        val options = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")
        OceanBottomListSheet(this)
            .withTitle("Title")
            .withHint("Hint Sample")
            .withSearch(
                manager = supportFragmentManager,
                limit = 3,
            )
            .withSimpleList(
                items = options,
                onItemSelect = {
                    Toast.makeText(
                        this,
                        "O Item selecionado foi \"${options[it]}\"",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            ).show()
    }

    fun onClickToast(view: View) {
        OceanToast(this)
            .withType(OceanToast.OceanToastType.Warning)
            .withMessage(R.string.message)
            .show()
    }

    fun onClickSnackBar(view: View) {
        OceanSnackBar(
            binding.container,
            getString(R.string.lorem_ipsum),
            OceanSnackBar.OceanSnackBarType.Error
        ).show()
    }

    fun onClickTooltip(view: View) {
        val message = getString(R.string.message)
        val tooltip = OceanTooltip(
            context = this
        ).withMessage(message)
            .build()

        tooltip.show(binding.tooltip)
        tooltip.dismissWithDelay(1000)
    }
}