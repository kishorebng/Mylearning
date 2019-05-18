package com.kishore.news.model.network.newsapi


/**
 * Constants used throughout the app.
 */
val NEWS_API_URL = "https://newsapi.org"

/* The format we want our API to return */
const val format = "json"

/* The query parameter allows to get newsDetail of specific country
The 2-letter ISO 3166-1 code of the country you want to get headlines for.
Possible options: ae ar at au be bg br ca ch cn co cu cz de eg fr gb gr hk hu id ie il in it jp kr lt
                  lv ma mx my ng nl no nz ph pl pt ro rs ru sa se sg si sk th tr tw ua us ve za .
Note: you can't mix this param with the sources param.
*/
const val country = "country"

/* The category you want to get headlines for. Possible options:
 business entertainment general health science sports technology .
 Note: you can't mix this param with the sources param.  */
const val category = "category"

/* A comma-seperated string of identifiers for the newsDetail sources or blogs you want headlines from.
   Use the /sources endpoint to locate these programmatically or look at the sources index.
   Note: you can't mix this param with the country or category params.  */
const val sources = "sources"

/* q Keywords or a phrase to search for.*/
const val q = "q"

/* The number of results to return per page (request). 20 is the default, 100 is the maximum.  */
const val pageSize = "pageSize"

/* Use this to page through the results if the total results found is greater than the page size.  */
const val page = "page"

const val API_KEY = "apiKey"

const val language = "language"


