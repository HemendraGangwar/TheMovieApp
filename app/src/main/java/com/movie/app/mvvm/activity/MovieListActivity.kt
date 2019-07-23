package com.movie.app.mvvm.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.movie.app.R
import com.movie.app.common.factoryExtensions.vm
import com.movie.app.data.model.Status
import com.movie.app.data.roomDb.entity.Movie
import com.movie.app.databinding.LayoutActivityMainBinding
import com.movie.app.mvvm.adapter.MovieListAdapter
import com.movie.app.mvvm.adapter.MovieViewHolder
import com.movie.app.mvvm.viewModel.MainListViewModel
import com.movie.app.utils.NetworkUtility
import com.skydoves.baserecyclerviewadapter.RecyclerViewPaginator
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.layout_activity_main.*
import javax.inject.Inject

/**
 * This class allow to display list of movie
 * It contains views for displaying information list of movie
 * Created by hemendrag on 14/07/2019.
 */
class MovieListActivity : AppCompatActivity(), MovieViewHolder.Delegate {

    //declare global references
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by lazy { vm(viewModelFactory, MainListViewModel::class) }
    private lateinit var binding: LayoutActivityMainBinding
    private lateinit var paginator: RecyclerViewPaginator
    var sortByLatest: Int = 0
    var sortByPopularity: Int = 1
    var sortByRating: Int = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        // dependency injection used for dagger
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        //data binding which requires changes in layout files
        binding = DataBindingUtil.setContentView(this, R.layout.layout_activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        //initialize ui for details screen
        init()
    }

    override fun onStart() {
        super.onStart()
        loadPages(page = 1)
    }

    /**
     * This function is used for initialize and assigning the list of movie
     */
    private fun init() {
        //first of all check if internet is connected or not before loading data
        if (!NetworkUtility.getInstance().isConnectingToInternet(this@MovieListActivity)) {
            NetworkUtility.getInstance().showPopUp(this@MovieListActivity, resources.getString(R.string.internet_error))
            return
        }
        //To display list of movies on screen
        // also assign adapter to represent the information we want to show as list
        // add pagination fucntion for the movie list
        recyclerView.layoutManager = GridLayoutManager(this@MovieListActivity, 2)
        recyclerView.adapter = MovieListAdapter(this)
        paginator = RecyclerViewPaginator(
            recyclerView = recyclerView,
            isLoading = { viewModel.getMovieListValues()?.status == Status.LOADING },
            loadMore = { loadPages(it) },
            onLast = { viewModel.getMovieListValues()?.onLastPage!! }
        )
        paginator.currentPage = 1
    }


    private fun loadPages(page: Int) {
        viewModel.postMoviePage(page)
    }


    /**
     * start movie details screen on movie item tap
     */
    override fun onItemClick(movie: Movie) {
        DetailActivity.startActivityModel(this@MovieListActivity, movie)
    }

    /**
     * Menu for adding action items to the ActionBar
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    /**
     * onPrepareOptionsMenu is for updating text and other thing once menu option is created,
     */
    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        val item = menu.findItem(R.id.sort_by)
        item.isEnabled = false
        return true;
    }

    /**
     * On menu item tap, adding functionality of sorting of movie list
     */
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {

            R.id.rating -> {
                viewModel.sortBy = sortByRating
                viewModel.postMoviePage(page = 1)
            }
            R.id.popularity -> {
                viewModel.sortBy = sortByPopularity
                viewModel.postMoviePage(page = 1)
            }
            R.id.none -> {
                viewModel.sortBy = sortByLatest
                viewModel.postMoviePage(page = 1)
            }
            else -> {
                viewModel.sortBy = sortByLatest
                viewModel.postMoviePage(page = 1)
            }
        }
        return true
    }

}
