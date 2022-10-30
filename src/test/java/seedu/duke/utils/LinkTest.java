package seedu.duke.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.duke.exceptions.YamomException;
import seedu.duke.model.LessonType;
import seedu.duke.model.Module;
import seedu.duke.model.SelectedModule;

public class LinkTest {
    @Test
    public void link_parseLink_parsedCorrectly() throws YamomException {
        State state = new State();
        Ui ui = new Ui();
        Link.parseLink("https://nusmods.com/timetable/sem-1/share?" 
                + "CS1010=LAB:D04,TUT:06,SEC:1&CS2113=TUT:3,LEC:1", state, ui);
        assertEquals(state.getSelectedModulesList().size(), 2);
        SelectedModule mod = state
                .getSelectedModulesList()
                .stream()
                .filter(x -> x.getModule().moduleCode.equals("CS2113"))
                .collect(Collectors.toList())
                .get(0);
        assertEquals(mod.getModule().moduleCode, "CS2113");
        assertEquals(mod.getSelectedSlots().size(), 2);
        assertEquals(mod.getSelectedSlots().get(LessonType.TUTORIAL), "3");
    }

    @Test
    public void link_parseLink_overwriteOldState() throws YamomException {
        State state = new State();
        Ui ui = new Ui();
        Link.parseLink("https://nusmods.com/timetable/sem-1/share?MA1511=LEC:1,TUT:10", state, ui);
        assertEquals(state.getSelectedModulesList().size(), 1);
        Link.parseLink("https://nusmods.com/timetable/sem-1/share?" 
                + "CS1010=LAB:D04,TUT:06,SEC:1&CS2113=TUT:3,LEC:1", state, ui);
        assertEquals(state.getSelectedModulesList().size(), 2);
        assertEquals(state
                .getSelectedModulesList()
                .stream()
                .filter(x -> x.getModule().moduleCode.equals("MA1511"))
                .count(), 0);
    }

    @Test
    public void getLink_noModuleState_noModuleLink() {
        State state = new State();
        String link = Link.getLink(state);

        assertEquals("https://nusmods.com/timetable/sem-1/share?", link);
    }

    @Test
    public void getLink_currentState_correctLink() {
        State state = new State();
        Module module = Module.get("CS2113");
        SelectedModule selectedModule = new SelectedModule(module, 1);
        selectedModule.selectSlot(LessonType.LECTURE, "1");
        selectedModule.selectSlot(LessonType.TUTORIAL, "4");
        state.addSelectedModule(selectedModule);
        String link = Link.getLink(state);

        assertEquals("https://nusmods.com/timetable/sem-1/share?CS2113=LEC:1,TUT:4", link);
    }
}
