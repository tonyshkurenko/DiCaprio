package oscar.dicaprio.scene.actors.runnerstates;

import oscar.dicaprio.scene.actors.RunnerActor;
import oscar.dicaprio.scene.actors.buffs.RelativeSlowBuff;
import oscar.dicaprio.utils.Bank;
import oscar.dicaprio.utils.C;

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: DiCaprio
 * Date: 2/11/16
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */

/**
 * State to handle live -> dead
 */
public abstract class AbstractAliveState implements State {

  @Override public void handleEvent(RunnerActor runner, int eventType) {
    switch (eventType) {
      case C.event.event_collision_runner_with_enemy:
      case C.event.event_collision_runner_with_iceberg:
        die(runner);
        break;
      case C.event.event_collision_runner_with_coin:
        collect(runner);
        break;
      case C.event.event_collision_runner_with_snowball:
        slow(runner);
        break;
    }
  }

  protected void die(RunnerActor runner) {
    runner.getBody().applyAngularImpulse(runner.getUserData().getHitAngularImpulse(), true);
    runner.setState(runner.getStatesHolder().getHitState());
  }

  protected void collect(RunnerActor runner) {
    // maybe smile? And collect also here, because he's alive

    Bank.getInstance().incrementCoins();
  }

  protected void slow(RunnerActor runner) {
    runner.addAction(new RelativeSlowBuff(C.world.runner_max_slow_speed));
  }
}
