package com.example.health_spring.service;

import com.example.health_spring.entity.Board;
import com.example.health_spring.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BoardService {
    @Autowired
    BoardRepository boardRepository;

    public List<Board> getBoardList() throws Exception {
        return boardRepository.findAll();
    }

    public void writeform2(String title, String writer, String subtitle, MultipartFile file, String content) throws Exception {
        String filename = null;
//                System.out.println(title);
//        System.out.println(writer);
//        System.out.println(subtitle);
        Board b = new Board();
        if (file != null && !file.isEmpty()) {
            String path = "C:/kg/";
            filename = file.getOriginalFilename();
            File dFile = new File(path + filename);
            file.transferTo(dFile);
            b.setThumbnail(filename);
        }

        b.setTitle(title);
        b.setSubtitle(subtitle);
        b.setWriter(writer);
        b.setRegdate(new Date(System.currentTimeMillis()));
        b.setContent(content);

        Board save = boardRepository.save(b);

    }

    public Board detailBoard(Integer id) throws Exception {
        Optional<Board> oboard = boardRepository.findById(id);
        if (oboard.isEmpty()) {
            throw new Exception("글번호 오류");
        }
        return oboard.get();
    }

    public List<Board> communitylist() throws  Exception {
        List<Board>  oboard = boardRepository.findAll();
        if(oboard==null){
            throw  new Exception("게시글없음");
        }
        return oboard;
    }
}
