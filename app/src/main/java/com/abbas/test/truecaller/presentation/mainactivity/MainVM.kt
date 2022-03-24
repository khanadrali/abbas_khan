package com.abbas.test.truecaller.presentation.mainactivity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abbas.test.truecaller.domain.usecase.Get10thLetterUseCase
import com.abbas.test.truecaller.domain.usecase.GetMainUseCases
import com.abbas.test.truecaller.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(
    val mainUseCases: GetMainUseCases
) : ViewModel() {

    val loading: MutableLiveData<Int> = MutableLiveData(0)
    val task1TextView: MutableLiveData<String> =
        MutableLiveData("1. Truecaller10thCharacterRequest")
    val task2TextView: MutableLiveData<String> =
        MutableLiveData("2. TruecallerEvery10thCharacterRequest")
    val task3TextView: MutableLiveData<String> =
        MutableLiveData("3. TruecallerWordCountRequest")

    fun sendThreeRequest() {

        task1TextView.value = "1. Truecaller10thCharacterRequest"
        task2TextView.value = "2. TruecallerEvery10thCharacterRequest"
        task3TextView.value = "3. TruecallerWordCounterRequest"



        tenthCharacterRequest()
        every10thCharacterRequest()
        wordCountRequest()


    }


    // ist task request
    private fun tenthCharacterRequest() {

        viewModelScope.launch {
            mainUseCases.get10thLetterUseCase.invoke().collect { it ->
                when (it) {
                    is Resource.Error -> {
                        loading.value = loading.value!! - 1
                        task1TextView.value =
                            "${task1TextView.value}\n ${it.errorMessage}"
                    }
                    is Resource.Loading -> {
                        loading.value = loading.value!! + 1
                    }
                    is Resource.Success -> {
                        it.data?.let {

                            // here i have to check if the 10th character is white space then i have to mention else
                            //show the character
                            val tenthCharacter = if (it.get(9).toString().contentEquals(" "))
                                "Empty Space"
                            else it[9]

                            task1TextView.value =
                                "${task1TextView.value}\nThe 10th character is: $tenthCharacter"

                        }
                        loading.value = loading.value!! - 1

                    }
                }
            }
        }

    }

    // 2nd task request
    private fun every10thCharacterRequest() {

        viewModelScope.launch {

            mainUseCases.getEvery10thUseCase.invoke().collect {
                when (it) {
                    is Resource.Error -> {
                        loading.value = loading.value!! - 1
                        task2TextView.value =
                            "${task2TextView.value}\n  ${it.errorMessage}"
                    }
                    is Resource.Loading -> {
                        loading.value = loading.value!! + 1
                    }

                    is Resource.Success -> {
                        it.data?.let {
                            val every10thCharacter: ArrayList<String> = arrayListOf()

                            for (i in 9 until it.length step 10) {
                                when (it[i].toString()) {
                                    " " -> every10thCharacter.add("Character: Empty Space, index: $i \n")
                                    "\n" -> every10thCharacter.add("Character: New Line, index: $i \n")
                                    else -> every10thCharacter.add("Character: ${it[i]}, index: $i \n")

                                }
                            }

                            task2TextView.value =
                                "${task2TextView.value}\nEvery 10th character in the response is:\n " + every10thCharacter.toString()
                        }
                        loading.value = loading.value!! - 1

                    }
                }
            }

        }
    }

    // 3nd task request
    private fun wordCountRequest() {
        viewModelScope.launch {

            mainUseCases.getEvery10thUseCase.invoke().collect {
                when (it) {
                    is Resource.Error -> {
                        loading.value = loading.value!! - 1
                        task3TextView.value =
                            "${task3TextView.value}\n  ${it.errorMessage}"
                    }
                    is Resource.Loading -> {
                        loading.value = loading.value!! + 1
                    }

                    is Resource.Success -> {

                        it.data?.let {

                            val wordCount: ArrayList<String> = arrayListOf()
                            val array = it.split("\n", " ", "\t").filter {
                                !it.contentEquals("")
                            }.groupBy { it }

                            for (entry in array) {
                                wordCount.add("word: ${entry.key}, count: ${entry.value.size} \n")
                            }

                            task3TextView.value =
                                "${task3TextView.value}\nWord count in the response is:\n " + wordCount.toString()
                        }

                        loading.value = loading.value!! - 1
                    }

                }
            }

        }

    }

}