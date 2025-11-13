package delft;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.within;
import java.time.temporal.ChronoUnit;

import java.util.*;
import java.util.stream.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import java.time.*;

class AutoAssignerTest {
    private List<Student> students = new ArrayList<>();
    private Map<ZonedDateTime, Integer> spotsPerDate = new HashMap<>();
    private List<Workshop> workshops = new ArrayList<>();
    private AutoAssigner autoAssigner = new AutoAssigner();

    private ZonedDateTime date(int year, int month, int day, int hour, int minute) {
        return ZonedDateTime.of(year, month, day, hour, minute, 0, 0, ZoneId.systemDefault());
    }

    @Test
    public void TestNoAvailableSpots(){
        students.add(new Student(1, "louis", "louis@mail"));
        spotsPerDate.put(date(2025, 11, 5, 9, 0), 0);
        workshops.add(new Workshop(1, "koko", spotsPerDate));

        AssignmentsLogger assignments = autoAssigner.assign(students, workshops);


        assertThat(assignments.getErrors()).containsExactly("koko,louis");
        assertThat(assignments.getAssignments()).isEmpty();
    }

    @Test
    public void TestAvailableSpot(){
        students.add(new Student(1, "louis", "louis@mail"));
        spotsPerDate.put(date(2025, 11, 5, 9, 0), 1);
        workshops.add(new Workshop(1, "koko", spotsPerDate));
        AssignmentsLogger assignments = autoAssigner.assign(students, workshops);


        assertThat(assignments.getErrors()).isEmpty();
        assertThat(assignments.getAssignments()).containsExactlyInAnyOrder("koko,louis,05/11/2025 09:00");
    }

}
