package com.sistema.blog.service;

import com.sistema.blog.dto.CommentDto;
import com.sistema.blog.entity.Comment;
import com.sistema.blog.entity.Post;
import com.sistema.blog.exeptions.BlogAppExeption;
import com.sistema.blog.exeptions.ResourceNotFoundExeption;
import com.sistema.blog.repository.CommetRepository;
import com.sistema.blog.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImp implements CommentService{

    @Autowired
    private CommetRepository commetRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto save(long postId, CommentDto commentDto) {
      Post post= postRepository.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundExeption("Post","id",postId));

        Comment comment =mapearToComment(commentDto);
        comment.setPost(post);
        commetRepository.save(comment);
        return  maperarToDto(comment);
    }

    @Override
    public List<CommentDto> getAllCommentByIdPost(long postId) {

        List<Comment> comments =commetRepository.findByPostId(postId);
        return comments.stream().map(comment -> maperarToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public List<CommentDto> getAllcomment() {
        return commetRepository.findAll()
                .stream().map(comment -> maperarToDto(comment)).collect(Collectors.toList());
    }

    @Override
    //obtener Cometarios por id de un cometario
    public CommentDto getByIdComment(long commentId) {
        return commetRepository.findById(commentId).map(
                comment -> {
                    return maperarToDto(comment);
                }
        ).orElseThrow(()-> new ResourceNotFoundExeption("Comment","id",commentId));
    }

    @Override
    //obetenr un comentario por el post id al que pertnece y su id propio solo ejemplo
    public CommentDto getByIdCommentAndIdPost(Long postId, Long CommentId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFoundExeption("Post","id",postId));

        Comment comment =commetRepository.findById(CommentId)
                .orElseThrow(()-> new ResourceNotFoundExeption("Comment","id",CommentId));
        if(!comment.getPost().getId().equals(post.getId())){
            BlogAppExeption blogAppExeption =new BlogAppExeption (HttpStatus.BAD_REQUEST,"El comentario no pertenece a la publicacion");
           throw blogAppExeption;
        }

        return maperarToDto(comment);
    }

    @Override
    public CommentDto update(long postId, long commentId, CommentDto commentDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFoundExeption("Post","id",postId));
        Comment comment =commetRepository.findById(commentId).map(comment1 -> {
                    comment1.setName(commentDto.getName());
                    comment1.setBody(commentDto.getBody());
                    comment1.setEmail(commentDto.getEmail());
                    comment1.setPost(post);
                    return commetRepository.save(comment1);
                })
                .orElseThrow(()-> new ResourceNotFoundExeption("Comment","id",commentId));
                 return maperarToDto(comment);
    }

    @Override
    public boolean delete(long commentId) {
        return commetRepository.findById(commentId)
                .map(comment ->{
                    commetRepository.delete(comment);
                    return true;
                } ).orElseThrow(()-> new ResourceNotFoundExeption("Comment","id",commentId));
    }

    private CommentDto maperarToDto(Comment comment){
       return new CommentDto(comment.getId(),comment.getName(),comment.getEmail(),comment.getBody(),comment.getPost());

        // CommentDto commentDto= modelMapper.map(comment,CommentDto.class);
        //return commentDto;
    }
    private Comment mapearToComment(CommentDto commentDto){
       Comment comment =new Comment();
       comment.setName(commentDto.getName());
       comment.setEmail(commentDto.getEmail());
       comment.setBody(commentDto.getBody());
       return comment;
         //Comment comment= modelMapper.map(commentDto,Comment.class);
        //return comment;
    }
}
