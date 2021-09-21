package kasem.sm.delightplayground.features.rocket_list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kasem.sm.delightplayground.core.ProgressState
import kasem.sm.delightplayground.core.Resource
import kasem.sm.delightplayground.interactors.GetRocketsUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class RocketListViewModel @Inject constructor(
    private val getRocketsUseCase: GetRocketsUseCase
) : ViewModel() {

    val state = mutableStateOf(RocketState())

    init {
        provoke(RocketListEvent.GetRockets)
    }

    fun provoke(event: RocketListEvent) {
        viewModelScope.launch {
            try {
                when (event) {
                    is RocketListEvent.GetRockets -> {
                        getRockets()
                    }
                }
            } catch (e: Exception) {
            }
        }
    }

    private fun getRockets() {
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
