package com.bobmitchigan.com.dataaccess

import kotlinx.serialization.json.Json

val nonStrictSerializer = Json { ignoreUnknownKeys = true }
