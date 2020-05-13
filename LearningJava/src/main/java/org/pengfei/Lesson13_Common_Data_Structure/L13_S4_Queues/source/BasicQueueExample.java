package org.pengfei.Lesson13_Common_Data_Structure.L13_S4_Queues.source;

public class BasicQueueExample {
    public static void exp1(){
        ArrayBasedQueue<Integer> myQueue=new ArrayBasedQueue();
        myQueue.enqueue(3);
        myQueue.enqueue(5);
        int maxSize=myQueue.size();
        System.out.println("queue size is :"+maxSize);

        Integer firstElement=myQueue.dequeue();
        Integer secondElement=myQueue.dequeue();

        System.out.println("first element is :"+firstElement);
        System.out.println("Second element is :"+secondElement);
    }

    public static void exp2(){
        SinglyLinkedListBasedQueue<Integer> myQueue= new SinglyLinkedListBasedQueue<>();
        myQueue.enqueue(3);
        myQueue.enqueue(5);
        int maxSize=myQueue.size();
        System.out.println("queue size is :"+maxSize);

        Integer firstElement=myQueue.dequeue();
        Integer secondElement=myQueue.dequeue();

        System.out.println("first element is :"+firstElement);
        System.out.println("Second element is :"+secondElement);
    }

    public static void exp3(){
        ListBasedCircularQueue<Integer> myQueue= new ListBasedCircularQueue<>();
        for(int i=0;i<5;i++){
            myQueue.enqueue((Integer) i);
        }

        Integer beforRotate = myQueue.first();
        System.out.println("first element before rotate is :"+beforRotate);

        myQueue.rotate();
        Integer afterRotate=myQueue.first();
        System.out.println("first element after rotate is :"+afterRotate);
    }
}
