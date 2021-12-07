import java.util.Scanner;

public class Cinema {
    public static Scanner in = new Scanner(System.in);
    public static int currentIncome = 0;
    public static int rows;
    public static int seats;

    public static void main(String[] args) {
        System.out.println("Enter the number of rows:");
        rows = in.nextInt();

        System.out.println("Enter the number of seats in each row::");
        seats = in.nextInt();

        char[][] cinema = new char[rows+1][seats+1];

        initializeArray(cinema);

        System.out.println();
        printCinemaHall(cinema);

        System.out.println();
        menu(cinema);
    }

    public static int totalIncome() {
        int income = 0;
        for(int i = 1; i <= rows; i++) {
            income += getTicketPrice(i)*seats;
        }
        return income;
    }

    public static int numOfTickets(char[][] cinema) {
        int counter = 0;
        for(int i = 0; i <= rows; i++) {
            for(int j = 0; j <= seats; j++) {
                if(cinema[i][j] == 'B') {
                    counter++;
                }
            }
        }
        return counter;
    }

    public static boolean seatPurchased(char[][] cinema, int rowInput, int seatInput) {
        return cinema[rowInput][seatInput] == 'B';
    }

    public static void menu(char[][] cinema) {
        boolean loopEnd = false;
        while (!loopEnd) {
            System.out.println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
            int userInput = in.nextInt();
            System.out.println();
            switch (userInput) {
                case 1:
                    printCinemaHall(cinema);
                    System.out.println();
                    break;
                case 2:
                    buyTicket(cinema);
                    printCinemaHall(cinema);
                    System.out.println();
                    break;
                case 3:
                    int purchasedTickets = numOfTickets(cinema);
                    System.out.println("Number of purchased tickets: " + purchasedTickets);
                    System.out.printf("Percentage: %.2f%%%n", (double) purchasedTickets / (rows * seats) * 100);
                    System.out.println("Current income: $" + currentIncome);
                    int totalIncome = totalIncome();
                    System.out.println("Total income: $" + totalIncome);

                    break;
                case 0:
                    System.out.println("End");
                    loopEnd = true;
                    break;
                default:
                    System.out.println("That is not a valid input");
                    System.out.println();
                    break;
            }
        }
    }

    public static void initializeArray(char[][] cinema) {
        for(int i = 0; i < cinema.length; i++) {
            for(int j = 0; j < cinema[i].length; j++) {
                cinema[i][j] = 'S';
            }
        }
    }

    public static void buyTicket(char[][] cinema) {
        boolean loopEnd = false;
        while(!loopEnd) {
            int rowInput =  getRowInput();
            int seatInput =  getSeatInput();
            if(rowInput <= rows && seatInput <= seats) {
                if (!seatPurchased(cinema, rowInput, seatInput)) {
                    cinema[rowInput][seatInput] = 'B';
                    int ticketPrice = getTicketPrice(rowInput);
                    System.out.println("Ticket price: $" + ticketPrice);
                    currentIncome += ticketPrice;
                    loopEnd = true;
                } else {
                    System.out.println("That ticket has already been purchased!");
                }
            } else {
                System.out.println("Wrong input");
            }
        }
        System.out.println();
    }

    public static int getTicketPrice(int rowInput) {
        int frontHalf = getFrontHalf(rows);
        int ticketPrice;
        if(largeRoom(rows, seats) && rowInput <= frontHalf) {
            ticketPrice = 10;
        } else if(largeRoom(rows, seats) && (rowInput > frontHalf)){
            ticketPrice = 8;
        } else {
            ticketPrice = 10;
        }
        return ticketPrice;
    }

    public static void printCinemaHall(char[][] cinema) {
        System.out.println("Cinema:");
        for(int i = 1; i <= cinema[0].length-1; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        for(int i = 1; i <= cinema.length-1; i++) {
            System.out.print(i + " ");
            for(int j = 1; j <= cinema[i].length-1; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int getFrontHalf(int rows) {
        return rows / 2;
    }

    public static int getRowInput() {
        System.out.println("Enter a row number:");
        return in.nextInt();
    }

    public static int getSeatInput() {
        System.out.println("Enter a seat number in that row:");
        return in.nextInt();
    }

    public static boolean largeRoom(int rows, int seats) {
        return rows*seats > 60;
    }

}