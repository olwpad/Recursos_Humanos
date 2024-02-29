package com.example.RH.controlador;

import com.example.RH.excepcion.RecursoNoEncontradoExepcion;
import com.example.RH.modelo.Empleado;
import com.example.RH.servicio.EmpleadoServicio;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rh-app")
@CrossOrigin(value="http://localhost:5173")
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

    @PostMapping("/empleados")
    public Empleado agregarEmpleado(@RequestBody Empleado empleado){
       logger.info("empleado a agregar" +empleado);
       return empleadoServicio.guardarEmpleado(empleado);
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> obtenerEmpleadoPorId(@PathVariable Integer id){
        logger.info("empleado a agregar" +id);
        Empleado empleado= empleadoServicio.buscarEmpleadoPorId(id);
        if(empleado==null){
            throw  new RecursoNoEncontradoExepcion("No se encontro el id:"+id);
        }
        return ResponseEntity.ok(empleado);
    }

    @PutMapping("/empleados/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Integer id, @RequestBody Empleado empleadoRecibido){
     Empleado empleado =empleadoServicio.buscarEmpleadoPorId(id);
     if(empleado==null)
         throw new RecursoNoEncontradoExepcion("el id recibido no existe");
     empleado.setNombre(empleadoRecibido.getNombre());
     empleado.setDepartamento(empleadoRecibido.getDepartamento());
     empleado.setSueldo(empleadoRecibido.getSueldo());
     empleadoServicio.guardarEmpleado(empleado);
     return ResponseEntity.ok(empleado);
    }

    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<Map <String, Boolean>>  eliminarEmpleado(@PathVariable Integer id){
        Empleado empleado =empleadoServicio.buscarEmpleadoPorId(id);
        if(empleado==null)
            throw new RecursoNoEncontradoExepcion("el id recibido no existe");
        empleadoServicio.elimnarEmpleado(empleado);
        Map<String,Boolean> respuesta =new HashMap<>();
        respuesta.put("eliminado",Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
