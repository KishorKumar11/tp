package seedu.duke.command;

import seedu.duke.exception.GetJackDException;
import seedu.duke.lists.WorkoutList;
import seedu.duke.tasks.Exercise;
import seedu.duke.ui.Ui;

import static seedu.duke.parser.Parser.EXERCISE_KEYWORD;
import static seedu.duke.parser.Parser.WORKOUT_KEYWORD;

public class MarkExerciseAsDoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    public static final String MESSAGE_USAGE = "done: Marks the exercise in the workout workout as complete.\n"
            + "\tParameters: " + WORKOUT_KEYWORD + "WORKOUT_INDEX, " + EXERCISE_KEYWORD + "EXERCISE_INDEX\n"
            + "\tExample: done " + WORKOUT_KEYWORD + "3 " + EXERCISE_KEYWORD + "2\n";
    public static final String MESSAGE_SUCCESS = "Completed: %1$s";
    private final int workoutIndex;
    private final int exerciseIndex;

    public MarkExerciseAsDoneCommand(int workoutIndex, int exerciseIndex) {
        this.workoutIndex = workoutIndex;
        this.exerciseIndex = exerciseIndex;
    }

    @Override
    public void executeUserCommand(WorkoutList workouts, Ui ui) throws GetJackDException {
        try {
            Exercise toMarkDone = workouts.getWorkout(workoutIndex).getExercise(exerciseIndex);
            toMarkDone.setDone();
            ui.showToUser(String.format(MESSAGE_SUCCESS, toMarkDone));
        } catch (IndexOutOfBoundsException e) {
            ui.showToUser(MESSAGE_EXERCISE_NOT_FOUND);
        }
    }
}
