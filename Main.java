import java.io.*;

public class Main {

    public static void main(String[] args) throws ArrayIndexOutOfBoundsException, IOException {

        // Getting input file and set initial values
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        int lines = 0;
        System.setOut(new PrintStream(new FileOutputStream("output.txt")));
        while (reader.readLine() != null) lines++;
        reader.close();
        Float loadFactor1 = Float.valueOf(args[1]);
        Float loadFactor2 = Float.valueOf(args[2]);
        int searchKey = Integer.parseInt(args[3]);
        int TABLE_SIZE1 = (int) ((lines-1) / loadFactor1);
        int TABLE_SIZE2 = (int) ((lines-1) / loadFactor2);
        Linkedlist[] LinkListArray = new Linkedlist[TABLE_SIZE1];
        Linkedlist[] LinkListArrayP2 = new Linkedlist[TABLE_SIZE2];
        Linkedlist[] LinkListArrayP3 = new Linkedlist[TABLE_SIZE2];
        MyHashTable table = new MyHashTable(LinkListArray, TABLE_SIZE1);
        MyHashTable tablePart2 = new MyHashTable(LinkListArrayP2,TABLE_SIZE2);
        MyHashTable tablePart3 = new MyHashTable(LinkListArrayP3,TABLE_SIZE2);
        Linkedlist myLinkedList = new Linkedlist();


        File file = new File(args[0]);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        StringBuffer sb = new StringBuffer();
        String line;

        // reading input file line by line and creating hashtables
        while ((line=br.readLine()) != null){
            sb.append(line);
            sb.append("\n");
            String[] myNewLine = line.split(" ");
            if (myNewLine[0].equals("E_Code")){
                continue;
            }
            Employee employee = new Employee();
            employee.E_Code = myNewLine[0];
            employee.NRIC = myNewLine[1];
            employee.Phone = Integer.parseInt(myNewLine[2].trim());
            table.put(employee.Phone,employee,table);
            tablePart2.linearHashPut(employee.Phone,employee,tablePart2);
            tablePart3.doubleHashPut(employee.Phone,employee,tablePart3,0);


        }

        // printing and searching in here
        String fileName = String.valueOf(args[0]).split("\\.")[0];
        System.out.println(fileName+","+"LF1="+args[1]+","+"LF2="+args[2]+","+args[3]);
        System.out.println("PART1");
        Employee searchingEmployeePart1 = new Employee();
        Employee searchingEmployeePart2 = new Employee();
        Employee searchingEmployeePart3 = new Employee();
        table.printTable(LinkListArray);
        System.out.println("PART2");
        System.out.println("Hashtable for Linear Probing");
        tablePart2.printPart2(LinkListArrayP2);
        System.out.println("Hashtable for Double Hashing");
        tablePart3.printPart2(LinkListArrayP3);
        System.out.println("SEPARATE CHAINING:");
        searchingEmployeePart1 = table.getSeperateChaining(searchKey,table);
        System.out.println("LINEAR PROBING:");
        searchingEmployeePart2 = tablePart2.getLinearProbing(searchKey,tablePart2);
        System.out.println("DOUBLE HASHING:");
        searchingEmployeePart3 = tablePart3.getDoubleHashing(searchKey,tablePart3);
    }
}
