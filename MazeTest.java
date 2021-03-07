package lurongrong.java;

import java.util.Scanner;

/**
 * @description：
 * @auther lurongrong
 * @create 2021-03-04 19:43
 */
public class MazeTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入：");
        int row = scanner.nextInt();
        int colum = scanner.nextInt();
        Maze maze = new Maze(row,colum);
        maze.goMaze();
    }
}
