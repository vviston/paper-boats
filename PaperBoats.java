package main.java;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class PaperBoats {

    static int rows;
    static int cols;

    public static void main(String[] args) {
        String[] B1 = {".##.#", "#.#..", "#...#", "#.##."};
        System.out.println(Arrays.toString(solution(B1)));

        String[] B2 = {"##.", "#.#", ".##"};
        System.out.println(Arrays.toString(solution(B2)));

        String[] B3 = {"...", "...", "..."};
        System.out.println(Arrays.toString(solution(B3)));

        String[] B4 = {".#..#", "##..#", "...#."};
        System.out.println(Arrays.toString(solution(B4)));
    }

    // checks if i,j is a valid position in the grid
    public static boolean checkPosition(char[][] grid, int i, int j) {
        if (i >= rows || j >= cols || i < 0 || j < 0 || grid[i][j] == '.') {
            return false;
        }
        return true;
    }

    // checking neighbour cells with recursion
    /*public static int checkNeighbourCells(char[][] grid, int i, int j) {
        if (grid[i][j] == '#') {
            grid[i][j] = '.';

            int x = 0, y = 0, z = 0, w = 0;

            if (checkPosition(grid, i-1, j)) {
                x = checkNeighbourCells(grid, i-1, j);
            }

            if (checkPosition(grid, i+1, j)) {
                y = checkNeighbourCells(grid, i+1, j);
            }

            if (checkPosition(grid, i, j-1)) {
                z = checkNeighbourCells(grid, i, j-1);
            }

            if (checkPosition(grid, i, j+1)) {
                w = checkNeighbourCells(grid, i, j+1);
            }
            return 1+x+y+z+w;
        }
        return 0;
    }*/

    public static int checkNeighbourCells(char[][] grid, int i, int j) {
        if (grid[i][j] == '#') {
            grid[i][j] = '.';
            int count = 1;

            Deque<int[]> stack = new ArrayDeque<>();
            stack.push(new int[]{i, j});

            while (!stack.isEmpty()) {
                int[] pos = stack.pop();
                int x = pos[0];
                int y = pos[1];

                if (checkPosition(grid, x-1, y) && grid[x-1][y] == '#') {
                    grid[x-1][y] = '.';
                    count++;
                    stack.push(new int[]{x-1, y});
                }

                if (checkPosition(grid, x+1, y) && grid[x+1][y] == '#') {
                    grid[x+1][y] = '.';
                    count++;
                    stack.push(new int[]{x+1, y});
                }

                if (checkPosition(grid, x, y-1) && grid[x][y-1] == '#') {
                    grid[x][y-1] = '.';
                    count++;
                    stack.push(new int[]{x, y-1});
                }

                if (checkPosition(grid, x, y+1) && grid[x][y+1] == '#') {
                    grid[x][y+1] = '.';
                    count++;
                    stack.push(new int[]{x, y+1});
                }
            }
            return count;
        }
        return 0;
    }

    public static int[] solution(String[] B) {
        // empty board initialization
        char[][] grid = new char[B.length][B[0].length()];

        // read the ships from input
        for (int i = 0; i < B.length; i++) {
            String row = B[i];
            for (int j = 0; j < row.length(); j++) {
                grid[i][j] = row.charAt(j);
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }

        int patrolBoats = 0, submarines = 0, destroyers = 0;
        rows = B.length;
        cols = B[0].length();

        for (int i = 0; i < B.length; i++) {
            for (int j = 0; j < B[0].length(); j++) {

                if (grid[i][j] == '#') {

                    int val = checkNeighbourCells(grid, i, j);

                    if (val == 1) {
                        patrolBoats++;
                    }

                    if (val == 2) {
                        submarines++;
                    }

                    if (val == 3) {
                        destroyers++;
                    }
                }
            }
        }
        return new int[] {patrolBoats, submarines, destroyers};
    }
}
