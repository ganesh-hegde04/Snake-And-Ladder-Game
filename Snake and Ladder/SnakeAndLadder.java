import java.util.*;

public class SnakeAndLadder {
    private static final Map<Integer, Integer> snakes = new HashMap<>();
    private static final Map<Integer, Integer> ladders = new HashMap<>();
    private static final int WINNING_POSITION = 100;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        // Snakes
        snakes.put(99, 54);
        snakes.put(70, 55);
        snakes.put(52, 42);
        snakes.put(25, 2);
        snakes.put(95, 72);

        // Ladders
        ladders.put(6, 25);
        ladders.put(11, 40);
        ladders.put(60, 85);
        ladders.put(46, 90);
        ladders.put(17, 69);

        System.out.println("=== Welcome to Snake and Ladder ===");
        System.out.print("Enter number of players: ");
        int numPlayers = sc.nextInt();
        sc.nextLine();
        String[] playerNames = new String[numPlayers];
        int[] positions = new int[numPlayers];

        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Enter name for Player " + (i + 1) + ": ");
            playerNames[i] = sc.nextLine();
            positions[i] = 0;
        }
        boolean gameWon = false;
        int currentPlayer = 0;
        while (!gameWon) {
            System.out.println("\n" + playerNames[currentPlayer] + "'s turn.");
            printSnakesAndLadders();
            System.out.print("Press Enter to roll the dice...");
            sc.nextLine();

            int dice = rand.nextInt(6) + 1;
            System.out.println(playerNames[currentPlayer] + " rolled a " + dice);
            int newPos = positions[currentPlayer] + dice;
            if (newPos > WINNING_POSITION) {
                System.out.println("Roll too high! Need exact number to win.");
            } else {
                if (ladders.containsKey(newPos)) {
                    System.out.println("Yay! " + playerNames[currentPlayer] + " climbed a ladder from " + newPos + " to " + ladders.get(newPos));
                    newPos = ladders.get(newPos);
                } else if (snakes.containsKey(newPos)) {
                    System.out.println("Oh no! " + playerNames[currentPlayer] + " got bitten by a snake from " + newPos + " to " + snakes.get(newPos));
                    newPos = snakes.get(newPos);
                }
                positions[currentPlayer] = newPos;
                System.out.println(playerNames[currentPlayer] + " is now at position " + newPos);

                if (newPos == WINNING_POSITION) {
                    System.out.println( playerNames[currentPlayer] + " wins the game!:)");
                    gameWon = true;
                }
            }
            printBoard(positions, playerNames);
            currentPlayer = (currentPlayer + 1) % numPlayers;
        }
        sc.close();
    }
    private static void printSnakesAndLadders() {
        System.out.println("\n--- Snakes ---");
        for (Map.Entry<Integer, Integer> s : snakes.entrySet()) {
            System.out.println("Snake: " + s.getKey() + " ->" + s.getValue());
        }
        System.out.println("\n--- Ladders ---");
        for (Map.Entry<Integer, Integer> l : ladders.entrySet()) {
            System.out.println("Ladder: " + l.getKey() + "->" + l.getValue());
        }
        System.out.println();
    }
    private static void printBoard(int[] positions, String[] names) {
        System.out.println("\n===== BOARD =====");
        for (int row = 10; row >= 1; row--) {
            for (int col = 1; col <= 10; col++) {
                int cellNum = (row % 2 == 0) ? (row * 10 - col + 1) : ((row - 1) * 10 + col);
                String cellStr = String.format("%3d", cellNum);
                String playersHere = "";
                for (int i = 0; i < positions.length; i++) {
                    if (positions[i] == cellNum) {
                        playersHere += names[i].charAt(0); 
                    }
                }
                if (!playersHere.isEmpty()) {
                    cellStr = "[" + playersHere + "]";
                }
                System.out.print(cellStr + " ");
            }
            System.out.println();
        }
        System.out.println("=================\n");
    }
}
