package name.velikodniy.vitaliy.vspmr.bills

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavHost(
    navController: NavHostController,
    viewModel: BillsViewModel,
    contentPadding: PaddingValues
) {
    NavHost(
        navController,
        startDestination = "main",
        modifier = Modifier.padding(contentPadding)
    ) {
        composable("main") {
            MainScreen(viewModel, navController)
        }
        composable("details") {
            val bill by viewModel.selectedBill.collectAsState()
            BillDetailsScreen(bill)
        }
    }
}