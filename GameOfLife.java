package GameOfLife__;

import java.util.ArrayList;
import java.util.List;

public class GameOfLife {

    public static int[][] nextGeneration(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;

        int[][] nextBoard = new int[rows][cols];
        List<Integer> neighborList = new ArrayList<>();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int liveNeighbors = countLiveNeighbors(board, row, col);
                neighborList.add(liveNeighbors); // הוספה לרשימה

                if (board[row][col] == 1) {
                    if (liveNeighbors < 2 || liveNeighbors > 3) {
                        nextBoard[row][col] = 0;
                    } else {
                        nextBoard[row][col] = 1;
                    }
                } else {
                    if (liveNeighbors == 3) {
                        nextBoard[row][col] = 1;
                    } else {
                        nextBoard[row][col] = 0;
                    }
                }
            }
        }

        // הדפסת הרשימה
        System.out.println("רשימת שכנים חיים לכל תא:");
        System.out.println(neighborList);

        return nextBoard;
    }

    private static int countLiveNeighbors(int[][] board, int row, int col) {
        int count = 0;
        int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
        int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};

        for (int i = 0; i < 8; i++) {
            int newRow = row + dx[i];
            int newCol = col + dy[i];

            if (newRow >= 0 && newRow < board.length &&
                    newCol >= 0 && newCol < board[0].length &&
                    board[newRow][newCol] == 1) {
                count++;
            }
        }

        return count;
    }

    private static void printBoard(int[][] board) {
        for (int[] row : board) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] board = {
                {0, 1, 0},
                {0, 1, 0},
                {0, 1, 0}
        };

        System.out.println("לוח התחלתי:");
        printBoard(board);

        int[][] next = nextGeneration(board);

        System.out.println("הדור הבא:");
        printBoard(next);
    }
}
