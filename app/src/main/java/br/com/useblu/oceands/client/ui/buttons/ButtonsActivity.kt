package br.com.useblu.oceands.client.ui.buttons

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.R
import br.com.useblu.oceands.client.databinding.ActivityButtonsBinding
import com.savvyapps.togglebuttonlayout.Toggle
import com.savvyapps.togglebuttonlayout.ToggleButtonLayout


class ButtonsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityButtonsBinding
    private lateinit var viewModel: ButtonsViewModel

    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_buttons
        )
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this)[ButtonsViewModel::class.java]
        binding.viewmodel = viewModel

        this.initToggleButtons()

        this.initToggleListeners()
    }

    private fun initToggleButtons() {
        binding.toggleType.setToggled(R.id.toggle_type_primary, true)
        binding.toggleIconState.setToggled(R.id.toggle_icon_none, true)
        binding.toggleBlockedState.setToggled(R.id.toggle_width_default, true)
        binding.toggleState.setToggled(R.id.toggle_state_default, true)
        binding.toggleSize.setToggled(R.id.toggle_size_large, true)

        viewModel.setButtonType("Primary")
        viewModel.setIconEnabled(false)
        viewModel.setWidthStateBlocked(false)
        viewModel.setState("Default")
        viewModel.setButtonSize("Large")
    }

    private fun initToggleListeners() {
        binding.toggleType.onToggledListener =
            { _: ToggleButtonLayout, toggle: Toggle, _: Boolean ->
                viewModel.setButtonType(toggle.title.toString())
            }

        binding.toggleIconState.onToggledListener =
            { _: ToggleButtonLayout, toggle: Toggle, _: Boolean ->
                viewModel.setIconEnabled(toggle.id == R.id.toggle_icon_visible && toggle.isSelected)
            }

        binding.toggleBlockedState.onToggledListener =
            { _: ToggleButtonLayout, toggle: Toggle, _: Boolean ->
                viewModel.setWidthStateBlocked(toggle.id == R.id.toggle_width_blocked && toggle.isSelected)
            }

        binding.toggleState.onToggledListener =
            { _: ToggleButtonLayout, toggle: Toggle, _: Boolean ->
                viewModel.setState(toggle.title.toString())
            }

        binding.toggleSize.onToggledListener =
            { _: ToggleButtonLayout, toggle: Toggle, _: Boolean ->
                viewModel.setButtonSize(toggle.title.toString())
            }
    }

}

