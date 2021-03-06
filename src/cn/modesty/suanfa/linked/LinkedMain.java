package cn.modesty.suanfa.linked;

import cn.modesty.suanfa.offer.ListNode;
import cn.modesty.suanfa.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 检查代码是否正确的边界条件有这么几个：
 * 1.如果链表为空，代码是否能正常工作。
 * 2.如果链表只包含一个节点时，代码是否能正常工作？
 * 3.如果链表只包含两个节点时，代码是否能正常工作？
 * 4.代码逻辑在处理头节点和尾节点的时候，能否正常工作？
 */
public class LinkedMain {
    public static void main(String[] args) {
        //单链表测试数据
        /*Linked<Integer> linked = new Linked<Integer>();
        linked.add(2);
        linked.add(9);
        linked.add(3);
        linked.add(7);
        linked.add(1);
        linked.add(0);
        linked.add(22);
        linked.add(33);
        //linked.delete(4);
        linked.insert(1,3);
        linked.printElement();*/

        //LRU算法的使用
       /* LruLinked<Integer> lruLinked = new LruLinked();
        lruLinked.insert(0);
        lruLinked.insert(1);
        lruLinked.insert(2);
        lruLinked.insert(3);
        lruLinked.insert(4);
        lruLinked.insert(5);
        lruLinked.insert(2);
        lruLinked.insert(5);
        //lruLinked.delete(4);
        lruLinked.printElement();*/

        //单链表的反转
        /*Linked<Integer> linked = new Linked<Integer>();
        linked.add(2);
        linked.add(9);
        linked.add(3);
        linked.add(7);
        linked.add(1);
        linked.printElement();
        Linked.Node node = linked.recursionReversalLinked(linked.getHead());
        linked.setHead(node);
        linked.printElement();*/

        //找出链表中的环
      /*  Linked<Integer> linked = new Linked<Integer>();
        linked.add(2);
        linked.add(9);
        linked.add(3);
        linked.add(7);
        linked.add(1);
        //创建一个带环的链表
        linked.getTail().next = linked.indexElement(2);
        //输出环的长度
        System.out.println(linked.findLinkedRingLength());
        System.out.println(linked.findCoincideNode().data);*/

        //解除环
       /* Linked<Integer> linked = new Linked<Integer>();
        linked.add(2);
        linked.add(9);
        linked.add(3);
        linked.add(7);
        linked.add(1);
        //创建一个带环的链表
        linked.getTail().next = linked.indexElement(2);
        Linked.Node firstRingNode = linked.findCoincideNode();//获取第一个环结点
        int ringleng = linked.findLinkedRingLength();//获取环的长度
        for (int i = 0; i < ringleng; i++) {
            if (i == ringleng - 1){
                firstRingNode.next = null;
            }
            firstRingNode = firstRingNode.next;
        }
        linked.printElement();*/

     /*  //两个有序链表合并
        Linked<Integer> linkedone = new Linked<Integer>();
        linkedone.add(1);
        linkedone.add(3);
        linkedone.add(5);
        linkedone.add(7);
        linkedone.add(9);
        Linked<Integer> linkedtwo = new Linked<Integer>();
        linkedtwo.add(2);
        linkedtwo.add(4);
        linkedtwo.add(6);
        linkedtwo.add(8);
        linkedtwo.add(10);

        Linked.Node head = linkedone.mergeLinked(linkedone.getHead(), linkedtwo.getHead());
        linkedone.printElement(head);*/

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(1);
        ListNode node5 = new ListNode(5);
        node1.next = node1;
      /*  node2.next = node3;
        node3.next = node4;*/
        /*node4.next = node5;
        ListNode node = oddEvenList(node1);
        while (node != null) {
            System.out.print(node.val + ",");
            node = node.next;
        }*/

        //swapPairs(node1);
        System.out.println(detectCycle(node1));
    }

