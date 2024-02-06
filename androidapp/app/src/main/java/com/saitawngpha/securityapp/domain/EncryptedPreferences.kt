package com.saitawngpha.securityapp.domain

import com.saitawngpha.securityapp.model.Keys

/**
 * @Author: ၸၢႆးတွင်ႉၾႃႉ
 * @Date: 24/01/2024.
 */
interface EncryptedPreferences {
    fun saveEncryptedData(keys: Keys) : Boolean
    fun readEncryptedData(): Keys?
    fun areApiKeysReady(): Boolean
}