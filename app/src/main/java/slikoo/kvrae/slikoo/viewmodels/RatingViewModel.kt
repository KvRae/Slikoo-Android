package slikoo.kvrae.slikoo.viewmodels


import androidx.lifecycle.ViewModel
import slikoo.kvrae.slikoo.data.datasources.entities.Rating
import java.util.Date

class RatingViewModel : ViewModel() {
    private var ratings = mutableListOf<Rating>(
        Rating(
            id = 1,
            comment = "great i like you slikko",
            rate = 5,
            date = Date(),
            user = "Karam"
        ),
        Rating(id = 2, comment = "Lorem i", rate = 3, date = Date(), user = "Ahmed Mohsen"),
        Rating(
            id = 3,
            comment = "helooo guys its amazing",
            rate = 1,
            date = Date(),
            user = "Samir lousif"
        ),
        Rating(id = 4, comment = "why people are bad ", rate = 2, date = Date(), user = "Ram ala"),
        Rating(id = 5, comment = "make me fell buzzy ", rate = 5, date = Date(), user = "Kaso JJ "),
        Rating(
            id = 6,
            comment = "dude im full of energy ilove the way people are using this app its " +
                    "so magnificient the way its and how impact it is ",
            rate = 0,
            date = Date(),
            user = "Morad alamdar"
        ),
    )

    fun getRatings(): List<Rating> {
        return ratings
    }
}