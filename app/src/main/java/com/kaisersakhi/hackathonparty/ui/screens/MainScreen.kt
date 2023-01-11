package com.kaisersakhi.hackathonparty.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kaisersakhi.hackathonparty.R
import com.kaisersakhi.hackathonparty.ui.components.OutletListItem
import com.kaisersakhi.hackathonparty.ui.theme.Purple700
import com.kaisersakhi.hackathonparty.viewmodels.MainScreenViewModel


@Composable
fun MainScreen(mViewModel: MainScreenViewModel = viewModel()) {

    val searchResponse by mViewModel.outlets.observeAsState()
    val context = LocalContext.current
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopSearchBar(onSearch = {
                mViewModel.search(it, context)
            })
        }
    ) {
        if (searchResponse == null)  {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Search The Happiness!",
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
            }
        }else if (searchResponse!!.outlets.isEmpty()){
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Couldn't Find The Happiness!\nTry Again.",
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    items(searchResponse!!.outlets) { outlet ->
                        OutletListItem(businessOutlet = outlet)
                    }
                })
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TopSearchBar(onSearch: (String) -> Unit) {

    var inputText by remember {
        mutableStateOf("")
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),

        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(.95f),
            shape = RoundedCornerShape(CornerSize(30.dp)),
            elevation = 10.dp,
//            backgroundColor = Purple700
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                TextField(
                    value = inputText,
                    onValueChange = {
                        inputText = it
                    },
                    shape = RoundedCornerShape(CornerSize(30.dp)),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(onSearch = {
                        onSearch.invoke(inputText)
                        keyboardController?.hide()
                    }),
                    modifier = Modifier.fillMaxWidth(.60f),
                    maxLines = 1
                )

                Spacer(modifier = Modifier.width(10.dp))

                Card(
                    modifier = Modifier
                        .clickable {
                            onSearch.invoke(inputText)
                            keyboardController?.hide()
                        }
                        .size(40.dp),
                    shape = CircleShape,
                    elevation = 10.dp
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.search),
                        contentDescription = "",
                        tint = Purple700,
                        modifier = Modifier
                            .size(28.dp)
                            .padding(8.dp)
                    )
                }
            }
        }
    }

}

