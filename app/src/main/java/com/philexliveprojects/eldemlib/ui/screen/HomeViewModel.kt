package com.philexliveprojects.eldemlib.ui.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.philexliveprojects.eldemlib.data.category.CategoryRepository
import com.philexliveprojects.eldemlib.data.entity.Category
import com.philexliveprojects.eldemlib.data.entity.CategoryWithImgUrls
import com.philexliveprojects.eldemlib.data.image.ImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val categoryRepository: CategoryRepository
) : ViewModel() {
    private val _homeUiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val homeUiState = _homeUiState.asStateFlow()

    private val _homeDialogUiState = MutableStateFlow(HomeDialogUiState())
    val homeDialogUiState = _homeDialogUiState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val categoriesWithImgUrlsCount =
                categoryRepository.categoriesWithImgUrlsCount().first() ?: 0
            if (categoriesWithImgUrlsCount > 0) {

                _homeUiState.value =
                    categoryRepository.getAllCategoriesWithImgUrls()
                        .filterNotNull()
                        .catch {
                            Log.e("HomeViewModel", it.message, it)
                            HomeUiState.Error
                        }.map {
                            HomeUiState.Success(it)
                        }.first()
            } else {
                _homeUiState.value =
                    categoryRepository.getAllCategories()
                        .filterNotNull()
                        .catch {
                            Log.e("HomeViewModel", it.message, it)
                            HomeUiState.Error
                        }.map { categories ->
                            HomeUiState.Success(
                                categories.map {
                                    CategoryWithImgUrls(it, emptyList())
                                }
                            )
                        }
                        .first()
            }
        }
    }

    fun addCategory(categoryId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val categoryCount = categoryRepository.getCategoryCount().first() ?: 0
            if (categoryCount == 0) {
                viewModelScope.launch(Dispatchers.IO) {
                    categoryRepository.insertCategory(category = Category(categoryId))
                }

                _homeDialogUiState.value = _homeDialogUiState.value.copy(error = false)
            }
        }


        onDialogHide()
    }

    fun deleteCategory(category: Category) {
        viewModelScope.launch(Dispatchers.IO) {
            categoryRepository.deleteCategory(category)
        }
    }

    fun onDialogShow() {
        _homeDialogUiState.value = HomeDialogUiState(true)
    }

    fun onDialogHide() {
        _homeDialogUiState.value = HomeDialogUiState()
    }

    fun onDialogTextInput(input: String) {
        _homeDialogUiState.value = homeDialogUiState.value.copy(input = input)
    }
}

sealed interface HomeUiState {
    object Loading : HomeUiState

    data class Success(
        val categories: List<CategoryWithImgUrls>,
    ) : HomeUiState

    object Error : HomeUiState
}

data class HomeDialogUiState(
    val show: Boolean = false,
    val input: String = "",
    val error: Boolean = false
)


