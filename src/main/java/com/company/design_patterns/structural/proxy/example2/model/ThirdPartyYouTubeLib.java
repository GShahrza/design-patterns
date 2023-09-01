package com.company.design_patterns.structural.proxy.example2.model;

import java.util.HashMap;

public interface ThirdPartyYouTubeLib {

    HashMap<String,Video> popularVideos();

    Video getVideo(String videoId);
}
