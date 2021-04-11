package top.kojoy.weather.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.kojoy.weather.entity.dto.WeatherDTO;
import top.kojoy.weather.service.IWeatherService;

/**
 * @Description:
 * @Auther: kejun.xuan
 * @Date: 08/04/2021
 */
@Api("天气API")
@RequestMapping(value = "/weather")
@RestController
public class WeatherController {
    private final static Logger log = LoggerFactory.getLogger(WeatherController.class);

    @Autowired
    private IWeatherService weatherService;

    @ApiOperation("查询天气信息")
    @ApiImplicitParam(name = "cityNum", value = "城市编号", required = true, dataType = "String")
    @RequestMapping(value = "/{cityNum}", method = RequestMethod.GET)
    public WeatherDTO getWeather(@PathVariable String cityNum) {
        log.info("调用城市 " + cityNum + " 数据.");
        return weatherService.getWeatherInfo(cityNum);
    }
}
