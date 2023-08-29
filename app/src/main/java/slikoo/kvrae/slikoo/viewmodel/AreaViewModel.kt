package slikoo.kvrae.slikoo.viewmodel

import androidx.lifecycle.ViewModel
import slikoo.kvrae.slikoo.data.entities.Area
import java.util.Date

class AreaViewModel : ViewModel() {
    private var areas: List<Area> = mutableListOf(
        Area(1, "Ariana", 1, "Description 1", "Image 1", 20.00, "Place 1", Date()),
        Area(2, "Tunis", 2, "Description 2", "Image 2", 26.0, "Place 2", Date()),
        Area(3, "Jendouba", 3, "Description 3", "Image 3", 70.0, "Place 3", Date()),
        Area(4, "Sousse", 4, "Description 4", "Image 4", 10.0, "Place 4", Date()),
        Area(5, "Sfax", 5, "Description 5", "Image 5", 9.0, "Place 5", Date()),
        Area(6, "Bizerte", 6, "Description 6", "Image 6", 40.0, "Place 6", Date()),
        Area(7, "Nabeul", 7, "Description 7", "Image 7", 70.0, "Place 7", Date()),
        Area(8, "Kairouan", 8, "Description 8", "Image 8", 50.0, "Place 8", Date()),
        Area(9, "Gabes", 9, "Description 9", "Image 9", 10.0, "Place 9", Date()),
        Area(10, "Gafsa", 10, "Description 10", "Image 10", 50.0, "Place 10", Date()),
        Area(11, "Kasserine", 11, "Description 11", "Image 11", 10.0, "Place 11", Date()),
    )

    fun getAreas(): List<Area> {
        return areas
    }

    fun removeArea(area: Area) {
        areas = areas.minus(area)
    }

    fun addArea(area: Area) {
        areas = areas.plus(area)
    }

    fun updateArea(area: Area) {
        areas = areas.minus(area)
        areas = areas.plus(area)
    }


}