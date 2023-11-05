package mapper;

import com.sistema.pedidos.DTO.*;
import com.sistema.pedidos.entity.*;

import java.util.ArrayList;
import java.util.List;

public  class PlatoMapper {


    public PlatoDTO platoToPlatoDTO(Plato plato) {
        PlatoDTO platoDTO = new PlatoDTO();
        List<ProductDTO> productsDTO = new ArrayList<>();
        CategoriaDTO categoriaDTO = new CategoriaDTO();

        platoDTO.setId(plato.getId());
        platoDTO.setNombre(plato.getNombre());
        platoDTO.setDescripcion(plato.getDescripcion());
        platoDTO.setPrecio(plato.getPrecio());
        platoDTO.setEstado(plato.isEstado());
        platoDTO.setUrlImagen(plato.getUrlImagen());

        categoriaDTO.setId(plato.getCategoria().getId());
        categoriaDTO.setNombre(plato.getCategoria().getNombre());
        categoriaDTO.setEstado(plato.getCategoria().getEstado());
        categoriaDTO.setImg(plato.getCategoria().getImg());

        plato.getPlatoProducto().forEach(x -> {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(x.getProducto().getId());
            productDTO.setNombre(x.getProducto().getNombre());
            productDTO.setDescripcion(x.getProducto().getDescripcion());
            productDTO.setPrecioVenta(x.getProducto().getPrecioVenta());
            productDTO.setUrlImagen(x.getProducto().getUrlImagen());
            productsDTO.add(productDTO);
        });

        platoDTO.setCategoria(categoriaDTO);
        platoDTO.setProductos(productsDTO);

        return platoDTO;
    }

    Plato platoDTOToPlato(PlatoDTO platoDTO) {
        Plato plato = new Plato();
        Category category = new Category();
        List<PlatoProducto> platoProductos = new ArrayList<>();


        plato.setId(platoDTO.getId());
        plato.setNombre(platoDTO.getNombre());
        plato.setDescripcion(platoDTO.getDescripcion());
        plato.setPrecio(platoDTO.getPrecio());
        plato.setEstado(platoDTO.isEstado());
        plato.setUrlImagen(platoDTO.getUrlImagen());

        category.setId(platoDTO.getCategoria().getId());
        category.setNombre(platoDTO.getCategoria().getNombre());
        category.setEstado(platoDTO.getCategoria().isEstado());
        category.setImg(platoDTO.getCategoria().getImg());

        platoDTO.getProductos().forEach(x -> {
            PlatoProducto platoProducto = new PlatoProducto();
            Product producto = new Product();
            platoProducto.setPlato(plato);

            producto.setId(x.getId());
            producto.setNombre(x.getNombre());
            producto.setDescripcion(x.getDescripcion());
            producto.setPrecioVenta(x.getPrecioVenta());
            producto.setUrlImagen(x.getUrlImagen());
            producto.setGanancia(x.getGanancia());

            platoProducto.setProducto(producto);
            platoProductos.add(platoProducto);
        });



        plato.setCategoria(category);
        plato.setPlatoProducto(platoProductos);
        return plato;
    }

}
