package kasem.sm.delightplayground.features.rocket_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kasem.sm.delightplayground.core.state.ProgressState
import kasem.sm.delightplayground.core.state.Resource
import kasem.sm.delightplayground.interactors.GetRocketsUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class RocketListViewModel @Inject constructor(
    private val getRocketsUseCase: GetRocketsUseCase
) : ViewModel() {

    val state: MutableState<RocketState> = mutableStateOf(RocketState())

    init {
        getRockets()
    }

    fun getRockets() {
        getRocketsUseCase().onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    when (resource.progressState) {
                        is ProgressState.Progress -> {
                            state.value = state.value.copy(isLoading = true)
                        }
                        is ProgressState.Idle -> {
                            state.value = state.value.copy(isLoading = false)
                        }
                    }
                }
                is Resource.Success -> {
                    state.value = state.value.copy(rockets = resource.data ?: listOf())
                }
                is Resource.Error -> {
                    state.value = state.value.copy(
                        errorMessage = resource.errorMessage ?: "Unknown Error"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}
