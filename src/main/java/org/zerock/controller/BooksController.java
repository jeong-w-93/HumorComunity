package org.zerock.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.BookVO;
import org.zerock.helper.FileHelper;
import org.zerock.mapper.BookMapper;

@Controller

public class BooksController {
    @Autowired
    private BookMapper bookMapper;
   
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String index(Model model) {
        List<BookVO> books = bookMapper.getList();
        // 모델을 통해 뷰페이지로 데이터를 전달
        model.addAttribute("books", books);
        return "books/index";
    }
    @RequestMapping(value = "/books/new", method = RequestMethod.GET)
    public String newBook() {
        return "books/new";
    }
    
/*    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public String create(@ModelAttribute BookVO book) {
        System.out.println(book.toString());
        bookMapper.create(book);
        return "redirect:/board/list";
    }*/
    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public String create(@ModelAttribute BookVO book, @RequestParam MultipartFile file, HttpServletRequest request) {
        String fileUrl = FileHelper.upload("/uploads", file, request);
        book.setImage(fileUrl);
        bookMapper.create(book);
        return "redirect:/books";
    }
    /*@PathVariable 어노테이션은 URL로부터 변수 값을 받아올 수 있게 합니다.*/
    @RequestMapping(value = "/books/edit/{bno}", method = RequestMethod.GET)
    public String edit(@PathVariable int bno,Model model) {
        // bno 값을 통해 데이터를 가져옴
        BookVO book = bookMapper.getBook(bno);
        // 뷰 페이지로 데이터를 전달(key/value 형식)
        model.addAttribute("book", book);
        return "books/edit";
    }
    
    @RequestMapping(value = "/books/update", method = RequestMethod.POST)
    public String update(@ModelAttribute BookVO book) {
        System.out.println(book.toString());
        bookMapper.update(book);
        return "redirect:/books";
    }
    
    @RequestMapping(value = "/books/delete/{bno}", method = RequestMethod.GET)
    public String delete(@PathVariable int bno) {
        bookMapper.delete(bno);
        return "redirect:/books";
    }
}