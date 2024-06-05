package org.lotka.xenonx.domain.model

data class CoinModel(
    val id: String,
    val isActive: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
)