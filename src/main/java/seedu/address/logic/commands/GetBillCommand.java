package seedu.address.logic.commands;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKING_ID;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.booking.Booking;
import seedu.address.model.booking.exception.BookingNotFoundException;
import seedu.address.model.room.Room;
import seedu.address.model.roomservice.RoomService;
import seedu.address.model.roomservice.RoomServiceType;

/**
 * Calculates the total bill for a particular occupied room.
 */
public class GetBillCommand extends Command {

    public static final String COMMAND_WORD = "getBill";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Gets total bill for a particular occupied room.\n"
            + "Parameters: BOOKING ID "
            + PREFIX_BOOKING_ID + "[BOOKING ID]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_BOOKING_ID + "8";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "getBill command not implemented yet";
    public static final String MESSAGE_ARGUMENTS = "Room id: %1$d";
    public static final String MESSAGE_BOOKING_MISSING = "No valid booking can be found.";
    public static final String MESSAGE_SUCCESS_GET_BILL = "Total bill for booking id: %1$d is %2$d";

    private final int bookingId;

    /**
     * Creates a GetBillCommand.
     * @param bookingId the room id of which to get the bill for
     */
    public GetBillCommand(int bookingId) {
        requireAllNonNull(bookingId);
        this.bookingId = bookingId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Booking booking;
        Room room;

        if (!model.hasBookingWithId(bookingId)) {
            throw new CommandException(MESSAGE_BOOKING_MISSING);
        }

        assert model.hasBookingWithId(bookingId);

        try {
            booking = model.getBookingWithId(bookingId);
        } catch (BookingNotFoundException e) {
            throw new CommandException(MESSAGE_BOOKING_MISSING);
        }

        int roomId = booking.getRoomId();
        room = model.getRoom(roomId);
        int pricePerNight = room.getPrice();
        int numNights = booking.getDuration();
        int totalPrice = pricePerNight * numNights;

        ObservableList<RoomService> roomServices = model.getRoomServicesForBooking(booking.getId());

        String billBreakdown = "";

        for (RoomService roomService : roomServices) {
            RoomServiceType roomServiceType = roomService.getType();
            billBreakdown += roomServiceType.getVerboseName() + ": " + roomServiceType.getPrice() + "\n";
            totalPrice += roomServiceType.getPrice();
        }

        return new CommandResult(billBreakdown
                + String.format(MESSAGE_SUCCESS_GET_BILL, bookingId, totalPrice));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GetBillCommand)) {
            return false;
        }

        // state check
        GetBillCommand e = (GetBillCommand) other;
        return bookingId == e.bookingId;
    }
}
