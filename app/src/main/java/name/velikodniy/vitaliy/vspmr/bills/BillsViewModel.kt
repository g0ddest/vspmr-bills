package name.velikodniy.vitaliy.vspmr.bills

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BillsViewModel @Inject constructor(
    private val api: BillsApi
) : ViewModel() {

    private val _bills = MutableStateFlow<List<Bill>>(emptyList())
    val bills: StateFlow<List<Bill>> = _bills

    private val _selectedBill = MutableStateFlow<BillDetails?>(null)
    val selectedBill: StateFlow<BillDetails?> = _selectedBill

    fun fetchBills(conv: String) {
        _bills.value = emptyList()
        viewModelScope.launch {
            try {
                val response = api.getBills(conv)
                _bills.value = response
                    .sortedWith(compareByDescending { it.number.extractNumber() })
//                    .take(15)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun fetchBillDetails(conv: String, number: String) {
        viewModelScope.launch {
            try {
                val response = api.getBillDetails(conv, number)
                _selectedBill.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun onBillSelected(bill: Bill) {
        fetchBillDetails(bill.conv, bill.number)
    }

    private fun String.extractNumber(): Int {
        val regex = Regex("""^\d+""")
        val matchResult = regex.find(this)
        return matchResult?.value?.toIntOrNull() ?: 0
    }
}