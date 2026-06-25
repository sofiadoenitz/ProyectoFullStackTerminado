package com.example.ms_descuento.Assambler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.example.ms_descuento.Controller.DescuentoControllerV2;
import com.example.ms_descuento.Model.Descuento;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class DescuentoAssambler implements RepresentationModelAssembler<Descuento, EntityModel<Descuento>> {

    @Override
    public EntityModel<Descuento> toModel(Descuento descuento) {
        return EntityModel.of(descuento,
                linkTo(methodOn(DescuentoControllerV2.class).buscar(descuento.getId())).withSelfRel(),
                linkTo(methodOn(DescuentoControllerV2.class).listar()).withRel("descuentos"));
    }
}