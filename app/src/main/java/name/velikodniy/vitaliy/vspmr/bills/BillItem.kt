package name.velikodniy.vitaliy.vspmr.bills

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BillItem(bill: Bill, onClick: () -> Unit) {
    Card(modifier = Modifier
        .padding(8.dp)
        .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        elevation = 1.dp) {
        Column(modifier = Modifier.padding(16.dp)
        ) {
            Text(text = bill.number, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = colorResource(id = R.color.colorText))
            Spacer(modifier = Modifier.padding(vertical = 7.dp))
            Text(text = bill.name, fontWeight = FontWeight.Medium, color = colorResource(id = R.color.colorText))
            Spacer(modifier = Modifier.padding(vertical = 7.dp))
            if (bill.date != "") {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_calendar_month_24),
                        contentDescription = "Calendar Icon",
                        modifier = Modifier.padding(end = 5.dp)
                    )
                    Text(
                        text = bill.date,
                        fontWeight = FontWeight.Medium,
                        color = colorResource(id = R.color.colorAdditionalText)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBillItem() {
    val sampleBill = Bill(
        number = "999",
        conv = "VII",
        name = "Проект закона Приднестровской Молдавской Республики «О внесении изменения и дополнения в Закон Приднестровской Молдавской Республики «Об образовании»",
        url = "http://www.vspmr.org/legislation/bills/vii-soziv/999.html",
        date = "09.06.2023",
        read_numbers = listOf("1", "2")
    )
    MyTheme {
        BillItem(bill = sampleBill, onClick = {})
    }
}