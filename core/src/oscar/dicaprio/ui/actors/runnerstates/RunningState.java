package oscar.dicaprio.ui.actors.runnerstates;

import com.badlogic.gdx.physics.box2d.Body;
import oscar.dicaprio.mechanics.box2d.RunnerUserData;
import oscar.dicaprio.ui.actors.RunnerActor;
import oscar.dicaprio.utils.Constants;

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: DiCaprio
 * Date: 2/10/16
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */

/**
 * Simple boring running state.
 */
public class RunningState extends AbstractAliveState {

  @Override public void handleInput(RunnerActor runner, int inputType) {
    switch (inputType) {
      case Constants.INPUT_TYPE_LEFT_TOUCH_DOWN:
        dodge(runner);
        break;
      case Constants.INPUT_TYPE_RIGHT_TOUCH_DOWN:
        jump(runner);
        break;
    }
  }

  protected void jump(final RunnerActor runner) {

    final Body body = runner.getBody();
    body.applyLinearImpulse(runner.getUserData().getJumpingLinearImpulse(), body.getWorldCenter(),
        true);

    runner.setState(runner.getStatesHolder().getJumpingState());
  }

  protected void dodge(final RunnerActor runner) {

    final Body body = runner.getBody();
    final RunnerUserData userData = runner.getUserData();
    body.setTransform(userData.getDodgePosition(), userData.getDodgeAngle());

    runner.setState(runner.getStatesHolder().getDodgingState());
  }
}
