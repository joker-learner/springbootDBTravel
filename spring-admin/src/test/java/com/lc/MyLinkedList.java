package com.lc;
public class MyLinkedList {

    int currentSize = 0;
    int maxSize = 5;
    int[] linked = new int[maxSize];

    public void resize() {
        this.maxSize = this.maxSize + 1;
        int[] newList = new int[maxSize];
        int i = 0;
        while (i < currentSize) {
            newList[i] = linked[i];
            i++;
        }
        this.linked = newList;
    }

    /**
     * @param item
     * @return
     */
    public int add(int item) {
        if (currentSize < maxSize) {
            linked[currentSize++] = item;
        } else {
            resize();
            linked[currentSize++] = item;
        }
        return 1;
    }

    /**
     * @param item  元素
     * @return
     */
    public int remove(int item) {
        int i = 0;
        boolean flag = false;
        //先判断有没有这个元素
        while (i < currentSize) {
            if (item == linked[i]) {
                flag = true;
                break;
            } else {
                i++;
            }
        }
        if (flag == true) {
            //得到待删除的元素下标 i
            for (int j = i + 1; j < currentSize; j++) {
                linked[j - 1] = linked[j];
            }
            currentSize--;
            return 1;
        }else {
            return -1;
        }
    }

    /**
     * @param index 第几个
     * @return
     */
    public int get(int index) {
        Integer result = linked[index - 1];
        return result;
    }

    public void toArray() {
        StringBuilder s = new StringBuilder("[");
        int i = 0;
        while (i < currentSize) {
            s = s.append(linked[i] + ",");
            i++;
        }
        s.append("]");
        System.out.println(s);
    }

    public static void main(String[] args) {

        MyLinkedList link = new MyLinkedList();
        link.add(5);
        link.add(6);
        link.add(7);
        link.add(8);
        link.add(9);
        link.add(10);
        System.out.println("加入元素5, 6, 7, 8, 9, 10 后:***********************");
        link.toArray();
        System.out.println("取得第2个元素是:" + link.get(2));
        link.remove(5);
        System.out.println("移除元素5后:******************************************");
        link.toArray();
        System.out.println("当前大小为" + link.currentSize);
        System.out.println("最大长度为" + link.maxSize);
        System.out.println(Integer.MAX_VALUE);
    }
}
