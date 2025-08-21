package com.example.GamingDiaryBackend.Models;

import com.example.GamingDiaryBackend.DTO.StoryDTO;

public class UpdateRequest {
    private StoryDTO story;
    private String id;

    public StoryDTO getStory(){
        return story;
    }

    public void setStory(StoryDTO story){
        this.story = story;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }
    
}
