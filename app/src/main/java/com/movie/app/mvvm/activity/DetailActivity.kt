package com.movie.app.mvvm.activity

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.crashlytics.android.Crashlytics
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import com.movie.app.R
import com.movie.app.data.roomDb.entity.Movie
import com.movie.app.mvvm.viewModel.DetailViewModel
import com.movie.app.common.factoryExtensions.customizeHeader
import com.movie.app.common.factoryExtensions.setSupportHeader
import com.movie.app.common.factoryExtensions.vm
import com.movie.app.data.roomDb.AppDatabase
import com.movie.app.data.roomDb.MovieDao
import com.movie.app.data.webApi.ApiMapService
import com.movie.app.databinding.LayoutActivityDetailBinding
import com.movie.app.utils.NetworkUtility
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.layout_activity_detail.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject

/**
 * This class allow to display details of movie
 * It contains views for displaying additional information
 * Created by hemendrag on 15/07/2019.
 */
class DetailActivity : AppCompatActivity() {

    //declare global references
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by lazy { vm(viewModelFactory, DetailViewModel::class) }
    private lateinit var binding: LayoutActivityDetailBinding
    private var isLike: Int = 0

    /**
     * onCreate(Bundle) to initialize activity
     * When Activity is started and application is not loaded,
     * then both onCreate() methods will be called.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        // dependency injection used for dagger
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        //data binding which requires changes in layout files
        binding = DataBindingUtil.setContentView(this@DetailActivity, R.layout.layout_activity_detail)
        binding.viewModel = viewModel
        binding.movie = getMovieFromIntent()
        binding.lifecycleOwner = this

        //initialize ui for details screen
        init()
    }

    companion object {
        private const val movieId = "movie"
        fun startActivityModel(context: Context?, movie: Movie) {
            context?.startActivity<DetailActivity>(movieId to movie)
        }
    }

    /**
     * This function is used for assigning the details in the views
     */
    private fun init() {

        //first of all check if internet is connected or not before loading data
        if (!NetworkUtility.getInstance().isConnectingToInternet(this@DetailActivity)) {
            NetworkUtility.getInstance().showPopUp(this@DetailActivity, resources.getString(R.string.internet_error))
            return
        }
        // customize and update header toolbar
        customizeHeader(toolBar)
        setSupportHeader(toolBar, getMovieFromIntent().title)
        // load image of movie poster in imageView
        updateImage()
        // post data in viewModel
        postData()

    }


    /**
     * This method is used for displayimg data in views of details activity
     * also it has the function of like/unlike of movie
     */
    private fun updateImage() {

        var movie = getMovieFromIntent()
        movie?.poster_path?.let {
            Glide.with(this@DetailActivity)
                .load(ApiMapService.getPosterPath(it))
                .listener(
                    GlidePalette.with(ApiMapService.getPosterPath(it))
                        .use(BitmapPalette.Profile.VIBRANT)
                        .intoBackground(appBarLayout)
                        .crossfade(true)
                )
                .into(movie_detail_poster)
        }
        textViewReviews.text = movie?.reviews?.size.toString()


        //Perform functions of Like/Unlike on fav button tap and set value in Room
        // get reference of Dao to update value of like
        try {
            val movieDao: MovieDao = AppDatabase.get(applicationContext).movieDao()
            isLike = movieDao.getLike(movie.id)
            if (isLike == 1) {
                imageView_like.setImageResource(R.drawable.ic_like)
            } else {
                imageView_like.setImageResource(R.drawable.ic_unlike)
            }

            imageView_like.setOnClickListener {
                 if (isLike == 1) {
                    isLike = 0
                    movieDao.updateLike(isLike,movie.id)
                    imageView_like.setImageResource(R.drawable.ic_unlike)
                } else {
                    isLike = 1
                    movieDao.updateLike(isLike,movie.id)
                    imageView_like.setImageResource(R.drawable.ic_like)
                }
            }
        } catch (e: Exception) {
            //handle the Exception by declaring the type of exception
            Crashlytics.log(e.toString())
        }
    }

    private fun postData() {
        viewModel.postMovieId(getMovieFromIntent().id)
    }

    private fun getMovieFromIntent(): Movie {
        return intent.getParcelableExtra(movieId) as Movie
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) onBackPressed()
        return false
    }

}
