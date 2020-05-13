package org.pengfei.Lesson13_Common_Data_Structure.L13_S4_Queues.source.blocking_queue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayedQueueElement implements Delayed {
    private String message;
    private long time;

    public DelayedQueueElement(String message, long time) {
        this.message = message;
        // calculate delayed time
        this.time = System.currentTimeMillis()+time;
    }
/** Because the time of element creation may be before the getDelay method call. And this elapsed time
 * need to be removed from delayed time, because the element is waiting as delayed.*/
    @Override
    public long getDelay(TimeUnit timeUnit) {
        //realDelay can be 0 or negative
        long realDelay=time-System.currentTimeMillis();
        // convert long back to millis seconds.
        return timeUnit.convert(realDelay,TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed delayedElement) {
        if(this.time>((DelayedQueueElement) delayedElement).time) return 1;
        else if(this.time<((DelayedQueueElement) delayedElement).time) return -1;
        else return 0;
    }

    @Override
    public String toString(){
        return "\n{"
                + "name=" + message
                + ", time=" + time
                + "}";
    }
}
