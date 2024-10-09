package kkk.dainyong.tale.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import kkk.dainyong.tale.model.FairyTale;
import kkk.dainyong.tale.model.dto.FairyTaleDTO;
import kkk.dainyong.tale.repository.FairyTaleRepository;

/**
 * @author dae
 * @date 2024-10-10
 */
class FairyTaleServiceTest {

	@Mock
	private FairyTaleRepository fairyTaleRepository;

	@InjectMocks
	private FairyTaleService fairyTaleService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void getTop5FairyTalesByViews_ShouldReturnTop5FairyTales() {
		// Given
		List<FairyTale> mockFairyTales = Arrays.asList(
			FairyTale.builder()
				.id(5L)
				.title("흥부와 놀부")
				.imageUrl("https://image.yes24.com/Goods/103854123/XL")
				.views(200)
				.rentalPrice(1500)
				.purchasePrice(3000)
				.description("흥부와 놀부 이야기입니다.")
				.author("다이뇽")
				.build(),
			FairyTale.builder()
				.id(7L)
				.title("선녀와 나무꾼")
				.imageUrl("https://image.yes24.com/Goods/129868670/XL")
				.views(180)
				.rentalPrice(1000)
				.purchasePrice(2000)
				.description("선녀와 나무꾼 이야기입니다.")
				.author("다이뇽")
				.build(),
			FairyTale.builder()
				.id(8L)
				.title("장화 신은 고양이")
				.imageUrl("https://image.yes24.com/Goods/113570/XL")
				.views(170)
				.rentalPrice(1000)
				.purchasePrice(2000)
				.description("장화 신은 고양이 이야기입니다.")
				.author("다이뇽")
				.build(),
			FairyTale.builder()
				.id(2L)
				.title("백설공주")
				.imageUrl("https://image.yes24.com/Goods/117122846/XL")
				.views(150)
				.rentalPrice(2000)
				.purchasePrice(4000)
				.description("백설공주의 이야기입니다.")
				.author("다이뇽")
				.build(),
			FairyTale.builder()
				.id(9L)
				.title("빨간 모자")
				.imageUrl("https://image.yes24.com/Goods/35523160/XL")
				.views(140)
				.rentalPrice(1300)
				.purchasePrice(2600)
				.description("빨간 모자의 이야기입니다.")
				.author("다이뇽")
				.build()
		);
		when(fairyTaleRepository.findTop5ByOrderByViewsDesc()).thenReturn(mockFairyTales);

		// When
		List<FairyTaleDTO> result = fairyTaleService.getTop5FairyTalesByViews();

		// Then
		assertEquals(5, result.size());
		assertEquals("흥부와 놀부", result.get(0).getTitle());
		assertEquals(200, result.get(0).getViews());
		assertEquals(1500, result.get(0).getRentalPrice());
		assertEquals(3000, result.get(0).getPurchasePrice());

		assertEquals("선녀와 나무꾼", result.get(1).getTitle());
		assertEquals(180, result.get(1).getViews());
		assertEquals("장화 신은 고양이", result.get(2).getTitle());
		assertEquals(170, result.get(2).getViews());
		assertEquals("백설공주", result.get(3).getTitle());
		assertEquals(150, result.get(3).getViews());
		assertEquals("빨간 모자", result.get(4).getTitle());
		assertEquals(140, result.get(4).getViews());

		verify(fairyTaleRepository, times(1)).findTop5ByOrderByViewsDesc();
	}

	@Test
	void getTop5FairyTalesByViews_ShouldReturnEmptyList_WhenNoFairyTalesFound() {
		// Given
		when(fairyTaleRepository.findTop5ByOrderByViewsDesc()).thenReturn(Arrays.asList());

		// When
		List<FairyTaleDTO> result = fairyTaleService.getTop5FairyTalesByViews();

		// Then
		assertTrue(result.isEmpty());
		verify(fairyTaleRepository, times(1)).findTop5ByOrderByViewsDesc();
	}
}