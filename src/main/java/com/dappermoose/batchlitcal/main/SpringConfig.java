package com.dappermoose.batchlitcal.main;

import java.util.Locale;

import javax.inject.Inject;
import javax.validation.Validator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import lombok.extern.log4j.Log4j2;

/**
 * The Class SpringConfig.
 */
@ComponentScan ("com.dappermoose.batchlitcal")
@Configuration
@Log4j2
public class SpringConfig
{
    @Inject
    private ApplicationContext context;
    
    /**
     * Message source.
     *
     * @return the message source
     */
    @Bean
    MessageSource messageSource ()
    {
        final ReloadableResourceBundleMessageSource source =
                new ReloadableResourceBundleMessageSource ();
        source.setCacheSeconds (60);
        source.setBasenames ("classpath:messages");
        return source;
    }
    
    /**
     * the locale bean.
     * 
     * @return the locale object in the bean
     */
    @Bean
    Locale locale ()
    {
        // get the locale and save it off as a bean
        Locale myLocale = Locale.getDefault ();
        String localeName = myLocale.getDisplayName ();
        LOG.debug ("locale is " + localeName);
        LOG.debug ("locale bean is " + myLocale);
        
        return myLocale;
    }

    /**
     * Add the entries from git.properties into Spring environment.
     * 
     * @return propsConfig - the new property sources placeholder configurer.
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer ()
    {
        PropertySourcesPlaceholderConfigurer propsConfig = 
            new PropertySourcesPlaceholderConfigurer ();
        propsConfig.setLocation (new ClassPathResource ("git.properties"));
        propsConfig.setIgnoreResourceNotFound (true);
        propsConfig.setIgnoreUnresolvablePlaceholders (true);
        return propsConfig;
    }

    /**
     * weekday names bean.
     * 
     * @return the list of date names
     */
    @Bean
    @DependsOn ({"messageSource", "locale"})
    String[] dateNames ()
    {
        String [] dateNames = new String[7];
        Locale locale = (Locale) context.getBean ("locale");
        MessageSource msgSource = (MessageSource) context.getBean ("messageSource");
        dateNames[0] = msgSource.getMessage ("sunday", null, locale);
        dateNames[1] = msgSource.getMessage ("monday", null, locale);
        dateNames[2] = msgSource.getMessage ("tuesday", null, locale);
        dateNames[3] = msgSource.getMessage ("wednesday", null, locale);
        dateNames[4] = msgSource.getMessage ("thursday", null, locale);
        dateNames[5] = msgSource.getMessage ("friday", null, locale);
        dateNames[6] = msgSource.getMessage ("saturday", null, locale);
        return dateNames;
    }

    /**
     * hibernate validator bean. 
     *
     * @return the validator
     */
    @Bean
    Validator validator ()
    {
        return new LocalValidatorFactoryBean ();
    }
}
