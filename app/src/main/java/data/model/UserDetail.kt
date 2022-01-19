package data.model

data class UserDetail(
    val login: String,
    val name: String,
    val bio: String,
    val id: Int,
    val avatar_url: String,
    val twitter_username: String,
    val repos_url: String
)
