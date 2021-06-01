package rungame.framework.utils;

public class Counter {
    private long startTime, duration;

    public Counter() {
        this.startTime = System.currentTimeMillis();
        this.duration = 1000;
    }
    public Counter(long duration) {
        this.startTime = System.currentTimeMillis();
        this.duration = duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public void reset() {
        this.startTime = System.currentTimeMillis();
    }

    public boolean count() {
        // 當duration為0 持續 reset()
        if (duration == 0) {
            reset();
            return true;
        }

        // 當起始時間+持續時間比現在還久時回傳false
        if (startTime + duration > System.currentTimeMillis()) {
            return false;
        }

        // 避免lag造成counter執行多次
        long cur = System.currentTimeMillis();
        while (startTime + duration <= cur) {
            startTime += duration;
        }

        return true;
    }
}
