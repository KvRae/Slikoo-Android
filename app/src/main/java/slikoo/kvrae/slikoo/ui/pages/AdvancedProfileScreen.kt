package slikoo.kvrae.slikoo.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.CustomDropDownMenu
import slikoo.kvrae.slikoo.ui.components.CustomTextField
import slikoo.kvrae.slikoo.ui.theme.LightSecondary

@Preview
@Composable
fun AdvancedProfileScreen() {
    val choix = listOf("oui","non")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = LightSecondary)
            .statusBarsPadding()
            .padding(16.dp)
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        CustomDropDownMenu(dropDownList = choix, onChange ={} )
        CustomDropDownMenu(
            dropDownList = listOf("Gluten Free", "Vegan", "Vegetarian", "Lactose Free")
            , onChange = {})
        CustomTextField(onChange = {}, value = "",
            label ="Facebook",
            placeHolder = "www.facebook.com/johnDOE",
            leadingIcon =  ImageVector.vectorResource(id = R.drawable.facebook_icon)
        )
        CustomTextField(onChange = {}, value = "", label ="X(Twitter)" )
        CustomTextField(onChange = {}, value = "", label ="Instagram" )
        CustomTextField(onChange = {}, value = "", label ="LinkedIn" )



        
    }

}