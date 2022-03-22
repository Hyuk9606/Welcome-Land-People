package com.ssafy.boonmoja.oauth.info;

import java.util.Map;

public abstract class OAuth2UserInfo {
    protected Map<String, Object> attributes;
    
    public OAuth2UserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
    
    public Map<String, Object> getAttributes() {
        return attributes;
    }
    
    public abstract String getId();
    
    public abstract String getName();
    
    public String getEmail(){
        return "SOCIAL";
    };
    
//    public abstract String getImageUrl();
    
    public abstract String getAgeRange();
    
    public abstract String getGender();
}
