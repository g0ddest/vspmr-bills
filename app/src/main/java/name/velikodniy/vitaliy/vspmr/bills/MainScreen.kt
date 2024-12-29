package name.velikodniy.vitaliy.vspmr.bills

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun MainScreen(viewModel: BillsViewModel, navController: NavHostController) {

    val convocations = listOf("V", "VI", "VII")
    val selectedConvocation = remember { mutableStateOf(convocations.last()) }
    val bills by viewModel.bills.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchBills(selectedConvocation.value)
    }
    Column {
        ConvocationDropdownMenu(
            convocations = convocations,
            selectedConvocation = selectedConvocation.value,
            onConvocationSelected = { conv ->
                selectedConvocation.value = conv
                viewModel.fetchBills(conv)
            }
        )

        if (bills.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        } else {
            LazyColumn {
                items(bills) { bill ->
                    BillItem(bill) {
                        viewModel.onBillSelected(bill)
                        navController.navigate("details")
                    }
                }
            }
        }
    }
}
