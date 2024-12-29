package name.velikodniy.vitaliy.vspmr.bills

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.flowlayout.FlowRow

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ConvocationDropdownMenu(
    convocations: List<String>,
    selectedConvocation: String,
    onConvocationSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Row(
        verticalAlignment = Alignment.CenterVertically, modifier = Modifier
            .padding(all = 15.dp)
    ) {
        Text(
            text = "Созыв",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = colorResource(id = R.color.colorText)
        )
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp)
                .padding(vertical = 8.dp),
            mainAxisSpacing = 8.dp,
            crossAxisSpacing = 8.dp
        ) {
            convocations.forEach { conv ->
                Chip(
                    onClick = { onConvocationSelected(conv) },
                    colors = ChipDefaults.chipColors(
                        if (selectedConvocation == conv) colorResource(id = R.color.colorText) else colorResource(
                            id = R.color.colorSurface
                        ),
                        contentColor = if (selectedConvocation == conv) colorResource(id = R.color.colorSurface) else colorResource(
                            id = R.color.colorText
                        )
                    )
                ) {
                    Text(text = conv, fontWeight = FontWeight.Medium)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewConvocationDropdownMenu() {

    MyTheme {
        ConvocationDropdownMenu(
            convocations = listOf("V", "VI", "VII"),
            selectedConvocation = "VII",
            onConvocationSelected = {}
        )
    }
}