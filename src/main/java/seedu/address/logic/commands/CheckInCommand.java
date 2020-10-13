package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSONAL_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;

import java.time.LocalDate;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.booking.Booking;
import seedu.address.model.booking.exception.ConflictingBookingException;
import seedu.address.model.booking.exception.DuplicateBookingException;

/**
 * Encapsulates the Check In feature.
 */
public class CheckInCommand extends Command {

    public static final String COMMAND_WORD = "checkIn";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Checks in a person into the hotel.\n"
            + "Parameters: " + PREFIX_PERSONAL_ID + "[PERSONAL_ID] (must be a positive integer) "
            + PREFIX_ROOM_ID + "[ROOM_ID] (must be a valid room number) "
            + PREFIX_START_DATE + "[START_DATE] (in the format YYYY-MM-DD) "
            + PREFIX_END_DATE + "[END_DATE] (in the format YYYY-MM-DD)\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_PERSONAL_ID + "69 "
            + PREFIX_ROOM_ID + "4102 "
            + PREFIX_START_DATE + "2020-09-14 "
            + PREFIX_END_DATE + "2020-09-17";

    public static final String MESSAGE_ARGUMENTS = "Personal id: %1$d, Room Id: %2$d, Start date: %3$s, End date: %4$s";
    public static final String MESSAGE_PERSONAL_ID_MISSING = "No valid personalId can be found.";
    public static final String MESSAGE_ROOM_ID_MISSING = "No valid roomId can be be found";
    public static final String MESSAGE_SUCCESS = "Successfully checked in: %s";
    public static final String MESSAGE_CONFLICTING_BOOKING = "The room has already been booked during this period";
    public static final String MESSAGE_DUPLICATE_BOOKING = "Cannot add duplicate booking";

    private final int personalId;
    private final int roomId;
    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Creates a CheckInCommand.
     *
     * @param personalId the personalId of the person checking in
     * @param roomId the roomId of the room that the person is checking into
     * @param startDate the start date of the booking
     * @param endDate the end date of the booking
     */
    public CheckInCommand(int personalId, int roomId, LocalDate startDate, LocalDate endDate) {
        requireAllNonNull(personalId, roomId, startDate, endDate);

        this.personalId = personalId;
        this.roomId = roomId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Booking booking;

        if (!model.hasPersonWithId(personalId)) {
            throw new CommandException(MESSAGE_PERSONAL_ID_MISSING);
        }

        if (!model.hasRoom(roomId)) {
            throw new CommandException(MESSAGE_ROOM_ID_MISSING);
        }

        booking = new Booking(roomId, personalId, startDate, endDate, true);
        int bookingId = booking.getId();

        try {
            model.addBooking(booking);
            return new CommandResult(String.format(MESSAGE_SUCCESS, booking));
        } catch (DuplicateBookingException e) {
            Booking.setNextAvailableId(bookingId);
            throw new CommandException(MESSAGE_DUPLICATE_BOOKING);
        } catch (ConflictingBookingException e) {
            Booking.setNextAvailableId(bookingId);
            throw new CommandException(MESSAGE_CONFLICTING_BOOKING);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CheckInCommand)) {
            return false;
        }

        // state check
        CheckInCommand e = (CheckInCommand) other;
        return personalId == e.personalId
                && roomId == e.roomId
                && startDate.isEqual(e.startDate)
                && endDate.isEqual(e.endDate);
    }
}
