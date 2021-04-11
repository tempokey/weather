package top.kojoy.weather.entity.dto;

import java.io.Serializable;

/**
 * @Description:
 * @Auther: kejun.xuan
 * @Date: 09/04/2021
 */
public class WeatherResponseDTO implements Serializable {
    private int success;
    private WeatherResponseResultDTO result;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public WeatherResponseResultDTO getResult() {
        return result;
    }

    public void setResult(WeatherResponseResultDTO result) {
        this.result = result;
    }
}
