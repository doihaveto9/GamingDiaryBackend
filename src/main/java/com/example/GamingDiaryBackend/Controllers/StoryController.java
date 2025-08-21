package com.example.GamingDiaryBackend.Controllers;

import org.springframework.web.bind.annotation.*;

import java.util.List;
//import java.util.stream.Collectors;

import com.example.GamingDiaryBackend.DTO.StoryDTO;
import com.example.GamingDiaryBackend.Models.Story;
import com.example.GamingDiaryBackend.Models.UpdateRequest;
import com.example.GamingDiaryBackend.Services.StoryService;
import com.google.cloud.Timestamp;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/stories")
public class StoryController {
    
    private final StoryService storyService;

    public StoryController(StoryService storyService){
        this.storyService = storyService;
    }

    @GetMapping("/getCurrentStories")
    public List<Story> getCurrentStories() {
        return storyService.getStories();
    }

    @PostMapping("/createStory")
    public Story postStory(@RequestBody StoryDTO dto) {
        Story story = new Story();

        story.setAuthor(dto.getAuthor());
        story.setTitle(dto.getTitle());
        story.setComments(dto.getComments());
        story.setCreated_ts(Timestamp.parseTimestamp(dto.getCreated_ts()));
        story.setUpdated_ts(Timestamp.parseTimestamp(dto.getUpdated_ts()));
        story.setViews(dto.getViews());
        
        return storyService.postStory(story);
    }

    @PostMapping("/updateStory")
    public Story updateStory(@RequestBody UpdateRequest request) {
        StoryDTO dto = request.getStory();

        Story story = new Story();

        story.setAuthor(dto.getAuthor());
        story.setTitle(dto.getTitle());
        story.setComments(dto.getComments());
        story.setCreated_ts(Timestamp.parseTimestamp(dto.getCreated_ts()));
        story.setUpdated_ts(Timestamp.parseTimestamp(dto.getUpdated_ts()));
        story.setViews(dto.getViews());
        
        String id = request.getId();

        return storyService.updateStory(id, story);
    }
}
