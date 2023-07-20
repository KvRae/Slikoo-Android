package slikoo.kvrae.slikoo.viewmodel

import androidx.lifecycle.ViewModel
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.data.models.Category

class CategoryViewModel : ViewModel() {
    fun getCategories() : List<Category>{
        val categories = listOf<Category> (
        Category(1, "Paris", R.drawable.paris, "Image 1"),
        Category(2, "Lyon", R.drawable.lyon, "Image 2"),
        Category(3, "Montpelier", R.drawable.montpellier, "Image 3"),
        )
        return categories
    }
}