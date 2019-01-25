package coffeerun;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.graphics.GL20;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.mockito.Mockito;

/**
 * This is the class that creates a new headless application (server side) that allows us to mock
 * and use the gdx related things in our tests.
 */
public class GameTest {
    private static Application application;

    @BeforeClass
    public static void init() {
        application = new HeadlessApplication(new ApplicationListener() {

            @Override
            public void create() {

            }

            @Override
            public void resize(int width, int height) {

            }

            @Override
            public void render() {

            }

            @Override
            public void pause() {

            }

            @Override
            public void resume() {

            }

            @Override
            public void dispose() {

            }
        });

        Gdx.gl20 = Mockito.mock(GL20.class);
        Gdx.gl = Gdx.gl20;

        }

        @AfterClass
        public static void cleanUp() {

            application.exit();
            application = null;

        }
}
