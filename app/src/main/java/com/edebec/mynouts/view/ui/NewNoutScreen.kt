package com.edebec.mynouts.view.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.edebec.mynouts.R
import com.edebec.mynouts.ui.theme.MyNoutsTheme
import com.edebec.mynouts.view.ui.model.Category
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewNoutScreen(modifier: Modifier = Modifier, navHostController: NavHostController, noutId: Int) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBarDefault(navHostController = navHostController, noutId = noutId)
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                NoutTitle(title = ""/*TODO(get value from Model in the viewModel)*/) {}
                NoutDescription(description = ""/*TODO(get value from Model in the viewModel)*/) {}
                CategoryListItem(categories = emptyList()/*TODO(get value from Model in the viewModel)*/)
            }
            Button(
                onClick = { /*TODO(save nout)*/ },
                enabled = false/*TODO(Validate in viewModel method)*/,
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(text = stringResource(id = R.string.newNout_screen_save_nout_button))
            }
        }
    }
}

@Composable
fun NoutTitle(modifier: Modifier = Modifier, title: String, onTextChanged: (String) -> Unit) {
    CustomTextField(
        value = title,
        onValueChange = { onTextChanged(it) },
        modifier = modifier.fillMaxWidth(),
        textStyle = MaterialTheme.typography.headlineLarge.copy(color = MaterialTheme.colorScheme.primary),
        placeholder = {
            Text(
                text = stringResource(id = R.string.newNout_screen_title_nout_hint),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.outlineVariant,
                modifier = Modifier.fillMaxWidth()
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = LocalTextStyle.current,
    placeholder: @Composable (() -> Unit)? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }

    BasicTextField(
        value = value,
        modifier = modifier
            .defaultMinSize(
                minWidth = TextFieldDefaults.MinWidth,
                minHeight = 8.dp
            ),
        onValueChange = onValueChange,
        cursorBrush = SolidColor(textStyle.color),
        textStyle = textStyle,
        interactionSource = interactionSource,
        decorationBox = @Composable { innerTextField ->
            TextFieldDefaults.DecorationBox(
                value = value,
                visualTransformation = VisualTransformation.None,
                innerTextField = innerTextField,
                placeholder = placeholder,
                interactionSource = interactionSource,
                enabled = true,
                singleLine = false,
                contentPadding = PaddingValues(0.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                )
            )
        }
    )

}

@Composable
fun NoutDescription(modifier: Modifier = Modifier, description: String, onTextChanged: (String) -> Unit) {
    CustomTextField(
        value = description,
        onValueChange = { onTextChanged(it) },
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        textStyle = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurface),
        placeholder = {
            Text(
                text = stringResource(id = R.string.newNout_screen_description_nout_hint),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.surfaceVariant,
                modifier = Modifier.fillMaxWidth()
            )
        }
    )
}

@Composable
fun CategoryListItem(modifier: Modifier = Modifier, categories: List<Category>) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        AddCategoryButton()
        LazyRow(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            items(categories, key = { it.id }) { category ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(MaterialTheme.shapes.extraLarge)
                        .background(category.containerColor)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(text = category.name, style = MaterialTheme.typography.labelMedium, color = category.textColor)
                }
            }
        }
    }
}

