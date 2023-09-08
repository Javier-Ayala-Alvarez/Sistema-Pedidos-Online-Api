package com.sistema.pedidos.service.impl;

import com.sistema.pedidos.DTO.EditPlatoDTO;
import com.sistema.pedidos.DTO.PlatoDTO;
import com.sistema.pedidos.DTO.SavePlatoDTO;
import com.sistema.pedidos.commons.GenericServiceImpl;
import com.sistema.pedidos.entity.*;
import com.sistema.pedidos.repository.CategoryRepository;
import com.sistema.pedidos.repository.PlatoRepository;
import com.sistema.pedidos.repository.ProductRepository;
import com.sistema.pedidos.repository.PromocionRepository;
import com.sistema.pedidos.service.PlatoService;
import lombok.AllArgsConstructor;
import mapper.PlatoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class PlatoServiceImpl extends GenericServiceImpl<Plato, Long> implements PlatoService {
    private final PlatoRepository platoRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final PromocionRepository promocionRepository;

    public ResponseEntity<Object> saveValidate(SavePlatoDTO savePlatoDTO) {

        Plato plato = new Plato();
        plato.setNombre(savePlatoDTO.getNombre());
        plato.setPrecio(savePlatoDTO.getPrecio());
        plato.setDescripcion(savePlatoDTO.getDescripcion());
        plato.setUrlImagen(savePlatoDTO.getUrlImagen());
        plato.setEstado(true);
        List<PlatoProducto> listaPlatoProducto = new ArrayList<>();

        for (Long productoId : savePlatoDTO.getListaProductos()) {
            Optional<Product> obj = productRepository.findById(productoId);
            obj.ifPresent(producto -> {
                PlatoProducto platoProducto = new PlatoProducto();
                platoProducto.setProducto(producto);
                platoProducto.setPlato(plato);
                platoProducto.setFechaRegistro(new Date());
                listaPlatoProducto.add(platoProducto);
            });
            if (!obj.isPresent())
                return new ResponseEntity<>("No existe el producto con id: " + productoId, HttpStatus.BAD_REQUEST);
        }
        plato.setPlatoProducto(listaPlatoProducto);
        Optional<Category> obj = categoryRepository.findById(savePlatoDTO.getIdCategoria());
        if (!obj.isPresent())
            return new ResponseEntity<>("No existe la categoria con id: " + savePlatoDTO.getIdCategoria(), HttpStatus.BAD_REQUEST);
        plato.setCategoria(obj.get());

        if (savePlatoDTO.nonNullPromocion()) {
            Optional<Promocion> objPromocion = promocionRepository.findById(savePlatoDTO.getIdPromocion());
            if (!objPromocion.isPresent())
                return new ResponseEntity<>("No existe la promocion con id: " + savePlatoDTO.getIdPromocion(), HttpStatus.BAD_REQUEST);

            plato.setPromocion(objPromocion.get());
        }

        return new ResponseEntity<>(new PlatoMapper().platoToPlatoDTO(save(plato)), HttpStatus.CREATED);

    }

    @Override
    public Page<PlatoDTO> listPlateActiveWithPagination(Pageable pageable) {
        Page<Plato> platoPage = platoRepository.getAllByEstadoEquals(Boolean.TRUE, pageable);
        PlatoMapper platoMapper = new PlatoMapper();
        return platoPage.map(platoMapper::platoToPlatoDTO);
    }

    @Override

    public ResponseEntity<String> updatePlateState(Boolean estado, Long id) {
        int result = platoRepository.updateEstado(estado, id);
        if (result > 0) return new ResponseEntity("Plato dado de baja", HttpStatus.OK);
        return new ResponseEntity("no existe", HttpStatus.NOT_FOUND);
    }

    @Override
    public Optional<Plato> getPlateById(Long id) {
        return platoRepository.findById(id);
    }


    public ResponseEntity<Object> updatePlato(EditPlatoDTO savePlatoDTO) {


        Optional<Plato> platoOptional = platoRepository.findById(savePlatoDTO.getId());
        if (!platoOptional.isPresent())
            return new ResponseEntity<>("No existe el plato con id: " + savePlatoDTO.getId(), HttpStatus.BAD_REQUEST);
        Plato plato = platoOptional.get();

        plato.setNombre(savePlatoDTO.getNombre());
        plato.setPrecio(savePlatoDTO.getPrecio());
        plato.setDescripcion(savePlatoDTO.getDescripcion());
        plato.setUrlImagen(savePlatoDTO.getUrlImagen());
        plato.setEstado(savePlatoDTO.getEstado());
        List<PlatoProducto> listaPlatoProducto = plato.getPlatoProducto();
        listaPlatoProducto.clear();

        for (Long productoId : savePlatoDTO.getListaProductos()) {
            Optional<Product> obj = productRepository.findById(productoId);
            obj.ifPresent(producto -> {
                PlatoProducto platoProducto = new PlatoProducto();
                platoProducto.setProducto(producto);
                platoProducto.setPlato(plato);
                platoProducto.setFechaRegistro(new Date());
                listaPlatoProducto.add(platoProducto);
            });
            if (!obj.isPresent())
                return new ResponseEntity<>("No existe el producto con id: " + productoId, HttpStatus.BAD_REQUEST);
        }
        plato.setPlatoProducto(listaPlatoProducto);
        Optional<Category> obj = categoryRepository.findById(savePlatoDTO.getIdCategoria());
        if (!obj.isPresent())
            return new ResponseEntity<>("No existe la categoria con id: " + savePlatoDTO.getIdCategoria(), HttpStatus.BAD_REQUEST);
        plato.setCategoria(obj.get());

        if (savePlatoDTO.nonNullPromocion()) {
            Optional<Promocion> objPromocion = promocionRepository.findById(savePlatoDTO.getIdPromocion());
            if (!objPromocion.isPresent())
                return new ResponseEntity<>("No existe la promocion con id: " + savePlatoDTO.getIdPromocion(), HttpStatus.BAD_REQUEST);

            plato.setPromocion(objPromocion.get());
        }

        return new ResponseEntity<>(new PlatoMapper().platoToPlatoDTO(save(plato)), HttpStatus.CREATED);

    }


    @Override
    public JpaRepository<Plato, Long> getDao() {
        return platoRepository;
    }


}
