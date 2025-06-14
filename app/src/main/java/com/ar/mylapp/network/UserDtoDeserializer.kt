package com.ar.mylapp.network

import ar.com.myldtos.users.PlayerDTO
import ar.com.myldtos.users.StoreDTO
import ar.com.myldtos.users.UserDTO
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class UserDtoDeserializer : JsonDeserializer<UserDTO> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): UserDTO? {
        if (json == null || context == null) return UserDTO();

        val jsonObject = json.asJsonObject

        return if (jsonObject.has("address")) {
            context.deserialize<StoreDTO>(json, StoreDTO::class.java)
        } else {
            context.deserialize<PlayerDTO>(json, PlayerDTO::class.java)
        }
    }
}