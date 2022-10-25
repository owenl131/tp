package seedu.duke.command;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.duke.command.GetModuleCommand.isModuleOfferedInCurrentSem;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.YamomException;
import seedu.duke.utils.State;
import seedu.duke.model.Module;
import seedu.duke.utils.Ui;


public class GetModuleCommandTest {
    @Test
    void getModuleCommand_validModuleCodeCS1010X_expectModuleDetailsOutput() throws YamomException {
        Ui ui = new Ui();
        State state = new State();

        // set state to semester 1
        State.setSemester(1);
        String[] input = {"get", "CS1010X"};
        GetModuleCommand getModuleCommand = new GetModuleCommand(input);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        getModuleCommand.execute(state, ui, null);
        String s = System.lineSeparator();
        String expectedOutput = "Module               : CS1010X" + s
                + "Module Name          : Programming Methodology" + s
                + "Module Description   : This module introduces the fundamental concepts of "
                + "problem solving by computing" + s
                + "\t\t\t\t\t and programming using an imperative programming language. It is the first and" + s
                + "\t\t\t\t\t foremost introductory course to computing and is equivalent to CS1010, CS1010S" + s
                + "\t\t\t\t\t and CS1010E Programming Methodology. The module will be taught using the Python" + s
                + "\t\t\t\t\t programming language and topics covered include problem solving by computing," + s
                + "\t\t\t\t\t writing pseudo-codes, basic problem formulation and problem solving, program" + s
                + "\t\t\t\t\t development, coding, testing and debugging, fundamental programming constructs" + s
                + "\t\t\t\t\t (variables, types, expressions, assignments, functions, control structures," + s
                + "\t\t\t\t\t etc.), fundamental data structures: arrays, strings and structures, simple file" + s
                + "\t\t\t\t\t processing, and basic recursion." + s
                + "\t\t\t\t\t" + s
                + "Module Credit        : 4" + s
                + "Department           : Computer Science" + s
                + "Faculty              : Computing" + s
                + "Workload             : [2, 1, 1, 3, 3]" + s
                + "Semester offering    : [2, 3]" + s
                + "Prerequisite         : Nil" + s
                + "Preclusion           : CS1010 or its equivalent, CS1010FC" + s
                + "Corequisite          : Nil" + s
                + "--------------------------------------" + s
                + "Module CS1010X is not offered in this semester, hence no timetable information "
                + "is available due to unforseen circumstances" + s;

        assertEquals(expectedOutput.replaceAll("\\s+", ""),
                outContent.toString().replaceAll("\\s+", ""));
    }

    @Test
    void getModuleCommand_validModuleCodeCS1010E_expectCorrectModuleDetailsOutput() throws YamomException {

        Ui ui = new Ui();
        State state = new State();

        // set state to semester 3 (special term 1)
        State.setSemester(3);
        String[] input = {"get", "CS1010e"};
        GetModuleCommand getModuleCommand = new GetModuleCommand(input);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        getModuleCommand.execute(state, ui, null);
        // String s = System.lineSeparator();
        String expectedOutput = "Module               : CS1010E\r\n"
                + "Module Name          : Programming Methodology\r\n"
                + "Module Description   : This module introduces the fundamental concepts of problem solving by "
                + "computing\r\n"
                + "\t\t\t\t\t   and programming using an imperative programming language. It is the first and\r\n"
                + "\t\t\t\t\t   foremost introductory course to computing.  Topics covered include computational\r\n"
                + "\t\t\t\t\t   thinking and computational problem solving, designing and specifying an\r\n"
                + "\t\t\t\t\t   algorithm, basic problem formulation and problem solving approaches, program\r\n"
                + "\t\t\t\t\t   development, coding, testing and debugging, fundamental programming constructs\r\n"
                + "\t\t\t\t\t   (variables, types, expressions, assignments, functions, control structures,\r\n"
                + "\t\t\t\t\t   etc.), fundamental data structures (arrays, strings, composite data types),\r\n"
                + "\t\t\t\t\t   basic sorting, and recursion.\r\n"
                + "\t\t\t\t\t  \r\n"
                + "Module Credit        : 4\r\n"
                + "Department           : Computer Science\r\n"
                + "Faculty              : Computing\r\n"
                + "Workload             : [2, 1, 1, 3, 3]\r\n"
                + "Semester offering    : [1, 2, 4]\r\n"
                + "Prerequisite         : Nil\r\n"
                + "Preclusion           : CS1010, CS1010J, CS1010S, CS1010X, CS1010XCP, CS1101S\r\n"
                + "Corequisite          : Nil\r\n"
                + "--------------------------------------\r\n"
                + "Module CS1010E is not offered in this semester, hence no timetable information is available due "
                + "to unforseen circumstances\r\n";

        assertEquals(expectedOutput.replaceAll("\\s+", ""),
                outContent.toString().replaceAll("\\s+", ""));
    }

