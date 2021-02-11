package com.healby.model

data class Posyandu(

        val id: String,

        val name: String,

        val address: String,

        val position: Position,

        val phone: String,

        val openTime: Int,

        val images: List<String> = listOf()

)