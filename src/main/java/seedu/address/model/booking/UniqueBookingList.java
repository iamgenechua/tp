package seedu.address.model.booking;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.booking.exception.BookingNotFoundException;
import seedu.address.model.booking.exception.ConflictingBookingException;
import seedu.address.model.booking.exception.DuplicateBookingException;

public class UniqueBookingList implements Iterable<Booking> {
    private final ObservableList<Booking> internalList = FXCollections.observableArrayList();
    private final ObservableList<Booking> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent booking as the given argument.
     */
    public boolean contains(Booking toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Returns true if the list contains a booking with the given id
     */
    public boolean hasBookingWithId(Integer id) {
        requireNonNull(id);
        return internalList.stream().anyMatch(booking -> booking.getId().equals(id));
    }

    /**
     * Adds a booking to the list.
     * The booking must not already exist in the list.
     */
    public void add(Booking toAdd) {
        requireNonNull(toAdd);
        // check duplicate
        if (contains(toAdd)) {
            throw new DuplicateBookingException();
        }
        // check if conflict
        boolean hasConflict;

        hasConflict = internalList.stream().anyMatch(booking -> booking.hasConflict(toAdd));
        if (hasConflict) {
            throw new ConflictingBookingException();
        } else {
            internalList.add(toAdd);
        }
    }

    public Booking getBooking(int roomId) {
        requireNonNull(roomId);
        return internalList.stream()
                .filter(booking -> booking.getRoomId().equals(roomId))
                .filter(Booking::isActive)
                .findFirst()
                .orElseThrow(() -> new BookingNotFoundException());
    }

    /**
     * Set a booking to inactive. Create new booking and set.
     * @param roomId The id of the room to be set inactive
     */
    public void setBookingInactive(int roomId) {
        requireNonNull(roomId);
        Booking booking = getBooking(roomId);
        Booking editedBooking = new Booking(booking.getRoomId(), booking.getPersonId(),
                booking.getStartDate(), booking.getEndDate(), false, booking.getId());
        setBooking(booking, editedBooking);
    }

    public void setBooking(Booking target, Booking editedBooking) {
        requireAllNonNull(target, editedBooking);
        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new BookingNotFoundException();
        }

        if (!target.equals(editedBooking) && contains(editedBooking)) {
            throw new DuplicateBookingException();
        }

        boolean hasConflict;

        hasConflict = internalList.stream()
                .filter(booking -> !booking.equals(target))
                .anyMatch(booking -> booking.hasConflict(editedBooking));
        if (hasConflict) {
            throw new ConflictingBookingException();
        }

        internalList.set(index, editedBooking);
    }

    public void setBookings(UniqueBookingList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code bookings}.
     * {@code bookings} must not contain duplicate bookings.
     */
    public void setBookings(List<Booking> bookings) {
        requireAllNonNull(bookings);
        if (!bookingsAreUnique(bookings)) {
            throw new DuplicateBookingException();
        }

        internalList.setAll(bookings);
    }

    /**
     * Returns true if {@code bookings} contains only unique bookings.
     */
    private boolean bookingsAreUnique(List<Booking> bookings) {
        for (int i = 0; i < bookings.size() - 1; i++) {
            for (int j = i + 1; j < bookings.size(); j++) {
                if (bookings.get(i).equals(bookings.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Booking> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Booking> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueBookingList // instanceof handles nulls
                && internalList.equals(((UniqueBookingList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

}