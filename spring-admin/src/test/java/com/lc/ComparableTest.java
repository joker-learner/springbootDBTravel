package com.lc;


public class ComparableTest implements Comparable<ComparableTest> {
    @Override
    public int compareTo(ComparableTest o) {
        return 0;
    }

    public static void main(String[] args) {

        String s1 = "hello jokerL";
        String newS = s1.replace('h', 'a');
        System.out.println(s1+"     "+ newS);
        System.out.println(s1.isEmpty());
        System.out.println(s1.hashCode()   + "      "+ newS.hashCode());
        String printbaby = s1.concat("printbaby");
        String[] s = s1.split(" ");
        for (String re : s
             ) {
            System.out.println(re);
        }
    }

}
