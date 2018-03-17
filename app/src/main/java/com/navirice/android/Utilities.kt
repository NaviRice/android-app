package com.navirice.android;

import com.mapbox.api.directions.v5.models.StepManeuver

/**
 * @author Harry Liu
 * @version Feb 18, 2018
 */

object Utilities {
    fun getIconIdentifier(stepManeuver: StepManeuver): String {
        val iconPieces = mutableListOf<String>()
        iconPieces.add(stepManeuver.type()!!)
        if (stepManeuver.modifier() != null) {
            stepManeuver.modifier()!!.split(" ").forEach({ m ->
                iconPieces.add(m)
            })
        }
        return iconPieces.joinToString("_")
    }
}
