package com.sistema.blog.controller;

import com.sistema.blog.dto.CommentDto;
import com.sistema.blog.exeptions.BlogAppExeption;
import com.sistema.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}")
    public ResponseEntity <CommentDto> save(@PathVariable(value = "postId") long postId, @Valid @RequestBody CommentDto commentDto){
       return new ResponseEntity<>(commentService.save(postId,commentDto),HttpStatus.CREATED);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentDto>> getAllCommentByIdpost(@PathVariable long postId){
        List<CommentDto> commentDtos =commentService.getAllCommentByIdPost(postId);
        if(commentDtos.stream().count()>0){
            return ResponseEntity.ok(commentDtos);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<List<CommentDto>> getAllComment(){
       return   commentService.getAllcomment().stream().count() >0
               ? ResponseEntity.ok(commentService.getAllcomment())
               : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    //obtener cometario solo por su id
    @GetMapping("/{idComment}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable("idComment") long commentId){
        return ResponseEntity.ok(commentService.getByIdComment(commentId));

    }

    //obtener comentario por un id del post y id del comentario
    @GetMapping("/post/{idPost}/comment/{idComment}")
    public ResponseEntity<CommentDto> getCommentByIdPostAndIdComment(@PathVariable(value = "idPost") Long postId, @PathVariable(value = "idComment") Long commentId){

       // BlogAppExeption blogAppExeption =new BlogAppExeption(HttpStatus.BAD_REQUEST,"El comentario no pertenece a la publicacion");
        //throw blogAppExeption;
       return new ResponseEntity<>(commentService.getByIdCommentAndIdPost(postId,commentId),HttpStatus.OK);

    }
    @PutMapping("/post/{idPost}/comment/{idComment}")
    public ResponseEntity<CommentDto> update(@PathVariable(value = "idPost") Long postId,
                                             @PathVariable(value = "idComment") Long commentId,
                                             @Valid @RequestBody CommentDto commentDto)
    {
        return new ResponseEntity<>(commentService.update(postId,commentId,commentDto),HttpStatus.OK);

    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Map>delete(@PathVariable(value = "commentId") long commentId){
        commentService.delete(commentId);
        return new  ResponseEntity<>(Collections.singletonMap("response","Comentario eliminado"),HttpStatus.OK);
    }

}
