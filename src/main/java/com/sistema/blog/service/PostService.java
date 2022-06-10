package com.sistema.blog.service;

import com.sistema.blog.dto.PostDto;
import com.sistema.blog.dto.PostResponsy;



public interface PostService {

    public PostDto crearPost(PostDto postDto);

    public PostResponsy getAllPost(int numeroPagina, int cantidadPost, String ordenarPor, String sortDir);

    public PostDto findByIdPost(long id);

    public PostDto update(long id,PostDto postDto);

    public boolean delete(long id);


}
