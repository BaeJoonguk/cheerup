package com.cheerup.batch.service;

import java.io.IOException;
import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

@Service
public class SolutionMatchService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	FirebaseApp firebaseApp = null;
	
	@PostConstruct
	public void post() throws IOException {
		FirebaseOptions options = new FirebaseOptions.Builder()
			      .setServiceAccount(new ClassPathResource("fir-test-fb80b-firebase-adminsdk-7rbna-1fb0b3ae29.json").getInputStream())
			      .setDatabaseUrl("https://fir-test-fb80b.firebaseio.com/")
			      .build();
		firebaseApp = FirebaseApp.initializeApp(options);
	}

	public void doSomething() throws IOException {
		final FirebaseDatabase database = FirebaseDatabase.getInstance(firebaseApp);
		DatabaseReference usersRef = database.getReference("users");
		usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
			
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				HashMap users = (HashMap)dataSnapshot.getValue();
				System.out.println(users.toString());
				
				HashMap<String, Object> statistics = new HashMap<>();
				statistics.put("userCount", users.size());
				
				DatabaseReference statisticsRef = database.getReference("statistics");
				statisticsRef.setValue(statistics);
			}
			
			@Override
			public void onCancelled(DatabaseError arg0) {
			}
		});
		
		logger.info("doSomething()");
	}
}