    public static ListNode detectCycle(ListNode head) {
        if(head == null)return null;
        ListNode p1 = head;
        ListNode p2 = head;
        while(p2 != null && p2.next != null){
            p1 = p1.next;
            p2 = p2.next.next;
            if(p1 == p2){
                p1 = head;
                while(p1 != null){
                    if(p1 == p2){
                        return p1;
                    }
                    p1 = p1.next;
                    p2 = p2.next;
                }
            }

        }
        return null;
    }

    public static boolean isPail (ListNode head) {
        // write code here
        ListNode p1 = head;
        ListNode pre = null;
        ListNode p2 = head;
        while(p1 != null){
            if(p2 == null || p2.next == null){
                if(pre == null)return true;
                if(p1.val != pre.val)return false;
                p1 = p1.next;
                pre = pre.next;
            }else{
                p2 = p2.next.next;
                ListNode temp = p1.next;
                p1.next = pre;
                pre = p1;
                p1 = temp;
            }

        }
        return true;

    }
    public static ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null)return null;
        ListNode temp = head.next.next;
        head.next.next = head;
        ListNode node= swapPairs(temp);
        ListNode temp1 = head.next;
        head.next = node;
        return temp1;
    }
    /**
     * 回文链表
     * @param head
     * @return
     */
    public boolean isPalindrome1(ListNode head) {
        if (head == null) {
            return true;
        }

        // 找到前半部分链表的尾节点并反转后半部分链表
        ListNode firstHalfEnd = endOfFirstHalf(head);
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);

        // 判断是否回文
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        boolean result = true;
        while (result && p2 != null) {
            if (p1.val != p2.val) {
                result = false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // 还原链表并返回结果
        firstHalfEnd.next = reverseList(secondHalfStart);
        return result;
    }

    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    private ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 奇偶链表
     *
     * @param head
     * @return
     */
    public static ListNode oddEvenList(ListNode head) {
        if (head == null) return head;
        ListNode eventHead = head.next;
        ListNode odd = head, even = head.next;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = eventHead;
        return head;
    }

    /**
     * 回文链表
     * 请判断一个链表是否为回文链表。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 1->2
     * 输出: false
     * 示例 2:
     * <p>
     * 输入: 1->2->2->1
     * 输出: true
     *
     * @param head
     * @return 整个流程可以分为以下五个步骤：
     * 找到前半部分链表的尾节点。
     * 反转后半部分链表。
     * 判断是否回文。
     * 恢复链表。
     * 返回结果。
     * 执行步骤一，我们可以计算链表节点的数量，然后遍历链表找到前半部分的尾节点。
     * 我们也可以使用快慢指针在一次遍历中找到：慢指针一次走一步，快指针一次走两步，快慢指针同时出发。当快指针移动到链表的末尾时，慢指针恰好到链表的中间。通过慢指针将链表分为两部分
     */
    private static ListNode firstNode;

    public static boolean isPalindrome(ListNode head) {
        firstNode = head;
        return re(head);
    }


    public static boolean re(ListNode currentNode) {
        if (currentNode != null) {
            if (!re(currentNode.next)) {
                return false;
            }
            if (currentNode.val != firstNode.val) {
                return false;
            }
            firstNode = firstNode.next;
        }
        return true;
    }

    /**
     * 将有序数组转换为二叉搜索树
     * <p>
     * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
     * <p>
     * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
     * <p>
     * 示例:
     * <p>
     * 给定有序数组: [-10,-3,0,5,9],
     * <p>
     * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
     * <p>
     * 0
     * / \
     * -3   9
     * /   /
     * -10  5
     *
     * @param nums
     * @return
     */
    public static TreeNode sortedArrayToBST(int[] nums) {
        return sort(nums, 0, nums.length - 1);
    }

    public static TreeNode sort(int[] nums, int s, int e) {
        if (s >= e) return null;
        int mid = s + (e - s) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = sort(nums, s, mid - 1);
        node.right = sort(nums, mid + 1, e);
        return node;
    }
}
