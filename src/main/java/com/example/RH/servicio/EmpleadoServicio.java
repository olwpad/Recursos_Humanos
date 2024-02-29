package com.example.RH.servicio;

import com.example.RH.modelo.Empleado;
import com.example.RH.repositorio.EmpleadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmpleadoServicio  implements  IEmpleadoServicio{
    @Autowired
    private EmpleadoRepositorio empleadoRepositorio;

    @Override
    public List<Empleado> listarEmpleados() {
        return empleadoRepositorio.findAll();
    }

    @Override
    public Empleado guardarEmpleado(Empleado empleado) {
        return empleadoRepositorio.save(empleado);
    }

    @Override
    public void elimnarEmpleado(Empleado empleado) {
    empleadoRepositorio.delete(empleado);
    }

    @Override
    public Empleado buscarEmpleadoPorId(Integer idEmpleado) {
        Empleado empleado =empleadoRepositorio.findById(idEmpleado).orElse((null));
        return empleado;
    }
}
