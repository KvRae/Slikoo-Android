package slikoo.kvrae.slikoo.ui.fragments.meal

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.CustomButton
import slikoo.kvrae.slikoo.ui.components.CustomSlidingBar
import slikoo.kvrae.slikoo.ui.components.ImagePickerField
import slikoo.kvrae.slikoo.ui.components.LoadingDialog
import slikoo.kvrae.slikoo.ui.fragments.profile.makeToast
import slikoo.kvrae.slikoo.ui.theme.LightSurface
import slikoo.kvrae.slikoo.utils.AppScreenNavigator
import slikoo.kvrae.slikoo.utils.compressFile
import slikoo.kvrae.slikoo.viewmodels.MealsViewModel
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream


@Composable
fun EventFinalFragment(
    onFragmentChange: (String) -> Unit,
    navController: NavController,

) {
    val mealsVm : MealsViewModel = viewModel()
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        CustomSlidingBar(sliderPosition = 2f)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = stringResource(R.string.step_three_event),
                color = LightSurface
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        ImagePickerField(
            onImageSelected = { mealsVm.mealUri.value = it!! },
            imageUrl = mealsVm.mealUri.value,
        )

        Spacer(modifier = Modifier.height(32.dp))

        CustomButton(text = stringResource(id = R.string.finish)) {
            coroutineScope.launch {
                val mealFile =
                    compressFile(
                        navController.context,
                        File(getRealPathFromURI(mealsVm.mealUri.value, navController.context)!!)
                    )
                mealsVm.onAddMeal(mealFile!!)
            }
        }

        TextButton(onClick = { onFragmentChange(navController.context.getString(R.string.second)) }) {
            Text(
                text = stringResource(id = R.string.previous),
                color = LightSurface
            )
        }
        if (mealsVm.isLoading.value) LoadingDialog()
        if (mealsVm.navigate.value)
            DisposableEffect(key1 = mealsVm.navigate) {
                makeToast(
                    navController.context,
                    navController.context.getString(R.string.meal_added)
                )
                navController.navigate(AppScreenNavigator.MainAppScreen.route) {
                    popUpTo(AppScreenNavigator.MainAppScreen.route) {
                        inclusive = true
                    }
                }
                onDispose { mealsVm.navigate.value = false }
            }


    }
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