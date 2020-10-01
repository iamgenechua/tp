package seedu.address.model.room;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.room.exceptions.DuplicateRoomException;

import java.util.List;
import javafx.collections.FXCollections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRooms.DEFAULT_ROOM;
import static seedu.address.testutil.TypicalRooms.DEFAULT_ROOMID;
import static seedu.address.testutil.TypicalRooms.ROOM_1;
import static seedu.address.testutil.TypicalRooms.ROOM_2;
import static seedu.address.testutil.TypicalRooms.ROOM_3;
import static seedu.address.testutil.TypicalRooms.ROOM_4;
import static seedu.address.testutil.TypicalRooms.ROOM_5;
import static seedu.address.testutil.TypicalRooms.ROOM_6;

public class UniqueRoomListTest {

    private final UniqueRoomList uniqueRoomList = new UniqueRoomList();

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueRoomList.contains(DEFAULT_ROOMID));
    }

    @Test public void contains_roomInList_returnsTrue() {
        uniqueRoomList.add(DEFAULT_ROOM);
        assertTrue(uniqueRoomList.contains(DEFAULT_ROOMID));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRoomList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniqueRoomList.add(DEFAULT_ROOM);
        assertThrows(DuplicateRoomException.class, () -> uniqueRoomList.add(DEFAULT_ROOM));
    }

    @Test
    public void getComplementRooms_returnsCorrectList() {
        List<Room> rooms = FXCollections.observableArrayList(ROOM_1,ROOM_2,ROOM_3, ROOM_4, ROOM_5, ROOM_6);
        uniqueRoomList.setRooms(rooms);
        ObservableList<Room> input = FXCollections.observableArrayList(ROOM_1, ROOM_3, ROOM_6);
        ObservableList<Room> output = uniqueRoomList.getComplementRooms(input);
        assertTrue(output.contains(ROOM_2) && output.contains(ROOM_4) && output.contains(ROOM_5));
    }
    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueRoomList.asUnmodifiableObservableList().remove(0));
    }
}
