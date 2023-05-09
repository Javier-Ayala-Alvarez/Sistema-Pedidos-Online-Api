package com.sistema.pedidos.service.impl;

import com.sistema.pedidos.DTO.PuestoDTO;
import com.sistema.pedidos.DTO.PuestoRespuesta;
import com.sistema.pedidos.entity.PuestoLaboralEntity;
import com.sistema.pedidos.exception.ResourceNotFoundException;
import com.sistema.pedidos.repository.PuestoRepository;
import com.sistema.pedidos.service.PuestoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PuestoServiceImp implements PuestoService {
    @Autowired
    private PuestoRepository puestoRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<PuestoRespuesta> mostrarTodos(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir) {
        // Se crea un objeto Sort en función de la dirección especificada y el nombre del campo por el cual se desea ordenar.
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(ordenarPor).ascending() : Sort.by(ordenarPor).descending();
        Pageable pageable = PageRequest.of(numeroDePagina, medidaDePagina, sort);
        Page<PuestoLaboralEntity> puestoLaboralEntities = puestoRepository.findAll(pageable);
        List<PuestoLaboralEntity> listaDePuesto = puestoLaboralEntities.getContent();
        List<PuestoDTO> contenido = listaDePuesto.stream().map(puesto -> mapearDTO(puesto)).collect(Collectors.toList());

        PuestoRespuesta puesto = new PuestoRespuesta();
        puesto.setContenido(contenido);
        puesto.setNumPagina(puestoLaboralEntities.getNumber());
        puesto.setMedidapagina(puestoLaboralEntities.getSize());
        puesto.setTotalElementos(puestoLaboralEntities.getTotalElements());
        puesto.setTotalPaginas(puestoLaboralEntities.getTotalPages());
        puesto.setUltima(puestoLaboralEntities.isLast());
        return new ResponseEntity<>(puesto, HttpStatus.OK);
    }


    @Override
    public PuestoDTO mostrarId(Integer id) {
        PuestoLaboralEntity puestoLaboral = puestoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Puesto", "id", id));
        return mapearDTO(puestoLaboral);
    }

    @Override
    public PuestoDTO guardar(PuestoDTO puestoDTO) {
        PuestoLaboralEntity puestoLaboral = mapearEntidad(puestoDTO);
        puestoLaboral.setEstado(true);
        PuestoLaboralEntity puestoLaboral1 = puestoRepository.save(puestoLaboral);
        PuestoDTO puestoDTO1 = mapearDTO(puestoLaboral1);
        return puestoDTO1;
    }

    @Override
    public PuestoDTO editar(PuestoDTO puestoDTO, Integer id) {
        PuestoLaboralEntity puestoLaboral = puestoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Puesto", "id", id));
        puestoLaboral.setEstado(true);
        puestoLaboral.setNombre(puestoDTO.getNombre());
        return mapearDTO(puestoRepository.save(puestoLaboral));
    }

    @Override
    public void eliminar(Integer id) {
        PuestoLaboralEntity puestoLaboral = puestoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Puesto", "id", id));
        puestoLaboral.setEstado(false);
        puestoRepository.save(puestoLaboral);
    }
    private PuestoDTO mapearDTO(PuestoLaboralEntity puestoLaboral) {
        PuestoDTO puestoDTO = modelMapper.map(puestoLaboral, PuestoDTO.class);
        return puestoDTO;
    }

    // Convierte de DTO a Entidad
    private PuestoLaboralEntity mapearEntidad(PuestoDTO puestoDTO) {
        PuestoLaboralEntity puestoLaboralEntitys = modelMapper.map(puestoDTO, PuestoLaboralEntity.class);
        return puestoLaboralEntitys;
    }
}
