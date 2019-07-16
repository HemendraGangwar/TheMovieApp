package com.movie.app.common.constants

/**
 * Created by hemendrag on 12/07/2019.
 * This class is used for having some of constant variables and methods that will be used in the project
 */
class AppConstant {

    val BASE_URL : String = "https://api.themoviedb.org/"


    companion object {

        /**
         * declare the reference of AppConstant class
         * In this class only the reference of AppConstant will be static & the other variable and methods will not be static
         * we can call them by class reference
         */
        var mAppConstant: AppConstant? = null

        /**
         * create singleton method for getting this class reference
         * @return
         */
        val obj: AppConstant
            get() {

                if (mAppConstant == null) {
                    mAppConstant = AppConstant()
                }
                return mAppConstant!!
            }

        const val API_KEY : String = "431d6732b9410f8947d0a4184dd4cf10"

    }



}
