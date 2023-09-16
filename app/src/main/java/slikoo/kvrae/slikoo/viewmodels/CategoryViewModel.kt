package slikoo.kvrae.slikoo.viewmodels

import androidx.lifecycle.ViewModel
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.data.datasources.entities.Category

class CategoryViewModel : ViewModel() {
    fun getCategories(): List<Category> {
        return listOf(
            Category(
                1,
                "Paris",
                R.drawable.paris),
            Category(
                2,
                "Lyon",
                R.drawable.lyon,

            ),
            Category(
                3,
                "Montpelier",
                R.drawable.montpellier,
            ),
        )
    }
}