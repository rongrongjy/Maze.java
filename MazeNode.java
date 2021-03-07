package lurongrong.java;

/**
 * @description：
 * @auther lurongrong
 * @create 2021-03-04 19:42
 */
public class MazeNode {
    private int value;//0/1
    private boolean[] wayState;//节点的四个方向，两种结果：可走/不可走
    private int xPos;//节点的行向标
    private  int yPos;//节点的列下标


    public MazeNode(int value,int xPos, int yPos) {

        this.value = value;
        wayState = new boolean[Constant.WAYSIZE];
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public boolean getWayState(int dic) {
        return wayState[dic];
    }

    public void setWayState(int dic,boolean isable) {
        wayState[dic] = isable;

    }

}
