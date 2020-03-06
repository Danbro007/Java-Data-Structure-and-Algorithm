package hashtab;

public class MyHashTable {

    private int size;
    public EmpLinkedList[] array;

    public MyHashTable(int size) {
        this.size = size;
        this.array = new EmpLinkedList[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = new EmpLinkedList();

        }
    }

    private int hash(Emp emp) {
        return emp.id % size;
    }
    public void put(Emp emp){
        array[hash(emp)].add(emp);
    }

    public void list(){
        for (int i = 0; i < array.length; i++) {
            System.out.println("第" + i + "数组：");
            array[i].list();
        }
    }

    public static void main(String[] args) {
        MyHashTable myhashTable = new MyHashTable(10);
        Emp emp = new Emp(1, "john1");
        Emp emp2 = new Emp(2, "john2");
        Emp emp3 = new Emp(11, "john11");
        myhashTable.put(emp);
        myhashTable.put(emp2);
        myhashTable.put(emp3);
        myhashTable.list();
    }



}


class EmpLinkedList {
    public Emp head;

    public void add(Emp emp) {
        if (head == null) {
            head = emp;
        } else {
            Emp cur = head;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = emp;
        }
    }


    public void list() {
        if (head == null){
            System.out.println("空");
            return;
        }
        Emp temp = head;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }

    }

}


class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
