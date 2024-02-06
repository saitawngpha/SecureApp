package com.saitawngpha.securebackend.util

import com.varabyte.kobweb.api.ApiContext
import java.io.StringReader
import java.lang.Exception
import java.security.KeyFactory
import java.security.spec.X509EncodedKeySpec
import java.util.Base64
import javax.crypto.Cipher

private const val TRANFORMATION = "RSA/ECB/PKCS1Padding"
object DataEncryption {
    fun encrypt(
        context: ApiContext,
        data: String,
        generatedPublicKey: String
    ): String? {
        return try {
        val publicKeyBytes = Base64.getDecoder().decode(generatedPublicKey)
            val publicKeySpec = X509EncodedKeySpec(publicKeyBytes)

            val keyFactory = KeyFactory.getInstance("RSA")
            val publicKey = keyFactory.generatePublic(publicKeySpec)

            val cihper = Cipher.getInstance(TRANFORMATION)
            cihper.init(Cipher.ENCRYPT_MODE, publicKey)

            val result = cihper.doFinal(data.toByteArray())
            Base64.getEncoder().encodeToString(result)
        }catch (e: Exception){
            context.logger.error("Error while encrypting: $e")
            null
        }
    }
}