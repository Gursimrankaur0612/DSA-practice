/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode partition(ListNode head, int x) {

        ListNode less=new ListNode(0);
        ListNode greater=new ListNode(0);

        ListNode l=less;
        ListNode g=greater;

        ListNode curr=head;
        while(curr!=null)
        {
            if(curr.val<x)
            {
                l.next=curr;
                l=l.next;
            }
            else
            {
                g.next=curr;
                g=g.next;
            }
            curr=curr.next;
        }
        g.next=null;
        l.next=greater.next;
        return less.next;
    }
}