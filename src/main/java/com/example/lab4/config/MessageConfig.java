package com.example.lab4.config;

import java.time.Duration;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class MessageConfig implements WebMvcConfigurer {
    // 1. Nạp file tài nguyên đa ngôn ngữ: i18n/layout_vi.properties, i18n/layout_en.properties
    //    Basename KHÔNG gồm mã ngôn ngữ (_vi, _en) và phần mở rộng (.properties)
    @Bean("messageSource")
    public MessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasenames("classpath:i18n/layout");
        ms.setDefaultEncoding("utf-8");   // đọc đúng tiếng Việt có dấu
        return ms;
    }

    // 2. Bắt tham số ?lang=vi / ?lang=en trên URL để đổi ngôn ngữ
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        registry.addInterceptor(interceptor);
    }

    // 3. Duy trì ngôn ngữ đã chọn bằng Cookie (sống 30 ngày, dùng cho mọi đường dẫn)
    @Bean("localeResolver")
    public LocaleResolver getLocaleResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setCookiePath("/");
        localeResolver.setCookieMaxAge(Duration.ofDays(30));
        localeResolver.setDefaultLocale(new Locale("vi"));   // lần đầu vào: tiếng Việt
        return localeResolver;
    }
}
