import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/**
 * This progam helps budget through a month when a person only has 600 dollars to spend. It reads your current amount from a .txt file, can output it to the user, and can also 
 * subtract whatever spendings you have from your current amount.
 * @author Trevor Harmon
 * @version 1.0
 */
public class Budget {
    Scanner scnr = new Scanner(System.in);
    
    public void writeToFileDate(int answer ){
        try {
            FileWriter writer = new FileWriter("budget.txt", true);
            double lastLine = lastLineOfFile();
            writer.write("Date: " + answer + "\n");
            writer.write(Double.toString(lastLine) + "\n");
            writer.close();
        } catch (IOException e){
            System.out.println("An error occured.");
            e.printStackTrace();
        }   
    }
    public void writeToFileMonth(String answer){
        try {
            FileWriter writer = new FileWriter("budget.txt", true);
            writer.write(answer + ": \n");
            writer.write("600\n");
            writer.close();
        } catch(IOException e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }
    }
    public void writeToFilePrice(double amount){
        try {
            FileWriter writer = new FileWriter("budget.txt", true);
            writer.write(Double.toString(amount) + "\n");
            writer.close();
        } catch(IOException e){
            System.out.println("An error occured.");
            e.printStackTrace();
        }
    }

    public double lastLineOfFile(){
        String filePath = "budget.txt"; 
        String lastLine = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                lastLine = currentLine;
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return Double.parseDouble(lastLine);
    }

    /**
     * 
     * @param answer passes the char variable answer through and sets it to whatever the user inputs
     * @return the character that the user inputted. 
     */
    public char getAnswer(){
        char c = scnr.next(".").charAt(0);
        return c;
    }

    /**
     * Outputs a message that lets the user know what buttons to press to be able to run whichever function.
     */
    public void runMessage(){
        System.out.println("Hello! Welcome to the budgeting app. Would you like to run me? Y/N"); 
    }
    public void spendings(){
        System.out.println("Is it a new day? Ex. Y/N");
        char dayAnswer = scnr.next(".").charAt(0);
        if (dayAnswer == 'y' || dayAnswer == 'Y'){
            System.out.println("What day is it?");
            int date = scnr.nextInt();
            // This line should be used to output the int variable date into the .txt file
            writeToFileDate(date);
        }
            System.out.println("How much did you spend today?");
            double spending = scnr.nextDouble();
            double total = lastLineOfFile();
            total -= spending;
            writeToFilePrice(total);
    }
    public void viewAmount() {
        System.out.printf("Your amount is: %.2f.%n", lastLineOfFile());
    }
    public void checkDays() {
        double days = lastLineOfFile() / 18;
        System.out.printf("You have %.2f left.%n", days );
    }
    public void addMonth(){
        System.out.println("What month is it? Make sure to spell it correctly");
        String month = scnr.next();
        writeToFileMonth(month);
    }
    public static void main(String[]args){
        
        Budget budget = new Budget();
        budget.runMessage();
        char answer1 = budget.getAnswer();
        

        while (answer1 == 'y' || answer1 == 'Y'){
            System.out.println("Press S for spending, V to view your current amount, D to check remaining days of meals per $18, or M to add a new month.");
            char answer = budget.getAnswer();
            if (answer == 's' || answer == 'S'){
                budget.spendings();
            }
            else if (answer == 'v' || answer == 'V'){
                budget.viewAmount();
            }
            else if(answer == 'm' || answer == 'M'){
                budget.addMonth();
            }
            else if(answer == 'd' || answer == 'D'){
                budget.checkDays();
            }
            System.out.println("Would you like to run again? Y/N");
            answer1 = budget.getAnswer();
        }


    }

}
