package com.saitawngpha.securebackend.api

import com.saitawngpha.securebackend.model.Keys
import com.saitawngpha.securebackend.util.DataEncryption
import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.http.setBodyText
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import java.lang.Exception

@Api("provide")
suspend fun keyProvider(context: ApiContext) {
    val firstKey = "ILOVEYOUVERMUCH!"
    val secondKey = "IHATEYOUONEHUNDERDYEARS"

    try {
        val publicKey = context.req.body?.decodeToString()
        context.logger.debug("Public Key: $publicKey")
        if (publicKey != null){
            val encryptedData = DataEncryption.encrypt(
                context = context,
                 data = Json.encodeToString(
                     Keys(
                         firstKey = firstKey,
                         secondKey = secondKey
                     )
                 ),
                generatedPublicKey = publicKey.replace("\"", "")
            )
            if(encryptedData != null) {
                context.logger.error("ENCRYPTED DATA HAS BEEN SUCCESSFUL.")
                context.res.setBodyText(Json.encodeToString(encryptedData))
            }else{
                context.logger.error("ENCRYPTED DATA IS NULL.")
                context.res.status = 400
            }
        }else{
            context.logger.error("PUBLIC KEY IS NULL.")
            context.res.status = 400
        }
    }catch (e: Exception){
        context.logger.error(e.message.toString())
        context.res.status = 400
    }
}