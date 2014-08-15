/**
 * Created by Faton on 15.08.2014.
 */
public class MyDoubleLinkedListCollection {
    static DoubleNode doubleListHead = null;
    static DoubleNode doubleListTail = null;

    public static void main(String[] args) {
        makeList(1, 2, 3, 6, 8, 10, 12);

        System.out.println("Основной список: "+printToString(doubleListTail)); //значки "<-  ->"обозначают ссылку, например 1<-(2)->3, значит что у элемента с индексом 1 и значением 2 предыдущий элемент со значением 1, а сдедующий элемент со значением 3
    }

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
