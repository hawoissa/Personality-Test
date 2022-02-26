import java.util.*;
import java.io.*;

public class Personality {
    public static final int dimensions = 4;
    public static void main(String[] args) throws FileNotFoundException {
        //Scanner input = new Scanner(new File("personality1.txt"));
        
        Scanner console = new Scanner(System.in);
        Scanner input = correctFile(console);
        System.out.print("Output file name: ");
        String fileName = console.nextLine();
        PrintStream output = new PrintStream(new File(fileName));
        
        while (input.hasNextLine()) {
           String name = input.nextLine();
           String letters = input.nextLine();
           
           int[] countsA = new int[dimensions];
           int[] countsB = new int[dimensions];
           
           computeCounts(letters, countsA, countsB);
           
           
           int[] total = computePercentage(countsA, countsB);
           
           String result = computePersonality(total);
           
           
           System.out.println(name + ": " + Arrays.toString(total) + " = " + result);
           output.println(name + ": " + Arrays.toString(total) + " = " + result);
           
         
        }
       
    }
    
    public static Scanner correctFile(Scanner console) throws FileNotFoundException {
      System.out.print("Input file name: ");
      String inFileName = console.nextLine();
      File inFile = new File(inFileName);
      
      return new Scanner(inFile);
   }
    
    public static void computeCounts (String str, int[] countsA, int[] countsB) {
        for (int i = 0; i < str.length(); i++) {
            char letter = str.charAt(i);
            if (letter == 'A'|| letter == 'a') {
               countsA[(i%7+1)/2]++;
            } else if (letter == 'B' || letter == 'b') {
               countsB[(i%7+1)/2]++;
            }
        }
    }
    
    public static int[] computePercentage (int[] countsA, int[] countsB) {
         int counts[] = new int[dimensions];
         
         for (int i = 0; i < dimensions; i++) {
            double a = countsB[i] + countsA[i];
            //counts[i] = (int)(((double)countsB[i] / a) * 100);
            //counts[i] = (int)((countsB[i] / a) * 100);
            double percentage = ((countsB[i] / a) * 100);
            //int roundedPercent = (int)Math.round(percentage);
            counts[i] = (int)Math.round(percentage);
         }
         return counts;
    }
    
    public static String computePersonality (int[] total) {
         String personalityA = "ESTJ"; 
         String personalityB = "INFP";
         String result = "";
         char letter = 'a';
         for (int i = 0; i < dimensions; i++) {
            if ( total[i] < 50) {
               letter = personalityA.charAt(i);
               result += letter;
            } else if (total[i] > 50) {
               letter = personalityB.charAt(i);
               result += letter;
            } else {
               letter = 'X';
               result += letter;
            }
         } 
         
         return result;               
         
    }
}

 
