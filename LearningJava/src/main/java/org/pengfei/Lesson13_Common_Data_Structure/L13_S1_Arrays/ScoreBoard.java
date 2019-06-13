package org.pengfei.Lesson13_Common_Data_Structure.L13_S1_Arrays;

public class ScoreBoard {
    private int numEntries = 0;
    private GameEntry[] board;

    public ScoreBoard(int boardCapacity){
        board=new GameEntry[boardCapacity];
    }

    /* The add method add a new gameEntry into the ScoreBoard, the order of the entries are decreasing on the game score
    * For example,
    * Index, name, score
    * 1, Mike, 1105,
    * 2, Rob, 750
    * 3, Paul, 720
    *
    *  Now, if we want to add a new (Jill 740), it will take the index 3, and Paul will take index 4.
    *
    *  If we remove Jill, Paul will retake the index 3.
    * */
    public void add(GameEntry e){
        int newScore=e.getScore();
        // if the board still has place, or the new score is greater than the mini register score
        if(numEntries<board.length || newScore > board[numEntries-1].getScore()){
            if(numEntries<board.length)
                numEntries++;
            int j = numEntries-1;
            while (j>0 && board[j-1].getScore()<newScore){
                board[j]=board[j-1];
                j--;
            }
        board[j]=e;
        }
    }

    public GameEntry remove(int i) {
        if(i<0 || i> board.length){
            throw new IndexOutOfBoundsException("Invalid index: "+i);
        }

        GameEntry removedGameEntry = board[i];
        // use numEntries instead the board length to avoid loop all elements
        for(int j=i;j<numEntries-1;j++){
            board[j]=board[j+1];
        }
        board[numEntries-1]=null;
        numEntries--;

        return removedGameEntry;
    }

    public void showScores(){
        for(int i=0; i<board.length; i++){
            GameEntry currentGE=board[i];
            if (currentGE!=null) {System.out.println("Game name: "+currentGE.getName()+"Game score: "+currentGE.getScore());
            }
        }

    }
}
