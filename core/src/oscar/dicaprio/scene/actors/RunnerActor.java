package oscar.dicaprio.scene.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import oscar.dicaprio.mechanics.userdata.RunnerUserData;
import oscar.dicaprio.scene.actors.runnerstates.State;
import oscar.dicaprio.scene.actors.runnerstates.StatesHolder;
import oscar.dicaprio.utils.C;

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: DiCaprio
 * Date: 2/8/16
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */

/**
 * Actor, that consists of the physical body and physical params of the runner
 */
public class RunnerActor extends BaseActor {

  private static final String TAG = RunnerActor.class.getSimpleName();

  protected final StatesHolder mStatesHolder = new StatesHolder();

  protected State mState = mStatesHolder.getRunningState();

  public RunnerActor(Body body) {
    super(body);
    mUserData = new RunnerUserData(C.world.runner_width, C.world.runner_height);
  }

  @Override public RunnerUserData getUserData() {
    return ((RunnerUserData) mUserData);
  }

  /**
   * @param delta time since last frame
   */
  @Override public void act(float delta) {
    super.act(delta);
    /**
     * it sets new speed from scratch, so
     * keep jumping velocity, to save jump state
     * and move player forward or backward, related on velocity
     */

    float velocityX = getUserData().getLinearVelocity().x;

    /**
     * If runner isn't slowed, speed up him to get start position
     */
    if (mBody.getPosition().x < C.world.runner_x && velocityX <= 0.01f && velocityX >= -0.01f) {
      velocityX = 0.4f;
    }

    mBody.setLinearVelocity(velocityX, mBody.getLinearVelocity().y);
  }

  //region Handling by state
  public void handleInput(int inputType) {
    mState.handleInput(this, inputType);
  }

  public void handleEvent(int eventType) {
    mState.handleEvent(this, eventType);
  }
  //endregion

  //region Getters and setters
  public void setState(State state) {

    Gdx.app.log(TAG,
        "State changed from " + mState.getClass().getSimpleName() + " to " + state.getClass()
            .getSimpleName());
    mState = state;
  }

  public StatesHolder getStatesHolder() {
    return mStatesHolder;
  }
  //endregion

  //region Collider
  @Override public void collide(CollidableActor c) {
    c.collideTo(this);
  }

  @Override public void collideTo(BaseActor base) {
    // runner and base
    Gdx.app.log(TAG, "Collision: runner to base");
  }

  @Override public void collideTo(GroundActor ground) {
    // runner and ground
    Gdx.app.log(TAG, "Collision: runner to ground");

    handleEvent(C.event.event_collision_runner_with_ground);
  }

  @Override public void collideTo(EnemyActor enemy) {
    // runner and enemy
    Gdx.app.log(TAG, "Collision: runner to enemy");

    handleEvent(C.event.event_collision_runner_with_enemy);
  }

  @Override public void collideTo(CoinActor coin) {
    // runner and coin
    Gdx.app.log(TAG, "Collision: runner to coin, coin is collected: " + coin.isRemovable());

    // collect coin inside, because it's related to state?
    if (!coin.isRemovable()) {
      handleEvent(C.event.event_collision_runner_with_coin);

      coin.setRemovable();
    }
  }

  @Override public void collideTo(IcebergActor iceberg) {
    // runner and runner
    // *should never happen
    Gdx.app.log(TAG, "Collision: runner to iceberg");

    handleEvent(C.event.event_collision_runner_with_iceberg);
  }

  @Override public void collideTo(SnowballActor snowball) {
    // runner and snowball
    Gdx.app.log(TAG, "Collision: runner to coin, coin is collected: " + snowball.isRemovable());

    if (!snowball.isRemovable()) {
      handleEvent(C.event.event_collision_runner_with_snowball);

      snowball.setRemovable();
    }
  }

  @Override public void collideTo(RunnerActor runner) {
    // runner and runner
    // *should never happen
    Gdx.app.log(TAG, "Collision: runner to runner");
  }
  //endregion
}
