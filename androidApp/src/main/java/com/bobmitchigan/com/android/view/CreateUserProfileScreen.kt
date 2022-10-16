package com.bobmitchigan.com.android.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bobmitchigan.com.android.R
import com.bobmitchigan.com.android.ui.components.TextHeadline3

@Composable
fun CreateUserProfileScreen() {
    CreateUserProfile()
}

@Composable
private fun CreateUserProfile() {
    Column(Modifier.padding(16.dp)) {
        TextHeadline3(text = stringResource(id = R.string.create_profile))
    }
}
