package data.model

data class UserRepo(
    val name: String,
    val description: String,
    val stargazers_count: Int,
    val owner: Data
)

data class Data(
    val login: String
)
