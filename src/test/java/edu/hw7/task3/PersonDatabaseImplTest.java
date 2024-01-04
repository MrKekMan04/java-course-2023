package edu.hw7.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.stream.IntStream;
import static org.junit.jupiter.api.Assertions.*;

public class PersonDatabaseImplTest {
    private static final int ITERATIONS_COUNT = 10_000;
    private static final int THREADS_COUNT = 4;
    private PersonDatabase database;

    @BeforeEach
    public void setUp() {
        database = new PersonDatabaseImpl();
    }

    @Test
    public void assertThatAddWorksRight() {
        assertEquals(0, database.size());
        database.add(new Person(1, "Peter", null, null));
        assertEquals(1, database.size());
        database.add(new Person(2, null, null, null));
        assertEquals(2, database.size());
    }

    @Test
    public void assertThatRemoveWorksRight() {
        Person peter = new Person(1, "Peter", null, null);

        assertEquals(0, database.size());
        database.add(peter);
        assertEquals(1, database.size());
        database.delete(peter.id());
        assertEquals(0, database.size());
    }

    @Test
    public void assertThatFindByNameWorksRight() {
        String peterName = "Peter";

        Person peter1 = new Person(1, peterName, "null", "null");
        Person peter2 = new Person(2, peterName, "null", "null");
        Person notPeter = new Person(3, "Ally", "null", "null");
        Person nullableAttributes = new Person(4, peterName, null, null);

        database.add(peter1);
        database.add(peter2);
        database.add(notPeter);
        database.add(nullableAttributes);

        assertEquals(List.of(peter1, peter2), database.findByName(peterName));
        assertEquals(4, database.size());
    }

    @Test
    public void assertThatFindByAddressWorksRight() {
        String address = "NYC";

        Person peter = new Person(1, "Peter", address, "null");
        Person henry = new Person(2, "Henry", "null", "null");
        Person ally = new Person(3, "Ally", address, "null");
        Person nullableAttributes = new Person(4, null, address, null);

        database.add(peter);
        database.add(henry);
        database.add(ally);
        database.add(nullableAttributes);

        assertEquals(List.of(peter, ally), database.findByAddress(address));
        assertEquals(4, database.size());
    }

    @Test
    public void assertThatFindByPhoneWorksRight() {
        String phone = "5315632";

        Person peter = new Person(1, "Peter", "null", "6116136");
        Person henry = new Person(2, "Henry", "null", phone);
        Person ally = new Person(3, "Ally", "null", phone);
        Person nullableAttributes = new Person(4, null, null, phone);

        database.add(peter);
        database.add(henry);
        database.add(ally);
        database.add(nullableAttributes);

        assertEquals(List.of(henry, ally), database.findByPhone(phone));
        assertEquals(4, database.size());
    }

    @Test
    public void assertThatMultiThreadingWorksRight() {
        List<Thread> threads = IntStream.range(0, THREADS_COUNT)
            .mapToObj(i -> new Thread(() -> {
                for (int j = 0; j < ITERATIONS_COUNT; j++) {
                    database.add(new Person(i * ITERATIONS_COUNT + j, null, null, null));
                }
            }))
            .toList();

        threads.forEach(Thread::start);
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException ignored) {
            }
        });

        assertEquals(THREADS_COUNT * ITERATIONS_COUNT, database.size());
    }
}
