package com.arvind.kmmpoc.feature.case

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CaseViewModel(
    private val caseRepository: CaseRepository = CaseRepository()
) : ViewModel() {
    private val _state = MutableStateFlow(CaseListState())
    val state get() = _state


    fun onEvent(event: CaseListEvent) {
        when (event) {
            CaseListEvent.OnCaseListScreenLaunch -> fetchAllCase()
            is CaseListEvent.SelectCase -> Unit
        }

    }

    private fun fetchAllCase() {
        viewModelScope.launch {
            state.update {
                it.copy(
                    cases = caseRepository.fetchAllCases()
                )
            }
        }
    }
}