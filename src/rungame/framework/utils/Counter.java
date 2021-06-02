package rungame.framework.utils;

public class Counter {
    private int nowCount, endCount;

    public Counter() {
        this.nowCount = 0;
        this.endCount = 10;
    }
    public Counter(int endCount) {
        this.nowCount = 0;
        this.endCount = endCount;
    }

    public void setEndCount(int endCount) {
        this.endCount = endCount;
    }

    public void reset() {
        this.nowCount = 0;
    }

    public boolean count() {
        ++nowCount;
        if (nowCount < endCount) {
            return false;
        }

        reset();
        return true;
    }
}
