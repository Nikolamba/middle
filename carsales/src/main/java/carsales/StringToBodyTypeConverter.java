package carsales;

import carsales.models.BodyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class StringToBodyTypeConverter implements Converter<String, BodyType> {
    private LogicLayer logic;

    @Autowired
    public StringToBodyTypeConverter(LogicLayer logic) {
        this.logic = logic;
    }

    @Override
    public BodyType convert(String source) {
        return logic.findBodyTypeByName(source);
    }
}
