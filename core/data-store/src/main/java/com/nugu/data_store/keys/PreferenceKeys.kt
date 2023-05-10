package com.nugu.data_store.keys

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

/** Auth */
val AUTH_PROVIDE_TYPE = stringPreferencesKey("auth_provide_type")
val AUTH_PROVIDE_ID = stringPreferencesKey("auth_provide_id")
val AUTH_PROVIDE_TOKEN = stringPreferencesKey("auth_provide_token")

/** Splash */
val SPLASH_FIRST_INIT = booleanPreferencesKey("isFirstInit")