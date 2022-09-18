package com.bobmitchigan.com.android.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.touchlab.kermit.Logger
import com.bobmitchigan.com.android.R
import com.bobmitchigan.com.android.ui.components.LabelValue
import com.bobmitchigan.com.android.ui.components.TextHeadline3
import com.bobmitchigan.com.android.ui.theme.KtMultiNostrTheme
import com.bobmitchigan.com.android.viewmodel.ProfileViewModel
import com.bobmitchigan.com.domain.Profile
import org.koin.androidx.compose.getViewModel

@Composable
fun ProfileScreen(viewModel: ProfileViewModel = getViewModel()) {

    val profiles by viewModel.profiles.collectAsState()

    ProfileContent(profiles)
}

@Composable
private fun ProfileContent(profiles: List<Profile>) {
    Logger.d("vaclav " + profiles)

    LazyColumn {
        item {
            TextHeadline3(
                text = stringResource(id = R.string.profiles),
                modifier = Modifier.padding(16.dp)
            )
        }
        items(profiles) { profile -> ProfileCell(profile) }
    }
}

@Composable
private fun ProfileCell(profile: Profile) {
    Card(Modifier.padding(16.dp)) {
        Column(Modifier.padding(16.dp)) {
            LabelValue(label = stringResource(id = R.string.author), value = profile.name)
            LabelValue(label = stringResource(id = R.string.about), value = profile.about.orEmpty())
        }
    }
}

@Preview
@Composable
internal fun ProfileScreenPreview() {
    KtMultiNostrTheme {
        ProfileContent(
            profiles = listOf(
                Profile(
                    "John Doe",
                    "",
                    "About text",
                    ""
                )
            )
        )
    }
}
