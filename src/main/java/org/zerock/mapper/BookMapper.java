package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.zerock.domain.BookVO;

public interface BookMapper {
	  @Insert("insert into tbl_book(bno,title, author, image) values (seq_book.nextval,#{title}, #{author}, #{image})")
	    public boolean create(BookVO book);
	  
	  @Select("select * from tbl_book")
	    public List<BookVO> getList();
	  
	  @Select("select * from tbl_book where bno = #{bno}")
	    public BookVO getBook(int bno);
	  
	  @Update("update books set title = #{title}, author = #{author}, image = #{image} where id = #{id}")
	  public boolean update(BookVO book);

	  @Delete("delete from tbl_book where bno = #{bno}")
	  public boolean delete(int bno);
}
