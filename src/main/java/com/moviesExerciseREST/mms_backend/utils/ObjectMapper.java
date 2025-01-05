package com.moviesExerciseREST.mms_backend.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.text.SimpleDateFormat;

public class ObjectMapper extends com.fasterxml.jackson.databind.ObjectMapper {
    public ObjectMapper() {
        super();
        this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        this.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        this.registerModule(new JavaTimeModule());
        this.addMixIn(Object.class, HibernateProxyMixIn.class);
        SimpleModule module = new SimpleModule();
        module.setSerializerModifier(new CustomBeanSerializerModifier());
        this.registerModule(module);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        this.setDateFormat(df);
    }
}
