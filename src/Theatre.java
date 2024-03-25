import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;


public class Theatre {
    // declare scanner object as a class variable
    public static Scanner scanner = new Scanner(System.in);
    // declare seats array as a class variable
    public static int[][] seats = new int[3][];

    //an Arraylist of tickets to save all tickets
    public  static ArrayList<Ticket> tickets = new ArrayList<>();
    private static ArrayList<Ticket> sortedTickets;


    public static void main(String[] args) {
        System.out.println("Welcome to the New Theatre!");

        // initialize seats array with appropriate number of seats
        seats[0] = new int[12];
        seats[1] = new int[16];
        seats[2] = new int[20];

        int option= -1;
        boolean quit = false;
        do {
        System.out.print("\n-----------------------------------------------------------------------------------------");

        System.out.println("\nPlease select an option: ");
        System.out.println("1) Buy a ticket");
        System.out.println("2) Print seating area");
        System.out.println("3) Cancel ticket");
        System.out.println("4) List available seats");
        System.out.println("5) Save to file");
        System.out.println("6) Load from file");
        System.out.println("7) Print ticket information and total price");
        System.out.println("8) Sort tickets by price");
        System.out.println("    0) Quit");
        System.out.print("-------------------------------------------------------------------------------------------");

            System.out.println(" ");
            System.out.print("\nEnter your option: ");
            option = scanner.nextInt();

            switch(option) {
                case 1:
                    buyTicket();
                    break;
                case 2:
                    printSeatingArea();
                    break;
                case 3:
                    cancelTicket();
                    break;
                case 4:
                    showAvailableSeats();
                    break;
                case 5:
                    saveToFile();
                    break;
                case 6:
                    loadFromFile();
                    break;
                case 7:
                    printTicketInfo(sortedTickets);
                    break;
                case 8:
                    sort_tickets();
                    break;
                case 0:
                    System.out.println("Thank you! Good Bye!");
                    break;
                default:
                    System.out.println("Invalid option,Please try again.");
            }
        } while (option != 0);

        // close the scanner object
        scanner.close();
    }

   


    public static void buyTicket() {
        String firstName;
        String surname;
        String email;

        System.out.print("\nEnter row number (1-3): ");
        int row = scanner.nextInt() - 1;
        if (row < 0 || row >= seats.length) {
            System.out.println("Invalid row number");
            return;
        }

        int seatNumber;
        if (row == 0) {
            seatNumber = 12;
        } else if (row == 1) {
            seatNumber = 16;
        } else {
            seatNumber = 20;
        }

        System.out.print("Enter seat number (1-" + seatNumber + "): ");
        int seat = scanner.nextInt() - 1;
        if (seat < 0 || seat >= seats[row].length) {
            System.out.println("Invalid seat number");
            return;
        }

        System.out.print("\nEnter First name: ");
        firstName = scanner.next();

        System.out.print("Enter Surname: ");
        surname = scanner.next();

        System.out.print("Enter email: ");
        email = scanner.next();

        System.out.println("First row price is 100, Second row price is 250, Third row price is 200.");
        System.out.print("Enter your seat price: $");
        int ticket_price = scanner.nextInt();
        seats[row][seat]=1;
        Person person = new Person(firstName,surname,email);
        Ticket ticket = new Ticket( row,  seat,  ticket_price, person);
        tickets.add(ticket);
        while (true) {
            if ((row == 0 && ticket_price == 100) || (row == 1 && ticket_price == 250) || (row == 2 && ticket_price == 200)) {
                System.out.println("Thank you for your purchase.");
                break;
            } else {
                System.out.println("The entered price is invalid.");
            }
        }
    }


    public static void printSeatingArea() {
        System.out.println("     ***********");
        System.out.println("     *  STAGE  *");
        System.out.println("     ***********");
        for (int i = 0;i< seats.length;i++){
            if (i==0){
                System.out.print("    ");
            }
            if (i==1){
                System.out.println();
                System.out.print("  ");
            }
            if (i==2){
                System.out.println("");
            }
            for(int j = 0;j< seats[i].length;j++){
                if (seats[i][j]==1){
                    System.out.print("X");
                }else{
                    System.out.print("0");
                }
                if (j==5 && i==0){
                    System.out.print(" ");

                }else if (j==7 && i==1){
                    System.out.print(" ");
                }else if (j==9 && i==2){
                    System.out.print(" ");
                }
            }
        }

    }

