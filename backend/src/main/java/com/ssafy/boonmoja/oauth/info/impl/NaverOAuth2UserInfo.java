package com.ssafy.boonmoja.oauth.info.impl;

import com.ssafy.boonmoja.oauth.info.OAuth2UserInfo;

import java.util.Map;

public class NaverOAuth2UserInfo extends OAuth2UserInfo {
    
    public NaverOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        if (response == null) {
            return null;
        }

        return (String) response.get("id");
    }

    @Override
    public String getName() {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        if (response == null) {
            return null;
        }

        return (String) response.get("nickname");
    }

//    @Override
//    public String getEmail() {
//        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
//
//        if (response == null) {
//            return null;
//        }
//
//        return (String) response.get("email");
//    }

//    @Override
//    public String getImageUrl() {
//        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
//
//        if (response == null) {
//            return null;
//        }
//
//        return (String) response.get("profile_image");
//    }
    
    @Override
    public String getGender() {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        
        if (response == null) {
            return null;
        }
        
        return (String) response.get("gender");
    }
    
    @Override
    public String getAgeRange() {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        
        if (response == null) {
            return null;
        }
        return (String) response.get("age");
    }
}
