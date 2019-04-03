package com.medical.medical.configuration;


import com.medical.medical.interceptor.LoginRequiredInterceptor;
import com.medical.medical.interceptor.PassportInterceptor;
import com.medical.medical.service.DoctorRecordService;
import com.medical.medical.service.RegisterRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class WendaWebConfiguration extends WebMvcConfigurerAdapter {
    @Autowired
    PassportInterceptor passportInterceptor;

    @Autowired
    LoginRequiredInterceptor loginRequiredInterceptor;

    @Autowired
    DoctorRecordService doctorRecordService;

    @Autowired
    RegisterRecordService registerRecordService;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(passportInterceptor);
        registry.addInterceptor(loginRequiredInterceptor).addPathPatterns("/users/*");
        doctorRecordService.AutoGenerate();
        registerRecordService.autoAddRegisterRecord();
        super.addInterceptors(registry);
    }
}
