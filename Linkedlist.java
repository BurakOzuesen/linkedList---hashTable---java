public class Linkedlist {
    public Node head;
    public Linkedlist next;
    public int sizeOfList;

    static class Node {
        Employee employee;
        Node next;
    }

    public static void printList(Linkedlist list) {
        try{
        Node current = list.head;

            for (int i = 0; i < list.sizeOfList ; i++){
                if (i > 0){
                    System.out.print("---->");
                }
                System.out.print(current.employee.Phone);
                current = current.next;
            }
        }catch (Exception exception){
            System.out.print("Null");
        }
    }

    // my print function for linear probing
    public static void printListPart2(Linkedlist list) {
        try{
            Node current = list.head;
            for (int i = 0; i < list.sizeOfList ; i++){
                if (i > 0){
                    System.out.print("---->");
                }
                System.out.print(current.employee.Phone);
                current = current.next;
            }
        }catch (Exception exception){
            System.out.print("null");
        }
    }

    // insert function for linked list
    public static void insert(Linkedlist list, Employee employee) {

        Node new_node = new Node();

        new_node.employee = employee;

        if (list.head == null) {
            list.head = new_node;

        } else {

            Node last = list.head;
            while (last.next != null) {
                last = last.next;
            }

            last.next = new_node;

        }

    }


    public static Employee getEmployee(Linkedlist list, int PhoneNumber){
        long start = System.nanoTime();


        int comparison = 0;
        while (list.head.employee.Phone != PhoneNumber){
            list.head = list.head.next;
            comparison++;
        }
        if (list.head.employee.Phone == PhoneNumber){
            comparison++;
            long end = System.nanoTime();
            float elapsedTime = end - start;
            System.out.println("Key found with "+comparison+" comparisons");
            System.out.println("CPU time taken to search = "+elapsedTime+" ns");
            return list.head.employee;
        }
        else return null;
    }


}