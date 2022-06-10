package com.sistema.blog.service;

import com.sistema.blog.dto.PostDto;
import com.sistema.blog.dto.PostResponsy;
import com.sistema.blog.entity.Post;
import com.sistema.blog.exeptions.ResourceNotFoundExeption;
import com.sistema.blog.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImp implements PostService{

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDto crearPost(PostDto postDto) {
        //se convierte el dtoPost a post para poder guardarlo en la db
        Post post =mapearToPost(postDto);

        //se guarda la entidad ya mapeada
        Post newPost = postRepository.save(post);

        //luego se convierte a la entidad a dtoPost para poder retornarla
        PostDto convertPostDto =mapearToDto(newPost);
        return convertPostDto;

    }

    @Override
    public PostResponsy getAllPost(int numeroPagina, int cantidadPost, String ordenarPor, String sortDir) {
        Sort sort =sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(ordenarPor).ascending()
                :Sort.by(ordenarPor).descending();
        //se crea un objeto pageable
        Pageable pageable = PageRequest.of(numeroPagina,cantidadPost, sort);
        //se crea un Page de post al momento de llamar al ametodo finall se pasa como parametro el pageable
        Page<Post> postsPaginados =postRepository.findAll(pageable);
        //se crea una lista de post para asignarle los post paginados
        List<Post> posts =postsPaginados.getContent();
        //luego mapeamos los post a posDto
        List<PostDto> postDtos = posts.stream().map(post -> mapearToDto(post)).collect(Collectors.toList());

        //se crea un objeto PostREsponsy y se le pasan parametros de pagiancion
        return  new PostResponsy (
                postDtos,postsPaginados.getNumber(),postsPaginados.getSize(),postsPaginados.getTotalElements(),
                postsPaginados.getTotalPages(),postsPaginados.isLast()
                );
    }

    @Override
    public PostDto findByIdPost(long id) {

      Post post =postRepository.findById(id)
              .orElseThrow(()-> new ResourceNotFoundExeption("Post","id",id));
      return mapearToDto(post);
    }

    @Override
    public PostDto update(long id, PostDto postDto) {

    /*    Post post =postRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundExeption("Post","id",id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        post.setDate(LocalDateTime.now());
         postRepository.save(post);
        return mapearToDto(post);*/


       Post post1 =postRepository.findById(id).map(post -> {
            post.setTitle(postDto.getTitle());
            post.setDescription(postDto.getDescription());
            post.setContent(postDto.getContent());
            post.setDate(LocalDateTime.now());
            return postRepository.save(post);

        }).orElseThrow(()-> new ResourceNotFoundExeption("Post","id",id));
        return mapearToDto(post1);

    }

    @Override
    public boolean delete(long id) {
        return postRepository.findById(id).map(post -> {
                postRepository.deleteById(id);
                return true;
        }).orElseThrow(()-> new ResourceNotFoundExeption("Post","id",id));

    }

    //convierte un post a postDto
    private PostDto mapearToDto(Post post){
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        postDto.setDate(post.getDate());
        postDto.setComments(post.getComments());
        return postDto;

       // return modelMapper.map(post,PostDto.class);

    }
    //convierte un postDto a Post
    private Post mapearToPost(PostDto postDto){

        Post post =new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        post.setDate(LocalDateTime.now());
        return  post;

        //return modelMapper.map(postDto,Post.class);
    }
}
