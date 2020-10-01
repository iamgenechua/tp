package seedu.address.model.room;

import java.util.Objects;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class RoomID {

    public static final String MESSAGE_CONSTRAINTS = "RoomID must be 4 digits long, and it should not be blank";
    public final int id;
    public static final String VALIDATION_REGEX = "[0-9]{4}";

    public RoomID(int id) {
        requireNonNull(id);
        checkArgument(isValidRoomID(Integer.toString(id)), MESSAGE_CONSTRAINTS);
        this.id = id;
    }

    /**
     * Returns true if a given string is a valid Room ID.
     */
    public static boolean isValidRoomID(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomID roomID = (RoomID) o;
        return id == roomID.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return Integer.toString(id);
    }
}
