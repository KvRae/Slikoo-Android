package slikoo.kvrae.slikoo.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

//@Composable
//fun BottomSheetExample() {
//    var bottomSheetState by rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
//    val context = LocalContext.current
//    var text by remember { mutableStateOf("Hello, Bottom Sheet!") }
//
//    ModalBottomSheetLayout(
//        sheetState = bottomSheetState,
//        sheetContent = {
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(16.dp)
//            ) {
//                TextField(
//                    value = text,
//                    onValueChange = { newText -> text = newText },
//                    label = { Text("Type something") },
//                    keyboardOptions = KeyboardOptions.Default.copy(
//                        imeAction = ImeAction.Done
//                    ),
//                    keyboardActions = KeyboardActions(
//                        onDone = {
//                            bottomSheetState.hide()
//                        }
//                    ),
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(8.dp)
//                )
//                Spacer(modifier = Modifier.height(16.dp))
//                Button(onClick = { bottomSheetState.hide() }) {
//                    Text("Close Bottom Sheet")
//                }
//            }
//        },
//        content = {
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(16.dp)
//            ) {
//                Button(onClick = { bottomSheetState.show() }) {
//                    Text("Show Bottom Sheet")
//                }
//            }
//        }
//    )
//}