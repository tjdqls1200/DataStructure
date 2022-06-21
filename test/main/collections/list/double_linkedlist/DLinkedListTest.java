package main.collections.list.double_linkedlist;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class DLinkedListTest {
    private final DLinkedList<String> list = new DLinkedList<>();

    @BeforeEach
    void setUp() {
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
    }

    @AfterEach
    void after() {
        list.clear();
    }

    @Test
    @DisplayName("맨 앞에 추가")
    void addFirst() {
        list.addFirst("a");

        assertThat(list.get(0)).isEqualTo("a");
        assertThat(list.getHead().data).isEqualTo("a");
    }

    @Test
    @DisplayName("기본 add는 맨 뒤에 추가")
    void add() {
        list.add("f");

        assertThat(list.get(list.size() - 1)).isEqualTo("f");
    }

    @Test
    @DisplayName("특정 인덱스에 add")
    void testAdd() {
        list.add(list.size(), "f");

        assertThat(list.getTail().data).isEqualTo("f");
        assertThrows(IndexOutOfBoundsException.class, () ->
                list.add(-1, "f"));
        assertThrows(IndexOutOfBoundsException.class, () ->
                list.add(10000, "f"));
    }

    @Test
    void remove() {
        list.remove();

        assertThat(list.getHead().data).isEqualTo("c");
    }

    @Test
    @DisplayName("특정 index의 요소를 삭제하고 데이터 반환")
    void removeElementOfIndex() {
        assertThat(list.remove(2)).isEqualTo("d");
    }

    @Test
    @DisplayName("특정 요소 삭제")
    void removeElement () {
        int index = list.indexOf("d");

        assertThat(list.remove("d")).isEqualTo(true);
        assertThat(list.get(index)).isNotEqualTo("d");
    }

    @Test
    void set() {
        String before = list.get(2);
        list.set(2, "f");
        String after = list.get(2);

        assertThat(before).isNotEqualTo(after);
        assertThat(list.get(2)).isEqualTo("f");
    }

    @Test
    @DisplayName("LastIndexOf, 뒤부터 조회")
    void lastIndexOf() {
        list.addLast("b");
        int index = list.lastIndexOf("b");

        assertThat(index).isEqualTo(list.size() - 1);
    }

    @Test
    @DisplayName("기본 IndexOf, 앞부터 조회")
    void indexOf() {
        int index = list.indexOf("b");
        assertThat(index).isEqualTo(0);
    }

}