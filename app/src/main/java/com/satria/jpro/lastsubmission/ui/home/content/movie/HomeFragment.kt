package com.satria.jpro.lastsubmission.ui.home.content.movie
//copyright satria junanda//
import android.app.ActionBar
import android.graphics.BitmapFactory
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.satria.jpro.lastsubmission.BuildConfig
import com.satria.jpro.lastsubmission.R
import kotlinx.android.synthetic.main.fragment_maps.*
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.progress_bar.*
import org.osmdroid.api.IMapController
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment :  Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_maps, container, false)
        val ctx = requireActivity().applicationContext

        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID);
        val map = view.findViewById<MapView>(R.id.mapview)
        map.setUseDataConnection(true)
        //val map = view.findViewById(R.id.map) as MapView
        map.setTileSource(TileSourceFactory.MAPNIK)
        //map.setBuiltInZoomControls(true) //Map ZoomIn/ZoomOut Button Visibility
        map.setMultiTouchControls(true)
        val mapController: IMapController
        mapController = map.getController()

        //mapController.zoomTo(14, 1)
        mapController.setZoom(15)

        val mGpsMyLocationProvider = GpsMyLocationProvider(activity)
        val mLocationOverlay = MyLocationNewOverlay(mGpsMyLocationProvider, map)
        mLocationOverlay.enableMyLocation()
        mLocationOverlay.enableFollowLocation()

        val icon = BitmapFactory.decodeResource(resources, R.drawable.ic_menu_compass)
//        mLocationOverlay.setPersonIcon(icon)
//        map.getOverlays().add(mLocationOverlay)


        mLocationOverlay.runOnFirstFix {
            map.getOverlays().clear()
            map.getOverlays().add(mLocationOverlay)
//            indc.text=("Lokasi : " +mLocationOverlay.myLocation.latitude+" , "+mLocationOverlay.myLocation.longitude)
            latt.text="Latitude : "+mLocationOverlay.myLocation.latitude.toString()
            longg.text="Longitude : "+mLocationOverlay.myLocation.longitude.toString()
//
//            mapController.animateTo(mLocationOverlay.myLocation)
        }


        return view
    }


}


//copyright satria junanda//