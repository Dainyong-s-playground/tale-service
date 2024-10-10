package kkk.dainyong.tale.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	private String id;
	private String nickname;
	private String birth;
	private String gender;
}
