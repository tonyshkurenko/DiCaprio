package oscar.dicaprio.mechanics.box2d;

import com.badlogic.gdx.math.Vector2;
import oscar.dicaprio.utils.C;

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: DiCaprio
 * Date: 2/9/16
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */

/**
 * Class, that describes physical behavior and parameters of the enemy
 */
public class EnemyUserData extends UserData {

  private Vector2 mLinearVelocity;

  public EnemyUserData(float width, float height) {
    super(width, height);
    mLinearVelocity = new Vector2(C.world.enemy_linear_velocity);
  }

  //region Getters and setters
  public Vector2 getLinearVelocity() {
    return mLinearVelocity;
  }
  //endregion
}
