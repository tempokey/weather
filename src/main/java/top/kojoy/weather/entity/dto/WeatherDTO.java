package top.kojoy.weather.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Auther: kejun.xuan
 * @Date: 08/04/2021
 */
@ApiModel("天气")
public class WeatherDTO implements Serializable {
    private String city;
    private String temperature;
    private Date date;

    @ApiModelProperty("城市")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @ApiModelProperty("温度")
    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    @ApiModelProperty("日期")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
