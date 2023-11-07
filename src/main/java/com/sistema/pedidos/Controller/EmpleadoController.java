package com.sistema.pedidos.Controller;

import com.sistema.pedidos.Utileria.ConstantUtileria;
import com.sistema.pedidos.entity.*;
import com.sistema.pedidos.repository.BranchOfficeRepository;
import com.sistema.pedidos.repository.EmpleadoRepository;
import com.sistema.pedidos.repository.RolRepositorio;
import com.sistema.pedidos.repository.RolRepository;
import com.sistema.pedidos.service.EmpleadoService;
import com.sistema.pedidos.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {
    private final EmpleadoService empleadoService;

    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private BranchOfficeRepository branchOfficeRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private RolRepositorio rolRepositorio;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/list")
    public ResponseEntity<Iterable<Empleado>> listarEmpleados() {
        return new ResponseEntity<>(empleadoService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/listf/pageables")
    public ResponseEntity<Page<Empleado>> listarEmpleadosPorPagina(
            @RequestParam(defaultValue = ConstantUtileria.NUMERO_PAGINA_DEFECTO) int page,
            @RequestParam(defaultValue = ConstantUtileria.MEDIDA_PAGINA_DEFECTO) int size,
            @RequestParam(defaultValue = ConstantUtileria.ORDENAR_DEFECTO) String order,
            @RequestParam(defaultValue = ConstantUtileria.ORDENAR_DIRECCION_DEFECTO) boolean asc
    ) {
        Page<Empleado> empleadoPage = empleadoService.listarEmpleadosPorPagina(
            PageRequest.of(page, size, Sort.by(order)));
       return new ResponseEntity<Page<Empleado>>(empleadoPage, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<Empleado> agregarEmpleado(@RequestBody Empleado empleado) {
        return new ResponseEntity<>(empleadoService.save(empleado), HttpStatus.CREATED);
    }
   /* @PostMapping("/GuardarUsuarioEmpleado")
    public ResponseEntity<?> guardarUsuarioEmpleado(@Valid @RequestBody Empleado empleado)throws Exception{

        Optional<Sucursal>sucursalOptional=branchOfficeRepository.findById(empleado.getSucursal().getId());
        if (!sucursalOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        Usuario usuario = new Usuario();
        usuario.setUsername(empleado.getUsuario().getUsername());
        usuario.setEmail(empleado.getUsuario().getEmail());
        usuario.setPassword(passwordEncoder.encode(empleado.getUsuario().getPassword()));

        Set<UsuarioRol> roles = new HashSet<>();

        Rol rol = new Rol();
        rol.setRolId(3L);
        rol.setNombre("COCINA");

        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);

        empleado.setSucursal(sucursalOptional.get());
        empleado.setNombre(empleado.getNombre());
        empleado.setApellido(empleado.getApellido());
        empleado.setTelefono(empleado.getTelefono());
        empleado.setLugarNacimiento(empleado.getLugarNacimiento());
        empleado.setSalario(empleado.getSalario());
        empleado.setDui(empleado.getDui());
        empleado.setEstado(empleado.getEstado());
        empleado.setSucursal(sucursalOptional.get());

        roles.add(usuarioRol);

        usuario.setEmpleado(empleado);

        return new ResponseEntity<>(usuarioService.guardarUsuario(usuario,roles),HttpStatus.OK);
    }*/


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Empleado> eliminarEmpleado(@PathVariable Integer id) {
        empleadoService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Empleado> actualizarEmpleado(@RequestBody Empleado empleado) {
        Empleado empleadoActualizado = empleadoService.save(empleado);
        return new ResponseEntity<>(empleadoActualizado, HttpStatus.OK);
    }

    @PutMapping("update2/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado2(@RequestBody Empleado empleado, @PathVariable Integer id) {
        return empleadoService.actualizarEmpleado2(empleado, id);
    }

    @PutMapping("/baja/{id}")
    public ResponseEntity darDeBajaEmpleado(@PathVariable Integer id) {
        return empleadoService.darDeBajaEmpleado(id);
    }

    @GetMapping("/list/search")
    public ResponseEntity<Page<Empleado>> listarEmpleadosPorNombrePagina(
            @RequestParam("empleado") String emp_Nombre,
            @RequestParam(defaultValue = ConstantUtileria.NUMERO_PAGINA_DEFECTO) int page,
            @RequestParam(defaultValue = ConstantUtileria.MEDIDA_PAGINA_DEFECTO) int size,
            @RequestParam(defaultValue = ConstantUtileria.ORDENAR_DIRECCION_DEFECTO) boolean asc
    ) {
        Page<Empleado> EmpleadoPage = empleadoService.listarEmpleadoPorNombrePagina(emp_Nombre,
                PageRequest.of(page, size));
        return new ResponseEntity<Page<Empleado>>(EmpleadoPage, HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Empleado> listarEmpleadoPorId(@PathVariable Integer id) {
        try {
            Empleado empleado = empleadoService.listarEmpleadoPorId(id);
            return new ResponseEntity<Empleado>(empleado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Empleado>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/listtt/{id}")
    public Empleado getEmpleado(@PathVariable Integer id) {
        return empleadoService.listarEmpleadoPorId(id);
    }


    // obtener estado de empleado por id
    @GetMapping("/usuario/estado/{id}")
    public ResponseEntity<Object> getEstadoEmpleadoByIdUsuario(@PathVariable Long id) {
        Optional<String> opEstado = empleadoService.getEstadoEmpleadoByIdUsuario(id);
        if (!opEstado.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(opEstado.get(), HttpStatus.OK);
    }

    @PutMapping("/estado/{id}")
    public ResponseEntity updateEstadoEmpleado(@PathVariable Long id, @RequestBody String estado) {
        return empleadoService.updateEstadoEmpleado(id, estado);
    }

    @GetMapping("/usuario/estado/delivery/{id}")
    public ResponseEntity<Object> getEstadoEntregandoEmpleadoByIdUsuario(@PathVariable Long id) {
        Optional<String> opEstado = empleadoService.getEstadoEmpleadoByIdUsuario(id);
        if (!opEstado.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (opEstado.get().equals("ENTREGANDO")) return new ResponseEntity<>(true, HttpStatus.OK);

        return new ResponseEntity<>(false, HttpStatus.OK);


    }

}


