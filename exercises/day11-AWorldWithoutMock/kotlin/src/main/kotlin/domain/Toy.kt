package domain

data class Toy(
    val name: String,
    var state: State
) {
    enum class State {
        UNASSIGNED,
        IN_PRODUCTION
    }
}
