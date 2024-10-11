package kkk.dainyong.tale.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kkk.dainyong.tale.model.FairyTale;
import kkk.dainyong.tale.model.PurchaseList;
import kkk.dainyong.tale.model.RentalList;

@Mapper
public interface FairyTaleOwnershipRepository {
	String findUserIdByProfileId(@Param("profileId") Long profileId);

	PurchaseList findPurchaseByUserIdAndFairyTaleId(@Param("userId") String userId,
		@Param("fairyTaleId") Long fairyTaleId);

	RentalList findActiveRentalByUserIdAndFairyTaleId(@Param("userId") String userId,
		@Param("fairyTaleId") Long fairyTaleId);

	FairyTale findFairyTaleById(@Param("fairyTaleId") Long fairyTaleId);

	void saveRental(RentalList rental);

	void savePurchase(PurchaseList purchase);

	List<RentalList> findRentalListByUserId(String userId);

	List<PurchaseList> findPurchaseListByUserId(String userId);

	List<PurchaseList> findPurchaseListByProfileId(@Param("profileId") Long profileId);

	List<RentalList> findRentalListByProfileId(@Param("profileId") Long profileId);
}