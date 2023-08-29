package slikoo.kvrae.slikoo.viewmodel

import androidx.lifecycle.ViewModel
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.data.entities.Category

class CategoryViewModel : ViewModel() {
    fun getCategories(): List<Category> {
        return listOf(
            Category(
                1,
                "Paris",
                R.drawable.paris,
                "https://th.bing.com/th/id/R.18b6ce6b4df8e992d357669c14b1e8b0?rik=QMMnCd0mchAArw&riu=http%3a%2f%2fclipart-library.com%2fdata_images%2f149046.jpg&ehk=ZQ9XdgeopyxcPc4oiR2pysWs%2fKuBlCLb1WwkSi9HPmk%3d&risl=&pid=ImgRaw&r=0"
            ),
            Category(
                2,
                "Lyon",
                R.drawable.lyon,
                "https://raw.githubusercontent.com/KvRae/Slikoo-JsonCollection/main/Assets/PARIS.png"
            ),
            Category(
                3,
                "Montpelier",
                R.drawable.montpellier,
                "https://raw.githubusercontent.com/KvRae/Slikoo-JsonCollection/main/Assets/LYON.png"
            ),
        )
    }
}