package com.sistema.blog.service;

import com.sistema.blog.dto.CommentDto;
import com.sistema.blog.entity.Comment;

import java.util.List;

public interface CommentService {

    public CommentDto save(long postId,CommentDto commentDto);

    public List<CommentDto> getAllCommentByIdPost(long postId);

    public List<CommentDto> getAllcomment();

    public CommentDto getByIdComment(long commentId);

    public CommentDto getByIdCommentAndIdPost(Long postId,Long CommentId);

    public CommentDto update(long postId,long commentId, CommentDto commentDto );

    public boolean delete(long commentId);


}
