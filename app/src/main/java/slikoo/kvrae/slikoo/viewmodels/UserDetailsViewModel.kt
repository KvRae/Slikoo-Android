package slikoo.kvrae.slikoo.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
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
        "Voyaage: Europe, Asie, Afrique, etc.",
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


    var feedbacks by mutableStateOf(mutableListOf<Feedback>())

    var userDetails by mutableStateOf(
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
                userDetails = async { userDetailsRDS.getUserDetails(
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
        viewModelScope.launch(Dispatchers.IO) {
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
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isLoading = true
                feedbacks = async { feedbackRDS
                    .getMyReceivedFeedbacks(
                    token = TempSession.token,
                    id = id
                ) }.await()
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


    fun onFoodAlergiesChange(foodAlergies: String) {
        val list = userDetails.algalimentaire.toMutableList()
        userDetails.algalimentaire.clear()
        list.removeAll{
            it == foodAlergies
        }
        list.add(foodAlergies)

        userDetails = userDetails.copy(algalimentaire = list)
    }

    fun onLanguagesChange(languages: String) {
        val list = userDetails.langues.toMutableList()
        userDetails = userDetails.copy(langues = mutableListOf())

        if (list.contains(languages)) {
            list.remove(languages)
        }
        else {
            list.removeAll{
                it == languages
            }
            list.add(languages)
        }
        userDetails = userDetails.copy(langues = list)
    }

    fun onAreaOfInterestChange(areaOfInterest: String) {
        val list = userDetails.centreinteret.toMutableList()
        userDetails = userDetails.copy(centreinteret = mutableListOf())
        if (list.contains(areaOfInterest)) {
            list.remove(areaOfInterest)
        }
        else {
            list.removeAll{
                it == areaOfInterest
            }
            list.add(areaOfInterest)
        }
        userDetails = userDetails.copy(centreinteret = list)
    }

    fun onLookingForChange(lookingFor: String) {
        val list = userDetails.cherche.toMutableList()
        userDetails = userDetails.copy(cherche = mutableListOf())

        if (list.contains(lookingFor)) {
            list.remove(lookingFor)
        }
        else {
            list.removeAll{
                it == lookingFor
            }
            list.add(lookingFor)
        }
        userDetails = userDetails.copy(cherche = list)
    }

    fun onLookingPlusChange(lookingPlus:String) {
        val list = userDetails.chercherplus.toMutableList()
        userDetails = userDetails.copy(chercherplus = mutableListOf())

        if (list.contains(lookingPlus, )) {
            list.remove(lookingPlus)
        }
        else {
            list.removeAll{
                it == lookingPlus
            }
            list.add(lookingPlus)
        }
        userDetails = userDetails.copy(chercherplus = list)
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