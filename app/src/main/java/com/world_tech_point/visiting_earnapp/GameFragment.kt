package com.world_tech_point.visiting_earnapp

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.facebook.shimmer.ShimmerFrameLayout
import com.startapp.sdk.adsbase.StartAppAd
import com.startapp.sdk.adsbase.StartAppSDK


class GameFragment : Fragment() {

    var freeFireTV: TextView? = null
    var pubgTV: TextView? = null
    var pubgLiteTV: TextView? = null
    var ludoTV: TextView? = null

    var freeFire: LinearLayout? = null
    var pubg:LinearLayout? = null
    var pubgLite:LinearLayout? = null
    var ludo:LinearLayout? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        freeFire?.setOnClickListener(View.OnClickListener {

            go("https://showcase.codethislab.com/games/zippy_pixie/")

        })
        pubg?.setOnClickListener(View.OnClickListener {

           // go("Ludo")
            var i = Intent(context, ReferActivity::class.java)
            startActivity(i)

        })

        pubgLite?.setOnClickListener(View.OnClickListener {

            go("Ludo")

        })

        ludo?.setOnClickListener(View.OnClickListener {

            go("Ludo")

        })

    }

    private fun go(value: String){

        var i = Intent(context, GameWebViewActivity::class.java)
        i.putExtra("url", value)
        startActivity(i)
        StartAppAd.showAd(context)

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root =  inflater.inflate(R.layout.fragment_game, container, false)

        context?.let { StartAppSDK.init(it, "201742204", false) }
        StartAppSDK.setTestAdsEnabled(true)


        freeFireTV = root.findViewById<TextView>(R.id.freeFireStatus)
        pubgTV = root.findViewById<TextView>(R.id.PUBGMatchStatus)

        pubgLiteTV = root.findViewById<TextView>(R.id.pubgLiteGameStatus)
        ludoTV = root.findViewById<TextView>(R.id.ludoGameStatus)

        freeFire = root.findViewById(R.id.freeFireGame)
        pubg = root.findViewById(R.id.pubgGame)
        pubgLite = root.findViewById(R.id.pubgLiteGame)
        ludo = root.findViewById(R.id.ludoGame)

        val container2 = root.findViewById(R.id.shimmer_view_container2) as ShimmerFrameLayout
        container2.startShimmer() // If auto-start is set to false


        val imageList = ArrayList<SlideModel>() // Create image list
        imageList.add(SlideModel("https://image.shutterstock.com/image-photo/concept-earning-spending-money-dice-260nw-1416371084.jpg", "", ScaleTypes.FIT))
        imageList.add(SlideModel("https://image.shutterstock.com/image-photo/earning-wood-word-on-compressed-260nw-794016937.jpg", "", ScaleTypes.FIT))
        imageList.add(SlideModel("https://earningtricks.info/wp-content/uploads/2020/03/money-earning-apps-in-india.jpg", "", ScaleTypes.FIT))


        val imageSlider = root.findViewById<ImageSlider>(R.id.image_slider)
        imageSlider.setImageList(imageList)
        imageSlider.startSliding(5000) // with new period
        imageSlider.startSliding()
        imageSlider.stopSliding()
        imageSlider.setItemClickListener(object : ItemClickListener {
            override fun onItemSelected(position: Int) {

                if (position == 0) {

                    try {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://m.youtube.com/channel/UCgTbF5i9nrQuMRgvS1YwGtw")))
                    } catch (e: ActivityNotFoundException) {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://m.youtube.com/channel/UCgTbF5i9nrQuMRgvS1YwGtw")))
                    }


                } else if (position == 1) {

                    try {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/joinchat/PdPVixfYYxmDAxXjQwrz2w")))
                    } catch (e: ActivityNotFoundException) {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/joinchat/PdPVixfYYxmDAxXjQwrz2w")))
                    }

                } else if (position == 2) {

                } else if (position == 3) {

                } else if (position == 4) {

                }
            }
        })

        return root;
    }



}