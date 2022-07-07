package org.patika;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScoreHandler extends Score {
    protected static List<Score> scoreList = new ArrayList<>();
    private static File jsonFile;
    private static ObjectMapper mapper;

    public static void init(){
        try {
            jsonFile = new File("scores.json");
            // Create the JSON file if it does not exist, otherwise do nothing.
            jsonFile.createNewFile();
            readJSONFile();
        } catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    // Reads the scores.json and parses it into scoreList. If the file is invalid, show an error.
    public static void readJSONFile(){
        mapper = new ObjectMapper();
        try {
            scoreList = mapper.readValue(jsonFile, new TypeReference<List<Score>>(){});
        } catch (IOException e){
            System.err.println("Invalid \"scores.json\" file, discarding...");
        }
    }

    // Writes the values of scoreList to scores.json file.
    public static void writeJSONFile() throws IOException {
        mapper = new ObjectMapper();
        // Create JSON string from the scoreList data.
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(scoreList);
        try {
            // Write the JSON string to student_list.json.
            BufferedWriter jsonWriter = new BufferedWriter(new FileWriter(jsonFile));
            jsonWriter.write(json);
            jsonWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    // Check if there is already an entry with the same name.
    // If so, add the score and game count values into that entry. Otherwise, create a new entry.
    public static void addScore(Score score){
        int scoreIndex = getScoreIndexByName(score.getName());
        if (scoreIndex == -1) {
            scoreList.add(score);
        } else {
            scoreList.get(scoreIndex).setScore(scoreList.get(scoreIndex).getScore() + 1);
            scoreList.get(scoreIndex).setGameCount(scoreList.get(scoreIndex).getGameCount() + 1);
        }
    }

    // Gets score list index by name attribute.
    public static int getScoreIndexByName(String name){
        for (int i = 0; i < scoreList.size(); i++){
            if (scoreList.get(i).getName().equals(name)){
                return i;
            }
        }
        return -1;
    }

    // Returns an output string of each list members' .toString() method.
    public static String getAll(){
        StringBuilder output = new StringBuilder();
        for (Score score : scoreList){
            output.append(score.toString());
        }
        return output.toString();
    }

}
