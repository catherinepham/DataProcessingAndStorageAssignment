public class Main {
    public static void main(String[] args) {
        InMemoryDB inMemoryDB = new InMemoryDB();

        //System.out.println(inMemoryDB.get("A"));
        inMemoryDB.begin_transaction();
        inMemoryDB.put("A",5);
        System.out.println(inMemoryDB.get("A"));
        inMemoryDB.put("A", 6);
        inMemoryDB.commit();
        System.out.println(inMemoryDB.get("A"));
//        inMemoryDB.commit();
//        inMemoryDB.rollback();
        System.out.println(inMemoryDB.get("B"));
        inMemoryDB.begin_transaction();
        inMemoryDB.put("B", 10);
        inMemoryDB.rollback();
        System.out.println(inMemoryDB.get("B"));

    }
}