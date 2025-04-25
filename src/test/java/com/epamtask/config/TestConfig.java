package com.epamtask.config;

import com.epamtask.model.Trainee;
import com.epamtask.model.Trainer;
import com.epamtask.model.Training;
import com.epamtask.service.metrics.ApiMetricsService;
import com.epamtask.service.metrics.AuthenticationMetricsService;
import com.epamtask.service.metrics.DatabaseMetricsService;
import com.epamtask.service.metrics.TrainingMetricsService;
import com.epamtask.storege.loader.TraineeStorageLoader;
import com.epamtask.storege.loader.TrainerStorageLoader;
import com.epamtask.storege.loader.TrainingStorageLoader;
import com.epamtask.storege.loader.initializer.StorageInitializer;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Profile("test")
@EnableAspectJAutoProxy
public class TestConfig {

    @Bean
    public Map<Long, Trainee> traineeStorage() {
        return new HashMap<>();
    }

    @Bean
    public Map<Long, Trainer> trainerStorage() {
        return new HashMap<>();
    }

    @Bean
    public Map<Long, Training> trainingStorage() {
        return new HashMap<>();
    }

    @Bean
    @Qualifier("traineeFilePath")
    public String traineeFilePath() {
        return "trainee-json-data/valid-data/valid-trainee.json";
    }

    @Bean
    @Qualifier("trainerFilePath")
    public String trainerFilePath() {
        return "trainer-json-data/valid-data/valid-trainer.json";
    }

    @Bean
    @Qualifier("trainingFilePath")
    public String trainingFilePath() {
        return "training-json-data/valid-data/valid-training.json";
    }

    @Bean
    public TraineeStorageLoader traineeStorageLoader(
            @Qualifier("traineeFilePath") String traineeFilePath) {
        return Mockito.mock(TraineeStorageLoader.class);
    }

    @Bean
    public TrainerStorageLoader trainerStorageLoader(
            @Qualifier("trainerFilePath") String trainerFilePath) {
        return Mockito.mock(TrainerStorageLoader.class);
    }

    @Bean
    public TrainingStorageLoader trainingStorageLoader(
            @Qualifier("trainingFilePath") String trainingFilePath) {
        return Mockito.mock(TrainingStorageLoader.class);
    }

    @Bean
    public StorageInitializer storageInitializer(
            Map<Long, Trainee> traineeStorage,
            Map<Long, Trainer> trainerStorage,
            Map<Long, Training> trainingStorage,
            TraineeStorageLoader traineeLoader,
            TrainerStorageLoader trainerLoader,
            TrainingStorageLoader trainingLoader
    ) {
        return new StorageInitializer(
                traineeStorage,
                trainerStorage,
                trainingStorage,
                traineeLoader,
                trainerLoader,
                trainingLoader
        );
    }

    @Bean
    public AuthenticationMetricsService authenticationMetricsService() {
        return Mockito.mock(AuthenticationMetricsService.class);
    }

    @Bean
    public ApiMetricsService apiMetricsService() {
        return Mockito.mock(ApiMetricsService.class);
    }

    @Bean
    public DatabaseMetricsService databaseMetricsService() {
        return Mockito.mock(DatabaseMetricsService.class);
    }

    @Bean
    public TrainingMetricsService trainingMetricsService() {
        return Mockito.mock(TrainingMetricsService.class);
    }
}