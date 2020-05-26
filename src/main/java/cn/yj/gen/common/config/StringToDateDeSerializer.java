package cn.yj.gen.common.config;

import cn.yj.gen.common.enums.LPATTERM;
import cn.yj.gen.common.utils.DateTimeUtils;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.Date;

/**
 * <br>
 *
 * @author 永健
 * @since 2020-05-12 10:55
 */
public class StringToDateDeSerializer extends JsonDeserializer<Date>
{

    @SneakyThrows
    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException
    {
        return DateTimeUtils.format(p.getText(), LPATTERM.YMDMS_);
    }
}
