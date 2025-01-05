package com.moviesExerciseREST.mms_backend.utils;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers;

import java.io.IOException;
import java.math.BigDecimal;

public class CustomBeanSerializerModifier extends BeanSerializerModifier {
    @Override
    public JsonSerializer<?> modifySerializer(
            SerializationConfig config,
            BeanDescription beanDesc,
            JsonSerializer<?> serializer
    ) {
        if (serializer instanceof NumberSerializers.DoubleSerializer) {
            return new JsonSerializer<Double>() {
                @Override
                public void serialize(Double value, com.fasterxml.jackson.core.JsonGenerator gen, com.fasterxml.jackson.databind.SerializerProvider serializers) throws IOException, IOException {
                    BigDecimal truncatedValue = BigDecimal.valueOf(value).setScale(2, BigDecimal.ROUND_DOWN);
                    gen.writeNumber(truncatedValue);
                }
            };
        }
        return serializer;
    }
}