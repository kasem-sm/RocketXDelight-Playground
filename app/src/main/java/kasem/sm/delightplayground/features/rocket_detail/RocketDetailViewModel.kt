package kasem.sm.delightplayground.features.rocket_detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kasem.sm.delightplayground.core.state.ProgressState
import kasem.sm.delightplayground.core.state.Resource
import kasem.sm.delightplayground.interactors.GetRocketByIdUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class RocketDetailViewModel @Inject constructor(
    private val getRocketByIdUseCase: GetRocketByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val state = mutableStateOf(RocketDetailState())

    init {
        savedStateHandle.get<Int>("rocketId")?.let { id ->
            getRocketById(id)
        }
    }

    private fun getRocketById(id: Int) {
        getRocketByIdUseCase(id).onEach { resource ->
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
                    state.value = state.value.copy(rocket = resource.data)
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