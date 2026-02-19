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
class Solution 
{
    public ListNode reverseKGroup(ListNode head, int k)
    {
        ListNode curr=head;
        int count=0;
        while(count<k && curr!=null)
        {
            curr=curr.next;
            count++;
        }
        if(count==k)
        {
            ListNode prev=null;
            ListNode next=null;
            curr=head;
            for(int i=0;i<k;i++)
            {
                next=curr.next;
                curr.next=prev;
                prev=curr;
                curr=next;
            }
            if(next!=null)
            {
                head.next=reverseKGroup(next,k);
            }
            return prev;
        }
        return head;
    }
}

        
        
        
        
        
        
       