package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyBookingBook;
import seedu.address.model.ReadOnlyRoomServiceBook;

/**
 * Represents a storage for {@link seedu.address.model.RoomServiceBook}.
 */
public interface RoomServiceBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getRoomServiceBookFilePath();

    /**
     * Returns RoomServiceBook data as a {@link ReadOnlyAddressBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyBookingBook> readRoomServiceBook() throws DataConversionException, IOException;

    /**
     * @see #getRoomServiceBookFilePath()
     */
    Optional<ReadOnlyBookingBook> readRoomServiceBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyRoomServiceBook} to the storage.
     * @param bookingBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveRoomServiceBook(ReadOnlyRoomServiceBook bookingBook) throws IOException;

    /**
     * @see #saveRoomServiceBook(ReadOnlyRoomServiceBook)
     */
    void saveRoomServiceBook(ReadOnlyRoomServiceBook roomServiceBook, Path filePath) throws IOException;
}