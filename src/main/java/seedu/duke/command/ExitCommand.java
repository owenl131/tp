package seedu.duke.command;

import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String COMMAND_USAGE = "bye";
    public static final String COMMAND_DESCRIPTION = "Exit YAMOM!";

    public ExitCommand(String[] input) {
        super(input);
    }

    @Override
    public void execute(State state, Ui ui, Storage storage) {

    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String getExecutionMessage() {
        return null;
    }

    public static String getCommandDescription() {
        return COMMAND_WORD + DESCRIPTION_DELIMITER + COMMAND_DESCRIPTION;
    }

    public static String getUsage() {
        return COMMAND_USAGE;
    }
}
