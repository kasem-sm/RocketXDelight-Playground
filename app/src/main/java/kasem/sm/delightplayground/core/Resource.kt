package kasem.sm.delightplayground.core

sealed class Resource<T> {

    data class Error<T>(
        val errorMessage: String?
    ) : Resource<T>()

    data class Success<T>(
        val data: T? = null
    ) : Resource<T>()

    data class Loading<T>(
        val progressState: ProgressState = ProgressState.Idle
    ) : Resource<T>()
}

sealed class ProgressState {

    object Progress : ProgressState()

    object Idle : ProgressState()
}
