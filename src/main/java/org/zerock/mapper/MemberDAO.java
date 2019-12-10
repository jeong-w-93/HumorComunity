package org.zerock.mapper;

import org.zerock.domain.MemberVO;

public interface MemberDAO {
	public MemberVO idCheck(String userid) throws Exception;
}
