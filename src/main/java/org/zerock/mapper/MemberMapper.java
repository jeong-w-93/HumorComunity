 package org.zerock.mapper;

import org.zerock.domain.MemberVO;

public interface MemberMapper {

	public void create(MemberVO vo);
	public MemberVO read(String userid);
	public MemberVO idCheck(String userid) throws Exception;
	
}
