import java.util.HashMap;

public class InMemoryDB {
    private HashMap<String, Integer> database;
    private HashMap<String, Integer> transactionSnapshot;
    private boolean inTransaction;

    public InMemoryDB() {
        database = new HashMap<>();
        transactionSnapshot = new HashMap<>();
        inTransaction = false;
    }

    public Integer get(String key) {
        return database == null ? null : database.get(key);
    }
    public void put(String key, int value) {
        if (!inTransaction) {
            throw new RuntimeException("No transaction in progress");
        }
        transactionSnapshot.put(key, value);
    }

    public void commit() {
        if (!inTransaction) {
            throw new RuntimeException("No transaction in progress");
        }
        database.clear();
        database.putAll(transactionSnapshot);
        inTransaction = false;
    }

    public void rollback() {
        if (!inTransaction) {
            throw new RuntimeException("No transaction in progress");
        }
        transactionSnapshot.clear();
        inTransaction = false;
    }

    public void begin_transaction() {
        if (inTransaction) {
            throw new RuntimeException("Transaction already in progress");
        }
        inTransaction = true;
        if (transactionSnapshot != null) {
            transactionSnapshot.clear();
            transactionSnapshot.putAll(database);
        }

    }
}

