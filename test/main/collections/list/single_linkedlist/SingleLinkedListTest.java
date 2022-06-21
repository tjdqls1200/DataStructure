package main.collections.list.single_linkedlist;

import main.collections.list.single_linkedlist.SingleLinkedList;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class SingleLinkedListTest {
    @Test
    void remove_object() {
        //given
        SingleLinkedList<String> sLinkedList = new SingleLinkedList<>();
        for (int i = 1; i <= 5; i++) {
            boolean result = sLinkedList.add(Integer.toString(i));
        }

        boolean result = sLinkedList.remove("5");

        //then
        //Assertions.assertThat(result).isTrue();
        Assertions.assertThat(sLinkedList.getSize()).isEqualTo(4);
    }
}