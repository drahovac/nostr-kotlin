package com.bobmitchigan.com.android.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bobmitchigan.com.android.R
import com.bobmitchigan.com.android.ui.components.OutlineInput
import com.bobmitchigan.com.android.ui.components.TextBody1
import com.bobmitchigan.com.android.ui.components.TextHeadline3
import com.bobmitchigan.com.android.viewmodel.CreateProfileState
import com.bobmitchigan.com.android.viewmodel.CreateUserProfileActions
import com.bobmitchigan.com.android.viewmodel.CreateUserProfileViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun CreateUserProfileScreen(viewModel: CreateUserProfileViewModel = getViewModel()) {

    val state by viewModel.state.collectAsState()

    CreateUserProfile(state, viewModel)
}

@Composable
private fun CreateUserProfile(
    state: CreateProfileState,
    actions: CreateUserProfileActions,
) {
    Column(
        Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        TextHeadline3(text = stringResource(id = R.string.create_profile))

        OutlineInput(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            value = state.username,
            onValueChanged = actions::updateUsername,
            label = stringResource(id = R.string.username),
            placeholder = stringResource(id = R.string.username_place_holder)
        )

        OutlineInput(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            value = state.about,
            onValueChanged = actions::updateAbout,
            label = stringResource(id = R.string.about),
            placeholder = stringResource(id = R.string.about_placeholder)
        )

        OutlineInput(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            value = state.picture,
            onValueChanged = actions::updatePictureLink,
            label = stringResource(id = R.string.picture),
            placeholder = stringResource(id = R.string.picture_link)
        )

        Button(onClick = actions::submit) {
            TextBody1(text = stringResource(id = R.string.create))
        }
    }
}

@Preview
@Composable
fun CreateUserProfileScreenPreview() {
    CreateUserProfile(
        state = CreateProfileState(),
        actions = object : CreateUserProfileActions {
            override fun updateUsername(value: String) {
            }

            override fun updateAbout(value: String) {
            }

            override fun updatePictureLink(value: String) {
            }

            override fun submit() {
            }
        }
    )
}
