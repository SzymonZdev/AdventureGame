package com.example;

import com.example.Location;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static Locations locations = new Locations();
    private static Map<String, String> vocabulary = new HashMap<>();
    private static final int STARTING_LOCATION = 1;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        vocabulary.put("QUIT", "Q");
        vocabulary.put("SOUTH", "S");
        vocabulary.put("NORTH", "N");
        vocabulary.put("EAST", "E");
        vocabulary.put("WEST", "W");


        Location currentLocation = locations.getLocation(STARTING_LOCATION);
        while(true) {
            System.out.println(currentLocation.getDescription());
            if(currentLocation.getLocationID() == 0) {
                break;
            }

            Map<String, Integer> exits = currentLocation.getExits();
            System.out.print("Available exits are ");
            for(String exit: exits.keySet()) {
                System.out.print((exit + ", "));
            }
            System.out.println();

            String direction = scanner.nextLine().toUpperCase();
            if(direction.length() > 1) {
                String[] words = direction.split(" ");
                for(String word: words){
                    if(vocabulary.containsKey(word)) {
                        direction = vocabulary.get(word);
                        break;
                    }
                }
            }

            if(exits.containsKey(direction)) {
                currentLocation = locations.getLocation(currentLocation.getExits().get(direction));
            } else {
                System.out.println("You cannot go in that direction");
            }
        }

        locations.close();
    }
}
