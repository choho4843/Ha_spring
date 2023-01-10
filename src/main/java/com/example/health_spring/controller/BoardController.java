package com.example.health_spring.controller;


import com.example.health_spring.entity.Board;
import com.example.health_spring.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

@RestController
public class BoardController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/community")
    public ResponseEntity<List<Board>> boardList() {
        ResponseEntity<List<Board>> res = null;
        try {
            List<Board> boardList = boardService.getBoardList();
            res = new ResponseEntity<>(boardList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return res;
    }

    @PostMapping("/writeform2")
    public ResponseEntity<String> writeform2(@RequestParam String title, @RequestParam String writer,
                                             @RequestParam String subtitle,
                                             @RequestParam String content,
                                             @RequestParam(name = "file", required = false) MultipartFile file) {
//        System.out.println(title);
//        System.out.println(writer);
//        System.out.println(subtitle);여기까지 찍힘ㅁ
        System.out.println(content);
        ResponseEntity<String> res = null;
        try {
            boardService.writeform2(title, writer, subtitle, file, content);
            res = new ResponseEntity<String>("게시글 저장 성공", HttpStatus.OK);
//            System.out.println(res);
        } catch (Exception e) {
            e.printStackTrace();
            res = new ResponseEntity<String>("게시글 저장 실패", HttpStatus.BAD_REQUEST);
        }
        return res;
    }

    @GetMapping("/boarddetail/{id}")
    public ResponseEntity<Board> boarddetail(@PathVariable Integer id) {
        ResponseEntity<Board> res = null;
        System.out.println("#####id는#####" + id);
        try {
            Board board = boardService.detailBoard(id);
            res = new ResponseEntity<Board>(board, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            res = new ResponseEntity<Board>(HttpStatus.BAD_REQUEST);
        }
        return res;
    }

    @GetMapping("/community")
    public ResponseEntity<List<Board>> communitylist() {
        ResponseEntity<List<Board>> res = null;
        try {
                List<Board> boardList = boardService.communitylist();
                res = new ResponseEntity<>(boardList,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return res;
    }


    @GetMapping("/thumbnail/{filename}")
    public void thumbnail(@PathVariable String filename, HttpServletResponse response) {
        try {
            String path = "C:/kg/";
            FileInputStream fis = new FileInputStream(path + filename);
            FileCopyUtils.copy(fis, response.getOutputStream());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

