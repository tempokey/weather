package top.kojoy.weather.util;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import top.kojoy.weather.entity.dto.WeatherDTO;
import top.kojoy.weather.entity.dto.WeatherResponseDTO;
import top.kojoy.weather.entity.dto.WeatherResponseResultDTO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Auther: kejun.xuan
 * @Date: 09/04/2021
 */
public class WeatherUtil {
    private final static Logger log = LoggerFactory.getLogger(WeatherUtil.class);

    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static WeatherDTO transformResponseToDTO(HttpEntity httpEntity) {
        WeatherDTO weatherDTO = new WeatherDTO();
        String jsonString = null;
        try {
            jsonString = new BufferedReader(new InputStreamReader(httpEntity.getContent()))
                    .lines().parallel().collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException ioException) {
            log.error("Transform response data exception: " + ioException.getMessage());
        }
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }

        WeatherResponseDTO weatherResponseDTO = JSON.parseObject(jsonString, WeatherResponseDTO.class);
        WeatherResponseResultDTO weatherResponseResultDTO = weatherResponseDTO.getResult();

        weatherDTO.setCity(weatherResponseResultDTO.getCitynm());
        weatherDTO.setTemperature(weatherResponseResultDTO.getTemperature());

        try {
            weatherDTO.setDate(dateFormat.parse(weatherResponseResultDTO.getDays()));
        } catch (ParseException parseException) {
            log.error("Transform response date info exception: " + parseException.getMessage());
        }
        return weatherDTO;
    }
}
