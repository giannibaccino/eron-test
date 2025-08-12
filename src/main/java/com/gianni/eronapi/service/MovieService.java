package com.gianni.eronapi.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
public class MovieService {

    public List<String> getDirectors (int threshold) {
        int page = 1;
        int totalPages = 1;
        HashMap<String, Integer> directors = new HashMap<>();
        List<String> directorsList = new ArrayList<>();

        try {
            while (page <= totalPages) {
                URL url = new URL("https://wiremock.dev.eroninternational.com/api/movies/search?page=" + page);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                int responseCode = connection.getResponseCode();

                if (responseCode == 200) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder resp = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        resp.append(line);
                    }

                    reader.close();

                    JSONObject respJson = new JSONObject(resp.toString());
                    totalPages = respJson.getInt("total_pages");
                    JSONArray movieArray = respJson.getJSONArray("data");

                    for (int i = 0; i < movieArray.length(); i++) {
                        JSONObject movie = movieArray.getJSONObject(i);
                        String director = movie.getString("Director");

                        if (!directors.containsKey(director)) {
                            directors.put(director, 1);
                        } else {
                            directors.put(director, directors.get(director) + 1);
                        }
                    }

                    connection.disconnect();
                    page++;


                } else {
                    connection.disconnect();
                    throw new RuntimeException("Error while trying to get movies info");
                }
            }

            directors.forEach((key, value) -> {
                if (value > threshold) {
                    directorsList.add(key);
                }
            });
            Collections.sort(directorsList);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return directorsList;
    }
}
