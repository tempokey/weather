package top.kojoy.weather.service;

import top.kojoy.weather.entity.dto.WeatherDTO;
import java.io.IOException;

/**
 * @Description:
 * @Auther: kejun.xuan
 * @Date: 09/04/2021
 */
public interface IWeatherService {
    WeatherDTO getWeatherInfo(String cityNum);
}
