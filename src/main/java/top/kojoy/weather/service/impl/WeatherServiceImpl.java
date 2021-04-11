package top.kojoy.weather.service.impl;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.kojoy.weather.entity.dto.WeatherDTO;
import top.kojoy.weather.service.IWeatherService;
import top.kojoy.weather.util.WeatherUtil;
import java.io.IOException;
import java.text.MessageFormat;

/**
 * @Description:
 * @Auther: kejun.xuan
 * @Date: 08/04/2021
 */
@Service
public class WeatherServiceImpl implements IWeatherService {
    private final static Logger log = LoggerFactory.getLogger(WeatherServiceImpl.class);

    @Value("${url.api.weather}")
    private String weatherApiUrl;

    @Override
    public WeatherDTO getWeatherInfo(String cityNum) {
        WeatherDTO weatherDTO = null;
        try {
            weatherDTO = getWeatherFromHttp(cityNum);
        } catch (IOException ioException) {
            log.error("Http io exception: " + ioException.getMessage());
        }
        return weatherDTO;
    }

    private WeatherDTO getWeatherFromHttp(String cityNum) throws IOException {
        WeatherDTO weatherDTO = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        log.info("Http get is beginning, URL: " + MessageFormat.format(weatherApiUrl, cityNum));
        HttpGet httpGet = new HttpGet(MessageFormat.format(weatherApiUrl, cityNum));
        CloseableHttpResponse response = httpClient.execute(httpGet);
        try {
            HttpEntity httpEntity = response.getEntity();
            weatherDTO = WeatherUtil.transformResponseToDTO(httpEntity);
            EntityUtils.consume(httpEntity);
        } catch (Exception e) {
            log.error("Http exception: " + e.getMessage());
        } finally {
            response.close();
        }
        return weatherDTO;
    }


}
