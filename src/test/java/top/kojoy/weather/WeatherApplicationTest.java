package top.kojoy.weather;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Description:
 * @Auther: kejun.xuan
 * @Date: 12/04/2021
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WeatherApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WeatherApplicationTest {
    private final static Logger log = LoggerFactory.getLogger(WeatherApplicationTest.class);

    private URL url;
    private int cityNum;
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * Init.
     */
    @Before
    public void setUp() {
        this.cityNum = (int) (Math.random() * (3 - 1) + 1);
        try {
            this.url = new URL("http://localhost:" + this.port + "/");
        } catch (MalformedURLException malformedURLException) {
            log.error("Generate URL error: ", malformedURLException.getMessage());
        }
    }

    /**
     * Test it to get weather info successfully.
     */
    @Test
    public void testGetWeatherInfo() {
        ResponseEntity<String> response = this.restTemplate.getForEntity(
                this.url.toString() + "/weather/" + this.cityNum, String.class, "");
        log.info(String.format("Test result: %s", response.getBody()));
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("city"));
        assertTrue(response.getBody().contains("temperature"));
        assertTrue(response.getBody().contains("date"));
    }
}
