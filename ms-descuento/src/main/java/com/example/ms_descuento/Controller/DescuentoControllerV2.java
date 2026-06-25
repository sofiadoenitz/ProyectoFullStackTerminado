package com.example.ms_descuento.Controller;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.example.ms_descuento.Assambler.DescuentoAssambler;
import com.example.ms_descuento.Model.Descuento;
import com.example.ms_descuento.Service.DescuentoService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v2/descuentos")
public class DescuentoControllerV2 {

    @Autowired
    private DescuentoService serv;

    @Autowired
    private DescuentoAssambler assembler;

    // Listar
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Descuento>> listar() {
        log.info("INFORMACION: Listando todos los descuentos");
        List<EntityModel<Descuento>> descuentos = serv.listar().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(descuentos,
                linkTo(methodOn(DescuentoControllerV2.class).listar()).withSelfRel());
    }

    // Buscar por id
    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Descuento> buscar(@PathVariable Long id) {
        log.info("INFORMACION: Buscando descuento con id: {}", id);
        Descuento descuento = serv.buscar(id);
        return assembler.toModel(descuento);
    }

    // Guardar
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Descuento>> agregar(@RequestBody Descuento descuento) {
        log.info("INFORMACION: Guardando nuevo descuento: {}", descuento.toString());
        Descuento nuevo = serv.agregarDescuento(descuento);
        return ResponseEntity
                .created(linkTo(methodOn(DescuentoControllerV2.class).buscar(nuevo.getId())).toUri())
                .body(assembler.toModel(nuevo));
    }

    // Actualizar
    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Descuento>> actualizar(@PathVariable Long id, @RequestBody Descuento descuento) {
        log.info("INFORMACION: Actualizando descuento con id: {}", id);
        descuento.setId(id);
        Descuento actualizado = serv.actualizar(descuento);
        return ResponseEntity.ok(assembler.toModel(actualizado));
    }

    // Eliminar
    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        log.info("INFORMACION: Eliminando descuento con id: {}", id);
        serv.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // Buscar por juego
    @GetMapping(value = "/juego/{juegoId}", produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Descuento>> buscarPorJuego(@PathVariable Long juegoId) {
        log.info("INFORMACION: Buscando descuentos para el juego con id: {}", juegoId);
        List<EntityModel<Descuento>> descuentos = serv.buscarPorJuego(juegoId).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(descuentos,
                linkTo(methodOn(DescuentoControllerV2.class).buscarPorJuego(juegoId)).withSelfRel(),
                linkTo(methodOn(DescuentoControllerV2.class).listar()).withRel("descuentos"));
    }
}