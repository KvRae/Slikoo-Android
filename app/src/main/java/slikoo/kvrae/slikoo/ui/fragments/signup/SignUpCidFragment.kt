package slikoo.kvrae.slikoo.ui.fragments.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.CustomButton
import slikoo.kvrae.slikoo.ui.components.CustomSliderPointers
import slikoo.kvrae.slikoo.ui.components.ImagePickerField
import slikoo.kvrae.slikoo.ui.theme.LightPrimary
import slikoo.kvrae.slikoo.ui.theme.LightSurface
import slikoo.kvrae.slikoo.utils.SignUpNavigator
import slikoo.kvrae.slikoo.viewmodels.SignUpViewModel

@Composable
fun SignUpCidFragment(onChange: (String) -> Unit) {
    val  viewModel: SignUpViewModel = viewModel()
    var isImagePicked by remember {
        mutableStateOf(
            viewModel.cid.toString().isNotEmpty()
        )

    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(R.string.cid),
            style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.size(8.dp))
        CustomSliderPointers(maxSlide = 4, currentSlide = 3)
        Row(
            modifier = Modifier
                .padding(20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            ImagePickerField(
                onImageSelected = {
                    isImagePicked = true
                    viewModel.cid = it!!

                                  },
                imageUrl = viewModel.cid,
            )
        }

        if (!isImagePicked) {
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = stringResource(R.string.cid_hint),
                fontSize = 12.sp,
                color = LightPrimary,
            )
        }
        Spacer(modifier = Modifier.size(8.dp))


        CustomButton(text = stringResource(R.string.next),
            onClick = {
                if (isImagePicked)
                onChange(SignUpNavigator.SignUpProfilePictureFragment.route)
            })
        TextButton(onClick = { onChange(SignUpNavigator.SignUpSecondFormFragment.route) }) {
            Text(text = stringResource(id = R.string.previous), color = LightSurface)
        }

    }
}