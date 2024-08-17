package presentation.history

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons.AutoMirrored.Filled
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import domain.history.model.HistoryDomainModel
import presentation.utils.LoaderShow
import presentation.viewmodel.HistoryViewModel
import utils.RequestState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(viewModel: HistoryViewModel, navController: NavController) {
  val allHistory by viewModel.allHistory.collectAsState()
  val scrollState = rememberScrollState()


  LaunchedEffect(key1 = true) {
    viewModel.getAllHistory()
  }

  Scaffold(
    modifier = Modifier.fillMaxSize(),
    topBar = {
      Surface(
        shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp),
      ) {
        CenterAlignedTopAppBar(
          colors = TopAppBarColors(
            containerColor = Color.LightGray,
            scrolledContainerColor = Color.LightGray,
            navigationIconContentColor = Color.Black,
            titleContentColor = Color.Black,
            actionIconContentColor = Color.Black
          ),
          title = {
            Text("History")
          },
          navigationIcon = {
            IconButton(
              onClick = {
                navController.navigateUp()
              }
            ) {
              Icon(imageVector = Filled.ArrowBack, contentDescription = null)
            }
          }
        )
      }
    },
  ) {
    when (allHistory) {
      is RequestState.Idle -> {
        LoaderShow()
      }

      is RequestState.Loading -> {
        LoaderShow()
      }

      is RequestState.Success -> {
        val data = allHistory as RequestState.Success
        if(data.data.isEmpty()){
          Column(
            modifier = Modifier.fillMaxSize().padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
          ){
            Text(
              text = "History is Empty",
              color = Color.Black,
              fontSize = 18.sp,
              fontWeight = FontWeight.Bold,
              maxLines = 1,
              overflow = TextOverflow.Ellipsis
            )
          }
        }else{
          HistoryScreenSuccessView(data.data, it, scrollState)
        }
      }

      is RequestState.Error -> {
        val error = allHistory as RequestState.Error
        Column(
          modifier = Modifier.fillMaxSize().padding(it),
          verticalArrangement = Arrangement.Center,
          horizontalAlignment = Alignment.CenterHorizontally
        ){
          Text(
            text = "${error.error.message}",
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
          )
        }
      }
    }
  }
}

@Composable
fun HistoryScreenSuccessView(
  data: List<HistoryDomainModel>,
  innerPaddingValues: PaddingValues,
  scrollState: ScrollState,
) {
  Column(
    modifier = Modifier.fillMaxSize().padding(innerPaddingValues).verticalScroll(scrollState)
  ) {
    Column(
      modifier = Modifier.fillMaxWidth()
    ) {
      data.forEach {
        HistoryItemView(it)
      }
    }
  }
}

@Composable
fun HistoryItemView(data: HistoryDomainModel) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .height(100.dp)
      .padding(horizontal = 15.dp, vertical = 10.dp),
    shape = RoundedCornerShape(12.dp),
    elevation = CardDefaults.cardElevation(5.dp),
    colors = CardDefaults.cardColors(
      containerColor = Color.White
    )
  ) {
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .padding(15.dp)
    ) {
      Column(
        modifier = Modifier.align(Alignment.TopStart)
      ) {
        Text(
          text = data.title,
          color = Color.Black,
          fontSize = 18.sp,
          fontWeight = FontWeight.Bold,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
          text = data.dateTime,
          color = Color.Black,
          fontSize = 16.sp,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis
        )
      }
    }
  }
}