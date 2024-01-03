package slikoo.kvrae.slikoo.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import slikoo.kvrae.slikoo.data.datasources.entities.Feedback
import slikoo.kvrae.slikoo.data.datasources.entities.UserDetails
import slikoo.kvrae.slikoo.data.datasources.remote.FeedbackRemoteDataSource
import slikoo.kvrae.slikoo.data.datasources.remote.UserDetailsRemoteDataSource
import slikoo.kvrae.slikoo.utils.TempSession

class UserDetailsViewModel : ViewModel() {


    //Raw Data For Dropdown Lists
    val languages = listOf("Le mandarin", "Espagonl", "Francais", "Anglais", "Italien", "Allemand", "Russe", "Portugais",
        "Japonais", "Arabe", "Hindi", "Bengali", "Punjabi", "Javanais", "Wu", "Telugu", "Marathi", "Turc", "Tamil",
        "Vietnamien", "Coréen", "Cantonais", "Urdu", "Min Nan", "Jinyu", "Gujarati", "Polonais", "Oriya",
        "Malayalam", "Bhojpuri", "Hakka", "Kannada", "Hausa", "Indonésien", "Arabe égyptien", "Népalais",
        "Sindhi", "Amharique", "Xiang", "Malais", "Saraiki", "Néerlandais", "Serbe", "Népalais", "Sindhi")
    val areaOfInterest = listOf(
        "Sport: football, basketball, tennis, etc.",
        "Musique: piano, guitare, chant, etc.",
        "Voyage: Europe, Asie, Afrique, etc.",
        "Art: peinture, sculpture, etc.",
        "Lecture: romans, poésie, etc.",
        "Cuisine: française, italienne, etc.",
        "Technologie: informatique, robotique, etc.",
        "Nature: animaux, plantes, etc.",
        "Activités manuelles: bricolage, couture, etc.",
        "Sciences: physique, chimie, etc.",
        "Autre"

        )
    val foodAlergies = listOf(
        "Arachides", "Fruits à coque", "Crustacés", "Oeufs", "Poissons", "Lupin", "Lait", "Mollusques", "Moutarde",)
    val lookingFor = listOf(
        "Homme", "Femme", "Autre")
    val lookingPlus = listOf(
        "Rencontre", "Amis", "Relation sérieuse", "Relation libre", "Relation éphémère", "Relation virtuelle", "Autre")


    private val userDetailsRDS = UserDetailsRemoteDataSource()
    private val feedbackRDS = FeedbackRemoteDataSource()


    val feedbacks = mutableStateListOf<Feedback>()

    val userDetails = mutableStateOf(
        UserDetails(
            iduser = TempSession.user.id.toString(),
        )
    )
    val choices = listOf("Oui", "Non")




    var isLoading by mutableStateOf(false)
    var isError by mutableStateOf(false)
    var navigate by mutableStateOf(false)
    private var resCode by mutableStateOf(0)

