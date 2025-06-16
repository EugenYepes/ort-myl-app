package com.ar.mylapp.screens.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ar.mylapp.R
import com.ar.mylapp.components.buttons.Button1
import com.ar.mylapp.components.entryData.MyDropdownMenu
import com.ar.mylapp.viewmodel.AdvancedSearchViewModel
import com.ar.mylapp.viewmodel.TopBarViewModel

@Composable
fun AdvancedSearchScreen(
    viewModel: AdvancedSearchViewModel = hiltViewModel(),
    onFiltersApplied: (Map<String, List<Int>>) -> Unit,
    topBarViewModel: TopBarViewModel
) {
    val options = viewModel.dropdownOptions
    val isLoading = viewModel.isLoading
    val error = viewModel.error

    val title = stringResource(R.string.topbar_cards_title)
    val subtitle = stringResource(R.string.topbar_advancesearch_subtitle)
    LaunchedEffect(Unit) {
        topBarViewModel.setTopBar(title, subtitle)
        viewModel.loadOptions()
    }

    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }

    if (error != null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Error: $error", color = Color.Red)
        }
        return
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        options.forEach { (label, list) ->
            item {
                MyDropdownMenu(
                    label = label,
                    options = list.map { it.name },
                    selectedOptions = viewModel.selectedOptions[label]?.map { it.name } ?: emptyList(),
                    onOptionToggled = { name, isChecked ->
                        list.find { it.name == name }?.let {
                            viewModel.toggleOption(label, it, isChecked)
                        }
                    },
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            Button1(
                text = stringResource(R.string.button_search),
                onClick = {
                val filters = viewModel.selectedOptions.mapValues { it.value.map { it.id } }
                onFiltersApplied(filters)
            })
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
