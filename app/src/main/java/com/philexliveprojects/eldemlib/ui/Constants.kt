package com.philexliveprojects.eldemlib.ui

const val GLOBAL = "global"

const val HOME_ROUTE = "home"

const val GROUP = "group"
const val GROUP_ID = "groupId"

const val GROUP_ROUTE = "$GROUP/{$GROUP_ID}"
const val UNIT = "unit"
const val UNIT_ID = "unitId"

const val UNIT_ROUTE = "$UNIT/{$UNIT_ID}"
const val IMAGE = "image"
const val IMAGE_URL = "imgUrl"

const val IMAGE_SCREEN = "$IMAGE/{$IMAGE_URL}"
const val SEARCH = "search"
const val SEARCH_SCOPE = "tag"

const val SEARCH_ROUTE = "$SEARCH/{$SEARCH_SCOPE}"

const val TIMEOUT_MILLIS = 5_000L