@Composable
fun AddCategoryButton(modifier: Modifier = Modifier) {
    var isDDExpanded by rememberSaveable { mutableStateOf(false) }

    Column {
        TextButton(
            onClick = { isDDExpanded = true },
            colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.tertiary),
            modifier = modifier
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(painter = painterResource(id = R.drawable.category), contentDescription = "Circle")
                Text(
                    text = stringResource(id = R.string.newNout_screen_add_category_button),
                    modifier = Modifier.padding(start = 8.dp),
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }

        ShowCategoriesDropDown(isExpanded = isDDExpanded, onDismiss = { isDDExpanded = false }, categories = emptyList())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarDefault(modifier: Modifier = Modifier, navHostController: NavHostController, noutId: Int) {
    var showDeleteDialog by rememberSaveable { mutableStateOf(false) }

    TopAppBar(title = { }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent), navigationIcon = {
        IconButton(onClick = { navHostController.popBackStack() }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }, actions = {
        if (noutId != 0) {
            IconButton(onClick = {
                showDeleteDialog = true
            }) {
                Icon(imageVector = Icons.Outlined.Delete, contentDescription = "Delete")
            }
        }
    }, modifier = modifier)

    ShowDeleteDialog(showDialog = showDeleteDialog, onConfirm = { /*TODO(delete nout in viewModel)*/ }, onDismiss = { showDeleteDialog = false })
}

@Composable
fun ShowDeleteDialog(showDialog: Boolean, onConfirm: () -> Unit, onDismiss: () -> Unit) {
    if (showDialog) {
        AlertDialog(
            title = {
                Text(text = stringResource(id = R.string.newNout_screen_delete_nout_dialog_title))
            },
            text = {
                Text(text = stringResource(id = R.string.newNout_screen_delete_nout_dialog_message))
            }, dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text(text = stringResource(id = R.string.action_button_cancel))
                }
            }, confirmButton = {
                TextButton(onClick = { onConfirm() }) {
                    Text(text = stringResource(id = R.string.action_button_yes))
                }
            },
            onDismissRequest = { onDismiss() }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowCategoriesDropDown(modifier: Modifier = Modifier, isExpanded: Boolean, onDismiss: () -> Unit, categories: List<Category>) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }

    Column(modifier = modifier) {
        DropdownMenu(expanded = isExpanded, onDismissRequest = onDismiss) {
            DropdownMenuItem(text = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                    Text(text = stringResource(id = R.string.newNout_screen_add_category_menu_option), modifier = Modifier.padding(start = 8.dp))
                }
            }, onClick = {
                onDismiss()
                showBottomSheet = true
            })
            categories.forEach { category ->
                DropdownMenuItem(
                    text = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(MaterialTheme.shapes.extraLarge)
                                    .background(category.containerColor)
                            )
                            Text(text = category.name, modifier = Modifier.padding(start = 8.dp))
                        }
                    },
                    onClick = { /*TODO*/ }
                )
            }
        }
    }

    if (showBottomSheet) {
        ShowSheetAddCategory(sheetState = sheetState, onDismiss = {
            scope.launch { sheetState.hide() }.invokeOnCompletion {
                if (!sheetState.isVisible) {
                    showBottomSheet = false
                }
            }
        })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SheetPreview() {
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }

    MyNoutsTheme {
        Button(onClick = { showBottomSheet = true }) {

        }

        if (showBottomSheet) {
            ShowSheetAddCategory(sheetState = sheetState, onDismiss = { showBottomSheet = false })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowSheetAddCategory(modifier: Modifier = Modifier, sheetState: SheetState, onDismiss: () -> Unit) {
    ModalBottomSheet(onDismissRequest = onDismiss, sheetState = sheetState, modifier = modifier.padding(6.dp)) {
        var categoryName by rememberSaveable { mutableStateOf("") }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 56.dp)
        ) {
            CustomTextField(
                value = categoryName,
                onValueChange = { categoryName = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                textStyle = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.onSurface),
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.newNout_screen_add_category_title_hint),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            )
            Text(
                text = stringResource(id = R.string.newNout_screen_add_category_color_hint),
                style = MaterialTheme.typography.bodySmall,
                color = if (categoryName.isEmpty()) MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            LazyRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(CATEGORY_COLORS) { color ->
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(MaterialTheme.shapes.extraLarge)
                            .background(color)
                            .clickable(
                                enabled = categoryName.isNotBlank(),
                                onClick = {
                                    /*TODO(save category)*/
                                    onDismiss()
                                }
                            )
                    )
                }
            }
        }
    }
}

val CATEGORY_COLORS = listOf(
    Color(0xFF000000),
    Color(0xFF00DF00),
    Color(0xFF000000),
    Color(0xFF00DF00)
)