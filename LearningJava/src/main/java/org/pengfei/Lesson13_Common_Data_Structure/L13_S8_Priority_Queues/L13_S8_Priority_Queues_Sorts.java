package org.pengfei.Lesson13_Common_Data_Structure.L13_S8_Priority_Queues;

public class L13_S8_Priority_Queues_Sorts {

    /************************************************************************************************
     * **************************** 13.8.3 Sorting a priority Queues  *****************************
     * **********************************************************************************************/

    /*
    * The methods of the priority queue ADT given in Section 13.8.3 are sufficient for most basic applications of
    * priority queues, such as sorting. However, there are situations in which additional methods would be useful,
    * as shown by the scenarios below involving the standby airline passenger application.
    *
    * - A standby passenger with a pessimistic attitude may become tired of waiting and decide to leave ahead of
    *   the boarding time, requesting to be removed from the waiting list. Thus, we would like to remove from the
    *   priority queue the entry associated with this passenger. Operation removeMin does not suffice since the
    *   passenger leaving does not necessarily have first priority. Instead, we want a new operation, remove, that
    *   removes an arbitrary entry.
    *
    * - Another standby passenger finds her gold frequent-flyer card and shows it to the agent. Thus, her priority
    *   has to be modified accordingly. To achieve this change of priority, we would like to have a new operation
    *   replaceKey allowing us to replace the key of an existing entry with a new key.
    *
    * - Finally, a third standby passenger notices her name is misspelled on the ticket and asks it to be corrected.
    *   To perform this change, we need to update the passenger’s record. Hence, we would like to have a new
    *   operation replaceValue, allowing us to replace the value of an existing entry with a new value.
    *   */

    /*********************** 13.8.3.1 The Adaptable Priority Queue ADT *************************/

    /*
    * The above scenarios motivate the definition of a new adaptable priority queue ADT that extends the priority queue
    * ADT with additional functionality which includes the following methods:
    * - remove(e) : Removes entry e from the priority queue
    * - replaceKey(e,k) : Replaces the key of existing entry e with k.
    * - replaceValue(e,v) : Repalces the value of existing entry e with v.
    *
    * */
}
