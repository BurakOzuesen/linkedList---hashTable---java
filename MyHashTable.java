public class MyHashTable {
    private int TABLE_SIZE;
    private Linkedlist[] tableMember = new Linkedlist[TABLE_SIZE];

    public MyHashTable(Linkedlist[] tableMember, int TABLE_SIZE) {
        this.tableMember = tableMember;
        this.TABLE_SIZE = TABLE_SIZE;
    }

    private int hash1(int PhoneNumber, int size){
        return (PhoneNumber % size);
    }
    public void printTable(Linkedlist[] tableMember){
        for (int i = 0; i < TABLE_SIZE; i++){
            System.out.print("[Chain "+i+"]: ");
            Linkedlist.printList(tableMember[i]);
            System.out.println();
        }
    }

    // my first put function for seperate chaining
    public void put(int PhoneNumber, Employee employee, MyHashTable table){
        int index = hash1(PhoneNumber, TABLE_SIZE);
        if (tableMember[index] == null){
            tableMember[index] = new Linkedlist();
        }
        tableMember[index].insert(tableMember[index],employee);
        tableMember[index].sizeOfList++;

    }


    public Employee get(int PhoneNumber, MyHashTable table){
        int index = hash1(PhoneNumber, TABLE_SIZE);
        while (table.tableMember[index].head.employee.Phone != PhoneNumber)
        {
            table.tableMember[index].head = table.tableMember[index].head.next;
        }
        return table.tableMember[index].head.employee;
    }

    private int linearHash(int PhoneNumber, int size){
        return (PhoneNumber % (size));
    }

    // put function for linear hashing
    public MyHashTable linearHashPut(int PhoneNumber, Employee employee, MyHashTable table){
        int index = linearHash(PhoneNumber, TABLE_SIZE);
        int counter = 0;
        while (counter == 0) {
            if (tableMember[index] == null) {
                tableMember[index] = new Linkedlist();
                tableMember[index].insert(tableMember[index], employee);
                tableMember[index].sizeOfList++;
                counter++;
            } else {
                index++;
            }
        }
        return table;
    }

    // printing table through linear probing
    public void printPart2(Linkedlist[] tableMember){
        for (int i = 0; i < TABLE_SIZE; i++){
            System.out.print("["+i+"]--->");
            Linkedlist.printListPart2(tableMember[i]);
            System.out.println();
        }
    }

    public int doubleHashFunction (int PhoneNumber, int size, int counter){
        int h1 = hash1(PhoneNumber,size);
        int h2 = 1 + (PhoneNumber % (TABLE_SIZE - 1));
        int sum = h1 + counter * h2;
        return sum % size;
    }


    public MyHashTable doubleHashPut(int PhoneNumber, Employee employee, MyHashTable table,int i){
        int index = linearHash(PhoneNumber,TABLE_SIZE);

        int counter = 0;
        while (counter == 0) {
            if (tableMember[index] == null) {
                tableMember[index] = new Linkedlist();
                tableMember[index].insert(tableMember[index], employee);
                tableMember[index].sizeOfList++;
                counter++;
            } else {

                i = i + 1;

                index = doubleHashFunction(PhoneNumber, TABLE_SIZE, i);
            }

    }
        return table;
    }

    public Employee getSeperateChaining(int PhoneNumber, MyHashTable table){
        int index = hash1(PhoneNumber,TABLE_SIZE);
        Employee thisEmployee = new Employee();
        thisEmployee = Linkedlist.getEmployee(table.tableMember[index],PhoneNumber);
        return thisEmployee;
    }

    public Employee getLinearProbing(int PhoneNumber, MyHashTable table){
        long start = System.nanoTime();
        int comparison = 0;
        int index = linearHash(PhoneNumber,TABLE_SIZE);
        Employee thisEmployee = new Employee();
        while (table.tableMember[index] != null){
            if (table.tableMember[index].head.employee.Phone == PhoneNumber){
                comparison++;
                long end = System.nanoTime();
                float elapsedTime = end - start;
                System.out.println("Key found with "+comparison+" comparisons");
                System.out.println("CPU time taken to search = "+elapsedTime+" ns");
                return thisEmployee;
            }else {
                comparison++;
                index++;
            }
        }
        return thisEmployee;
    }

    public Employee getDoubleHashing(int PhoneNumber, MyHashTable table){
        long start = System.nanoTime();
        int comparison = 0;
        int index = linearHash(PhoneNumber,TABLE_SIZE);
        Employee thisEmployee = new Employee();
        int counter = 0;
        while (table.tableMember[index] != null){
            if (table.tableMember[index].head.employee.Phone == PhoneNumber){
                comparison++;
                long end = System.nanoTime();
                float elapsedTime = end - start;
                System.out.println("Key found with "+comparison+" comparisons");
                System.out.println("CPU time taken to search = "+elapsedTime+" ns");
                return thisEmployee;
            }else {
                comparison++;
                counter++;
                index = doubleHashFunction(PhoneNumber,TABLE_SIZE,counter);
            }
        }
        return thisEmployee;
    }
}