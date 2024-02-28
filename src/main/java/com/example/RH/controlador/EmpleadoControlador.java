package com.example.RH.controlador;

import com.example.RH.modelo.Empleado;
import com.example.RH.servicio.EmpleadoServicio;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("rh-app")
@CrossOrigin(value="http//localhost:3000")
public class EmpleadoControlador {
    private static Logger logger= LoggerFactory.getLogger(EmpleadoControlador.class);

    @Autowired EmpleadoServicio empleadoServicio;


    //http//localhost:8080/rh-app/emepelados
    @GetMapping("/empleados")
    public List<Empleado> obtenerEmpleados(){
        var empleados=empleadoServicio.listarEmpleados();
         empleados.forEach(empleado -> logger.info(empleado.toString()));
        return empleados;
    }
}
