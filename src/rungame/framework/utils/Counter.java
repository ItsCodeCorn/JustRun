package rungame.framework.utils;

public class Counter {
    private int nowCount;
    private int finishedCount;

    public Counter() {
        this.nowCount = 1;
        this.finishedCount = 10;
    }
    public Counter(int finishedCount) {
        this.nowCount = 0;
        this.finishedCount = finishedCount;
    }

    public void setFinishedCount(int finishedCount) {
        this.finishedCount = finishedCount;
    }

    public void reset() {
        this.nowCount = 0;
    }

    public boolean count() {
        ++nowCount;
        if (nowCount < finishedCount) {
            return false;
        }

        nowCount = 0;
        return true;
    }
}
