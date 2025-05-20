package com.ar.mylapp.screens.guidebook

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ar.mylapp.components.entryData.MyDropdownMenu
import com.ar.mylapp.components.entryData.getDropdownData
import com.ar.mylapp.viewmodel.DropdownViewModel

@Composable
fun GuidebookScreen(
    navController: NavController,
    viewModel: DropdownViewModel = viewModel()
){
    val dropdownData = getDropdownData()

    Column(modifier = Modifier.padding(16.dp)) {
        dropdownData.forEach { (key, options) ->
            val selectedOptions = viewModel.getSelectedOptions(key)

            MyDropdownMenu(
                label = key.replaceFirstChar { it.uppercaseChar() },
                options = options,
                selectedOptions = selectedOptions,
                onOptionToggled = { option, isChecked ->
                    viewModel.onOptionToggled(key, option, isChecked)
                }
            )

            Spacer(modifier = Modifier.size(20.dp))
        }
    }
}