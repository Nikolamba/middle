package carsales;

import carsales.models.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 * @author Nikolay Meleshkin (sol.of.f@mail.ru)
 * @version 0.1
 */
public class IntToModelConverter implements Converter<String, Model> {

    private LogicLayer logic;

    @Autowired
    public IntToModelConverter(LogicLayer logic) {
        this.logic = logic;
    }

    @Override
    public Model convert(String source) {
        return logic.findModelById(Integer.valueOf(source.trim()));
    }
}
