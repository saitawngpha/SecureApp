package com.saitawngpha.securityapp.data

import android.content.Context
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.saitawngpha.securityapp.domain.EncryptedPreferences
import com.saitawngpha.securityapp.model.Keys

/**
 * @Author: ၸၢႆးတွင်ႉၾႃႉ
 * @Date: 24/01/2024.
 */

const val PREFE_NAME = "apiKeys"
const val PREFE_FIRST_KEY = "firstKey"
const val PREFE_SECOND_KEY = "secondKey"

class EncryptedPreferencesImpl(context: Context): EncryptedPreferences {
    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build() as? MasterKey

    private val preferences = masterKey?.let {
        EncryptedSharedPreferences.create(
            context,
            PREFE_NAME,
            it,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    override fun saveEncryptedData(keys: Keys): Boolean {
        return if(preferences != null ) {
            preferences.edit {
                putString(PREFE_FIRST_KEY, keys.firstKey)
                putString(PREFE_SECOND_KEY, keys.secondKey)
            }
            true
        }else false
    }

    override fun readEncryptedData(): Keys? {
        val firstKey = preferences?.getString(PREFE_FIRST_KEY, null)
        val secondKey = preferences?.getString(PREFE_SECOND_KEY, null)
        return if(firstKey != null && secondKey != null)
            Keys(firstKey = firstKey, secondKey = secondKey)
        else null
    }

    override fun areApiKeysReady(): Boolean {
        val firstCondition = preferences != null
                && preferences.contains(PREFE_FIRST_KEY)
                && preferences.contains(PREFE_SECOND_KEY)
        val secondCondition = readEncryptedData() != null
        return firstCondition && secondCondition
    }
}