package com.sistema.pedidos.Controller;

import com.sistema.pedidos.DTO.ClienteUsuarioDTO;
import com.sistema.pedidos.Utileria.ConstantUtileria;
import com.sistema.pedidos.entity.ClientesEntity;
import com.sistema.pedidos.entity.Empleado;
import com.sistema.pedidos.repository.ClienteRepository;
import com.sistema.pedidos.repository.UsuarioRepositorio;
import com.sistema.pedidos.repository.UsuarioRepository;
import com.sistema.pedidos.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    private final ClienteService clienteService;
    private final ClienteRepository clienteRepository;
    private final UsuarioRepositorio usuarioRepository;

    @GetMapping("/list")
    public ResponseEntity<Iterable<ClientesEntity>> mostrarCliente() {
        return new ResponseEntity<>(clienteService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/list/pageable")
    public ResponseEntity<Page<ClientesEntity>> listarClientesPorPagina(
            @RequestParam(defaultValue = ConstantUtileria.NUMERO_PAGINA_DEFECTO) int page,
            @RequestParam(defaultValue = ConstantUtileria.MEDIDA_PAGINA_DEFECTO) int size,
            @RequestParam(defaultValue = ConstantUtileria.ORDENAR_DEFECTO) String order,
            @RequestParam(defaultValue = ConstantUtileria.ORDENAR_DIRECCION_DEFECTO) boolean asc
    ) {
        Page<ClientesEntity> ClientePage = clienteService.getAllWithPagination(PageRequest.of(page, size, Sort.by(order)));
        return new ResponseEntity<>(ClientePage, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<ClientesEntity> agregarCliente(@RequestBody ClientesEntity cliente) {
        return new ResponseEntity<>(clienteService.save(cliente), HttpStatus.CREATED);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ClientesEntity> eliminarEmpleado(@PathVariable Long id) {
        clienteService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ClientesEntity> actualizarEmpleado(@RequestBody ClientesEntity cliente) {
        ClientesEntity clienteActualizado = clienteService.save(cliente);
        return new ResponseEntity<>(clienteActualizado, HttpStatus.OK);
    }

    @PatchMapping("/baja/{id}")
    public ResponseEntity darDeBajaCliente(@PathVariable Long id) {

        Optional<ClientesEntity> cliente = clienteRepository.findById(id);
        if (!cliente.isPresent()) return ResponseEntity.notFound().build();
        usuarioRepository.deshabilitarUsuario(cliente.get().getUsuario().getId());
        clienteRepository.darBajaCliente(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/list/search")
    public ResponseEntity<Page<ClientesEntity>> listarEmpleadosPorNombrePagina(
            @RequestParam("cliente") String clienteNombre,
            @RequestParam(defaultValue = ConstantUtileria.NUMERO_PAGINA_DEFECTO) int page,
            @RequestParam(defaultValue = ConstantUtileria.MEDIDA_PAGINA_DEFECTO) int size,
            @RequestParam(defaultValue = ConstantUtileria.ORDENAR_DIRECCION_DEFECTO) boolean asc
    ) {
        Page<ClientesEntity> clientePage = clienteRepository.listarClientesPorNombre(clienteNombre,
                PageRequest.of(page, size));
        return new ResponseEntity<Page<ClientesEntity>>(clientePage, HttpStatus.OK);
    }

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<ClienteUsuarioDTO> buscarPorId(@PathVariable Long id) {
        return clienteService.buscarClientePorIdUsuario(id);
    }


}
