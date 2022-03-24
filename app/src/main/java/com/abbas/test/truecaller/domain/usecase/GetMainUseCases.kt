package com.abbas.test.truecaller.domain.usecase

data class GetMainUseCases(
    val get10thLetterUseCase: Get10thLetterUseCase,
    val getEvery10thUseCase: GetEvery10thUseCase,
    val getAlphabetUseCase: GetAlphabetUseCase
)
