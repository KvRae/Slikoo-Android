package slikoo.kvrae.slikoo.viewmodel

import androidx.lifecycle.ViewModel
import slikoo.kvrae.slikoo.data.models.Area
import java.util.Date

class AreaViewModel: ViewModel() {

    fun getAreas() {
        listOf<Area>(
            Area(1, "Ariana", 1, "Description 1", "Image 1", "Price 1", "Place 1", Date()),
            Area(2, "Tunis", 2, "Description 2", "Image 2", "Price 2", "Place 2", Date()),
            Area(3, "Jendouba", 3, "Description 3", "Image 3", "Price 3", "Place 3", Date()),
            Area(4, "Sousse", 4, "Description 4", "Image 4", "Price 4", "Place 4", Date()),
            Area(5, "Sfax", 5, "Description 5", "Image 5", "Price 5", "Place 5", Date()),
            Area(6, "Bizerte", 6, "Description 6", "Image 6", "Price 6", "Place 6", Date()),
            Area(7, "Nabeul", 7, "Description 7", "Image 7", "Price 7", "Place 7", Date()),
            Area(8, "Kairouan", 8, "Description 8", "Image 8", "Price 8", "Place 8", Date()),
            Area(9, "Gabes", 9, "Description 9", "Image 9", "Price 9", "Place 9", Date()),
            Area(10, "Gafsa", 10, "Description 10", "Image 10", "Price 10", "Place 10", Date()),
            Area(11, "Kasserine", 11, "Description 11", "Image 11", "Price 11", "Place 11", Date()),
        )
    }
}