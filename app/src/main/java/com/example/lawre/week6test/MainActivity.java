package com.example.lawre.week6test;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MainActivity
{
    public int[] array;
    private int[] tempMergArr;
    private int length;

    public static void main(String[] args) {
        MainActivity mainActivity = new MainActivity();
        System.out.println("Problem 1: ");
        mainActivity.mergeSort();
        for (int i = 0; i < mainActivity.array.length; i++) {
            System.out.print(mainActivity.array[i] + " ");
        }
        System.out.println("Problem 2: ");
        mainActivity.checkForDuplicates(mainActivity.generateDupArray());
        System.out.println("Problem 3: ");
        mainActivity.lruCache();
        System.out.println("Problem 4: ");
        mainActivity.spiralForm(mainActivity.generateSpiralArray());
        System.out.println("Problem 5: ");
        mainActivity.checkBrackets("{([(}]}");
    }

    public MainActivity()
    {

    }

    public String[][] generateDupArray() {
        String[][] duplicatesArray = new String[3][3];
        duplicatesArray[0][0] = "a";
        duplicatesArray[0][1] = "b";
        duplicatesArray[0][2] = "c";
        duplicatesArray[1][0] = "d";
        duplicatesArray[1][1] = "e";
        duplicatesArray[1][2] = "f";
        duplicatesArray[2][0] = "g";
        duplicatesArray[2][1] = "a";
        duplicatesArray[2][2] = "b";
        return duplicatesArray;
    }

    public String[][] generateSpiralArray()
    {
        String[][] spiralArray = new String[3][3];
        int value = 1;
        for(int i = 0; i < spiralArray.length; i++)
        {
            for(int j = 0; j < spiralArray[0].length; j++)
            {
                spiralArray[i][j] = String.valueOf(value);
                value++;
            }
        }
        return spiralArray;
    }

    public void mergeSort() {
        int[] mergeArray = new int[20];
        Random rand = new Random(100);
        for (int i = 0; i < 20; i++) {
            mergeArray[i] = rand.nextInt();
        }
        sort(mergeArray);
    }

    public void checkForDuplicates(String[][] arrayOfDups) {
        ArrayList<GridString> characters = new ArrayList<>();
        for (int i = 0; i < arrayOfDups.length; i++) {
            for (int j = 0; j < arrayOfDups[i].length; j++) {
                characters.add(new GridString(new Point(i, j), arrayOfDups[i][j]));
            }
        }
        for (int i = 0; i < characters.size(); i++) {
            String valueToCheck = characters.get(0).value;
            boolean hasPrintedFirst = false;
            for (int j = i + 1; j < characters.size() - 1; j++) {
                if (characters.get(j).value.equals(valueToCheck)) {
                    if (!hasPrintedFirst) {
                        System.out.print(characters.get(0).position + " ");
                        hasPrintedFirst = true;
                    }
                    System.out.print(characters.get(j).position + " ");
                }
            }
        }
    }

    public void lruCache()
    {
        LRUCache lruCache = new LRUCache(3);
        lruCache.set(1,1);
        lruCache.set(2,2);
        lruCache.set(3,3);
        System.out.println(lruCache);
        lruCache.set(4,4);
        System.out.println(lruCache);
    }

    public void spiralForm(String[][] arrayToSpiral)
    {
        int minColumn = 0;
        int maxColumn = arrayToSpiral.length;
        int minRow = 0;
        int maxRow = arrayToSpiral[0].length;
        int totalRows = maxRow;
        for(int counter = 0; counter < maxRow;counter++) {
            for (int i = minRow; i < maxColumn; i++) {
                System.out.print(arrayToSpiral[minRow][i] + " ");
            }
            for (int j = minColumn; j < maxRow; j++) {
                System.out.print(arrayToSpiral[maxColumn - 1][j] + " ");
            }
            for (int i = maxColumn - 1; i > minColumn; i--) {
                System.out.print(arrayToSpiral[i][maxColumn - 1] + " ");
            }
            for (int j = maxRow - 1; j > minRow + 1; j--) {
                System.out.print(arrayToSpiral[minColumn][j] + " ");
            }
            minRow++;
            minColumn++;
            maxRow--;
            maxColumn--;
        }
    }

    public void checkBrackets(String checkString)
    {
        ArrayList<BracketNode> brackets = new ArrayList<BracketNode>();
        for(int i = 0; i < checkString.length(); i++)
        {
            brackets.add(new BracketNode(String.valueOf(checkString.charAt(i))));
        }
        for(int i = 0; i < brackets.size();i++)
        {
            if(!brackets.get(i).hasMatch)
            {
                String lookingForBracket = "";
                switch (brackets.get(i).character) {
                    case "{":
                        lookingForBracket = "}";
                        break;
                    case "(":
                        lookingForBracket = ")";
                        break;
                    case "[":
                        lookingForBracket = "]";
                        break;
                }
                for (int j = 1; j < brackets.size(); j++) {
                    if (brackets.get(j).character.equals(lookingForBracket) && !brackets.get(j).hasMatch) {
                        brackets.get(i).hasMatch = true;
                        brackets.get(j).hasMatch = true;
                    }
                }
            }
        }
        boolean allHaveMatches = true;
        for(int i = 0; i < brackets.size();i++)
        {
            if(!brackets.get(i).hasMatch)
            {
                allHaveMatches = false;
            }
        }
        System.out.println(allHaveMatches);
    }

    public class BracketNode
    {
        public String character;
        public boolean hasMatch;

        public BracketNode(String character) {
            this.character = character;
            hasMatch = false;
        }
    }

    public void sort(int inputArr[]) {
        this.array = inputArr;
        this.length = inputArr.length;
        this.tempMergArr = new int[length];
        doMergeSort(0, length - 1);
    }

    private void doMergeSort(int lowerIndex, int higherIndex) {

        if (lowerIndex < higherIndex) {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            // Below step sorts the left side of the array
            doMergeSort(lowerIndex, middle);
            // Below step sorts the right side of the array
            doMergeSort(middle + 1, higherIndex);
            // Now merge both sides
            mergeParts(lowerIndex, middle, higherIndex);
        }
    }

    private void mergeParts(int lowerIndex, int middle, int higherIndex) {

        for (int i = lowerIndex; i <= higherIndex; i++) {
            tempMergArr[i] = array[i];
        }
        int i = lowerIndex;
        int j = middle + 1;
        int k = lowerIndex;
        while (i <= middle && j <= higherIndex) {
            if (tempMergArr[i] <= tempMergArr[j]) {
                array[k] = tempMergArr[i];
                i++;
            } else {
                array[k] = tempMergArr[j];
                j++;
            }
            k++;
        }
        while (i <= middle) {
            array[k] = tempMergArr[i];
            k++;
            i++;
        }

    }

    public class GridString {
        public Point position;
        public String value;

        public GridString(Point position, String value) {
            this.position = position;
            this.value = value;
        }
    }

    class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public class LRUCache {
        int capacity;
        HashMap<Integer, Node> map = new HashMap<Integer, Node>();
        Node head = null;
        Node end = null;

        public LRUCache(int capacity) {
            this.capacity = capacity;
        }

        public int get(int key) {
            if (map.containsKey(key)) {
                Node n = map.get(key);
                delete(n);
                setHead(n);
                return n.value;
            }

            return -1;
        }

        /*This method will delete node*/
        public void delete(Node node) {
            if (node.prev != null) {
                node.prev.next = node.next;
            } else {
                head = node.next;
            }

            if (node.next != null) {
                node.next.prev = node.prev;
            } else {
                end = node.prev;
            }

        }

        /*This method will make passed node as head*/
        public void setHead(Node node) {
            node.next = head;
            node.prev = null;

            if (head != null)
                head.prev = node;

            head = node;

            if (end == null)
                end = head;
        }

        public void set(int key, int value) {
            if (map.containsKey(key)) {
                // update the old value
                Node old = map.get(key);
                old.value = value;
                delete(old);
                setHead(old);
            } else {
                Node newNode = new Node(key, value);
                if (map.size() >= capacity) {

                    map.remove(end.key);
                    // remove last node
                    delete(end);
                    setHead(newNode);

                } else {
                    setHead(newNode);
                }

                map.put(key, newNode);
            }
        }
    }
}
