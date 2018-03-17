package com.navirice.android.models

import kotlinx.serialization.Serializable

@Serializable
data class Step(val name: String, val instruction: String, val icon: String, val location: Location)