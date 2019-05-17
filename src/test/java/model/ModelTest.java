package model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {
    private Model m;


    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        m = new Model();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        m = null;
    }

    @org.junit.jupiter.api.Test
    void generateRandomNumbers() {
        List<Integer> num = new ArrayList<>();
        num.add(1);
        num.add(2);
        num.add(3);
        num.add(4);
        List<Integer> result = new ArrayList<>();
        result = m.generateRandomNumbers(1,4,4);
        assertTrue(num.equals(result));
    }

    @org.junit.jupiter.api.Test
    void generateCorrentAnswerPlace() {
        assertEquals(1, m.generateCorrentAnswerPlace(1, 1));
    }

    @org.junit.jupiter.api.Test
    void checkNewWordsString() {
        assertFalse(m.checkNewWordsString(""));
        assertFalse(m.checkNewWordsString("qwertzuiopasdfghjklyxcvbnmqwertzuiopasdfghjklyxcvbnmqwertzuiop"));
        assertTrue(m.checkNewWordsString("bicycle"));

    }

    @org.junit.jupiter.api.Test
    void searchWordInList(){
        List<szavak> temp = new ArrayList<>();
        temp.add(new szavak("zone", "zóna"));
        temp.add(new szavak("allow", "engedélyez"));

        assertTrue(m.searchWordInList("angol", "zone", temp));
        assertTrue(m.searchWordInList("magyar", "engedélyez", temp));
        assertFalse(m.searchWordInList("angol", "bicycle", temp));
        assertFalse(m.searchWordInList("magyar", "bicikli", temp));
    }
}