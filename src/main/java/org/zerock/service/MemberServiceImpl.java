package org.zerock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.MemberVO;
import org.zerock.mapper.MemberMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

	@Setter(onMethod_ = @Autowired)
	private MemberMapper mapper;
	
/*	@Autowired
	private MemberDAO dao;
*/
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	

	
	@Transactional
	@Override
	public void create(MemberVO member) {
		log.info("==========================");
		log.info("signup SERVICE: " + member);
		log.info("==========================");
		System.out.println("암호화 되기 전 : " + member.getUserpw());
		member.setUserpw(bcryptPasswordEncoder.encode(member.getUserpw())); // bcrypt 암호화
	    System.out.println("암호화 된 후 : " + member.getUserpw());
	    
	    mapper.create(member);
	}



	@Override
	public MemberVO idCheck(String userid) throws Exception{
		return mapper.idCheck(userid);
	}

	

}
