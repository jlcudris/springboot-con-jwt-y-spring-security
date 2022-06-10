package com.sistema.blog.controller;

import com.sistema.blog.dto.PostDto;
import com.sistema.blog.dto.PostResponsy;
import com.sistema.blog.entity.Rol;
import com.sistema.blog.entity.Usuario;
import com.sistema.blog.exeptions.ResourceNotFoundExeption;
import com.sistema.blog.repository.UsuarioRepository;
import com.sistema.blog.service.PostService;
import com.sistema.blog.utils.AppConstantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PostService postService;

    @GetMapping
    public  ResponseEntity<PostResponsy> getAllPost(
            @RequestParam(value = "numPagina",defaultValue = AppConstantes.numPaginaDefault,required = false) int numeroPagina,
            @RequestParam(value = "pageSize",defaultValue = AppConstantes.quantityElementDefault,required = false) int cantidadPost,
            @RequestParam(value = "sortBy",defaultValue = AppConstantes.orderByDefault,required = false) String ordenarPor,
            @RequestParam(value = "sortDir",defaultValue = AppConstantes.orderDirDefault,required = false) String sortDir)
    {
           return new ResponseEntity<>(postService.getAllPost(
                   numeroPagina,cantidadPost,ordenarPor,sortDir),HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PostDto> save(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.crearPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> findByIdPost(@PathVariable(name = "id") long id){
        return  ResponseEntity.ok(postService.findByIdPost(id));
    }

    @PutMapping("/{id}")
   @PreAuthorize("hasRole('ADMIN')")
    public  ResponseEntity<PostDto> updatePost(@Valid @PathVariable long id,@RequestBody PostDto postDto){
        return ResponseEntity.ok(postService.update(id,postDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> delete(@PathVariable long id){
      postService.delete(id);
           return new ResponseEntity<>("publicacion Eliminada",HttpStatus.OK);
    }


   /* @GetMapping("/probando/{username}")
    @Override
    public UserDetails loadUserByUsername(@PathVariable String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsernameOrEmail(username,username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con ese username o email :"+username));
        return  new User(usuario.getEmail(),usuario.getPassword(),mapearRoles(usuario.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapearRoles(Set<Rol> roles){
        return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getNombre())).collect(Collectors.toList());

    }*/
}
