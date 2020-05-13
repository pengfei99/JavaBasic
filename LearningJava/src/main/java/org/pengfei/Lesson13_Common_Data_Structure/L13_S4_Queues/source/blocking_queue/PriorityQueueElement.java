package org.pengfei.Lesson13_Common_Data_Structure.L13_S4_Queues.source.blocking_queue;

public class PriorityQueueElement implements Comparable {
    private String message;
    private int priority;

    public PriorityQueueElement(String message, int priority) {
        this.message = message;
        this.priority = priority;
    }

    @Override
    public int compareTo(Object o) {
        if(this.priority>((PriorityQueueElement)o).priority) return -1;
        else if(this.priority<((PriorityQueueElement)o).priority) return 1;
        else return 0;
    }

    @Override
    public String toString(){
      return   "\n{"
                + "message=" + message
                + ", priority=" + priority
                + "}";
    }
}
