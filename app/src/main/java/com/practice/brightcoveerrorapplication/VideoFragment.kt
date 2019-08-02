package com.practice.brightcoveerrorapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.brightcove.player.appcompat.BrightcovePlayerFragment
import com.brightcove.player.edge.Catalog
import com.brightcove.player.edge.VideoListener
import com.brightcove.player.model.Video
import kotlinx.android.synthetic.main.fragment_video_test.*

class VideoFragment : BrightcovePlayerFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video_test, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val eventEmitter = brightcove_video_view.eventEmitter
        val catalog = Catalog(eventEmitter, getString(R.string.brightcove_account), getString(R.string.brightcove_policy))

        catalog.findVideoByID(getString(R.string.videoId), object : VideoListener() {
            override fun onVideo(video: Video) {
                brightcove_video_view.add(video)
                brightcove_video_view.setOnClickListener {
                    if (brightcove_video_view.isPlaying) {
                        brightcove_video_view.stopPlayback()
                    } else {
                        brightcove_video_view.start()
                    }
                }
            }

            override fun onError(error: String?) {
                super.onError(error)
            }
        })
    }

}