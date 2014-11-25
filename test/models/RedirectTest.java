package models;

import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class RedirectTest extends UnitTest {

    private final String ALIAS = "google";
    private final String URL = "www.google.com";

    @Before
    public void setup() {
        Fixtures.deleteDatabase();
    }

    private void setupTestData() {
        new Redirect(URL, ALIAS, null).save();
    }

    @Test
    public void createAndRetreiveRedirect() {
        setupTestData();

        Redirect google = Redirect.findByAlias(ALIAS);

        assertNotNull(google);
        assertEquals(URL, google.url);
    }

    @Test
    public void tryRedirectLookup() {
        setupTestData();

        assertNotNull(Redirect.findByAlias(ALIAS));
        assertNull(Redirect.findByAlias("dne"));
    }

}
