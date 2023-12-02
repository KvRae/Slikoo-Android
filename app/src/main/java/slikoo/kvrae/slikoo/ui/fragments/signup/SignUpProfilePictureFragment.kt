package slikoo.kvrae.slikoo.ui.fragments.signup

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.CustomButton
import slikoo.kvrae.slikoo.ui.components.CustomSliderPointers
import slikoo.kvrae.slikoo.ui.components.LoadingDialog
import slikoo.kvrae.slikoo.ui.components.ProfileImagePicker
import slikoo.kvrae.slikoo.ui.theme.LightSurface
import slikoo.kvrae.slikoo.utils.SignUpNavigator
import slikoo.kvrae.slikoo.viewmodels.SignUpViewModel
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream


@Composable
fun ProfilePictureSection(
    onChange: (String) -> Unit,
    navController: NavController,
) {
    val context = LocalContext.current
    val viewModel: SignUpViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(R.string.profile_pic), style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.size(8.dp))
        CustomSliderPointers(maxSlide = 4, currentSlide = 4)
        ProfileImagePicker(
            imageUri = viewModel.profilePictureUri,
            onImageSelected = { viewModel.profilePictureUri = it!!
                             },
        )
        CustomButton(text = stringResource(R.string.finish),
            onClick = {
                val cidFile = File(getRealPathFromURI(viewModel.profilePictureUri, context))
                val profilePictureFile = File(getRealPathFromURI(viewModel.profilePictureUri, context))
                viewModel.onRegister(
                    avatar = profilePictureFile,
                    cidAvatar = cidFile,
                )
                /*navController.popBackStack()
                navController.navigate(AppScreenNavigator.SignInAppScreen.route)*/
            }
        )
        TextButton(onClick = { onChange(SignUpNavigator.SignUpIDCFragment.route) }) {
            Text(text = stringResource(id = R.string.previous), color = LightSurface)
        }
    }

    if (viewModel.isLoading) LoadingDialog()
}

fun getRealPathFromURI(uri: Uri, context: Context): String? {
    val returnCursor = context.contentResolver.query(uri, null, null, null, null)
    val nameIndex =  returnCursor!!.getColumnIndex(OpenableColumns.DISPLAY_NAME)
    val sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE)
    returnCursor.moveToFirst()
    val name = returnCursor.getString(nameIndex)
    val size = returnCursor.getLong(sizeIndex).toString()
    val file = File(context.filesDir, name)
    try {
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
        val outputStream = FileOutputStream(file)
        var read = 0
        val maxBufferSize = 1 * 1024 * 1024
        val bytesAvailable: Int = inputStream?.available() ?: 0
        //int bufferSize = 1024;
        val bufferSize = Math.min(bytesAvailable, maxBufferSize)
        val buffers = ByteArray(bufferSize)
        while (inputStream?.read(buffers).also {
                if (it != null) {
                    read = it
                }
            } != -1) {
            outputStream.write(buffers, 0, read)
        }
        Log.e("File Size", "Size " + file.length())
        inputStream?.close()
        outputStream.close()
        Log.e("File Path", "Path " + file.path)

    } catch (e: java.lang.Exception) {
        Log.e("Exception", e.message!!)
    }
    return file.path
}