    public static void cancelTicket() {

        System.out.print("Enter your row number(1-3): ");
        int row = scanner.nextInt();

        int seatRange;
        if (row == 1){
            seatRange = 12;
        }else if (row == 2){
            seatRange = 16;
        }else if (row == 3){
            seatRange = 20;
        }else{
            System.out.println("Invalid row number.");
            return;
        }

        System.out.print("Enter seat number (1-" + seatRange + "): ");
        int seat = scanner.nextInt();
        if (row < 1 || row > seats.length || seat < 1 || seat > seats[row - 1].length) {
            System.out.println("Invalid seat number.");

        }
        if (seats[row-1][seat-1]==0){
            System.out.println("Seat is already available.");
            return;
        }
        for(int i = 0; i < tickets.size(); i++) {
            if(tickets.get(i).getRow() == row -1 && tickets.get(i).getSeat() == seat -1) {
                tickets.remove(i);
            }
        }
        seats[row-1][seat-1]=0;
        System.out.println("Cancel successfully.");

    }

    public static void showAvailableSeats(){
        for (int i=0; i < seats.length; i++) {
            System.out.print("Available seats in row  " + (i + 1) + ": ");
            int j;
            int lastSeatIndex = -1;
            for (j = 0; j < seats[i].length; j++) {
                if (seats[i][j] == 0) {
                    lastSeatIndex = j;
                    System.out.print((j + 1) + ",");
                }
            }
            if (lastSeatIndex != -1){
                System.out.print("\b");//remove the last comma.
                System.out.println(".");
            }else {
                System.out.println("No available seats.");
            }
        }
    }

    public static void saveToFile() {
        try{
            File textfile = new File("my_save.txt");
            //saves the 3 arrays with the row's information in a file.
            FileWriter writer = new FileWriter("my_save.txt");
            for (int i = 0; i< seats.length; i++){
                for (int j = 0; j<seats[i].length; j++){
                    if(seats[i][j]==0){
                        writer.write("o ");
                    }else{
                        writer.write("x ");
                    }
                }
                writer.write("\n");
            }
            writer.close();
            System.out.println("Data saved to file... ");
        }catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();

        }

    }

    public static void loadFromFile() {
        try{
            File myObj = new File("my_save.txt");
            Scanner myReader = new Scanner(myObj);
            System.out.println("Loading from the file...");
            System.out.println(" ");
            while (myReader.hasNextLine()){
                //loads the save file and restore the 3 arrays with the row's information
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        }catch (FileNotFoundException e){
            System.out.println("An error occured.");
            e.printStackTrace();
        }
    }

    private static void printTicketInfo(ArrayList<Ticket> sortedTickets) {
        // implementation for printing ticket information and total price
        int total = 0;
        int ticket;
        int row;
        int seat;
        //loop through the Arraylist using for loop
        for(int i = 0; i < tickets.size(); i++) {
            ticket = i + 1;
            row = tickets.get(i).getRow() + 1;
            seat = tickets.get(i).getSeat() + 1;
        }
        double Total = 0.0;
        System.out.println(" ");
        System.out.println("TICKET DETAILS...!");
        for (Ticket tickets: tickets) {
            tickets.print();//prints all the ticket information for all tickets.
            System.out.println("-------------------------------------------------------------------------------------");
            Total += tickets.getPrice();
        }
        System.out.println("Total income from tickets: $"+ Total +"\n");
    }
    private static void sort_tickets() {
        ArrayList<Ticket> sortedTickets = new ArrayList<>(tickets);
        sortedTickets.sort(Comparator.comparingDouble(Ticket::getPrice));
        System.out.print("Sorted tickets by price(Cheapest first):\n");
        //returns a new list of Tickets sorted by Ticket price in ascending order.
        printTicketInfo(sortedTickets);
    }
}