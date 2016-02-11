package oscar.dicaprio.scene.actors.runnerstates;

import oscar.dicaprio.scene.actors.RunnerActor;
import oscar.dicaprio.utils.Constants;

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: DiCaprio
 * Date: 2/10/16
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */
public class JumpingState extends AbstractAliveState {

  @Override public void handleInput(RunnerActor runner, int inputType) {

  }

  @Override public void handleEvent(RunnerActor runner, int eventType) {

    super.handleEvent(runner, eventType);

    switch (eventType) {
      case Constants.EVENT_TYPE_COLLISION_RUNNER_WITH_GROUND:
        land(runner);
        break;
    }
  }

  protected void land(RunnerActor runner) {
    runner.setState(runner.getStatesHolder().getRunningState());
  }
}