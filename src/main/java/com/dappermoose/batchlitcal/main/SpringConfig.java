package com.dappermoose.batchlitcal.main;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.Locale;
import java.util.Properties;

import javax.inject.Inject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;

import com.dappermoose.batchlitcal.beans.CalendarOptions;
import com.dappermoose.batchlitcal.beans.Inputs;

import lombok.extern.log4j.Log4j2;

/**
 * The Class SpringConfig.
 * 
 * @author heitkergm@acm.org
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
        source.setBasenames ("classpath:messages", "classpath:daynames");
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
        LOG.debug ("context is " + context);
        String localeName = context.getEnvironment ().getProperty ("locale");
        LOG.debug ("locale is " + localeName);
        if (localeName == null)
        {
            localeName = Locale.getDefault ().getLanguage () + "_" +
                         Locale.getDefault ().getCountry ();
        }
        
        int ind = localeName.indexOf ('_');
        Locale myLocale;
        if (ind >= 0)
        {
            myLocale = new Locale (localeName.substring (0, ind),
                                   localeName.substring (ind + 1));
        }
        else
        {
            myLocale = new Locale (localeName);
        }
        
        LOG.debug ("locale bean is " + myLocale.getLanguage () + "_" +
                    myLocale.getCountry ());

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
        
        for (String name: dateNames)
        {
            LOG.debug ("date name: " + name);
        }
        
        return dateNames;
    }
    
    /**
     * Calendar Options bean.
     * 
     * @return the Calendar Options bean.
     */
    @Bean
    @DependsOn ({"messageSource", "locale"})
    CalendarOptions calendarOptions ()
    {
        Locale locale = (Locale) context.getBean ("locale");
        MessageSource messageSource = (MessageSource) context.getBean ("messageSource");

        String args = context.getEnvironment ().getProperty ("nonOptionArgs");
        String fileName = null;
        LOG.debug (args);
        if (fileName == null)
        {
            fileName = args;
        }
        LOG.debug ("input fileName is " + fileName);

        Properties props = new Properties ();
        if (fileName != null)
        {
            try (InputStream is =
                 Files.newInputStream (FileSystems.getDefault ()
                                       .getPath (fileName)))
            {
                props.load (is);
            }
            catch (IOException e)
            {
                Object [] arr = new Object[] {fileName};
                String msg = messageSource.getMessage ("noFileFound", 
                                                       arr,
                                                       locale);
                LOG.error (msg + " " +
                           e.getClass ().getName () + " " +
                           e.getMessage ());
                throw new RuntimeException (msg, e);
            }
        }
        else
        {
            String msg = messageSource.getMessage ("noFileGiven", 
                                                       null, locale);
            LOG.error (msg);
            throw new RuntimeException (msg);
        }
    
        return  Inputs.processInputs (props, locale, messageSource);
    }
}
