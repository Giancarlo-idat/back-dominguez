package com.store.dominguez.service.impl;

import com.store.dominguez.dto.TipoTransaccionDTO;
import com.store.dominguez.model.TipoTransaccionEntity;
import com.store.dominguez.repository.gestion.TipoTransaccionRepository;
import com.store.dominguez.service.gestion.TipoTransaccionService;
import com.store.dominguez.util.generator.IdGenerator;
import com.store.dominguez.util.validations.TipoTransaccionValidator;
import com.store.dominguez.util.validations.Validations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class TipoTransaccionServiceImpl implements TipoTransaccionService {

    private final TipoTransaccionRepository tipoTransaccionRepository;
    private final ModelMapper modelMapper;
    private final TipoTransaccionValidator tipoTransaccionValidator;

    @Autowired
    public TipoTransaccionServiceImpl(TipoTransaccionRepository tipoTransaccionRepository,  ModelMapper modelMapper, TipoTransaccionValidator tipoTransaccionValidator) {
        this.tipoTransaccionRepository = tipoTransaccionRepository;
        this.modelMapper = modelMapper;
        this.tipoTransaccionValidator = tipoTransaccionValidator;
    }

    @Override
    public List<TipoTransaccionDTO> findByNombre(String nombre) {
        if (Validations.isBlank(nombre)) throw new IllegalArgumentException("El nombre no puede ser nulo o vacio");
        if (Validations.isValidNames(nombre))
            throw new IllegalArgumentException("El nombre no puede contener caracteres especiales o numeros");

        try {
            List<TipoTransaccionEntity> listTransactionsName = tipoTransaccionRepository.findByNombre(nombre);
            return listTransactionsName.stream().
                    map(transact -> modelMapper.map(transact, TipoTransaccionDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al obtener el nombre de transaccion.\n " + e.getMessage());
        }

    }

    @Override
    public List<TipoTransaccionDTO> buscarTodos() {
        try {
            List<TipoTransaccionEntity> listTransactions = tipoTransaccionRepository.findAll();
            return listTransactions.stream().
                    map(transact -> modelMapper.map(transact, TipoTransaccionDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al obtener la lista de transacciones.\n " + e.getMessage());
        }
    }

    @Override
    public List<TipoTransaccionDTO> buscarActivo() {
        try {
            List<TipoTransaccionEntity> listTransactionsActive = tipoTransaccionRepository.buscarTipoTransaccionActivo();
            return listTransactionsActive.stream().
                    map(transact -> modelMapper.map(transact, TipoTransaccionDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al obtener la lista de transacciones activas.\n " + e.getMessage());
        }
    }

    @Override
    public List<TipoTransaccionDTO> buscarInactivo() {
        try {
            List<TipoTransaccionEntity> listTransactionsInactive = tipoTransaccionRepository.buscarTipoTransaccionInactivo();
            return listTransactionsInactive.stream().
                    map(transact -> modelMapper.map(transact, TipoTransaccionDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al obtener la lista de transacciones inactivas.\n " + e.getMessage());
        }
    }

    @Override
    public Optional<TipoTransaccionDTO> buscarId(String id) {
        if (Validations.isBlank(id)) throw new IllegalArgumentException("El id no puede ser nulo o vacio");

        try {
            Optional<TipoTransaccionEntity> transaction = tipoTransaccionRepository.findById(id);
            if (transaction.isPresent()) {
                return Optional.of(modelMapper.map(transaction.get(), TipoTransaccionDTO.class));
            } else {
                throw new IllegalArgumentException("La transaccion no existe");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al obtener la transaccion por id.\n " + e.getMessage());
        }
    }

    @Override
    public TipoTransaccionDTO agregar(TipoTransaccionDTO tipoTransaccionDTO) {

        tipoTransaccionValidator.validarTipoTransaccion(tipoTransaccionDTO);
        try {
            String id = IdGenerator.generarID("TTR", tipoTransaccionDTO.getNombre());
            tipoTransaccionDTO.setId(id);
            TipoTransaccionEntity tipoTransact = modelMapper.map(tipoTransaccionDTO, TipoTransaccionEntity.class);
            tipoTransact = tipoTransaccionRepository.save(tipoTransact);
            return modelMapper.map(tipoTransact, TipoTransaccionDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Error al agregar la transacción" + e.getMessage());
        }
    }

    @Override
    public TipoTransaccionDTO actualizar(TipoTransaccionDTO tipoTransaccionDTO, String id) {
        // Valida que el campo ID no esté vacío
        TipoTransaccionValidator.validarIdTransaccion(id);
        try {
            Optional<TipoTransaccionEntity> tipoTransaccionEntity = tipoTransaccionRepository.findById(id);
            if (tipoTransaccionEntity.isPresent()) {
                // Valida que el campo nombre no esté vacío
                TipoTransaccionValidator.validarNombreTransaccion(tipoTransaccionDTO.getNombre());
                TipoTransaccionEntity transaction = tipoTransaccionEntity.get();
                modelMapper.map(tipoTransaccionDTO, transaction);
                transaction.setFechaActualizacion(LocalDateTime.now());
                return modelMapper.map(tipoTransaccionRepository.save(transaction), TipoTransaccionDTO.class);
            } else {
                throw new RuntimeException("El tipo de transacción no existe");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el tipo de transacción" + e.getMessage());
        }
    }

    @Override
    public void eliminar(String id) {
        if (Validations.isBlank(id)) throw new IllegalArgumentException("El id no puede ser nulo o vacio");
        try {
            Optional<TipoTransaccionEntity> transaction = tipoTransaccionRepository.findById(id);
            if (transaction.isPresent()) {
                TipoTransaccionEntity tipoTransaccion = transaction.get();
                tipoTransaccion.setEstado(false);
                tipoTransaccion.setFechaActualizacion(LocalDateTime.now());
                modelMapper.map(tipoTransaccionRepository.save(tipoTransaccion), TipoTransaccionDTO.class);
            } else {
                throw new IllegalArgumentException("La transaccion no existe");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al eliminar la transaccion por id.\n " + e.getMessage());
        }
    }

    @Override
    public TipoTransaccionDTO habilitar(String id) {
        if (Validations.isBlank(id)) throw new IllegalArgumentException("El id no puede ser nulo o vacio");
        try {
            Optional<TipoTransaccionEntity> transaction = tipoTransaccionRepository.findById(id);
            if (transaction.isPresent()) {
                TipoTransaccionEntity tipoTransaccion = transaction.get();
                tipoTransaccion.setEstado(true);
                tipoTransaccion.setFechaActualizacion(LocalDateTime.now());
                return modelMapper.map(tipoTransaccionRepository.save(tipoTransaccion), TipoTransaccionDTO.class);
            } else {
                throw new IllegalArgumentException("La transaccion no existe");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al habilitar la transaccion por id.\n " + e.getMessage());
        }
    }
}
