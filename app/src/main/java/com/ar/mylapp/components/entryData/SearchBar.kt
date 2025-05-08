package com.ar.mylapp.components.entryData
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ar.mylapp.R
import com.ar.mylapp.ui.theme.GoldDark
import com.ar.mylapp.ui.theme.Gray
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.ar.mylapp.ui.theme.Black

@Preview
@Composable
fun SearchBarPreview() {
    val textFieldState = rememberTextFieldState()
    val searchResults = listOf("Result 1", "Result 2", "Result 3")
    val onSearch: (String) -> Unit = {}
    SearchBar(
        textFieldState = textFieldState,
        onSearch = onSearch,
        searchResults = searchResults
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    textFieldState: TextFieldState,
    onSearch: (String) -> Unit,
    searchResults: List<String>,
    modifier: Modifier = Modifier
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier
            .fillMaxSize()
            .semantics { isTraversalGroup = true }
    ) {
        SearchBar(
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.TopCenter)
                .semantics { traversalIndex = 0f },
            colors = SearchBarDefaults.colors(
                containerColor = Color.Transparent,
            ),
            inputField = {
                TextField(
                    value = textFieldState.text.toString(),
                    onValueChange = {
                        textFieldState.edit {
                            replace(0, length, it)
                        }
                    },
                    placeholder = {
                        Text(
                            "Buscar...",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = FontFamily(Font(R.font.patua_one_regular)),
                                fontWeight = FontWeight(400),
                                color = Gray
                            )
                        )
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.search),
                            contentDescription = "search",
                            tint = Gray,
                            modifier = Modifier.padding(start = 10.dp).size(24.dp)
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Gray,
                        unfocusedTextColor = Gray,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        cursorColor = GoldDark
                    ),
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.patua_one_regular)),
                        fontWeight = FontWeight(400),
                        color = Gray
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Black, shape = RoundedCornerShape(50.dp))
                        .clip(RoundedCornerShape(50.dp)) // Asegura que el contenido quede dentro
                        .border(
                            width = 4.dp,
                            color = GoldDark,
                            shape = RoundedCornerShape(50.dp)
                        )
                )
            },
            expanded = expanded,
            onExpandedChange = { expanded = it }
        ) {
            Column(Modifier.verticalScroll(rememberScrollState())) {
                searchResults.forEach { result ->
                    ListItem(
                        headlineContent = { Text(result) },
                        modifier = Modifier
                            .clickable {
                                textFieldState.edit {
                                    replace(0, length, result)
                                }
                                expanded = false
                            }
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}