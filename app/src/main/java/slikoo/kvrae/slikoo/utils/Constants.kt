package slikoo.kvrae.slikoo.utils

import java.util.Locale

object Constants {
 //API
 val BASE_URL = "https://api.spoonacular.com"
 //Strings
 var homeScreen = if(Locale.getDefault().language == "fr") "Acceuil" else "Home"
 var recipeScreen = if(Locale.getDefault().language == "fr") "Recette" else "Recipes"
 var eventScreen = if(Locale.getDefault().language == "fr") "Événement" else "Events"
 var notificationScreen = if(Locale.getDefault().language == "fr") "Notification" else "Notifications"
 var settingsScreen = if(Locale.getDefault().language == "fr") "Paramètres" else "Settings"
 var login = if(Locale.getDefault().language == "fr") "Se connecter" else "Login"
}