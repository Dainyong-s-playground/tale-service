package kkk.dainyong.tale.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserRepository {
	int getUserCredit(@Param("userId") String userId);

	void updateUserCredit(@Param("userId") String userId, @Param("credit") int credit);
}
