package lurongrong.java;

import java.util.Scanner;
import java.util.Stack;

/**
 * @description：
 * @auther lurongrong
 * @create 2021-03-04 19:42
 */
public class Maze {
    private MazeNode[][] mazeNodes;//迷宫里面的路径节点 0/1
    private int row;//迷宫的行数
    private int colum;//迷宫的列数
    private Stack<MazeNode> stack;

    public Maze(int row, int colum) {
        mazeNodes = new MazeNode[row][colum];
        this.row = row;
        this.colum = colum;
        stack = new Stack<>();//初始化栈
    }

    /**
     * 初始化迷宫节点的value值
     */
    public void initValue() {
        Scanner scanner = new Scanner(System.in);
        //从键盘输入迷宫节点的值
        System.out.println("请输入迷宫:");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < colum; j++) {
                mazeNodes[i][j] = new MazeNode(scanner.nextInt(), i, j);
            }
        }
    }

    /**
     * 初始化每个迷宫节点的行走状态
     */

    public void initWayState() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < colum; j++) {

                if (mazeNodes[i][j].getValue() == 0) {
                    //东
                    if (j + 1 < colum && mazeNodes[i][j + 1].getValue() == 0) {
                        mazeNodes[i][j].setWayState(Constant.WAY_EAST, Constant.WAY_ABLE);
                        //西
                    }  if (j - 1 >= 0 && mazeNodes[i][j - 1].getValue() == 0) {
                        mazeNodes[i][j].setWayState(Constant.WAY_WEST, Constant.WAY_ABLE);
                        //南
                    }  if (i + 1 < row && mazeNodes[i + 1][j].getValue() == 0) {
                        mazeNodes[i][j].setWayState(Constant.WAY_SOUTH, Constant.WAY_ABLE);
                        //北
                    }  if (i - 1 >= 0 && mazeNodes[i - 1][j].getValue() == 0) {
                        mazeNodes[i][j].setWayState(Constant.WAY_NORTH, Constant.WAY_ABLE);
                    }
                }
            }
        }
    }

    /**
     * 走迷宫
     */

    public void goMaze() {
        initValue();
        initWayState();
        if (mazeNodes[0][0].getValue() != 0 || mazeNodes[row-1][colum-1].getValue() != 0) {
            System.out.println("迷宫没有路径");
            return;
        }
        System.out.println("*******");//测试语句
        stack.push(mazeNodes[0][0]);
        System.out.println("&&&&&&&&&");//测试语句
        while (!stack.isEmpty()) {

            MazeNode top = stack.peek();

            if (top.getxPos() != row - 1 && top.getyPos() != colum - 1 && !top.getWayState(Constant.WAY_EAST)
                    && !top.getWayState(Constant.WAY_WEST) && !top.getWayState(Constant.WAY_SOUTH)
                    && !top.getWayState(Constant.WAY_NORTH)) {
                stack.pop();
            }
            if (top.getxPos() == row - 1 && top.getyPos() == colum - 1) {
                break;
            }
            //东 西 南 北
            int xPos = top.getxPos();
            int yPos = top.getyPos();
            if (top.getWayState(Constant.WAY_EAST)) {
                stack.push(mazeNodes[xPos][yPos + 1]);
                mazeNodes[xPos][yPos].setWayState(Constant.WAY_EAST, Constant.WAY_DISABLE);
                mazeNodes[xPos][yPos + 1].setWayState(Constant.WAY_WEST, Constant.WAY_DISABLE);
            } else if (top.getWayState(Constant.WAY_WEST)) {
                stack.push(mazeNodes[xPos][yPos-1]);
                mazeNodes[xPos][yPos].setWayState(Constant.WAY_WEST, Constant.WAY_DISABLE);
                mazeNodes[xPos][yPos - 1].setWayState(Constant.WAY_EAST, Constant.WAY_DISABLE);
            } else if (top.getWayState(Constant.WAY_SOUTH)) {
                stack.push(mazeNodes[xPos + 1][yPos]);
                mazeNodes[xPos][yPos].setWayState(Constant.WAY_SOUTH, Constant.WAY_DISABLE);
                mazeNodes[xPos + 1][yPos].setWayState(Constant.WAY_WEST, Constant.WAY_DISABLE);
            } else if (top.getWayState(Constant.WAY_NORTH)) {
                stack.push(mazeNodes[xPos-1][yPos]);
                mazeNodes[xPos][yPos].setWayState(Constant.WAY_NORTH, Constant.WAY_DISABLE);
                mazeNodes[xPos - 1][yPos].setWayState(Constant.WAY_SOUTH, Constant.WAY_DISABLE);
            }

        }
        show();

    }

    /**
     * 显示迷宫路径
     */

    public void show() {
        System.out.println("$$$$$$$$444444");
        if (stack.isEmpty()) {
            System.out.println("没有迷宫路径");
            return;
        }
        while (!stack.isEmpty()) {
            MazeNode top = stack.peek();
            top.setValue(6);
            stack.pop();
        }
        System.out.println("迷宫路径为：");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < colum; j++) {
                System.out.print(mazeNodes[i][j].getValue() + " ");
            }
            System.out.println();
        }
    }

}
