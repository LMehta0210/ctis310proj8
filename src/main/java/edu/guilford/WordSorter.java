package edu.guilford;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Hello world!
 *
 */
public class WordSorter {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Scanner scanFile = null;
        Path dataLocation = null; // what's our data file
        String fileName = null; // what's the name of the file
        boolean validData = false; // did we get valid data from the file
        double[][] values = null; // where we are putting the data when we get it?

        // let's get the file name from the user and try to open it
        // System.out.println("Enter the data file name: ");
        // fileName = scan.next();

        fileName = "source.txt";

        // open the file and read the data
        try {
            dataLocation = Paths.get(WordSorter.class.getResource("/" + fileName).toURI());
            // FileReader is a stream that readds a file one character (Unicode) at a time
            // It's meant for text files
            // If we had a binary file, we'd use FileInputStream, that reads a file one byte
            // at a time
            FileReader dataFile = new FileReader(dataLocation.toString());
            BufferedReader dataBuffer = new BufferedReader(dataFile); // so that we are reading the data efficiently
            // (not one character at a time)
            scanFile = new Scanner(dataBuffer);
            TreeSet<String> allWords = getAllWords(scanFile);
            System.out.println(allWords);
        } catch (URISyntaxException | FileNotFoundException | NullPointerException e) { // | allow us to catch multiple
                                                                                        // exception types
            // and do same basic thing with any of them
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        /*
         * //write the data to a file
         * try {
         * writeData(values, "output.txt");
         * } catch (URISyntaxException | IOException e) {
         * e.printStackTrace();
         * System.exit(1); // 1 means there was an error
         * }
         */
    }

    public static TreeSet<String> getAllWords(Scanner scan) {
        TreeSet<String> words = new TreeSet<String>();
        while (scan.hasNext()) {
            String rawWord = scan.next().toLowerCase();
            // remove punctutation, buyt not special characters
            rawWord = rawWord.replaceAll("[^a-zA-Z0-9]", "");
            // remove if the token is a number
            if (rawWord.matches("[0-9]+")) {
                continue;
            }
            //if the String isn't empty, add it to the Tree Set
            if (!rawWord.isEmpty()) {
                words.add(rawWord);
            }
        }
        return words;

    }

    // write values to a file
    public static void writeData(double[][] values, String location) throws URISyntaxException, IOException {
        // "throws" means "not our problem", it's the problem of whoever asked us to run
        // this method
        // Get the path of the right folder
        Path locationPath = Paths.get(WordSorter.class.getResource("edu/guilford/").toURI());
        // Open a file in that folder
        FileWriter fileLocation = new FileWriter(locationPath.toString() + "/" + location);
        BufferedWriter bufferWrite = new BufferedWriter(fileLocation);
        // write the data to the file
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[i].length; j++) {
                bufferWrite.write(values[i][j] + " ");
            }
            bufferWrite.newLine();
        }
        // Always close your files when you're done so that you flush the buffer
        bufferWrite.close();
    }

    public static class ScannerException extends Exception {
        public ScannerException(String message) {
            super(message);
        }
    }
}
