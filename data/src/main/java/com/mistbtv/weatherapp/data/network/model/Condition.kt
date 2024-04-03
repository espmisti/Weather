package com.mistbtv.weatherapp.data.network.model

import com.mistbtv.weatherapp.domain.model.Condition as DomainCondition

data class Condition(
    val code: Int,
    val icon: String,
    val text: String
) {
    companion object {
        fun Condition.toCondition(): DomainCondition = DomainCondition(
            code = code,
            text = text
        )
    }
}