    @Test
    void getModuleCommand_invalidOrNotPresentModuleCodeX1010SC_exceptionThrown() {
        Ui ui = new Ui();
        State state = new State();

        State.setSemester(1);

        try {
            String[] input = {"get", "X1010SC"};
            GetModuleCommand getModuleCommand = new GetModuleCommand(input);
            getModuleCommand.execute(state, ui, null);
            fail();
        } catch (YamomException e) {
            assertEquals("Error! \tModule not found! Please enter a valid module code! Try searching "
                    + "if you do not remember the exact module code.", e.getMessage());
        }
    }

    @Test
    void getModuleCommand_emptyModuleCode_exceptionThrown() {
        Ui ui = new Ui();
        State state = new State();

        State.setSemester(1);

        try {
            String[] input = {"get"};
            GetModuleCommand getModuleCommand = new GetModuleCommand(input);
            getModuleCommand.execute(state, ui, null);
            fail();
        } catch (YamomException e) {
            assertEquals("Error! \tPlease enter a module code!", e.getMessage());
        }
    }

    @Test
    void isModuleOfferedInCurrentSem_moduleCS2113OfferedInCurrentSemester_expectsTrue() {
        // check if module is offered in current semester
        State state = new State();
        String moduleCode = "CS2113";
        Module testModule = Module.get(moduleCode);

        // setting current semester to 1
        State.setSemester(1);

        // check if module is offered in current semester
        boolean testResult = isModuleOfferedInCurrentSem(testModule, state);

        assertTrue(testResult);
    }

    @Test
    void isModuleOfferedInCurrentSem_moduleCS2113NotOfferedInCurrentSemester_expectsFalse() {
        // check if module is offered in current semester
        State state = new State();
        String moduleCode = "CS2113";
        Module testModule = Module.get(moduleCode);

        // setting current semester to 4
        State.setSemester(4);

        // check if module is offered in current semester
        boolean testResult = isModuleOfferedInCurrentSem(testModule, state);

        assertFalse(testResult);
    }

    @Test
    void isModuleExist_moduleCodeCS2113Exist_expectTrue() {
        String moduleCode = "CS2113";
        List<Module> moduleList = Module.getAll();
        boolean moduleExistsInModuleList = false;
        for (Module module : moduleList) {
            if (moduleCode.equals(module.moduleCode)) {
                moduleExistsInModuleList = true;
                break;
            }
        }

        assertTrue(moduleExistsInModuleList);
    }

    @Test
    void isModuleExist_moduleCodeCS2113FDoNotExist_expectFalse() {
        String moduleCode = "CS2113F";
        List<Module> moduleList = Module.getAll();
        boolean moduleExistsInModuleList = false;
        for (Module module : moduleList) {
            if (moduleCode.equals(module.moduleCode)) {
                moduleExistsInModuleList = true;
                break;
            }
        }

        assertFalse(moduleExistsInModuleList);
    }
}