    fun getUserDetails(id : Int = TempSession.user.id) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isLoading = true
                userDetails.value = async { userDetailsRDS.getUserDetails(
                    TempSession.token,
                    id

                ) }.await()

            } catch (e: Exception) {
                e.printStackTrace()
                isError = true

            }
            finally {
                isLoading = false
            }
        }
    }

    fun addUserDetails(userDetails: UserDetails) {
        viewModelScope.launch() {
            try {
                navigate = false
                isLoading = true
                resCode = async {
                    userDetailsRDS.addUserDetails(
                    TempSession.token,
                    userDetails

                ) }.await()
            } catch (e: Exception) {
                e.printStackTrace()
                isError = true
                resCode = 500

            }
            finally {
                isLoading = false
                navigate = resCode == 200
            }
        }
    }

    fun updateUserDetails(userDetails: UserDetails) {
        if (onValidateLinkedinLink(userDetails.LinkedinLink ?: "") &&
            onValidateInstagramLink(userDetails.InstagramLink ?: "") &&
            onValidateTwitterLink(userDetails.TwitterLink ?: "")) {

            viewModelScope.launch(Dispatchers.IO) {
                try {
                    navigate = false
                    isLoading = true
                    resCode = async {
                        userDetailsRDS.updateUserDetails(
                            TempSession.token,
                            userDetails

                        )
                    }.await()
                } catch (e: Exception) {
                    e.printStackTrace()
                    isError = true
                    resCode = 500

                } finally {
                    isLoading = false
                    navigate = resCode == 200
                }
            }
        }
        else {
            isError = true
        }
    }

    fun getFeedbacks(id : Int = TempSession.user.id) {
        viewModelScope.launch() {
            try {
                isLoading = true
                val newFeedbacks = async { feedbackRDS
                    .getMyReceivedFeedbacks(
                    token = TempSession.token,
                    id = id
                ) }.await()
                feedbacks.clear()
                feedbacks.addAll(newFeedbacks)
            } catch (e: Exception) {
                e.printStackTrace()
                isError = true
            }
            finally {
                isLoading = false
                Log.d("Feedbacks", feedbacks.size.toString())
            }
        }
    }


    fun onFoodAllergiesChange(foodAllergies: String) {
        val stringList = userDetails.value.algalimentaire.joinToString()
        val list = userDetails.value.algalimentaire.toMutableList()

        if (!stringList.contains(foodAllergies, ignoreCase = true)) list.add(foodAllergies)
        else list.clear()

        userDetails.value = userDetails.value.copy(algalimentaire = list)
    }

    fun onLanguagesChange(language: String) {
        val stringList = userDetails.value.langues.joinToString()
        val list = userDetails.value.langues.toMutableList()

        if (!stringList.contains(language, ignoreCase = true)) list.add(language)
        else list.clear()

        userDetails.value = userDetails.value.copy(langues = list)

    }

    fun onAreaOfInterestChange(areaOfInterest: String) {
       val stringList = userDetails.value.centreinteret.joinToString()
        val list = userDetails.value.centreinteret.toMutableList()

        if (!stringList.contains(areaOfInterest, ignoreCase = true)) list.add(areaOfInterest)
        else list.clear()

        userDetails.value = userDetails.value.copy(centreinteret = list)

    }

    fun onLookingForChange(lookingFor: String) {
        val stringList = userDetails.value.cherche.joinToString()
        val list = userDetails.value.cherche.toMutableList()

        if (!stringList.contains(lookingFor, ignoreCase = true)) list.add(lookingFor)
        else list.clear()

        userDetails.value = userDetails.value.copy(cherche = list)
    }

    fun onLookingPlusChange(lookingPlus:String) {
        val stringList = userDetails.value.chercherplus.joinToString()
        val list = userDetails.value.chercherplus.toMutableList()

        if (!stringList.contains(lookingPlus, ignoreCase = true)) list.add(lookingPlus)
        else list.clear()

        userDetails.value = userDetails.value.copy(chercherplus = list)
    }

    fun onValidateFacebookLink(link: String) : Boolean {
        val facebookLinkRegex = Regex("^(https?:\\/\\/)?(www\\.)?facebook.com\\/.+")
        return facebookLinkRegex.matches(link)
    }

    fun onValidateTwitterLink(link: String) : Boolean {
        val twitterLinkRegex = Regex("^(https?:\\/\\/)?(www\\.)?twitter.com\\/.+")
        return twitterLinkRegex.matches(link)
    }

    fun onValidateInstagramLink(link: String) : Boolean {
        val instagramLinkRegex = Regex("^(https?:\\/\\/)?(www\\.)?instagram.com\\/.+")
        return instagramLinkRegex.matches(link)
    }

    fun onValidateLinkedinLink(link: String) : Boolean {
        val linkedinLinkRegex = Regex("^(https?:\\/\\/)?(www\\.)?linkedin.com\\/.+")
        return linkedinLinkRegex.matches(link)
    }
}