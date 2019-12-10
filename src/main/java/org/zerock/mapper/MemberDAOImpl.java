package org.zerock.mapper;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zerock.domain.MemberVO;
@Repository
public class MemberDAOImpl implements MemberDAO {

	@Autowired
	private SqlSession sql;
	
	@Autowired
	private static String namespace ="org.zerock.mapper.MemberMapper";
	
	@Override
	public MemberVO idCheck(String userid) throws Exception {
		
		return sql.selectOne(namespace+".idCheck",userid);
	
	}

}
