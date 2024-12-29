package name.velikodniy.vitaliy.vspmr.bills

data class Bill(
    val number: String,
    val conv: String,
    val name: String,
    val url: String,
    val date: String,
    val read_numbers: List<String>
)

data class BillDetails(
    val number: String,
    val conv: String,
    val name: String,
    val url: String,
    val date: String,
    val text: String,
    val files: List<File>,
    val committee: String,
    val committee_url: String,
    val author: String,
    val note: String,
    val reads: List<String>
)

data class File(
    val name: String,
    val url: String
)