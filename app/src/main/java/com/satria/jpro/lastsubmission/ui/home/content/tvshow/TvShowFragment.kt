package com.satria.jpro.lastsubmission.ui.home.content.tvshow
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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.satria.jpro.lastsubmission.R
import com.satria.jpro.lastsubmission.data.source.local.entity.MovieEntity
import com.satria.jpro.lastsubmission.data.source.local.entity.TvShowEntity
import com.satria.jpro.lastsubmission.ui.detail.DetailActivity
import com.satria.jpro.lastsubmission.ui.home.HomeViewModel
import com.satria.jpro.lastsubmission.ui.home.content.movie.MovieCallback
import com.satria.jpro.lastsubmission.ui.home.content.movie.PopularAdapter
import com.satria.jpro.lastsubmission.utils.Constants
import com.satria.jpro.lastsubmission.utils.Constants.TYPE_TVSHOW
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
class TvShowFragment : DaggerFragment(), MovieCallback {

    private lateinit var viewModel: HomeViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()

        activity?.let { setupViewModel(it) }
        observeListPopular()


    }

    private fun setupViewModel(fragmentActivity: FragmentActivity) {
        viewModel = ViewModelProvider(fragmentActivity, factory)[HomeViewModel::class.java]
    }



    private fun observeListPopular() {
        viewModel.getListNowPlayingComingSoon().observe(viewLifecycleOwner, Observer { listMovie ->
            if (listMovie != null) {
                when (listMovie.status) {
                    Status.LOADING -> progress_bar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        progress_bar.visibility = View.GONE
                        rv_tvshow.adapter?.let { adapter ->
                            when (adapter) {
                                is PopularAdapter -> {
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
        rv_tvshow.apply {
            val horizontalLayoutManagaer =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            layoutManager = GridLayoutManager(context,2)
            adapter = PopularAdapter(this@TvShowFragment)

        }

    }

    override fun onItemClicked(data: MovieEntity) {
        startActivity(
            Intent(
                context,
                DetailActivity::class.java
            )
                .putExtra(DetailActivity.EXTRA_DATA, data.movieId)
                .putExtra(DetailActivity.EXTRA_TYPE, Constants.TYPE_MOVIE)
        )
    }

}
//copyright satria junanda//