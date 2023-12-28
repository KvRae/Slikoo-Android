package slikoo.kvrae.slikoo.ui.pages

//
//@Composable
//fun FeedbackForm(
//    navController: NavController,
//    idMeal: Int,
//    idUser: Int
//) {
//
//
//    val viewModel: FeedbackViewModel = viewModel()
//    if (idMeal != 0 && idUser != 0) {
//        DisposableEffect(Unit) {
//            Log.d("FeedbackForm idMeal: ", "$idMeal")
//            Log.d("FeedbackForm idUser: ", "$idUser")
//            Log.d("FeedbackForm readOnly: ", "${viewModel.verifyFeedbackSubmitted(
//                idUser,
//                idMeal
//            )}")
//            viewModel.getMyFeedbackByUserMeal(idUser, idMeal)?: Feedback()
//            onDispose {}
//        }
//    }
//    Column(
//        modifier = Modifier
//            .fillMaxSize(1f)
//            .statusBarsPadding()
//            .navigationBarsPadding()
//            .background(color = LightSecondary)
//
//    ) {
//        EditProfileTopBar(
//            navController = navController,
//            title = stringResource(id = R.string.your_feeback)
//        )
//        Column(
//            modifier = Modifier
//                .fillMaxSize(1f)
//                .background(color = LightSecondary)
//                .padding(16.dp),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            Surface(
//                modifier = Modifier
//                    .size(120.dp)
//                    .background(color = LightSecondary),
//                shape = CircleShape,
//                elevation = 8.dp
//
//            ) {
//                AsyncImage(
//                    model = (viewModel.feedback.recipient.avatarUrl.plus(viewModel.feedback.recipient.avatar)),
//                    contentDescription = "Image",
//                    contentScale = ContentScale.Crop,
//                )
//            }
//            Spacer(modifier = Modifier.size(16.dp))
//            Text(
//                text = viewModel.feedback.recipient.nom + " " + viewModel.feedback.recipient.prenom,
//                fontSize = 24.sp,
//                fontWeight = FontWeight.Bold
//            )
//            Spacer(modifier = Modifier.size(32.dp))
//
//            Column(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalAlignment = Alignment.Start
//            ) {
//                Text(
//                    text = stringResource(R.string.your_feedback),
//                    fontSize = 18.sp,
//                    fontWeight = FontWeight.Bold
//                )
//                Spacer(modifier = Modifier.size(8.dp))
//                FeedbackRatingBar(
//                    iconsSize = 24,
//                    currentRating = viewModel.feedback.rate,
//                    onRatingChanged = {
//                        if (!viewModel.verifyFeedbackSubmitted(idUser, idMeal))
//                            viewModel.feedback.rate = it
//                    }
//                )
//            }
//            Spacer(modifier = Modifier.size(16.dp))
//            CustomTextField(
//                onChange = {
//                    if (!viewModel.verifyFeedbackSubmitted(idUser, idMeal))
//                        viewModel.feedback = viewModel.feedback.copy(comment = it)
//                },
//                leadingIcon = ImageVector.vectorResource(id = R.drawable.feedback),
//                value = viewModel.feedback.comment,
//                label = stringResource(R.string.comment),
//                readOnly = viewModel.verifyFeedbackSubmitted(idUser, idMeal)
//            )
//            Spacer(modifier = Modifier.size(24.dp))
//            if (idMeal != 0 && idUser != 0 &&
//                !viewModel.verifyFeedbackSubmitted(idUser, idMeal))
//                CustomButton(
//                    text = stringResource(id = R.string.submit),
//                    onClick = {
//                        viewModel.addFeedback(
//                            idReciver = idUser,
//                            idMeal = idMeal,
//                            comment = viewModel.feedback.comment,
//                            rate = viewModel.feedback.rate
//                        )
//                    }
//                )
//        }
//    }
//
//}