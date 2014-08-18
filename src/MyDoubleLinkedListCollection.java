/**
 * Created by Faton on 15.08.2014.
 */
public class MyDoubleLinkedListCollection {
    static DoubleNode doubleListHead = null;
    static DoubleNode doubleListTail = null;

    public static void main(String[] args) {
        makeList(1, 2, 3, 6, 8, 10, 12);

        System.out.println("Основной список: " + printToString(doubleListTail)); //значки "<-  ->"обозначают ссылку, например 1<-(2)->3, значит что у элемента с индексом 1 и значением 2 предыдущий элемент со значением 1, а сдедующий элемент со значением 3
//        System.out.println("Добавить значение в 'голову': "+printToString(addToHead(20, doubleListTail)));
//        System.out.println("Добавить значение в 'голову' итеративно: "+printToString(addToHeadIter(21, doubleListTail)));
//        System.out.println("Добавить значение в 'голову' рекурсивно: "+printToString(addToHeadRec(22, doubleListTail)));
//        System.out.println("Удалить 'голову': "+printToString(removeHead(doubleListTail)));
//        System.out.println("Удалить 'голову' итеративно: "+printToString(removeHeadIter(doubleListTail)));
//        System.out.println("Удалить 'голову' рекурсивно: "+printToString(removeHeadRec(doubleListTail)));
//        System.out.println("Вставить в позицию итеративно: "+printToString(inseartInPositionIter(30, 3, doubleListTail)));
//        System.out.println("Вставить в позицию рекурсивно: "+printToString(inseartInPositionIter(31, 1, doubleListTail)));
//        System.out.println("В списке содержится "+size(doubleListTail)+" элементов");
        System.out.println("Сумма всех элементов в списке: " + sumList(doubleListTail));
    }

    public static DoubleNode addToHead(int value, DoubleNode list) {
        doubleListHead.next = new DoubleNode(value, doubleListHead, null);
        doubleListHead = doubleListHead.next;
        return list;
    }

    public static DoubleNode addToHeadIter(int value, DoubleNode list) {
        DoubleNode currentItemIter = list;
        while (currentItemIter.next != null) {
            currentItemIter = currentItemIter.next;
        }
        currentItemIter.next = new DoubleNode(value, currentItemIter, null);
        doubleListHead = currentItemIter.next;
        return list;
    }

    public static DoubleNode addToHeadRec(int value, DoubleNode list) {
        if (list.next != null) {
            addToHeadRec(value, list.next);
        } else {
            list.next = new DoubleNode(value, list, null);
            doubleListHead = list.next;
        }
        return doubleListTail;
    }

    public static DoubleNode removeHead(DoubleNode list) {
        doubleListHead = doubleListHead.previous;
        doubleListHead.next = null;
        return list;
    }

    public static DoubleNode removeHeadIter(DoubleNode list) {
        DoubleNode currentItemIter = list;
        while (currentItemIter.next != null) {
            currentItemIter = currentItemIter.next;
        }
        currentItemIter = currentItemIter.previous;
        currentItemIter.next = null;
        doubleListHead = currentItemIter;
        return list;
    }

    public static DoubleNode removeHeadRec(DoubleNode list) {
        if (list.next != null) {
            removeHeadRec(list.next);
        } else {
            list = list.previous;
            list.next = null;
            doubleListHead = list;
        }
        return doubleListTail;
    }

    public static DoubleNode inseartInPositionIter(int value, int position, DoubleNode list) {
        DoubleNode currentItemIter = list;
        DoubleNode itemToInseart = new DoubleNode(value, null, null);
        if (position == 0) {
            list.previous = itemToInseart;
            itemToInseart.next = list;
            list = itemToInseart;
        } else {
            int i = 0;
            while (i < position) {
                currentItemIter = currentItemIter.next;
                i++;
            }
            itemToInseart.previous = currentItemIter.previous;
            currentItemIter = currentItemIter.previous;
            itemToInseart.next = currentItemIter.next;
            currentItemIter.next = itemToInseart;
            currentItemIter = currentItemIter.next.next;
            currentItemIter.previous = itemToInseart;
        }
        return list;
    }

    public static DoubleNode inseartInPositionRec(int value, int position, DoubleNode list) {
        if (position == 0) {
            list = new DoubleNode(value, null, list);
        } else {
            inseartInPositionRecInner(0, value, position, list);
        }
        return list;
    }

    public static Integer size(DoubleNode list) {
        int i = 0;
        while (list != null) {
            list = list.next;
            i++;
        }
        return i;
    }

    public static Integer sumList(DoubleNode list) {
        int result = 0;
        while (list != null) {
            result += list.value;
            list = list.next;
        }
        return result;
    }

    // Внутренние методы...

    public static DoubleNode makeList(int... values) {
        for (int i = values.length - 1; i >= 0; i--) {
            doubleListTail = new DoubleNode(values[i], null, doubleListTail);
        }
        DoubleNode currentItem = doubleListTail;
        DoubleNode prevItem = null;
        while (currentItem.next != null) {
            prevItem = currentItem;
            currentItem = currentItem.next;
            currentItem.previous = prevItem;
        }
        doubleListHead = currentItem;
        return doubleListTail;
    }

    public static String printToString(DoubleNode list) {
        String item = "";
        while (list != null) {
            if (list.previous == null) {
                item += "*<-(" + list.value + ")->" + list.next.value + "  ";
            } else if (list.next == null) {
                item += list.previous.value + "<-(" + list.value + ")->*";
            } else {
                item += list.previous.value + "<-(" + list.value + ")->" + list.next.value + "  ";
            }
            list = list.next;
        }
        return item;
    }

    public static DoubleNode inseartInPositionRecInner(int i, int valueInner, int positionInner, DoubleNode listInner) {
        if (i < positionInner) {
            i++;
            inseartInPositionRecInner(i, valueInner, positionInner, listInner.next);
        } else {
            DoubleNode itemToInseart = new DoubleNode(valueInner, listInner.previous, listInner);
            listInner = listInner.previous;
            listInner.next = itemToInseart;
            listInner = listInner.next.next;
            listInner.previous = itemToInseart;
        }
        return listInner;
    }

//    public static DoubleNode makeListSimple{  //метод, который создаёт список по простому
//        DoubleNode doubleNodeItem0 = new DoubleNode(0, null, null);
//        DoubleNode doubleNodeItem1 = new DoubleNode(1, null, null);
//        DoubleNode doubleNodeItem2 = new DoubleNode(2, null, null);
//
//        DoubleNode doubleListHead = doubleNodeItem0;
//        doubleNodeItem0.previous = doubleNodeItem1;
//        doubleNodeItem1.previous = doubleNodeItem2;
//        doubleNodeItem1.next = doubleNodeItem0;
//        doubleNodeItem2.next = doubleNodeItem1;
//        DoubleNode doubleListTail = doubleNodeItem2;
//
//        return ;
//    }
}
