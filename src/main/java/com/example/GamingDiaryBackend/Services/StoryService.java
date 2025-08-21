package com.example.GamingDiaryBackend.Services;

import com.example.GamingDiaryBackend.Models.Story;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

import org.springframework.beans.factory.annotation.Autowired;
//import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class StoryService {
    private final Firestore db;

    @Autowired
    public StoryService() {
        this.db = FirestoreOptions.getDefaultInstance().getService();
    }

    // Function to get most popular stories
    public List<Story> getStories() {
        try {
            CollectionReference storiesReference = db.collection("Stories");
            ApiFuture<QuerySnapshot> query = storiesReference.orderBy("Views", Query.Direction.DESCENDING)
                                             .limit(5) // Only pulls 5 top stories  
                                             .get();
            QuerySnapshot querySnapshot = query.get();

            return querySnapshot.getDocuments()
                    .stream()
                    .map(doc -> doc.toObject(Story.class))
                    .collect(Collectors.toList());
        
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Firestore query failed", e);
        }
    }

    public Story postStory(Story story) {
        try {
            CollectionReference storiesReference = db.collection("Stories");

            ApiFuture<DocumentReference> future = storiesReference.add(story);
            future.get();

            return story;
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to post story", e);
        }
    }

    public Story updateStory(String id, Story story){
        try{
            CollectionReference storiesReference = db.collection("Stories");
            DocumentReference docRef = storiesReference.document(id);

            ApiFuture<WriteResult> future = docRef.set(story, SetOptions.merge());
            future.get();

            return story;
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to update story", e);
        }
    }
}
