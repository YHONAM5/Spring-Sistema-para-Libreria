package com.sslibreriaGEO.SistemaGestionLibreria.service;

import com.sslibreriaGEO.SistemaGestionLibreria.model.Cliente;
import com.sslibreriaGEO.SistemaGestionLibreria.repository.ClienteRepository;
import jakarta.transaction.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar operaciones relacionadas con la entidad Cliente.
 * Proporciona métodos CRUD para interactuar con la base de datos a través del repositorio.
 */
@Service
public class ClienteService {

    private final ClienteRepository repository;

    /**
     * Constructor para inyección de dependencias.
     * @param clienteRepository repositorio para acceder a datos de clientes.
     */
    public ClienteService(ClienteRepository clienteRepository) {
        this.repository = clienteRepository;
    }

    /**
     * Obtiene la lista de todos los clientes registrados en la base de datos.
     * @return lista de clientes.
     */
    public List<Cliente> getAllCliente() {
        return repository.findAll();
    }

    /**
     * Busca un cliente por su identificador único.
     * @param id identificador del cliente.
     * @return cliente encontrado.
     * @throws RuntimeException si no se encuentra el cliente.
     */
    public Cliente getClienteById(Long id) {
        Optional<Cliente> clienteOptional = repository.findById(id);
        if (clienteOptional.isPresent()) {
            return clienteOptional.get();
        } else {
            throw new RuntimeException("Cliente no encontrado");
        }
    }

    /**
     * Crea un nuevo cliente en la base de datos.
     * Antes de guardar, verifica que no exista otro cliente con el mismo nombre (ignorando mayúsculas/minúsculas).
     * @param cliente objeto cliente a crear.
     * @return cliente creado.
     * @throws RuntimeException si ya existe un cliente con el mismo nombre.
     */
    public Cliente createCliente(Cliente cliente) {
        List<Cliente> clienteExistente = repository.findAll();
        for (Cliente existingCliente : clienteExistente) {
            if (existingCliente.getNombre().equalsIgnoreCase(cliente.getNombre())) {
                throw new RuntimeException("Ya existe un cliente con el mismo nombre");
            }
        }
        return repository.save(cliente);
    }

    /**
     * Actualiza los datos de un cliente existente identificado por su ID.
     * Verifica que el nuevo nombre no esté en uso por otro cliente.
     * @param cliente objeto con los datos actualizados.
     * @param id identificador del cliente a actualizar.
     * @return cliente actualizado.
     * @throws RuntimeException si no se encuentra el cliente o si el nombre ya está en uso por otro cliente.
     */
    public Cliente updateCliente(Cliente cliente, Long id) {
        Optional<Cliente> clienteOptional = repository.findById(id);
        Cliente clienteExistente;
        if (clienteOptional.isPresent()) {
            clienteExistente = clienteOptional.get();
        } else {
            throw new RuntimeException("Cliente no encontrado");
        }

        // Validar que el nuevo nombre no esté duplicado en otro cliente
        if (!clienteExistente.getNombre().equalsIgnoreCase(cliente.getNombre())) {
            List<Cliente> clientes = repository.findAll();
            for (Cliente existingCliente : clientes) {
                if (existingCliente.getNombre().equalsIgnoreCase(cliente.getNombre()) && !existingCliente.getIdCliente().equals(id)) {
                    throw new RuntimeException("Ya existe otro cliente con el mismo nombre");
                }
            }
        }

        // Actualizar campos del cliente existente
        clienteExistente.setNombre(cliente.getNombre());
        clienteExistente.setApellido(cliente.getApellido());
        clienteExistente.setEmail(cliente.getEmail());
        clienteExistente.setTelefono(cliente.getTelefono());

        return repository.save(clienteExistente);
    }

    /**
     * Elimina un cliente de la base de datos por su ID.
     * @param id identificador del cliente a eliminar.
     * @throws RuntimeException si no se encuentra el cliente.
     */
    public void deleteCliente(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Cliente no encontrado");
        }
        repository.deleteById(id);
    }
}
