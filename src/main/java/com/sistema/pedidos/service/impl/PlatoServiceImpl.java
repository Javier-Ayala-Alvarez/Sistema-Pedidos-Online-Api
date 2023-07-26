package com.sistema.pedidos.service.impl;

import com.sistema.pedidos.DTO.SavePlatoDTO;
import com.sistema.pedidos.commons.GenericServiceImpl;
import com.sistema.pedidos.entity.*;
import com.sistema.pedidos.repository.CategoryRepository;
import com.sistema.pedidos.repository.PlatoRepository;
import com.sistema.pedidos.repository.ProductRepository;
import com.sistema.pedidos.repository.PromocionRepository;
import com.sistema.pedidos.service.PlatoService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
        if (!savePlatoDTO.nonNullFields())
            return new ResponseEntity<>("Complete los campos requeridos correctamente", HttpStatus.BAD_REQUEST);
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
        return new ResponseEntity<>(save(plato), HttpStatus.CREATED);

    }

    @Override
    public JpaRepository<Plato, Long> getDao() {
        return platoRepository;
    }
}
