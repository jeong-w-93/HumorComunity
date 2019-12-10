package org.zerock.service;

import org.zerock.domain.MemberVO;

public interface MemberService {
	public void create(MemberVO vo);
	
	//아이디 확인
	public MemberVO idCheck(String userid) throws Exception;
}
