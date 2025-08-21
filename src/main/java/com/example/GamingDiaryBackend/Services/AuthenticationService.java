package com.example.GamingDiaryBackend.Services;

import com.google.cloud.Timestamp;
import com.google.cloud.bigquery.*;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final BigQuery bq;

    public AuthenticationService() {
        this.bq = BigQueryOptions.getDefaultInstance().getService();
    }

    // Set up a service to run the needed authentication queries in the db
    // public String runQuery(String sql) {
    //     try {
    //         QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(sql).build();
    //         TableResult result = bq.query(queryConfig);

    //         StringBuilder sb = new StringBuilder();

    //         for (FieldValueList row : result.iterateAll()) {
    //             sb.append(row.toString()).append("\n");
    //         }
    //         return sb.toString();
    //     } catch (Exception e) {
    //         throw new RuntimeException("Authenitcation failed on Google BigQuery", e);
    //     }
    // }

    public String authenticateUser(String username, String pass){
        String safeUsername = username.replace("'", "''");
        String safePass = pass.replace("'", "''");

        String sql = String.format(
            "SELECT username FROM `gaming-diary-469514.Gaming_Diary_Data.UserCredentials` WHERE Username = '%s' AND Password = '%s'",
            safeUsername, safePass);

        try {
            QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(sql).build();
            TableResult result = bq.query(queryConfig);

            if(!result.iterateAll().iterator().hasNext()){
                return "No User Found";
            }
            else {
                StringBuilder sb = new StringBuilder();
                for (FieldValueList row : result.iterateAll()) {
                    sb.append(row.get("Username").getStringValue()).append("\n");
                }
                return sb.toString().trim();
            }
        } catch (Exception e) {
            throw new RuntimeException("Authentication failed on Google BigQuery", e);
        }
        
        //return "";
    }

    public void registerUser(String username, String password, String email){
        String safeUsername = username.replace("'", "''");
        String safePass = password.replace("'", "''");
        String safeEmail = email.replace("'", "''");

        // Get the max ID value
        String sql = "SELECT MAX(ID) as maxId FROM `gaming-diary-469514.Gaming_Diary_Data.UserCredentials`";
        long newId = 1;

        try {
            QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(sql).build();
            TableResult result = bq.query(queryConfig);
            FieldValueList row = result.iterateAll().iterator().next();
            if (!row.get("maxId").isNull()) {
                newId = row.get("maxId").getLongValue() + 1;
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to get max ID from BigQuery", e);
        }

        TableId tableId = TableId.of("gaming-diary-469514", "Gaming_Diary_Data", "UserCredentials");

        Map<String, Object> rowContent = new HashMap<>();
        rowContent.put("ID", newId);
        rowContent.put("Username", safeUsername);
        rowContent.put("Password", safePass);
        rowContent.put("Created_TS", Timestamp.now().toString());
        rowContent.put("Updated_TS", Timestamp.now().toString());
        rowContent.put("Last_Login_TS", Timestamp.now().toString());
        rowContent.put("Email", safeEmail);

        InsertAllRequest insertRequest = InsertAllRequest.newBuilder(tableId)
            .addRow(rowContent)
            .build();

        InsertAllResponse response = bq.insertAll(insertRequest);

        if(response.hasErrors()){
            throw new RuntimeException("Failed to insert user: " + response.getInsertErrors());
        }
    }
}