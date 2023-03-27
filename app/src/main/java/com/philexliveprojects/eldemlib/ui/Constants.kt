package com.philexliveprojects.eldemlib.ui

const val GLOBAL = "global"

const val HOME_ROUTE = "home"

const val CATEGORY = "category"
const val CATEGORY_ID = "categoryId"
const val CATEGORY_ROUTE = "$CATEGORY/{$CATEGORY_ID}"

const val ARTICLE = "article"
const val ARTICLE_ID = "articleId"
const val ARTICLE_ROUTE = "$ARTICLE/{$ARTICLE_ID}"

const val IMAGE = "image"
const val IMAGE_URL = "imgUrl"
const val IMAGE_SCREEN = "$IMAGE/{$IMAGE_URL}"

const val SEARCH = "search"
const val SEARCH_SCOPE = "tag"
const val SEARCH_ROUTE = "$SEARCH/{$SEARCH_SCOPE}"

const val TIMEOUT_MILLIS = 5_000L