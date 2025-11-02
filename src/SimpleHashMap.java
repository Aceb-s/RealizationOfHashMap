public class SimpleHashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private Node<K, V>[] table;

    static class Node<K, V> {
        final K key;
        V value;
        Node<K, V> next;
        final int hash;

        Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    @SuppressWarnings("unchecked")
    public SimpleHashMap() {
        this.table = (Node<K, V>[]) new Node[DEFAULT_CAPACITY];
    }

    private int hash(K key) {
        return key.hashCode();
    }

    private int indexFor(int hash, int length) {
        return Math.abs(hash % length);
    }

    public V put(K key, V value) {
        int hash = hash(key);
        int index = indexFor(hash, table.length);

        Node<K, V> current = table[index];
        while (current != null) {
            if (current.hash == hash &&
                    (current.key == key || key.equals(current.key))) {
                V oldValue = current.value;
                current.value = value;
                return oldValue;
            }
            current = current.next;
        }

        Node<K, V> newNode = new Node<>(hash, key, value, table[index]);
        table[index] = newNode;

        return null;
    }

    public V get(K key) {
        int hash = hash(key);
        int index = indexFor(hash, table.length);

        Node<K, V> current = table[index];
        while (current != null) {
            if (current.hash == hash &&
                    (current.key == key || key.equals(current.key))) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public V remove(K key) {
        int hash = hash(key);
        int index = indexFor(hash, table.length);

        Node<K, V> current = table[index];
        Node<K, V> prev = null;

        while (current != null) {
            if (current.hash == hash &&
                    (current.key == key || key.equals(current.key))) {

                if (prev == null) {
                    table[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                return current.value;
            }
            prev = current;
            current = current.next;
        }
        return null;
    }
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("{");

        boolean firstElement = true;
        for (Node<K, V> node : table) {
            Node<K, V> current = node;
            while (current != null) {
                if (!firstElement) {
                    result.append(", ");
                }
                result.append(current.key).append("=").append(current.value);
                firstElement = false;
                current = current.next;
            }
        }

        result.append("}");
        return result.toString();
    }
}