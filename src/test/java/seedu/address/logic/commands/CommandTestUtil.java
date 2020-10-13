package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSONAL_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final Integer VALID_ID_AMY = 1;
    public static final Integer VALID_ID_BOB = 2;
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_NAME_GENE = "Gene Xiao";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_PHONE_GENE = "420420420";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_EMAIL_GENE = "gene@nuscomputing.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_ADDRESS_GENE = "Streety McStreetFace";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    // Check In Values
    public static final int VALID_PERSONAL_ID_GENE = 69;
    public static final int VALID_PERSONAL_ID_AMY = 23;
    public static final int VALID_PERSONAL_ID_BOB = 12;
    public static final int VALID_PERSONAL_ID_CHLOE = 6;
    public static final int VALID_PERSONAL_ID_DAN = 4;
    public static final int VALID_ROOM_ID_AMY = 4102;
    public static final int VALID_ROOM_ID_BOB = 2301;
    public static final int VALID_ROOM_ID_GENE = 1240;
    public static final int CONFLICT_AMY_ROOM_ID_CHLOE = 4102;
    public static final int VALID_ROOM_ID_DAN = 1237;
    public static final int INVALID_ROOM_ID = 1;
    public static final LocalDate VALID_START_DATE_AMY = LocalDate.parse("2020-10-05",
            DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    public static final LocalDate VALID_END_DATE_AMY = LocalDate.parse("2020-10-10",
            DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    public static final LocalDate VALID_START_DATE_BOB = LocalDate.parse("2020-12-12",
            DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    public static final LocalDate VALID_END_DATE_BOB = LocalDate.parse("2020-12-12",
            DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    public static final LocalDate CONFLICT_AMY_START_DATE_CHLOE = LocalDate.parse("2020-10-06",
            DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    public static final LocalDate CONFLICT_AMY_END_DATE_CHLOE = LocalDate.parse("2020-10-09",
            DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    public static final LocalDate VALID_START_DATE_DAN = LocalDate.parse("2020-11-11",
            DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    public static final LocalDate VALID_END_DATE_DAN = LocalDate.parse("2020-11-21",
            DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    public static final LocalDate VALID_START_DATE_GENE = LocalDate.parse("2025-01-30",
            DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    public static final LocalDate VALID_END_DATE_GENE = LocalDate.parse("2025-06-06",
            DateTimeFormatter.ofPattern("yyyy-MM-dd"));


    // Booking Values
    public static final int VALID_BOOKING_ID_AMY = 1;
    public static final int VALID_BOOKING_ID_BOB = 2;
    public static final int CONFLICT_AMY_VALID_BOOKING_ID_CHLOE = 1;
    public static final int VALID_BOOKING_ID_DAN = 4;
    public static final int BOOKING_DURATION_AMY = 5;


    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    // Check In Descriptions
    public static final String PERSONAL_ID_DESC_AMY = " " + PREFIX_PERSONAL_ID + VALID_PERSONAL_ID_AMY;
    public static final String PERSONAL_ID_DESC_BOB = " " + PREFIX_PERSONAL_ID + VALID_PERSONAL_ID_BOB;
    public static final String ROOM_ID_DESC_AMY = " " + PREFIX_ROOM_ID + VALID_ROOM_ID_AMY;
    public static final String ROOM_ID_DESC_BOB = " " + PREFIX_ROOM_ID + VALID_ROOM_ID_AMY;
    public static final String START_DATE_DESC_AMY = " " + PREFIX_START_DATE + VALID_START_DATE_AMY;
    public static final String START_DATE_DESC_BOB = " " + PREFIX_START_DATE + VALID_START_DATE_BOB;
    public static final String END_DATE_DESC_AMY = " " + PREFIX_END_DATE + VALID_END_DATE_AMY;
    public static final String END_DATE_DESC_BOB = " " + PREFIX_END_DATE + VALID_END_DATE_BOB;


    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    // Invalid Check In Descriptions
    public static final String INVALID_PERSONAL_ID_DESC = PREFIX_PERSONAL_ID + "a12"; // letters not allowed in PID
    public static final String INVALID_ROOM_ID_DESC = PREFIX_ROOM_ID + "88"; // roomId supposed to be four digits
    public static final String INVALID_ROOM_ID_DESC2 = PREFIX_ROOM_ID + "419&"; // '&' not allowed in roomIds

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    public static final int VALID_PRICE = 50;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

}
