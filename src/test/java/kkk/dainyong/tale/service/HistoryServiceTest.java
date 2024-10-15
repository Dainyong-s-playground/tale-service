package kkk.dainyong.tale.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import kkk.dainyong.tale.repository.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import kkk.dainyong.tale.model.FairyTale;
import kkk.dainyong.tale.model.History;
import kkk.dainyong.tale.model.dto.HistoryDTO;
import kkk.dainyong.tale.repository.FairyTaleRepository;
import kkk.dainyong.tale.repository.HistoryRepository;

class HistoryServiceTest {

	@Mock
	private HistoryRepository historyRepository;

	@Mock
	private FairyTaleRepository fairyTaleRepository;

	private HistoryService historyService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		historyService = new HistoryService(historyRepository, fairyTaleRepository);
	}

	/**
	 * 테스트 목적: getRecentlyWatchedContent 메서드가 정상적으로 HistoryDTO 리스트를 반환하는지 확인
	 * 시나리오:
	 * 1. 두 개의 History 객체와 해당하는 FairyTale 객체를 생성
	 * 2. Repository의 동작을 모의(mock)로 설정
	 * 3. 서비스 메서드 호출
	 * 4. 반환된 결과의 정확성 검증
	 */
	@Test
	void getRecentlyWatchedContent_shouldReturnListOfHistoryDTO() {
		// Given
		Long profileId = 1L;
		History history1 = History.builder()
			.profileId(profileId)
			.fairyTaleId(1L)
			.readDate(LocalDate.now())
			.progress(0.5f)
			.build();
		History history2 = History.builder()
			.profileId(profileId)
			.fairyTaleId(2L)
			.readDate(LocalDate.now().minusDays(1))
			.progress(0.7f)
			.build();

		FairyTale fairyTale1 = FairyTale.builder()
			.id(1L)
			.title("Fairy Tale 1")
			.imageUrl("image1.jpg")
			.views(100)
			.rentalPrice(1000)
			.purchasePrice(5000)
			.description("Description 1")
			.author("Author 1")
			.build();
		FairyTale fairyTale2 = FairyTale.builder()
			.id(2L)
			.title("Fairy Tale 2")
			.imageUrl("image2.jpg")
			.views(200)
			.rentalPrice(1500)
			.purchasePrice(7000)
			.description("Description 2")
			.author("Author 2")
			.build();

		when(historyRepository.getRecentlyWatchedContent(profileId)).thenReturn(Arrays.asList(history1, history2));
		when(fairyTaleRepository.findById(1L)).thenReturn(fairyTale1);
		when(fairyTaleRepository.findById(2L)).thenReturn(fairyTale2);

		// When
		List<HistoryDTO> result = historyService.getRecentlyWatchedContent(profileId);

		// Then
		assertNotNull(result);
		assertEquals(2, result.size());

		HistoryDTO historyDTO1 = result.get(0);
		assertEquals(profileId, historyDTO1.getProfileId());
		assertEquals(1L, historyDTO1.getFairyTaleId());
		assertEquals(history1.getReadDate(), historyDTO1.getReadDate());
		assertEquals(history1.getProgress(), historyDTO1.getProgress());
		assertEquals(fairyTale1.getTitle(), historyDTO1.getFairyTale().getTitle());

		HistoryDTO historyDTO2 = result.get(1);
		assertEquals(profileId, historyDTO2.getProfileId());
		assertEquals(2L, historyDTO2.getFairyTaleId());
		assertEquals(history2.getReadDate(), historyDTO2.getReadDate());
		assertEquals(history2.getProgress(), historyDTO2.getProgress());
		assertEquals(fairyTale2.getTitle(), historyDTO2.getFairyTale().getTitle());

		verify(historyRepository, times(1)).getRecentlyWatchedContent(profileId);
		verify(fairyTaleRepository, times(1)).findById(1L);
		verify(fairyTaleRepository, times(1)).findById(2L);
	}

	/**
	 * 테스트 목적: FairyTale을 찾을 수 없을 때 getRecentlyWatchedContent 메서드가 예외를 던지는지 확인
	 * 시나리오:
	 * 1. History 객체 생성
	 * 2. FairyTale을 찾을 수 없는 상황을 모의(mock)로 설정
	 * 3. 서비스 메서드 호출 시 예외 발생 확인
	 */
	@Test
	void getRecentlyWatchedContent_shouldThrowExceptionWhenFairyTaleNotFound() {
		// Given
		Long profileId = 1L;
		History history = History.builder()
			.profileId(profileId)
			.fairyTaleId(1L)
			.readDate(LocalDate.now())
			.progress(0.5f)
			.build();

		when(historyRepository.getRecentlyWatchedContent(profileId)).thenReturn(Arrays.asList(history));
		when(fairyTaleRepository.findById(1L)).thenReturn(null);

		// When & Then
		assertThrows(RuntimeException.class, () -> historyService.getRecentlyWatchedContent(profileId));

		verify(historyRepository, times(1)).getRecentlyWatchedContent(profileId);
		verify(fairyTaleRepository, times(1)).findById(1L);
	}

	@Test
	void insertHistory() {
		// Given
		Long profileId = 1L;
		History history = History.builder()
				.profileId(profileId)
				.fairyTaleId(1L)
				.readDate(LocalDate.now())
				.progress(0.5f)
				.build();

		// When (fairyTaleRepository가 null을 반환하게 설정)
		when(fairyTaleRepository.findById(1L)).thenReturn(null);

		// Then (IllegalStateException이 발생해야 함)
		assertThrows(IllegalStateException.class, () -> historyService.insertHistory(history));
	}
}