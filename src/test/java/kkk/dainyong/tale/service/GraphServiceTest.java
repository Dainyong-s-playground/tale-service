package kkk.dainyong.tale.service;

import kkk.dainyong.tale.repository.GraphRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class GraphServiceTest {

    @Mock
    private GraphRepository graphRepository;

    @InjectMocks
    private GraphService graphService;

    private Long profileId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        profileId = 1L;
    }

    @Test
    void incrementTotalCount() {
        // When
        graphService.incrementTotalCount(profileId);

        // Then
        verify(graphRepository).incrementTotalCount(profileId);
    }

    @Test
    void incrementRecordCount() {
        // When
        graphService.incrementRecordCount(profileId);

        // Then
        verify(graphRepository).incrementRecordCount(profileId);
    }

    @Test
    void incrementMotionCount() {
        // When
        graphService.incrementMotionCount(profileId);

        // Then
        verify(graphRepository).incrementMotionCount(profileId);
    }

    @Test
    void incrementGameCount() {
        // When
        graphService.incrementGameCount(profileId);

        // Then
        verify(graphRepository).incrementGameCount(profileId);
    }
}