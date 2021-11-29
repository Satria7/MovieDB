package com.satria.jpro.lastsubmission.ui.home.content.movie
//copyright satria junanda//
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.satria.jpro.lastsubmission.R
import com.satria.jpro.lastsubmission.data.source.local.entity.MovieEntity
import com.satria.jpro.lastsubmission.data.source.local.entity.TvShowEntity
import com.satria.jpro.lastsubmission.ui.detail.DetailActivity
import com.satria.jpro.lastsubmission.ui.home.HomeViewModel
import com.satria.jpro.lastsubmission.ui.home.content.tvshow.TvShowAdapter
import com.satria.jpro.lastsubmission.ui.home.content.tvshow.TvShowCallback
import com.satria.jpro.lastsubmission.utils.Constants
import com.satria.jpro.lastsubmission.utils.Constants.TYPE_MOVIE
import com.satria.jpro.lastsubmission.viewmodel.ViewModelFactory
import com.satria.jpro.lastsubmission.vo.Status
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.fragment_tv_show.*
import kotlinx.android.synthetic.main.progress_bar.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : DaggerFragment(), MovieCallback, TvShowCallback {

    private lateinit var viewModel: HomeViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()

        activity?.let { setupViewModel(it) }
        observeListMovies()
        observeListTvShow()

        val imageList = ArrayList<SlideModel>()
        imageList.add(SlideModel("https://image.tmdb.org/t/p/original/70nxSw3mFBsGmtkvcs91PbjerwD.jpg"))
        imageList.add(SlideModel("https://image.tmdb.org/t/p/original/cinER0ESG0eJ49kXlExM0MEWGxW.jpg"))
        imageList.add(SlideModel("https://image.tmdb.org/t/p/original/zBkHCpLmHjW2uVURs5uZkaVmgKR.jpg"))
        imageList.add(SlideModel("https://image.tmdb.org/t/p/original/vIPIyTJqcgOKgKcExCvTDpLpTYW.jpg"))

        imageSlider.setImageList(imageList, ScaleTypes.FIT)

    }

    private fun setupViewModel(fragmentActivity: FragmentActivity) {
        viewModel = ViewModelProvider(fragmentActivity, factory)[HomeViewModel::class.java]
    }


    private fun observeListTvShow() {
        viewModel.getListOnTheAirTvShows().observe(viewLifecycleOwner, Observer { listTvShow ->
            if (listTvShow != null) {
                when (listTvShow.status) {
                    Status.LOADING -> progress_bar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        progress_bar.visibility = View.GONE
                        rv_comingsoon.adapter?.let { adapter ->
                            when (adapter) {
                                is TvShowAdapter -> {
                                    adapter.submitList(listTvShow.data)
                                    adapter.notifyDataSetChanged()
                                }
                            }
                        }
                    }
                    Status.ERROR -> {
                        progress_bar.visibility = View.GONE
                        Toast.makeText(context, "Check your internet connection", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun observeListMovies() {
        viewModel.getListNowPlayingComingSoon().observe(viewLifecycleOwner, Observer { listMovie ->
            if (listMovie != null) {
                when (listMovie.status) {
                    Status.LOADING -> progress_bar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        progress_bar.visibility = View.GONE
                        rv_movie.adapter?.let { adapter ->
                            when (adapter) {
                                is MovieAdapter -> {
                                    adapter.submitList(listMovie.data)
//                                    adapter.filter.filter("Venom: Let There Be Carnage")
                                    adapter.notifyDataSetChanged()
                                }
                            }
                        }
                    }
                    Status.ERROR -> {
                        progress_bar.visibility = View.GONE
                        Toast.makeText(context, "Check your internet connection", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
    private fun observeListComingSoon() {
        viewModel.getListNowPlayingComingSoon().observe(viewLifecycleOwner, Observer { listMovie ->
            if (listMovie != null) {
                when (listMovie.status) {
                    Status.LOADING -> progress_bar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        progress_bar.visibility = View.GONE
                        rv_comingsoon.adapter?.let { adapter ->
                            when (adapter) {
                                is MovieAdapter -> {
                                    adapter.submitList(listMovie.data)
//                                    adapter.filter.filter("Venom: Let There Be Carnage")
                                    adapter.notifyDataSetChanged()
                                }
                            }
                        }
                    }
                    Status.ERROR -> {
                        progress_bar.visibility = View.GONE
                        Toast.makeText(context, "Check your internet connection", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    //copyright satria junanda//

    private fun setupRecyclerView() {
        rv_movie.apply {
            val horizontalLayoutManagaer =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            layoutManager = horizontalLayoutManagaer
            adapter = MovieAdapter(this@MovieFragment)

        }

        rv_comingsoon.apply {
            val horizontalLayoutManagaer =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            layoutManager = horizontalLayoutManagaer
            adapter = TvShowAdapter(this@MovieFragment)
        }
    }

    override fun onItemClicked(data: MovieEntity) {
        startActivity(
            Intent(
                context,
                DetailActivity::class.java
            )
                .putExtra(DetailActivity.EXTRA_DATA, data.movieId)
                .putExtra(DetailActivity.EXTRA_TYPE, TYPE_MOVIE)
        )
    }

    override fun onItemClicked(data: TvShowEntity) {
        startActivity(
            Intent(
                context,
                DetailActivity::class.java
            )
                .putExtra(DetailActivity.EXTRA_DATA, data.tvShowId)
                .putExtra(DetailActivity.EXTRA_TYPE, Constants.TYPE_TVSHOW)
        )
    }

}
//copyright satria junanda//