package com.cognizant.userapi.dataloader;

import com.cognizant.userapi.model.User;
import com.cognizant.userapi.repository.UserRepository;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Collections;
import java.util.List;

/**
 * Pre-load csv data using a Spring Boot {@link ApplicationRunner}.
 *
 * @author Harinath Kuntamukkala
 */
@Component
@Slf4j
public class CsvReaderApplicationRunner implements ApplicationRunner {

    private final UserRepository userRepository;

    /**
     * Constructor Injection
     * @param repository
     */
    CsvReaderApplicationRunner(UserRepository repository) {
        this.userRepository = repository;
    }

    @Value("${user-info-file.path}")
    private String user_info_file_path;

    /**
     * Use Spring to inject a {@link UserRepository} that can then load data. Since this will run only after the app
     * is operational, the database will be up.
     *
     * @param args
     */
    @Override
    public void run(final ApplicationArguments args) throws Exception {
        log.info("Application Runner - CSV Loader");
        userRepository.saveAll(loadObjectList(User.class, user_info_file_path));
    }

    /**
     * Csv Parser using Jackson Library
     * @param type
     * @param fileName
     * @param <T>
     * @return
     */
    private <T> List<T> loadObjectList(Class<T> type, final String fileName) {
        try {
            CsvMapper mapper = new CsvMapper();
            File file = new ClassPathResource(fileName).getFile();
            MappingIterator<T> readValues =
                    mapper.readerFor(type).with(CsvSchema.emptySchema().withHeader()).readValues(file);
            return readValues.readAll();
        } catch (Exception e) {
            log.error("Error occurred while loading object list from file " + fileName, e);
            return Collections.emptyList();
        }
    }

}